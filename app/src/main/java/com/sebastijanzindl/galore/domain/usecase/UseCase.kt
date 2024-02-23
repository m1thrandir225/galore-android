package com.sebastijanzindl.galore.domain.usecase

interface UseCase<InputT, OutputT>{
    suspend fun execute(input: InputT): OutputT
}