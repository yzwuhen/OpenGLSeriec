package com.zywuhen.opengldemo.opengl;

/**
 * Copyright (C) 2017,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：OpenglDemo
 * 类描述：
 * 创建人：yqw
 * 创建时间：2017/5/25 17:28
 * 修改人：yqw
 * 修改时间：2017/5/25 17:28
 * 修改备注：
 * Version:  1.0.0
 */
public class Cube2 extends Mesh {
    public  Cube2(float width,float height ,float depth) {
        width /= 2;
        height /= 2;
        depth /= 2;

        //自由发挥想象下 = = >方体
        float vertices[] = {
                0,0,0,
                0,1,0,
                0,0,1,
                0,1,1,
                1,1,1,
                1,0,1,
                1,0,0,
                1,1,0
               /* -width, -height, -depth,
                width, -height, -depth,
                width, height, -depth,
                -width, height, -depth,
                -width, -height, depth,
                width, -height, depth,
                width, height, depth,
                -width, height, depth,*/
        };
        setVertices(vertices);
    }
}
