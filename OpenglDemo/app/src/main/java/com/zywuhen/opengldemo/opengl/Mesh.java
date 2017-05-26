package com.zywuhen.opengldemo.opengl;

import android.icu.util.IndianCalendar;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Copyright (C) 2017,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：OpenglDemo
 * 类描述：
 * 创建人：yqw
 * 创建时间：2017/5/25 10:09
 * 修改人：yqw
 * 修改时间：2017/5/25 10:09
 * 修改备注：
 * Version:  1.0.0
 */
public class Mesh {

    //float 类型的缓冲区===》顶点坐标
    private FloatBuffer verticesBuffer=null;

    //short类型的缓冲区
    private ShortBuffer indicesBuffer = null;

    private int numofIndices = -1;

    private float verticesArrad[];

    private float[] rgba = new float[]{1.0f,1.0f,1.0f,1.0f};

    //颜色 的缓冲区
    private FloatBuffer colorBuffer =null;

    public float x= 0;
    public float y =0;;
    public float z =0;

    public float rx =0;
    public float ry =0;
    public float rz =0;


    //按照惯例 写一个draw方法 绘制
    public void draw(GL10 gl){
        //作用是控制多边形的正面是如何决定的。在默认情况下，mode是GL_CCW。
        //GL_CCW 表示窗口坐标上投影多边形的顶点顺序为逆时针方向的表面为正面。  CW即为顺时针方向
        //是opengl的初级命令，有两个基本作用，一是可以用来用在某些特殊场合（比如剔除面片），二是可以提高渲染效率。
        gl.glFrontFace(GL10.GL_CCW);

        //glEnable用于启动某一种功能，列如雾化  色彩混合
        //具体详见http://blog.csdn.net/heyuchang666/article/details/69524104
        //根据函数glCullFace要求启用隐藏图形材料的面。即开启剔除效果
        gl.glEnable(GL10.GL_CULL_FACE);

        //指定应剔除多边形的哪一个面，不是GL_FRONT就是GL_BACK。
        gl.glCullFace(GL10.GL_BACK);

        //启动Vertex的数组==》画图的数据都放在数组缓冲区里，最后再一起传过来作画。那么我们首先要告诉 OpenGL，我们需要用到哪些数组。例如我们需要顶点数组和颜色数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT,0,verticesBuffer);

        //设置颜色==》也可以用gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gl.glColor4f(rgba[0],rgba[1],rgba[2],rgba[3]);

        if (colorBuffer!=null){
            //启动颜色数组缓冲区
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        }

        gl.glTranslatef(x,y,z);
        gl.glRotatef(rx,1,0,0);
        gl.glRotatef(ry,0,1,0);
        gl.glRotatef(rz,0,0,1);

        gl.glDrawElements(GL10.GL_TRIANGLES,numofIndices,GL10.GL_UNSIGNED_SHORT, indicesBuffer);
        // gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,verticesArrad.length/3);

        //与开始时启动的想匹配 ，同时出现~等同于关闭
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);

    }

    //设置顶点坐标
    protected void setVertices(float[] vertices){

        verticesArrad =vertices;
        //创建缓冲区==>float 是四个字节的所以乘于4
        ByteBuffer vbb =  ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        verticesBuffer = vbb.asFloatBuffer();
        verticesBuffer.put(vertices);
        verticesBuffer.position(0);
    }
    //允许重新定义顶点顺序
    protected void setIndices(short[] indices){
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length*2);
        ibb.order(ByteOrder.nativeOrder());
        indicesBuffer= ibb.asShortBuffer();
        indicesBuffer.put(indices);
        indicesBuffer.position(0);
        //
        numofIndices =indices.length;

    }
    //允许重新定义颜色
    protected void setColor(float red,float green, float bule, float alpha){

       rgba[0]= red;
        rgba[1]= green;
        rgba[2]= bule;
        rgba[3]= alpha;

    }

    //允许重新定义颜色
    protected void setColors(float[] colors){

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer =cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }
}
