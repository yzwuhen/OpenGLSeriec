package com.zywuhen.opengldemo.opengl;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

/**
 *
 * 项目名称：OpenglDemo
 * 类描述：
 * 创建人：yqw
 * 创建时间：2017/5/25 14:18
 * 修改人：yqw
 * 修改时间：2017/5/25 14:18
 * 修改备注：
 * Version:  1.0.0
 */
public class Group extends Mesh {
    private Vector<Mesh> children = new Vector<>();

    //重写
    @Override
    public void draw(GL10 gl) {
      // super.draw(gl);
        int size = children.size();
        for (int i=0;i<size;i++){
            children.get(i).draw(gl);
        }
    }

    public void add(int location ,  Mesh object){
        children.add(location,object);
    }

    public boolean add(Mesh object){
        return  children.add(object);
    }
    public void clear(){
        children.clear();
    }

    public Mesh get(int location){
        return children.get(location);
    }

    public Mesh remove(int location){
        return children.remove(location);
    }

    public int size(){
        return children.size();
    }
}
