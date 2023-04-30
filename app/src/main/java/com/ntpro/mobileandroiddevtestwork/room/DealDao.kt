package com.ntpro.mobileandroiddevtestwork.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DealDao {

    @Query("SELECT * FROM deal ORDER BY id DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsByDateDesc(limit: Int, offset: Int): List<DealDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListDeal(deals: List<DealDB>)
}

