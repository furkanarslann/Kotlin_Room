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

            val newUser = User(0, "Furkan", 22)
            userDao.insertUser(User(0, "Jack", 34))
            userDao.insertUser(newUser)


            val updatedUser = User(3, "Ceren", 23)
            userDao.updateUser(updatedUser) // Updated person with ID 3

            userDao.deleteUser(User(4, "", 0))// Deleted Person with ID 4

        }

        printAllUsers()
    }

    fun printAllUsers() {
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