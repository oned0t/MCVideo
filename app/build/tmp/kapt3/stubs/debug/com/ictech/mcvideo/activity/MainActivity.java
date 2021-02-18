package com.ictech.mcvideo.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 72\u00020\u0001:\u00017B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0002J\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\nH\u0002J\b\u0010\u001b\u001a\u00020\nH\u0002J\b\u0010\u001c\u001a\u00020\nH\u0002J\b\u0010\u001d\u001a\u00020\u0015H\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010!\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0002J\b\u0010\"\u001a\u00020\u0015H\u0002J\b\u0010#\u001a\u00020\u0015H\u0002J\"\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\u0012\u0010)\u001a\u00020\u00152\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\b\u0010,\u001a\u00020\u0015H\u0002J\b\u0010-\u001a\u00020\u0015H\u0002J\b\u0010.\u001a\u00020\u0015H\u0002J\b\u0010/\u001a\u00020\u0015H\u0002J\b\u00100\u001a\u00020\u0015H\u0002J\b\u00101\u001a\u00020\u0015H\u0002J\b\u00102\u001a\u00020\u0015H\u0002J\b\u00103\u001a\u00020\u0015H\u0002J\b\u00104\u001a\u00020\u0015H\u0002J\u0010\u00105\u001a\u00020\u00152\u0006\u00106\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011\u00a8\u00068"}, d2 = {"Lcom/ictech/mcvideo/activity/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ictech/mcvideo/databinding/ActivityMainBinding;", "createMeetingInterstitialAd", "Lcom/google/android/gms/ads/InterstitialAd;", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "etName", "", "joinMeetingInterstitialAd", "minMeetingCodeLength", "", "viewModel", "Lcom/ictech/mcvideo/viewmodel/MainViewModel;", "getViewModel", "()Lcom/ictech/mcvideo/viewmodel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "createMeeting", "", "meetingCode", "disable", "view", "Landroid/view/View;", "generateMeetingCode", "getCreateMeetingCode", "getJoinMeetingCode", "initializeCreateMeetingInterstitialAd", "initializeJoinMeetingInterstitialAd", "isMeetingCodeValid", "", "joinMeeting", "loadCreateMeetingInterstitialAd", "loadJoinMeetingInterstitialAd", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateMeetingClick", "onCreateMeetingCodeChange", "onJoinMeetingClick", "onMeetingHistoryClick", "onMeetingToggleChange", "onProfileClick", "onShareMeetingCodeClick", "openDialogActivity", "setProfileIcon", "setThemeMode", "themeMode", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private final kotlin.Lazy viewModel$delegate = null;
    private com.ictech.mcvideo.databinding.ActivityMainBinding binding;
    private final int minMeetingCodeLength = 10;
    private com.google.firebase.auth.FirebaseUser currentUser;
    private com.google.android.gms.ads.InterstitialAd createMeetingInterstitialAd;
    private com.google.android.gms.ads.InterstitialAd joinMeetingInterstitialAd;
    private java.lang.String etName;
    public static final int DIALOG_ACTIVITY_REQUEST_CODE = 1;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME = "name";
    @org.jetbrains.annotations.NotNull()
    public static final com.ictech.mcvideo.activity.MainActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final com.ictech.mcvideo.viewmodel.MainViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void openDialogActivity() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void setProfileIcon() {
    }
    
    private final void initializeCreateMeetingInterstitialAd() {
    }
    
    private final void loadCreateMeetingInterstitialAd() {
    }
    
    private final void initializeJoinMeetingInterstitialAd() {
    }
    
    private final void loadJoinMeetingInterstitialAd() {
    }
    
    private final void onMeetingToggleChange() {
    }
    
    /**
     * Called when the meeting code in the EditText of the CREATE MEETING toggle changes
     */
    private final void onCreateMeetingCodeChange() {
    }
    
    private final java.lang.String generateMeetingCode() {
        return null;
    }
    
    /**
     * Called when the share icon is clicked in the EditText of the CREATE MEETING toggle
     */
    private final void onShareMeetingCodeClick() {
    }
    
    /**
     * Called when the JOIN button is clicked of the JOIN MEETING toggle
     */
    private final void onJoinMeetingClick() {
    }
    
    private final void joinMeeting(java.lang.String meetingCode) {
    }
    
    /**
     * Returns the meeting code for joining the meeting
     */
    private final java.lang.String getJoinMeetingCode() {
        return null;
    }
    
    /**
     * Called when the CREATE button is clicked of the CREATE MEETING toggle
     */
    private final void onCreateMeetingClick() {
    }
    
    private final void createMeeting(java.lang.String meetingCode) {
    }
    
    private final java.lang.String getCreateMeetingCode() {
        return null;
    }
    
    private final boolean isMeetingCodeValid(java.lang.String meetingCode) {
        return false;
    }
    
    private final void onMeetingHistoryClick() {
    }
    
    private final void onProfileClick() {
    }
    
    private final void setThemeMode(int themeMode) {
    }
    
    public final void disable(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public MainActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/ictech/mcvideo/activity/MainActivity$Companion;", "", "()V", "DIALOG_ACTIVITY_REQUEST_CODE", "", "NAME", "", "startActivity", "", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        public final void startActivity(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        private Companion() {
            super();
        }
    }
}