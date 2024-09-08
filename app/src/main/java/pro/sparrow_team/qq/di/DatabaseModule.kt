package pro.sparrow_team.qq.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pro.sparrow_team.qq.data.database.PingDao
import pro.sparrow_team.qq.data.database.QQDatabase
import pro.sparrow_team.qq.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext c: Context) = Room.databaseBuilder(
        c,
        QQDatabase::class.java,
        Constants.QQ_DATA_BASE
    )
        .build()


    @Provides
    fun providePersonDaoModule(database: QQDatabase): PingDao = database.getPingDao()

}