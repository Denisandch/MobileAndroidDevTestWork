package com.ntpro.mobileandroiddevtestwork.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DealDB::class], version = 1, exportSchema = false)
abstract class DealDataBase : RoomDatabase() {

    abstract fun dealDao(): DealDao

    companion object {

        @Volatile
        private var INSTANCE: DealDataBase? = null

        fun getDatabase(context: Context): DealDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DealDataBase::class.java,
                    "deal_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}