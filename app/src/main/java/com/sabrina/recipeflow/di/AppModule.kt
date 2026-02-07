package com.sabrina.recipeflow.di

import com.sabrina.domain.repository.RecipeRepository
import com.sabrina.domain.usecase.SearchRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSearchRecipesUseCase(repository: RecipeRepository) : SearchRecipesUseCase {
        return SearchRecipesUseCase(repository)
    }
}