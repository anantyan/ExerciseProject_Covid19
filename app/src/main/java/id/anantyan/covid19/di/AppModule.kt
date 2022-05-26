package id.anantyan.covid19.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.anantyan.covid19.data.api.CovidApi
import id.anantyan.covid19.ui.province.MainAdapter
import id.anantyan.covid19.ui.province.MainAdapterHelper
import id.anantyan.covid19.ui.score.ScoreAdapter
import id.anantyan.covid19.ui.score.ScoreAdapterHelper
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCovidApi(retrofit: Retrofit): CovidApi {
        return retrofit.create(CovidApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMainAdapter(mainAdapter: MainAdapter): MainAdapterHelper {
        return mainAdapter
    }

    @Singleton
    @Provides
    fun provideScoreAdapter(scoreAdapter: ScoreAdapter): ScoreAdapterHelper {
        return scoreAdapter
    }
}