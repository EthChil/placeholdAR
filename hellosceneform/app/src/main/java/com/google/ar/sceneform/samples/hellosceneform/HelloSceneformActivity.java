/*
 * Copyright 2018 Google LLC. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.ar.sceneform.samples.hellosceneform;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.ar.core.Anchor;
import com.google.ar.core.Config;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.ScaleController;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.ar.sceneform.ux.TranslationController;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an example activity that uses the Sceneform UX package to make common AR tasks easier.
 */
public class HelloSceneformActivity extends AppCompatActivity {
  private static final String TAG = HelloSceneformActivity.class.getSimpleName();
  private static final double MIN_OPENGL_VERSION = 3.0;
  private ArFragment arFragment;
  private ViewRenderable imgRenderable;

  //Handle shoes
  private GrabShoe shoeMaster = new GrabShoe();
  private Shoe allShoes[] = shoeMaster.shoes;



  private Session session;

    public void onClear() {
        List<Node> children = new ArrayList<>(arFragment.getArSceneView().getScene().getChildren());
        for (Node node : children) {
            if (node instanceof AnchorNode) {
                if (((AnchorNode) node).getAnchor() != null) {
                    ((AnchorNode) node).getAnchor().detach();
                }
            }

        }
    }

    @Override
  @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
  // CompletableFuture requires api level 24
  // FutureReturnValueIgnored is not valid
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);







    if (!checkIsSupportedDeviceOrFinish(this)) {
      return;
    }


    setContentView(R.layout.activity_ux);
    arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);


        ViewRenderable.builder()
                .setView(arFragment.getContext(), R.layout.test_view)
                .build()
                .thenAccept(
                        (renderable) -> {
                            imgRenderable = renderable;

//                          // Change the rotation
//                          if (plane.getType() ==  Plane.Type.VERTICAL) {
//                              float[] yAxis = plane.getCenterPose().getYAxis();
//                              Vector3 planeNormal = new Vector3(yAxis[0], yAxis[1], yAxis[2]);
//                              Quaternion upQuat = Quaternion.lookRotation(planeNormal, Vector3.up());
//                              img.setWorldRotation(upQuat);
//                          }
                        });

        arFragment.setOnTapArPlaneListener(
        (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
          if (imgRenderable == null) {
            return;
          }

          // Create the Anchor.
          Anchor anchor = hitResult.createAnchor();
          AnchorNode anchorNode = new AnchorNode(anchor);
          anchorNode.setName("left top");
          anchorNode.setParent(arFragment.getArSceneView().getScene());
          //Hardcoded andhors
          // Create the transformable andy and add it to the anchor.
          TransformableNode img = new TransformableNode(arFragment.getTransformationSystem());
          img.getScaleController().setMinScale(0.01f);
          img.getScaleController().setMaxScale(2.0f);
          img.setLocalScale(new Vector3(2f, 2f, 2f));
          img.setParent(anchorNode);
          img.setRenderable(imgRenderable);

          ViewRenderable.builder()
            .setView(arFragment.getContext(), R.layout.test_view)
            .build()
            .thenAccept(
                (renderable) -> {
                    imgRenderable = renderable;

              // Change the rotation
              if (plane.getType() ==  Plane.Type.VERTICAL) {
                  float[] yAxis = plane.getCenterPose().getYAxis();
                  Vector3 planeNormal = new Vector3(yAxis[0], yAxis[1], yAxis[2]);
                  Quaternion upQuat = Quaternion.lookRotation(planeNormal, Vector3.up());
                  img.setWorldRotation(upQuat);

//                              Matrix.translateM(arFragment, 0, 1, 0f, 1);
              }
                });

          img.select();
        });
        ImageView middleImg = findViewById(R.id.middle_img);
        Picasso.get().load("https://icon2.kisspng.com/20171221/fsq/cat-5a3c42efbe6bf2.68329414151389873578.jpg").into(middleImg);
        // Initialize the "left" button.
        Button leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(
                (unusedView) -> {
                    Picasso.get().load("https://cdn4.iconfinder.com/data/icons/defaulticon/icons/png/256x256/arrow-alt-left.png").into(middleImg);
                });
        // Initialize the "right" button.
        Button rightButton = findViewById(R.id.right_button);
        rightButton.setOnClickListener(
                (unusedView) -> {
                    Picasso.get().load("https://png.icons8.com/windows/1600/long-arrow-right.png").into(middleImg);
                });

        Button deletebutton = findViewById(R.id.delete_button);
        deletebutton.setOnClickListener(
                (unusedView) -> {
                    onClear();
                });
  }

    @Override
    protected void onResume() {
        super.onResume();

        if (session == null) try {
            session = new Session(this);
            Config config = new Config(session);
            config.setPlaneFindingMode(Config.PlaneFindingMode.HORIZONTAL_AND_VERTICAL);
            session.configure(config);
        } catch (UnavailableArcoreNotInstalledException | UnavailableApkTooOldException | UnavailableSdkTooOldException e) {
            e.printStackTrace();
        }
        try {
            session.resume();
        } catch (CameraNotAvailableException e) {
            session = null;
            e.printStackTrace();
        }
    }

    /**
   * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
   * on this device.
   *
   * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
   *
   * <p>Finishes the activity if Sceneform can not run
   */
  public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
    if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
      Log.e(TAG, "Sceneform requires Android N or later");
      Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
      activity.finish();
      return false;
    }
    String openGlVersionString =
        ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
            .getDeviceConfigurationInfo()
            .getGlEsVersion();
    if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
      Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
      Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
          .show();
      activity.finish();
      return false;
    }
    return true;
  }
}
