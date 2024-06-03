package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.FlavourRepository
import com.sebastijanzindl.galore.domain.usecase.GetUserFlavoursUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserFlavoursUseCaseImpl @Inject constructor(
    private val flavourRepository: FlavourRepository
) : GetUserFlavoursUseCase {
    override suspend fun execute(input: GetUserFlavoursUseCase.Input): GetUserFlavoursUseCase.Output =
        withContext(Dispatchers.IO) {
            val result = flavourRepository.getUserFlavours(input.userId)
            if(result.isNotEmpty()) {
                    GetUserFlavoursUseCase.Output.Success(result);
            } else {
                GetUserFlavoursUseCase.Output.Failure
            }
        }
}