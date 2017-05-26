package com.zywuhen.opengldemo.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import java.util.HashMap;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Copyright (C) 2017,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：OpenglDemo
 * 类描述：
 * 创建人：yqw
 * 创建时间：2017/5/22 15:27
 * 修改人：yqw
 * 修改时间：2017/5/22 15:27
 * 修改备注：
 * Version:  1.0.0
 */
public class EffectsRender implements GLSurfaceView.Renderer {
    private Bitmap mBitmap;
    private int photow,photoh;
    private int textures[] = new int[2];

    private Square square;
    private Context mContext;

    private float  angle =90;
    private float xdistance=3;
    private long startTime;


    private Mesh root;
    public EffectsRender(Context context) {
        super();
        this.mContext =context;

        Group group = new Group();
        Cube cube = new Cube(1,1,1);
        cube.rx=45;
        cube.ry =45;

        group.add(cube);
        root = group;

     //   square = new Square();
      /*  mBitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.bitmap);
        photow=mBitmap.getWidth();
        photoh =mBitmap.getHeight();*/
    }
    private void getSquare(){

        GLES20.glGenTextures(2,textures,0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textures[0]);

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //清除屏幕颜色
        gl.glClearColor(0.0f,0.0f,0.0f,1.0f);
        //
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        //这里告诉OpenGL我们希望进行最好的透视修正。这会十分轻微的影响性能。但使得透视图看起来好一点.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST);
        //启用smooth shading（阴影平滑）.阴影平滑通过多边形精细的混合色彩，并对外部光进行平滑
        gl.glShadeModel(GL10.GL_SMOOTH);
       // 关闭服务器端GL功能,在GL中很多都是一对一对的,比如这个的另一个gl.glEnable(...).
        gl.glDisable(GL10.GL_DITHER);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if(height==0){
            height=1;
        }
        float aspect=(float)width/height;

        //设置可见区域
        gl.glViewport(0,0,width,height);
        //有3种模式: GL_PROJECTION 投影, GL_MODELVIEW 模型视图, GL_TEXTURE 纹理.===>处理之前提前告诉计算机，
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //重设视图模型变换 ， 用于观测创建的物体.
        gl.glLoadIdentity();
        //个人理解 ===》即为视角 -  - 不知道准不准
        GLU.gluPerspective(gl,45,aspect,0.1f,100.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    //13-21毫秒执行一次，其中16-17毫秒居多
    @Override
    public void onDrawFrame(GL10 gl) {


        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();;
        gl.glTranslatef(0,0,-4);

        root.draw(gl);


        //将缓存清除为预先的设置值.
  /*      gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
      //  gl.glTranslatef(-3.0f,0.0f,-6.0f);

       gl.glTranslatef(0,0.0f,-10f);
        square.draw(gl);
*/
/*
        long ss = System.currentTimeMillis();
        Log.v("sssssss","sssssssssssssssss多久执行一次"+( ss -startTime));
       startTime =ss;*/


  /*     square.drawColor(gl);
        // Clears the screen and depth buffer.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT
                | GL10.GL_DEPTH_BUFFER_BIT);
        // Replace the current matrix with the identity matrix
        gl.glLoadIdentity();
        // Translates 10 units into the screen.
        gl.glTranslatef(0, 0, -20);

        // SQUARE A
        // Save the current matrix.\
        //变换前保存当前矩阵
        gl.glPushMatrix();
        // Rotate square A counter-clockwise.
        gl.glRotatef(angle, 0, 0, 1);
        // Draw square A.

        square.draw(gl);

        // Restore the last matrix.
        //变换后恢复当前矩阵
        gl.glPopMatrix();

        // SQUARE B
        // Save the current matrix
        gl.glPushMatrix();
        // Rotate square B before moving it,
        //making it rotate around A.
        gl.glRotatef(-angle, 0, 0, 1);
        // Move square B.
        gl.glTranslatef(1, 0, 0);
        // Scale it to 50% of square A
        gl.glScalef(.5f, .5f, .5f);
        // Draw square B.
        square.draw(gl);

        // SQUARE C
        // Save the current matrix
        gl.glPushMatrix();
        // Make the rotation around B
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(2, 0, 0);
        // Scale it to 50% of square B
        gl.glScalef(.5f, .5f, .5f);
        // Rotate around it's own center.
        gl.glRotatef(angle*10, 0, 0, 1);
        // Draw square C.
        square.draw(gl);

        // Restore to the matrix as it was before C.
        gl.glPopMatrix();
        // Restore to the matrix as it was before B.
        gl.glPopMatrix();

        // Increse the angle.
        angle++;*/
      /*  if (xdistance>=3){
            xdistance =0;
        }
        xdistance++;*/

    }
}
