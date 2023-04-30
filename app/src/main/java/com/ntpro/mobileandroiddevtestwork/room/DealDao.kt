package com.ntpro.mobileandroiddevtestwork.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DealDao {

    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY timeStamp ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsByDateAsc(limit: Int, offset: Int, maxID: Long): List<DealDB>
    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY timeStamp DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsByDateDesc(limit: Int, offset: Int, maxID: Long): List<DealDB>
    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY instrumentName ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsByInstrumentNameAsc(limit: Int, offset: Int, maxID: Long): List<DealDB>
    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY instrumentName DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsByInstrumentNameDesc(limit: Int, offset: Int, maxID: Long): List<DealDB>
    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY price ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsByPriceAsc(limit: Int, offset: Int, maxID: Long): List<DealDB>
    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY price DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsByPriceDesc(limit: Int, offset: Int, maxID: Long): List<DealDB>
    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY amount ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsByAmountAsc(limit: Int, offset: Int, maxID: Long): List<DealDB>
    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY amount DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsByAmountDesc(limit: Int, offset: Int, maxID: Long): List<DealDB>
    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY side ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsBySideAsc(limit: Int, offset: Int, maxID: Long): List<DealDB>
    @Query("SELECT * FROM deal WHERE id <= :maxID ORDER BY side DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllDealsBySideDesc(limit: Int, offset: Int, maxID: Long): List<DealDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListDeal(deals: List<DealDB>)
}

