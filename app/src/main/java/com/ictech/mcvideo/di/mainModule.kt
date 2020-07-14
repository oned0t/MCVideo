package com.ictech.mcvideo.di

import com.ictech.mcvideo.repository.MainRepository
import com.ictech.mcvideo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single { MainRepository(get()) }
    viewModel { MainViewModel(get()) }

}
