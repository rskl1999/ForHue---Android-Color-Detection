// Generated by view binder compiler. Do not edit!
package com.example.forhue_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.forhue_app.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.opencv.android.JavaCameraView;

public final class CameraLiveBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton captureButton;

  @NonNull
  public final TextView colorID;

  @NonNull
  public final ImageButton fliprotate;

  @NonNull
  public final ConstraintLayout frameLayout;

  @NonNull
  public final TextView heightdim;

  @NonNull
  public final ImageButton homeButton;

  @NonNull
  public final ImageButton imageButton;

  @NonNull
  public final ImageButton imageButton3;

  @NonNull
  public final ConstraintLayout imageView7;

  @NonNull
  public final ImageButton langicon;

  @NonNull
  public final JavaCameraView opencvSurfaceView;

  @NonNull
  public final ImageButton pauseButt;

  @NonNull
  public final ImageButton resoButton;

  @NonNull
  public final ImageButton resumeButt;

  @NonNull
  public final ListView setLang;

  @NonNull
  public final ListView setReso;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final TextView textView19;

  @NonNull
  public final TextView textView23;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final ConstraintLayout topView;

  @NonNull
  public final TextView widthdim;

  private CameraLiveBinding(@NonNull ConstraintLayout rootView, @NonNull ImageButton captureButton,
      @NonNull TextView colorID, @NonNull ImageButton fliprotate,
      @NonNull ConstraintLayout frameLayout, @NonNull TextView heightdim,
      @NonNull ImageButton homeButton, @NonNull ImageButton imageButton,
      @NonNull ImageButton imageButton3, @NonNull ConstraintLayout imageView7,
      @NonNull ImageButton langicon, @NonNull JavaCameraView opencvSurfaceView,
      @NonNull ImageButton pauseButt, @NonNull ImageButton resoButton,
      @NonNull ImageButton resumeButt, @NonNull ListView setLang, @NonNull ListView setReso,
      @NonNull TextView textView16, @NonNull TextView textView19, @NonNull TextView textView23,
      @NonNull TextView textView3, @NonNull ConstraintLayout topView, @NonNull TextView widthdim) {
    this.rootView = rootView;
    this.captureButton = captureButton;
    this.colorID = colorID;
    this.fliprotate = fliprotate;
    this.frameLayout = frameLayout;
    this.heightdim = heightdim;
    this.homeButton = homeButton;
    this.imageButton = imageButton;
    this.imageButton3 = imageButton3;
    this.imageView7 = imageView7;
    this.langicon = langicon;
    this.opencvSurfaceView = opencvSurfaceView;
    this.pauseButt = pauseButt;
    this.resoButton = resoButton;
    this.resumeButt = resumeButt;
    this.setLang = setLang;
    this.setReso = setReso;
    this.textView16 = textView16;
    this.textView19 = textView19;
    this.textView23 = textView23;
    this.textView3 = textView3;
    this.topView = topView;
    this.widthdim = widthdim;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CameraLiveBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CameraLiveBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.camera_live, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CameraLiveBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.capture_button;
      ImageButton captureButton = ViewBindings.findChildViewById(rootView, id);
      if (captureButton == null) {
        break missingId;
      }

      id = R.id.colorID;
      TextView colorID = ViewBindings.findChildViewById(rootView, id);
      if (colorID == null) {
        break missingId;
      }

      id = R.id.fliprotate;
      ImageButton fliprotate = ViewBindings.findChildViewById(rootView, id);
      if (fliprotate == null) {
        break missingId;
      }

      ConstraintLayout frameLayout = (ConstraintLayout) rootView;

      id = R.id.heightdim;
      TextView heightdim = ViewBindings.findChildViewById(rootView, id);
      if (heightdim == null) {
        break missingId;
      }

      id = R.id.home_button;
      ImageButton homeButton = ViewBindings.findChildViewById(rootView, id);
      if (homeButton == null) {
        break missingId;
      }

      id = R.id.imageButton;
      ImageButton imageButton = ViewBindings.findChildViewById(rootView, id);
      if (imageButton == null) {
        break missingId;
      }

      id = R.id.imageButton3;
      ImageButton imageButton3 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton3 == null) {
        break missingId;
      }

      id = R.id.imageView7;
      ConstraintLayout imageView7 = ViewBindings.findChildViewById(rootView, id);
      if (imageView7 == null) {
        break missingId;
      }

      id = R.id.langicon;
      ImageButton langicon = ViewBindings.findChildViewById(rootView, id);
      if (langicon == null) {
        break missingId;
      }

      id = R.id.opencv_surface_view;
      JavaCameraView opencvSurfaceView = ViewBindings.findChildViewById(rootView, id);
      if (opencvSurfaceView == null) {
        break missingId;
      }

      id = R.id.pauseButt;
      ImageButton pauseButt = ViewBindings.findChildViewById(rootView, id);
      if (pauseButt == null) {
        break missingId;
      }

      id = R.id.reso_button;
      ImageButton resoButton = ViewBindings.findChildViewById(rootView, id);
      if (resoButton == null) {
        break missingId;
      }

      id = R.id.resumeButt;
      ImageButton resumeButt = ViewBindings.findChildViewById(rootView, id);
      if (resumeButt == null) {
        break missingId;
      }

      id = R.id.set_lang;
      ListView setLang = ViewBindings.findChildViewById(rootView, id);
      if (setLang == null) {
        break missingId;
      }

      id = R.id.set_reso;
      ListView setReso = ViewBindings.findChildViewById(rootView, id);
      if (setReso == null) {
        break missingId;
      }

      id = R.id.textView16;
      TextView textView16 = ViewBindings.findChildViewById(rootView, id);
      if (textView16 == null) {
        break missingId;
      }

      id = R.id.textView19;
      TextView textView19 = ViewBindings.findChildViewById(rootView, id);
      if (textView19 == null) {
        break missingId;
      }

      id = R.id.textView23;
      TextView textView23 = ViewBindings.findChildViewById(rootView, id);
      if (textView23 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.topView;
      ConstraintLayout topView = ViewBindings.findChildViewById(rootView, id);
      if (topView == null) {
        break missingId;
      }

      id = R.id.widthdim;
      TextView widthdim = ViewBindings.findChildViewById(rootView, id);
      if (widthdim == null) {
        break missingId;
      }

      return new CameraLiveBinding((ConstraintLayout) rootView, captureButton, colorID, fliprotate,
          frameLayout, heightdim, homeButton, imageButton, imageButton3, imageView7, langicon,
          opencvSurfaceView, pauseButt, resoButton, resumeButt, setLang, setReso, textView16,
          textView19, textView23, textView3, topView, widthdim);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
