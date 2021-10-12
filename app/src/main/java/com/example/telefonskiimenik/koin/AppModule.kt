package com.example.telefonskiimenik.koin

import com.example.telefonskiimenik.screens.create.CreatePresenter
import com.example.telefonskiimenik.screens.main.MainPresenter
import com.example.telefonskiimenik.screens.update_delete.UpdateDeletePresenter
import com.example.telefonskiimenik.services.DatabaseHelper
import org.koin.dsl.module

object AppModule {

    val myModule = module {
        factory { MainPresenter() }
        factory { CreatePresenter() }
        factory { UpdateDeletePresenter() }
    }
}