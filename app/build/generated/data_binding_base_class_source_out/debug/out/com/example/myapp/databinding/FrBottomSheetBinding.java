// Generated by view binder compiler. Do not edit!
package com.example.myapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FrBottomSheetBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final ConstraintLayout btmSheetLayout;

  @NonNull
  public final MaterialButton btnUpdate;

  @NonNull
  public final TextInputEditText etEditNum;

  @NonNull
  public final TextInputEditText etEditProductName;

  @NonNull
  public final TextInputEditText etEditUnit;

  private FrBottomSheetBinding(@NonNull CoordinatorLayout rootView,
      @NonNull ConstraintLayout btmSheetLayout, @NonNull MaterialButton btnUpdate,
      @NonNull TextInputEditText etEditNum, @NonNull TextInputEditText etEditProductName,
      @NonNull TextInputEditText etEditUnit) {
    this.rootView = rootView;
    this.btmSheetLayout = btmSheetLayout;
    this.btnUpdate = btnUpdate;
    this.etEditNum = etEditNum;
    this.etEditProductName = etEditProductName;
    this.etEditUnit = etEditUnit;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FrBottomSheetBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FrBottomSheetBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fr_bottom_sheet, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FrBottomSheetBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btmSheetLayout;
      ConstraintLayout btmSheetLayout = ViewBindings.findChildViewById(rootView, id);
      if (btmSheetLayout == null) {
        break missingId;
      }

      id = R.id.btnUpdate;
      MaterialButton btnUpdate = ViewBindings.findChildViewById(rootView, id);
      if (btnUpdate == null) {
        break missingId;
      }

      id = R.id.etEditNum;
      TextInputEditText etEditNum = ViewBindings.findChildViewById(rootView, id);
      if (etEditNum == null) {
        break missingId;
      }

      id = R.id.etEditProductName;
      TextInputEditText etEditProductName = ViewBindings.findChildViewById(rootView, id);
      if (etEditProductName == null) {
        break missingId;
      }

      id = R.id.etEditUnit;
      TextInputEditText etEditUnit = ViewBindings.findChildViewById(rootView, id);
      if (etEditUnit == null) {
        break missingId;
      }

      return new FrBottomSheetBinding((CoordinatorLayout) rootView, btmSheetLayout, btnUpdate,
          etEditNum, etEditProductName, etEditUnit);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
