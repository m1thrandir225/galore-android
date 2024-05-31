package com.sebastijanzindl.galore.domain.usecase.impl

import android.util.Log
import com.sebastijanzindl.galore.data.repository.FlavourRepository
import com.sebastijanzindl.galore.domain.usecase.AddFlavoursToFavouritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AddFlavourToFavouritesUseCaseImpl @Inject constructor(
    private val flavourRepository: FlavourRepository
) : AddFlavoursToFavouritesUseCase {
    override suspend fun execute(input: AddFlavoursToFavouritesUseCase.Input): AddFlavoursToFavouritesUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = flavourRepository.addFlavoursToFavourites(
                flavourIds = input.flavoursIds,
                userId = input.userId
            )
            Log.i("Response from favourite flavours", response.toString())
            if(response.isNotEmpty()) {
                AddFlavoursToFavouritesUseCase.Output.Success
            } else {
                AddFlavoursToFavouritesUseCase.Output.Failure
            }
        }
}