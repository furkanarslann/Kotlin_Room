package com.furkanarslan.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var db: AppDatabase
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getDatabaseInstance(this)!!
        userDao = db.userDao()

        CoroutineScope(Dispatchers.Main).launch {

            val list = userDao.getAllUsers()

            for (user in list) {
                println(user.id)
                println(user.name)
                println(user.age)
                println("------------------")
            }
        }

    }
}