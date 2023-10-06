// Generated by view binder compiler. Do not edit!
package com.example.forhue_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.forhue_app.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ResoItemsBinding implements ViewBinding {
  @NonNull
  private final TextView rootView;

  @NonNull
  public final TextView textView;

  private ResoItemsBinding(@NonNull TextView rootView, @NonNull TextView textView) {
    this.rootView = rootView;
    this.textView = textView;
  }

  @Override
  @NonNull
  public TextView getRoot() {
    return rootView;
  }

  @NonNull
  public static ResoItemsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ResoItemsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.reso_items, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ResoItemsBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    TextView textView = (TextView) rootView;

    return new ResoItemsBinding((TextView) rootView, textView);
  }
}
