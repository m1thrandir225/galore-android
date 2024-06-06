package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.network.response.GetDailyHomeSectionsResponse
import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.usecase.GetDailyHomeSectionsUseCase
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDailyHomeSectionsUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : GetDailyHomeSectionsUseCase{
    override suspend fun execute(input: GetDailyHomeSectionsUseCase.Input): GetDailyHomeSectionsUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.getDailyHomeSections(authorizationToken = input.token)

            if(response.status.value in 200..299) {
                val body = response.body<GetDailyHomeSectionsResponse>()
                GetDailyHomeSectionsUseCase.Output(body.sections)
            } else {
                GetDailyHomeSectionsUseCase.Output(null)
            }
        }
}