package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.FlavourRepository
import com.sebastijanzindl.galore.domain.usecase.GetAllFlavoursUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllFlavoursUseCaseImpl @Inject constructor(
    private val flavourRepository: FlavourRepository
) : GetAllFlavoursUseCase {
    override suspend fun execute(input: GetAllFlavoursUseCase.Input): GetAllFlavoursUseCase.Output =
        withContext(Dispatchers.IO) {
            val result = flavourRepository.getAllFlavours();

            if(result.isNotEmpty()) {
                GetAllFlavoursUseCase.Output(result)
            } else {
                GetAllFlavoursUseCase.Output(null)
            }
        }
}