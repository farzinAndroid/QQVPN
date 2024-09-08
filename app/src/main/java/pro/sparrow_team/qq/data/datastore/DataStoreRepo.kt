package pro.sparrow_team.qq.data.datastore

interface DataStoreRepo {

    suspend fun putString(value:String,key:String)
    suspend fun getString(key: String) : String?

    suspend fun putLong(value:Long,key:String)
    suspend fun getLong(key: String) : Long?

    suspend fun putBoolean(value:Boolean,key:String)
    suspend fun getBoolean(key: String) : Boolean?

    suspend fun putInt(value:Int,key:String)
    suspend fun getInt(key: String) : Int?

}