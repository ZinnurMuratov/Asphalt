package zinnur.ru.rockylabs.kotlintest.di.modules

import zinnur.ru.rockylabs.kotlintest.API.APIInterface
import zinnur.ru.rockylabs.kotlintest.di.scopes.UserScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Zinnur on 12.01.17.
 */

@Module
class ApiModule {

    @Provides
    @UserScope
    fun providesGitHubInterface(retrofit: Retrofit): APIInterface {
        return retrofit.create(APIInterface::class.java)
    }
}
