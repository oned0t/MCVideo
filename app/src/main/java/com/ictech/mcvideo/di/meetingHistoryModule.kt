package com.ictech.mcvideo.di

import com.ictech.mcvideo.repository.MeetingHistoryRepository
import com.ictech.mcvideo.viewmodel.MeetingHistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val meetingHistoryModule = module {

    single { MeetingHistoryRepository(get()) }
    viewModel { MeetingHistoryViewModel(get()) }

}
