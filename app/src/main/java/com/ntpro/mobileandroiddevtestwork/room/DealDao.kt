package com.ntpro.mobileandroiddevtestwork.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DealDao {

    @Query(
        "SELECT * FROM deal WHERE id <= :maxID ORDER BY " +
                "CASE WHEN :q = 'timeStamp' THEN timeStamp " +
                "WHEN :q = 'instrumentName' THEN instrumentName " +
                "WHEN :q = 'price' THEN price " +
                "WHEN :q = 'amount' THEN amount " +
                "WHEN :q = 'side' THEN side " +
                "END ASC LIMIT :limit OFFSET :offset"
    )
    suspend fun getAllDealsAsc(limit: Int, offset: Int, maxID: Long, q: String): List<DealDB>

    @Query(
        "SELECT * FROM deal WHERE id <= :maxID ORDER BY " +
                "CASE WHEN :q = 'timeStamp' THEN timeStamp " +
                "WHEN :q = 'instrumentName' THEN instrumentName " +
                "WHEN :q = 'price' THEN price " +
                "WHEN :q = 'amount' THEN amount " +
                "WHEN :q = 'side' THEN side " +
                "END DESC LIMIT :limit OFFSET :offset"
    )
    suspend fun getAllDealsDesc(limit: Int, offset: Int, maxID: Long, q: String): List<DealDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListDeal(deals: List<DealDB>)
}

