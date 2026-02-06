package com.sabrina.data.di

import com.sabrina.data.local.dao.RecipeDao
import com.sabrina.data.remote.SpoonacularApi
import com.sabrina.data.repository.RecipeRepositoryImpl
import com.sabrina.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(
        api: SpoonacularApi,
        dao: RecipeDao
    ): RecipeRepository{
        return RecipeRepositoryImpl(
            api = api,
            recipeDao = dao,
            apiKey ="ee35c356bab84f179c2472ef1c338139"
        )
    }
}