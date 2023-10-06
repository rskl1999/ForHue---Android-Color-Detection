// Generated by view binder compiler. Do not edit!
package com.example.forhue_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.forhue_app.R;
import com.google.android.material.navigation.NavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class HomeBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final SeekBar brightBar;

  @NonNull
  public final Button button;

  @NonNull
  public final ImageButton cameraMenu;

  @NonNull
  public final ListView cameralang;

  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final ImageView imageView10;

  @NonNull
  public final ImageView imageView11;

  @NonNull
  public final ImageView imageView29;

  @NonNull
  public final ImageView imageView30;

  @NonNull
  public final ImageView imageView32;

  @NonNull
  public final ImageButton module1;

  @NonNull
  public final ImageButton module2;

  @NonNull
  public final NavigationView navView;

  @NonNull
  public final ImageButton opensettings;

  @NonNull
  public final ImageButton returnSett;

  @NonNull
  public final Spinner spinner;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView13;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final ImageButton uploadMenu;

  private HomeBinding(@NonNull DrawerLayout rootView, @NonNull SeekBar brightBar,
      @NonNull Button button, @NonNull ImageButton cameraMenu, @NonNull ListView cameralang,
      @NonNull DrawerLayout drawerLayout, @NonNull ImageView imageView10,
      @NonNull ImageView imageView11, @NonNull ImageView imageView29,
      @NonNull ImageView imageView30, @NonNull ImageView imageView32, @NonNull ImageButton module1,
      @NonNull ImageButton module2, @NonNull NavigationView navView,
      @NonNull ImageButton opensettings, @NonNull ImageButton returnSett, @NonNull Spinner spinner,
      @NonNull TextView textView10, @NonNull TextView textView13, @NonNull TextView textView2,
      @NonNull TextView textView4, @NonNull TextView textView6, @NonNull ImageButton uploadMenu) {
    this.rootView = rootView;
    this.brightBar = brightBar;
    this.button = button;
    this.cameraMenu = cameraMenu;
    this.cameralang = cameralang;
    this.drawerLayout = drawerLayout;
    this.imageView10 = imageView10;
    this.imageView11 = imageView11;
    this.imageView29 = imageView29;
    this.imageView30 = imageView30;
    this.imageView32 = imageView32;
    this.module1 = module1;
    this.module2 = module2;
    this.navView = navView;
    this.opensettings = opensettings;
    this.returnSett = returnSett;
    this.spinner = spinner;
    this.textView10 = textView10;
    this.textView13 = textView13;
    this.textView2 = textView2;
    this.textView4 = textView4;
    this.textView6 = textView6;
    this.uploadMenu = uploadMenu;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HomeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HomeBinding bind(@NonNull View rootView) {
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

      id = R.id.camera_menu;
      ImageButton cameraMenu = ViewBindings.findChildViewById(rootView, id);
      if (cameraMenu == null) {
        break missingId;
      }

      id = R.id.cameralang;
      ListView cameralang = ViewBindings.findChildViewById(rootView, id);
      if (cameralang == null) {
        break missingId;
      }

      DrawerLayout drawerLayout = (DrawerLayout) rootView;

      id = R.id.imageView10;
      ImageView imageView10 = ViewBindings.findChildViewById(rootView, id);
      if (imageView10 == null) {
        break missingId;
      }

      id = R.id.imageView11;
      ImageView imageView11 = ViewBindings.findChildViewById(rootView, id);
      if (imageView11 == null) {
        break missingId;
      }

      id = R.id.imageView29;
      ImageView imageView29 = ViewBindings.findChildViewById(rootView, id);
      if (imageView29 == null) {
        break missingId;
      }

      id = R.id.imageView30;
      ImageView imageView30 = ViewBindings.findChildViewById(rootView, id);
      if (imageView30 == null) {
        break missingId;
      }

      id = R.id.imageView32;
      ImageView imageView32 = ViewBindings.findChildViewById(rootView, id);
      if (imageView32 == null) {
        break missingId;
      }

      id = R.id.module1;
      ImageButton module1 = ViewBindings.findChildViewById(rootView, id);
      if (module1 == null) {
        break missingId;
      }

      id = R.id.module2;
      ImageButton module2 = ViewBindings.findChildViewById(rootView, id);
      if (module2 == null) {
        break missingId;
      }

      id = R.id.navView;
      NavigationView navView = ViewBindings.findChildViewById(rootView, id);
      if (navView == null) {
        break missingId;
      }

      id = R.id.opensettings;
      ImageButton opensettings = ViewBindings.findChildViewById(rootView, id);
      if (opensettings == null) {
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

      id = R.id.textView13;
      TextView textView13 = ViewBindings.findChildViewById(rootView, id);
      if (textView13 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.upload_menu;
      ImageButton uploadMenu = ViewBindings.findChildViewById(rootView, id);
      if (uploadMenu == null) {
        break missingId;
      }

      return new HomeBinding((DrawerLayout) rootView, brightBar, button, cameraMenu, cameralang,
          drawerLayout, imageView10, imageView11, imageView29, imageView30, imageView32, module1,
          module2, navView, opensettings, returnSett, spinner, textView10, textView13, textView2,
          textView4, textView6, uploadMenu);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}