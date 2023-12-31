// Generated by view binder compiler. Do not edit!
package com.example.forhue_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
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

public final class FhSetting2Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final SeekBar brightBar;

  @NonNull
  public final Button button;

  @NonNull
  public final ConstraintLayout constraintLayout3;

  @NonNull
  public final SeekBar fontBar;

  @NonNull
  public final ConstraintLayout grayDrawer;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageButton returnSett;

  @NonNull
  public final Spinner spinner;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView textView9;

  private FhSetting2Binding(@NonNull ConstraintLayout rootView, @NonNull SeekBar brightBar,
      @NonNull Button button, @NonNull ConstraintLayout constraintLayout3, @NonNull SeekBar fontBar,
      @NonNull ConstraintLayout grayDrawer, @NonNull ImageView imageView3,
      @NonNull ImageButton returnSett, @NonNull Spinner spinner, @NonNull TextView textView10,
      @NonNull TextView textView4, @NonNull TextView textView5, @NonNull TextView textView6,
      @NonNull TextView textView7, @NonNull TextView textView8, @NonNull TextView textView9) {
    this.rootView = rootView;
    this.brightBar = brightBar;
    this.button = button;
    this.constraintLayout3 = constraintLayout3;
    this.fontBar = fontBar;
    this.grayDrawer = grayDrawer;
    this.imageView3 = imageView3;
    this.returnSett = returnSett;
    this.spinner = spinner;
    this.textView10 = textView10;
    this.textView4 = textView4;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textView8 = textView8;
    this.textView9 = textView9;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FhSetting2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FhSetting2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fh_setting2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FhSetting2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bright_bar;
      SeekBar brightBar = ViewBindings.findChildViewById(rootView, id);
      if (brightBar == null) {
        break missingId;
      }

      id = R.id.button;
      Button button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.constraintLayout3;
      ConstraintLayout constraintLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout3 == null) {
        break missingId;
      }

      id = R.id.font_bar;
      SeekBar fontBar = ViewBindings.findChildViewById(rootView, id);
      if (fontBar == null) {
        break missingId;
      }

      id = R.id.gray_drawer;
      ConstraintLayout grayDrawer = ViewBindings.findChildViewById(rootView, id);
      if (grayDrawer == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.return_sett;
      ImageButton returnSett = ViewBindings.findChildViewById(rootView, id);
      if (returnSett == null) {
        break missingId;
      }

      id = R.id.spinner;
      Spinner spinner = ViewBindings.findChildViewById(rootView, id);
      if (spinner == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      return new FhSetting2Binding((ConstraintLayout) rootView, brightBar, button,
          constraintLayout3, fontBar, grayDrawer, imageView3, returnSett, spinner, textView10,
          textView4, textView5, textView6, textView7, textView8, textView9);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
