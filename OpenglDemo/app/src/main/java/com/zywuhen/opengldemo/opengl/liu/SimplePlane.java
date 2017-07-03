package com.zywuhen.opengldemo.opengl.liu;

import com.zywuhen.opengldemo.opengl.Mesh;

/**
 *
 * 项目名称：OpenglDemo
 * 类描述：
 * 创建人：yqw
 * 创建时间：2017/5/27 10:18
 * 修改人：yqw
 * 修改时间：2017/5/27 10:18
 * 修改备注：
 * Version:  1.0.0
 */
public class SimplePlane  extends Mesh {

    public SimplePlane (){
        this(1,1);
    }

    public SimplePlane(float width,float height){

        float textureCoordinates[] ={
          0.0f,2.0f,
                2.0f,2.0f,
                0.0f,0.0f,
                2.0f,0.0f
        };

        short[] indices = new short[]{
                0,1,2,1,3,2
        };

        float [] vertices = new float[]{
          -0.5f,-0.5f,0.0f,
                0.5f,-0.5f,0.0f,
                -0.5f,0.5f,0.0f,
                0.5f,0.5f,0.0f
        };

        setVertices(vertices);
        setIndices(indices);
        setTextureBuffer(textureCoordinates);

    }
}
