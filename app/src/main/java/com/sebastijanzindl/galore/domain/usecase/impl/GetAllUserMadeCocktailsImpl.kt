package com.sebastijanzindl.galore.domain.usecase.impl

import android.util.Log
import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.usecase.GetAllUserMadeCocktails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllUserMadeCocktailsImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
): GetAllUserMadeCocktails{
    override suspend fun execute(input: GetAllUserMadeCocktails.Input): GetAllUserMadeCocktails.Output =
        withContext(Dispatchers.IO) {
            val result = cocktailRepository.getYourGeneratedCocktails(
                userId = input.userId
            );
            Log.e("COCKTAILS", result.toString());
            GetAllUserMadeCocktails.Output(result);
        }
}