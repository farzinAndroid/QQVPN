package pro.sparrow_team.qq.di

import android.content.ClipboardManager
import android.content.Context
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCoroutineScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideFireBaseMessaging() = Firebase.messaging

    @Provides
    @Singleton
    fun provideClipBoardManager(
        @ApplicationContext context: Context
    ) = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

}