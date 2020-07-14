package com.ictech.mcvideo.repository

import com.ictech.mcvideo.db.MeetingDao
import com.ictech.mcvideo.model.Meeting
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MeetingHistoryRepository(private val meetingDao: MeetingDao) {

    fun getMeetingHistory(): LiveData<List<Meeting>> =
        meetingDao.getAllMeetings()

    suspend fun addMeetingToDb(meeting: Meeting) = withContext(Dispatchers.IO) {
        meetingDao.insertMeeting(meeting)
    }
}