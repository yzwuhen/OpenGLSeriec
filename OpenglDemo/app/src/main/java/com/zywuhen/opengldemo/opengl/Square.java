package com.zywuhen.opengldemo.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 *
 * 项目名称：OpenglDemo
 * 类描述：
 * 创建人：yqw
 * 创建时间：2017/5/22 15:32
 * 修改人：yqw
 * 修改时间：2017/5/22 15:32
 * 修改备注：
 * Version:  1.0.0
 */
public class Square {
    private FloatBuffer vertexBuffer;
    private ByteBuffer indexBuffer;
    private FloatBuffer colorBuffer;
    //这个是坐标轴的坐标，一个条数据代表一个坐标点，分别是XYZ轴的坐标点
    private float[] vertices={
            -1f,-1f,0.0f,
            1f,-1f,0.0f,
            -1f,1f,0.0f,
            1f,1f,0.0f
    };

    //red green blue alpha
    float[] colors = {
            1f, 0f, 0f, 1f, // vertex 0 red
            181f, 167f, 63f, 1f, // vertex 1 green
            0f, 0f, 1f, 1f, // vertex 2 blue
            1f, 0f, 1f, 1f, // vertex 3 magenta
    };


    private byte[] indices={0,1,2};
    public Square(){

        //创建一个缓冲区，长度是verticesBuffer的四倍 应为verticesBuffer 是四个字节的
        ByteBuffer vbb=ByteBuffer.allocateDirect(vertices.length*4);
        //定义在底层的本地平台上的byte的顺序=====》这个没弄懂，感觉就像fragmenglayout的层级关系差不多
        vbb.order(ByteOrder.nativeOrder());
        //把byte类型的buff转成Float
        vertexBuffer=vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        //保证是从缓冲区的开头读取，相当于list的第一个数据下标
        vertexBuffer.position(0);


        indexBuffer=ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);


        ByteBuffer colorByte = ByteBuffer.allocateDirect(colors.length*4);
        colorByte.order(ByteOrder.nativeOrder());
        colorBuffer = colorByte.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);


    }

    //此方法在后面被它的实例对象调用
    public void draw(GL10 gl){



        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //第一个参数代表每个坐标点用几个坐标表示（即xyz三轴），第二个参数指坐标轴应当被解析为folat类型（这样OpenGL就可以知道每个值占用几位）
        //第三个参数可以称为“步长”，代表每个点之间有几位分割。本例中，0代表一个点挨着一个点，有时候你可能会在点的后面定义颜色，这时，你应该指出每个颜色占用的位长，以便OpenGL在解析时跳过这段长度.
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer);

//        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glColorPointer(4,GL10.GL_FLOAT,0,colorBuffer);

       // super.draw(gl);

        //定义渲染多个图元
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,vertices.length/3);

        //渲染数组数据中的图元
        //gl.glDrawElements(int mode, int count, int type, Buffer indices)  ，可以重新定义顶点的顺序，顶点的顺序由indices Buffer 指定。
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        //X轴方向平移 一个单位
      //  gl.glTranslatef(1,0,0);
        //放大一倍
    //    gl.glScalef(2f,2f,2f);
      //  gl.glRotatef(45,0,0,0);
    }

    public void drawColor(GL10 gl) {

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4,GL10.GL_FLOAT,0,colorBuffer);

    }
}
