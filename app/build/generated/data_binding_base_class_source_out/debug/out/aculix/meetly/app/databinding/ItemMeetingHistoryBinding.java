// Generated by view binder compiler. Do not edit!
package aculix.meetly.app.databinding;

import aculix.meetly.app.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemMeetingHistoryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnRejoinMeeting;

  @NonNull
  public final MaterialTextView tvMeetingCode;

  @NonNull
  public final MaterialTextView tvMeetingTime;

  private ItemMeetingHistoryBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnRejoinMeeting, @NonNull MaterialTextView tvMeetingCode,
      @NonNull MaterialTextView tvMeetingTime) {
    this.rootView = rootView;
    this.btnRejoinMeeting = btnRejoinMeeting;
    this.tvMeetingCode = tvMeetingCode;
    this.tvMeetingTime = tvMeetingTime;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemMeetingHistoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemMeetingHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_meeting_history, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemMeetingHistoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnRejoinMeeting;
      MaterialButton btnRejoinMeeting = rootView.findViewById(id);
      if (btnRejoinMeeting == null) {
        break missingId;
      }

      id = R.id.tvMeetingCode;
      MaterialTextView tvMeetingCode = rootView.findViewById(id);
      if (tvMeetingCode == null) {
        break missingId;
      }

      id = R.id.tvMeetingTime;
      MaterialTextView tvMeetingTime = rootView.findViewById(id);
      if (tvMeetingTime == null) {
        break missingId;
      }

      return new ItemMeetingHistoryBinding((ConstraintLayout) rootView, btnRejoinMeeting,
          tvMeetingCode, tvMeetingTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
