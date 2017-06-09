package zinnur.iot.rockylabs.asphalt.presentation.di.modules

import dagger.Module
import dagger.Provides
import io.realm.Realm
import zinnur.iot.rockylabs.asphalt.domain.repository.RealmController
import zinnur.iot.rockylabs.asphalt.domain.repository.pothole.PotholeRepository
import zinnur.iot.rockylabs.asphalt.domain.repository.pothole.PotholeRepositoryImpl
import zinnur.iot.rockylabs.asphalt.presentation.di.scopes.ControllerScope
import javax.inject.Singleton

/**
 * Created by Zinnur on 04.06.17.
 */

@Module
class CachingModule {

    @Provides
    @Singleton
    fun provideRealmController(): RealmController {
        return RealmController(Realm.getDefaultInstance())
    }

}