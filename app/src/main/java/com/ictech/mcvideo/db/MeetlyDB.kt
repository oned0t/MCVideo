package com.ictech.mcvideo.db

import com.ictech.mcvideo.model.Meeting
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Meeting::class], version = 1)
abstract class MeetlyDB : RoomDatabase() {

    abstract fun meetingDao(): MeetingDao

}