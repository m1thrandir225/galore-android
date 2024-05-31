package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.Flavour

interface GetAllFlavoursUseCase: UseCase<GetAllFlavoursUseCase.Input, GetAllFlavoursUseCase.Output> {
    class Input
    class Output(val result: List<Flavour>?)
}