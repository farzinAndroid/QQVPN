package pro.sparrow_team.qq.model.mainscreen

import androidx.room.Entity
import androidx.room.PrimaryKey
import pro.sparrow_team.qq.utils.Constants

@Entity(tableName = Constants.PING_TABLE_NAME)
data class Ping(
    @PrimaryKey(autoGenerate = false)
    val id:Int = -1,
    val delay:Long = -1
)
