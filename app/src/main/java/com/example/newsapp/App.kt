package com.example.newsapp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.room.AppDataBase

class App : Application() {
    companion object{
        lateinit var dataBase: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(this, AppDataBase:: class.java, "database").allowMainThreadQueries().build()
    }
}