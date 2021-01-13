package com.ictech.mcvideo.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/ictech/mcvideo/viewmodel/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/ictech/mcvideo/repository/MainRepository;", "(Lcom/ictech/mcvideo/repository/MainRepository;)V", "addMeetingToDb", "", "meeting", "Lcom/ictech/mcvideo/model/Meeting;", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    private final com.ictech.mcvideo.repository.MainRepository repository = null;
    
    public final void addMeetingToDb(@org.jetbrains.annotations.NotNull()
    com.ictech.mcvideo.model.Meeting meeting) {
    }
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.ictech.mcvideo.repository.MainRepository repository) {
        super();
    }
}