package com.sebastijanzindl.galore.di

import com.sebastijanzindl.galore.domain.usecase.AddFlavoursToFavouritesUseCase
import com.sebastijanzindl.galore.domain.usecase.DeleteUserUseCase
import com.sebastijanzindl.galore.domain.usecase.GetAllFlavoursUseCase
import com.sebastijanzindl.galore.domain.usecase.GetAllUserMadeCocktails
import com.sebastijanzindl.galore.domain.usecase.GetPopularCocktailsUseCase
import com.sebastijanzindl.galore.domain.usecase.GetSingleCocktailUseCase
import com.sebastijanzindl.galore.domain.usecase.GetSingleGeneratedCocktailUseCase
import com.sebastijanzindl.galore.domain.usecase.GetUserFlavoursUseCase
import com.sebastijanzindl.galore.domain.usecase.GetUserLikedCocktailsUseCase
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import com.sebastijanzindl.galore.domain.usecase.LibraryScreenUseCase
import com.sebastijanzindl.galore.domain.usecase.SignInGoogleUseCase
import com.sebastijanzindl.galore.domain.usecase.SignInUseCase
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import com.sebastijanzindl.galore.domain.usecase.SignUpUseCase
import com.sebastijanzindl.galore.domain.usecase.UpdateUserProfileUseCase
import com.sebastijanzindl.galore.domain.usecase.impl.AddFlavourToFavouritesUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.DeleteUserUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.GetAllFlavoursUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.GetAllUserMadeCocktailsImpl
import com.sebastijanzindl.galore.domain.usecase.impl.GetPopularCocktailsUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.GetSingleCocktailUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.GetSingleGeneratedCocktailUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.GetUserFlavoursUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.GetUserLikedCocktailsUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.GetUserProfileUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.LibraryScreenUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.SignInGoogleUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.SignInUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.SignOutUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.SignUpUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.UpdateUserProfileUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    /**
     * Authentication Repository Use Cases
     */
    @Binds
    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase
    @Binds
    abstract fun bindSignInGoogleUseCase(impl: SignInGoogleUseCaseImpl): SignInGoogleUseCase
    @Binds
    abstract fun bindSignOutUseCase(impl: SignOutUseCaseImpl): SignOutUseCase
    @Binds
    abstract fun bindSignInUseCase(impl: SignInUseCaseImpl): SignInUseCase
    /**
     * Flavour Repository Use Cases
     */
    @Binds
    abstract fun bindGetUserFlavoursUseCase(impl: GetUserFlavoursUseCaseImpl): GetUserFlavoursUseCase
    @Binds
    abstract fun bindGetAllFlavoursUseCase(impl: GetAllFlavoursUseCaseImpl): GetAllFlavoursUseCase

    @Binds
    abstract  fun bindGetUserProfileUseCase(impl: GetUserProfileUseCaseImpl): GetUserProfileUseCase

    @Binds
    abstract fun bindUpdateUserProfileUseCase(impl: UpdateUserProfileUseCaseImpl): UpdateUserProfileUseCase

    @Binds
    abstract fun bindDeleteUserUseCase(impl: DeleteUserUseCaseImpl): DeleteUserUseCase

    @Binds
    abstract fun bindGetAllUserMadeCocktails(impl: GetAllUserMadeCocktailsImpl): GetAllUserMadeCocktails

    @Binds
    abstract fun  bindLibraryScreenUseCase(impl: LibraryScreenUseCaseImpl): LibraryScreenUseCase

    @Binds
    abstract fun bindGetUserLikedCocktailsUseCase(impl: GetUserLikedCocktailsUseCaseImpl): GetUserLikedCocktailsUseCase

    @Binds
    abstract fun bindGetSingleCocktailUseCase(impl: GetSingleCocktailUseCaseImpl): GetSingleCocktailUseCase

    @Binds
    abstract fun bindGetSingleGeneratedCocktailUseCase(impl: GetSingleGeneratedCocktailUseCaseImpl): GetSingleGeneratedCocktailUseCase

    @Binds
    abstract fun bindAddFlavourToFavouriteUseCase(impl: AddFlavourToFavouritesUseCaseImpl): AddFlavoursToFavouritesUseCase

    @Binds
    abstract fun bindGetPopularCocktailsUseCase(impl: GetPopularCocktailsUseCaseImpl): GetPopularCocktailsUseCase
}