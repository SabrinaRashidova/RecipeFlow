package com.sabrina.data.mapper

import android.text.Html
import com.sabrina.data.remote.dto.RecipeDetailDto
import com.sabrina.domain.model.RecipeDetail

fun RecipeDetailDto.toDomain() : RecipeDetail {
    val rawInstructions = if (!this.instructions.isNullOrBlank()){
        this.instructions
    }else{
        this.analyzedInstructions
            .firstOrNull()?.steps
            ?.joinToString(separator = "\n\n") { "${it.number}. ${it.step}" }
    }

    return RecipeDetail(
        id = this.id,
        title = this.title,
        imageUrl = this.image,
        summary = Html.fromHtml(this.summary, Html.FROM_HTML_MODE_COMPACT).toString(),
        instructions =Html.fromHtml(rawInstructions ?: "No instructions provided.", Html.FROM_HTML_MODE_COMPACT).toString(),
        ingredients = this.extendedIngredients.map { it.original },
        readyInMinutes = this.readyInMinutes,
        servings = this.servings,
        healthScore = this.healthScore
    )
}