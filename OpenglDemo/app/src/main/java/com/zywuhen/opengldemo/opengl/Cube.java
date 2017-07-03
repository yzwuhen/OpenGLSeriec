package com.zywuhen.opengldemo.opengl;

/**
 *
 * 项目名称：OpenglDemo
 * 类描述： 这里绘制正方体
 * 创建人：yqw
 * 创建时间：2017/5/25 14:07
 * 修改人：yqw
 * 修改时间：2017/5/25 14:07
 * 修改备注：
 * Version:  1.0.0
 */
public class Cube extends Mesh{
    public  Cube(float width,float height ,float depth){
        width /=2;
        height /=2;
        depth /=2;

        //自由发挥想象下 = = >方体
        float vertices[] = {
                -width,-height,-depth,
                width , -height,-depth,
                width , height,-depth,
                -width,height, -depth,
                -width, -height,depth,
                width,-height,depth,
                width,height,depth,
                -width,height,depth,
        };

        short indices[] =
                {0,4,5,
        0,5,1,
        1,5,6,
        1,6,2,
        2,6,7,
        2,7,3,
        3,7,4,
        3,4,0,
        4,7,6,
        4,6,5,
        3,0,1,
        3,1,2,};

        float[] colors = {
                1f, 0f, 0f, 1f,
                181f, 167f, 63f, 1f,
                0f, 0f, 1f, 1f,
                1f, 0f, 1f, 1f,

                12f, 1, 2f, 1f,
                181f, 167f, 63f, 1f,
                126f, 0f, 1f, 1f,
                1f, 126f, 1f, 1f,
        };


        setVertices(vertices);
     //   setColors(colors);
        setIndices(indices);
    }

}
