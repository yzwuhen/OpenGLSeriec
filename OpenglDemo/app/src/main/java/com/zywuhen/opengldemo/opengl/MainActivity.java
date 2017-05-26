package com.zywuhen.opengldemo.opengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

   private GLSurfaceView glSurfaceVie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceVie= new GLSurfaceView(this);
        setContentView(glSurfaceVie);
        glSurfaceVie.setRenderer(new EffectsRender(this));
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
