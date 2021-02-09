package com.ictech.mcvideo.utils

//import android.annotation.SuppressLint

import com.core.extensions.toast
import com.ictech.mcvideo.R
import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.google.firebase.auth.FirebaseAuth
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetUserInfo
import java.net.URL

object MeetingUtils : AppCompatActivity() {

//    @SuppressLint("StringFormatMatches")
    fun startMeeting(context: Context, meetingCode: String, @StringRes initialToastMessage: Int, name: String?) {
        context.toast(context.getString(initialToastMessage))

        val serverUrl = URL(context.getString(R.string.app_server_url))
        val defaultOptions = JitsiMeetConferenceOptions.Builder()
            .setServerURL(serverUrl)
            .setWelcomePageEnabled(false)
            .setFeatureFlag("invite.enabled", false)
            .setFeatureFlag("live-streaming.enabled", false)
            .setFeatureFlag("meeting-name.enabled", false)
            .setFeatureFlag("call-integration.enabled", false)
            .setFeatureFlag("recording.enabled", false)
            .build()
        JitsiMeet.setDefaultConferenceOptions(defaultOptions)

        val options = JitsiMeetConferenceOptions.Builder()
            .setRoom(meetingCode)
            .setUserInfo(null)

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userInfoBundle = if (currentUser != null) {
            bundleOf(
                "displayName" to currentUser.displayName,
                "email" to currentUser.email,
                "avatarURL" to currentUser.photoUrl
            )

        } else {


            bundleOf(
                "displayName" to name
                )

            /*bundleOf(
                "displayName" to "$text",
                "email" to null,
                "avatarURL" to null
            )*/
        }

        options.setUserInfo(JitsiMeetUserInfo(userInfoBundle))
//        options.setUserInfo(setUserNickname(userInfoBundle))
        JitsiMeetActivity.launch(context, options.build())
    }
}