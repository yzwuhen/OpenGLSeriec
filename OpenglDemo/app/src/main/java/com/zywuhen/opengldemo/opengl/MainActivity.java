package com.zywuhen.opengldemo.opengl;

import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zywuhen.opengldemo.opengl.liu.SimplePlane;


public class MainActivity extends AppCompatActivity {

   private GLSurfaceView glSurfaceVie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceVie= new GLSurfaceView(this);
        setContentView(glSurfaceVie);

        EffectsRender render = new EffectsRender(this);
        glSurfaceVie.setRenderer(render);


        SimplePlane simplePlane = new SimplePlane();
        simplePlane.z =1.7f;
        simplePlane.rx =-65;

        simplePlane.loadBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.jay));


        render.addMesh(simplePlane);
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceVie.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceVie.onPause();
    }
}
