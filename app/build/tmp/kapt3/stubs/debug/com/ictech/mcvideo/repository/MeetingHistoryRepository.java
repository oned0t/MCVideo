package com.ictech.mcvideo.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/ictech/mcvideo/repository/MeetingHistoryRepository;", "", "meetingDao", "Lcom/ictech/mcvideo/db/MeetingDao;", "(Lcom/ictech/mcvideo/db/MeetingDao;)V", "addMeetingToDb", "", "meeting", "Lcom/ictech/mcvideo/model/Meeting;", "(Lcom/ictech/mcvideo/model/Meeting;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMeetingHistory", "Landroidx/lifecycle/LiveData;", "", "app_debug"})
public final class MeetingHistoryRepository {
    private final com.ictech.mcvideo.db.MeetingDao meetingDao = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ictech.mcvideo.model.Meeting>> getMeetingHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addMeetingToDb(@org.jetbrains.annotations.NotNull()
    com.ictech.mcvideo.model.Meeting meeting, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> p1) {
        return null;
    }
    
    public MeetingHistoryRepository(@org.jetbrains.annotations.NotNull()
    com.ictech.mcvideo.db.MeetingDao meetingDao) {
        super();
    }
}