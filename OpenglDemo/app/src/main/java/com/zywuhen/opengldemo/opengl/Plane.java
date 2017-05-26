package com.zywuhen.opengldemo.opengl;

/**
 * Copyright (C) 2017,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：OpenglDemo
 * 类描述：
 * 创建人：yqw
 * 创建时间：2017/5/25 13:43
 * 修改人：yqw
 * 修改时间：2017/5/25 13:43
 * 修改备注：
 * Version:  1.0.0
 */
public class Plane extends Mesh {

    public Plane(){
        this(1,1,1,1);
    }

    public Plane(float width, float height){
        this(width,height,1,1);
    }
    public Plane(float width, float height,int widthSegments,int heightSegments){

        float[] vertices = new float[(widthSegments+1)*(heightSegments+1)*3];
        short[] indices = new short[(widthSegments+1)*(heightSegments+1)*6];

        float xOffset = width/-2;
        float yOffset = height/-2;
        float yHeight = height/heightSegments;
        float xWidth = width/widthSegments;
        int currentVertex = 0;
        int currentIndex = 0;
        short w = (short) (widthSegments+1);


        for (int y=0;y<heightSegments+1;y++){
            for (int x=0;x<widthSegments+1;x++){

                // xyz 三维坐标 z轴永远是0
                vertices[currentVertex] =xOffset+x*xWidth;
                vertices[currentVertex+1] =yOffset+y*yHeight;
                vertices[currentVertex+2] =0;
                currentVertex +=3;

                int n =y*(widthSegments+1)+x;
                if (y<heightSegments&&x<widthSegments){

                    indices[currentIndex] = (short) n;
                    indices[currentIndex+1] = (short) (n+1);
                    indices[currentIndex+2] = (short) (n+w);


                    indices[currentIndex+3] = (short) (n+1);
                    indices[currentIndex+4] = (short) (n+1+w);
                    //为了搞清楚流程，不写成n+w
                    indices[currentIndex+5] = (short) (n+1+w-1);
                    currentIndex +=6;
                }
            }
        }

        setIndices(indices);
        setVertices(vertices);
    }
}
