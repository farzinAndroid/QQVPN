package pro.sparrow_team.qq.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pro.sparrow_team.qq.model.mainscreen.Ping

@Dao
interface PingDao {

    @Query("select * from ping_table")
    fun getAllPings() : Flow<List<Ping>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPings(ping: List<Ping>)

}