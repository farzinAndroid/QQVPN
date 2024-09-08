package pro.sparrow_team.qq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.sparrow_team.qq.data.remote.MainScreenApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiInterfacesModule {

    @Provides
    @Singleton
    fun provideMainScreenApiInterface(retrofit: Retrofit): MainScreenApiInterface =
        retrofit.create(MainScreenApiInterface::class.java)

}