package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.Section

interface GetDailyHomeSectionsUseCase : UseCase<GetDailyHomeSectionsUseCase.Input, GetDailyHomeSectionsUseCase.Output>{
    class Input(val token: String)
    class Output(val result: List<Section>?)
}