// Generated by view binder compiler. Do not edit!
package com.ictech.mcvideo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;
import com.ictech.mcvideo.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final View dividerDarkMode;

  @NonNull
  public final View dividerOpenSourceLicenses;

  @NonNull
  public final Guideline glEnd;

  @NonNull
  public final Guideline glStart;

  @NonNull
  public final SwitchMaterial switchDarkMode;

  @NonNull
  public final MaterialTextView tvDotSeparator;

  @NonNull
  public final MaterialTextView tvFaqs;

  @NonNull
  public final MaterialTextView tvOpenSourceLicenses;

  @NonNull
  public final MaterialTextView tvPrivacyPolicy;

  @NonNull
  public final MaterialTextView tvRateApp;

  @NonNull
  public final MaterialTextView tvSendFeedback;

  @NonNull
  public final MaterialTextView tvShareApp;

  @NonNull
  public final MaterialTextView tvTermsOfService;

  private DialogProfileBinding(@NonNull ConstraintLayout rootView, @NonNull View dividerDarkMode,
      @NonNull View dividerOpenSourceLicenses, @NonNull Guideline glEnd, @NonNull Guideline glStart,
      @NonNull SwitchMaterial switchDarkMode, @NonNull MaterialTextView tvDotSeparator,
      @NonNull MaterialTextView tvFaqs, @NonNull MaterialTextView tvOpenSourceLicenses,
      @NonNull MaterialTextView tvPrivacyPolicy, @NonNull MaterialTextView tvRateApp,
      @NonNull MaterialTextView tvSendFeedback, @NonNull MaterialTextView tvShareApp,
      @NonNull MaterialTextView tvTermsOfService) {
    this.rootView = rootView;
    this.dividerDarkMode = dividerDarkMode;
    this.dividerOpenSourceLicenses = dividerOpenSourceLicenses;
    this.glEnd = glEnd;
    this.glStart = glStart;
    this.switchDarkMode = switchDarkMode;
    this.tvDotSeparator = tvDotSeparator;
    this.tvFaqs = tvFaqs;
    this.tvOpenSourceLicenses = tvOpenSourceLicenses;
    this.tvPrivacyPolicy = tvPrivacyPolicy;
    this.tvRateApp = tvRateApp;
    this.tvSendFeedback = tvSendFeedback;
    this.tvShareApp = tvShareApp;
    this.tvTermsOfService = tvTermsOfService;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dividerDarkMode;
      View dividerDarkMode = rootView.findViewById(id);
      if (dividerDarkMode == null) {
        break missingId;
      }

      id = R.id.dividerOpenSourceLicenses;
      View dividerOpenSourceLicenses = rootView.findViewById(id);
      if (dividerOpenSourceLicenses == null) {
        break missingId;
      }

      id = R.id.glEnd;
      Guideline glEnd = rootView.findViewById(id);
      if (glEnd == null) {
        break missingId;
      }

      id = R.id.glStart;
      Guideline glStart = rootView.findViewById(id);
      if (glStart == null) {
        break missingId;
      }

      id = R.id.switchDarkMode;
      SwitchMaterial switchDarkMode = rootView.findViewById(id);
      if (switchDarkMode == null) {
        break missingId;
      }

      id = R.id.tvDotSeparator;
      MaterialTextView tvDotSeparator = rootView.findViewById(id);
      if (tvDotSeparator == null) {
        break missingId;
      }

      id = R.id.tvFaqs;
      MaterialTextView tvFaqs = rootView.findViewById(id);
      if (tvFaqs == null) {
        break missingId;
      }

      id = R.id.tvOpenSourceLicenses;
      MaterialTextView tvOpenSourceLicenses = rootView.findViewById(id);
      if (tvOpenSourceLicenses == null) {
        break missingId;
      }

      id = R.id.tvPrivacyPolicy;
      MaterialTextView tvPrivacyPolicy = rootView.findViewById(id);
      if (tvPrivacyPolicy == null) {
        break missingId;
      }

      id = R.id.tvRateApp;
      MaterialTextView tvRateApp = rootView.findViewById(id);
      if (tvRateApp == null) {
        break missingId;
      }

      id = R.id.tvSendFeedback;
      MaterialTextView tvSendFeedback = rootView.findViewById(id);
      if (tvSendFeedback == null) {
        break missingId;
      }

      id = R.id.tvShareApp;
      MaterialTextView tvShareApp = rootView.findViewById(id);
      if (tvShareApp == null) {
        break missingId;
      }

      id = R.id.tvTermsOfService;
      MaterialTextView tvTermsOfService = rootView.findViewById(id);
      if (tvTermsOfService == null) {
        break missingId;
      }

      return new DialogProfileBinding((ConstraintLayout) rootView, dividerDarkMode,
          dividerOpenSourceLicenses, glEnd, glStart, switchDarkMode, tvDotSeparator, tvFaqs,
          tvOpenSourceLicenses, tvPrivacyPolicy, tvRateApp, tvSendFeedback, tvShareApp,
          tvTermsOfService);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
