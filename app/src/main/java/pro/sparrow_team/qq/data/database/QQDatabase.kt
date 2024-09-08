package pro.sparrow_team.qq.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pro.sparrow_team.qq.model.mainscreen.Ping

@Database(entities = [Ping::class], version = 1, exportSchema = false)
abstract class QQDatabase : RoomDatabase() {

    abstract fun getPingDao() : PingDao

}