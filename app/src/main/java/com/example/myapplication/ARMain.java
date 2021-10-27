package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.Sun;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;
import java.util.List;

import Domain.PlantDomain;

public class ARMain extends AppCompatActivity {
    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    private Button btn_anim, btn_anim1, size;
    private PlantDomain object;
    private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_main);

        object = (PlantDomain) getIntent().getSerializableExtra("object");
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        Toast.makeText(ARMain.this, "Tap on the plane to place the model", Toast.LENGTH_SHORT).show();
        setUpModel();
        setUpPlane();

        btn_anim= (Button) findViewById(R.id.btn_anim);
        btn_anim1 = (Button)findViewById(R.id.btn_anim1);
        size = (Button)findViewById(R.id.btn_size);

        btn_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Node> children = new ArrayList<>(arFragment.getArSceneView().
                        getScene().getChildren());
                for (Node node : children) {
                    if (node instanceof AnchorNode) {
                        if (((AnchorNode) node).getAnchor() != null) {
                            ((AnchorNode) node).getAnchor().detach();
                        }
                    }
                    if (!(node instanceof Camera) && !(node instanceof Sun)) {
                        node.setParent(null);
                    }
                }
               setUpModel();
                if(count==1){
                    setUpPlane();
                }else{
                    setUpPlane1();
                }
                Toast.makeText(ARMain.this, "Tap on the plane to place the model", Toast.LENGTH_SHORT).show();

            }
        });

        btn_anim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Node> children = new ArrayList<>(arFragment.getArSceneView().
                        getScene().getChildren());
                for (Node node : children) {
                    if (node instanceof AnchorNode) {
                        if (((AnchorNode) node).getAnchor() != null) {
                            ((AnchorNode) node).getAnchor().detach();
                        }
                    }
                    if (!(node instanceof Camera) && !(node instanceof Sun)) {
                        node.setParent(null);
                    }
                }
                setUpModel();
                if(count==1){
                    setUpPlane();
                }else{
                    setUpPlane2();
                }
                Toast.makeText(ARMain.this, "Tap on the plane to place the model", Toast.LENGTH_SHORT).show();
            }
        });

        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ARMain.this, plantsize.class);
                intent.putExtra("object", object);
                startActivity(intent);
            }
        });

    }

    private void setUpModel(){
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "raw",
                this.getPackageName());

        if(object.getPic().equals("daisie")){
            count =1;
        }
        if(object.getPic().equals("dandelion")){
            count =1;
        }
        if(object.getPic().equals("whitedaffodil")){
            count =1;
        }

        ModelRenderable.builder().setSource(this, drawableResourceId).build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(ARMain.this, "Model can't be loaded",
                            Toast.LENGTH_SHORT).show();
                    return null;
                });
    }

    private void setUpPlane(){
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createModel(anchorNode);
            }
        });
    }

    private void setUpPlane1(){
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createModel1(anchorNode);
            }
        });
    }


    private void setUpPlane2(){
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createModel2(anchorNode);
            }
        });
    }

    private void createModel(AnchorNode anchorNode){
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.getScaleController().setMaxScale(0.3f);
        node.getScaleController().setMinScale(0.2f);
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();

    }

    private void createModel1(AnchorNode anchorNode){
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.getScaleController().setMaxScale(0.6f);
        node.getScaleController().setMinScale(0.5f);
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();

    }

    private void createModel2(AnchorNode anchorNode){
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.getScaleController().setMaxScale(0.8f);
        node.getScaleController().setMinScale(0.7f);
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();

    }
}
