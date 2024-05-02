package com.sebastijanzindl.galore.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.sebastijanzindl.galore.R

private object  Routes {
    /**
     * Auth Graph Routes
     */
    const val AUTH = "auth"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val WELECOME = "welcome"

    /**
     * Onboarding Graph Routes
     */
    const val ONBOARDING = "onboarding"
    const val FEATURE_SHOWCASE = "feature-showcase"
    const val ENABLE_PUSH_NOTIFICATIONS = "enable-push-notifications"
    const val SETUP_FAVOURITE_FLAVOURS = "favourite-flavours-setup"
    const val ALL_SET = "all-set"

    /**
     * Main Graph Routes
     */
    const val MAIN = "main"
    const val HOME = "home"
    const val SEARCH = "search"
    const val GENERATE = "generate"
    const val LIBRARY = "library"

    const val  COCKTAIL_DETAILS= "cocktailDetail/${ArgParams.COCKTAIL_ID}"

    /**
     * Settings Graph Routes
     */

    const val SETTINGS_OVERVIEW = "settings-overview"
    const val ACCOUNT_SETTINGS = "account-settings"
    const val PASSWORD_SECURITY = "password-and-security"
    const val NOTIFICATION_SETTINGS = "notification-settings"
}

private object ArgParams {
    const val COCKTAIL_ID ="cocktail_id"
    fun toPath(param: String) = "{${param}}"
}

sealed class TopLevelDestination (
    val route: String,
    val title:  String,
    @DrawableRes val selectedIconResource: Int,
    @DrawableRes val unselectedIconResource: Int,
    val navArguments: List<NamedNavArgument> = emptyList()
)

sealed class AppScreen(val route: String, val navArguments: List<NamedNavArgument> = emptyList()
) {
    object Auth: AppScreen(Routes.AUTH) {
        object Welcome: AppScreen(Routes.WELECOME)
        object Login : AppScreen(Routes.LOGIN)
        object Register : AppScreen(Routes.REGISTER)
    }
    object Main: AppScreen(Routes.MAIN) {
        object Home : TopLevelDestination(
            route = Routes.HOME,
            title = "Home",
            selectedIconResource = R.drawable.home_24,
            unselectedIconResource = R.drawable.home_filled
        )
        object Search : TopLevelDestination(
            route = Routes.SEARCH,
            title = "Search",
            selectedIconResource = R.drawable.search_24,
            unselectedIconResource = R.drawable.search_24,
        )
        object Generate : TopLevelDestination(
            route = Routes.GENERATE,
            title = "Generate",
            selectedIconResource = R.drawable.sparkles_filled,
            unselectedIconResource = R.drawable.sparkles,
        )
        object Library : TopLevelDestination(
            route = Routes.LIBRARY,
            title = "Library",
            selectedIconResource = R.drawable.book_filled,
            unselectedIconResource = R.drawable.book_24px
        )
        object CocktailDetails : AppScreen(Routes.COCKTAIL_DETAILS, navArguments = listOf(
            navArgument(ArgParams.COCKTAIL_ID) {
                type = NavType.Companion.StringType
            }
        ));
    }

    object Settings : AppScreen(Routes.SETTINGS_OVERVIEW) {
        object AccountSettings : AppScreen(Routes.ACCOUNT_SETTINGS)

        object NotificationSettings: AppScreen(Routes.NOTIFICATION_SETTINGS)

        object PasswordAndSecurity: AppScreen(Routes.PASSWORD_SECURITY)
    }

    object Onboarding : AppScreen(Routes.ONBOARDING) {
        object FeatureShowcase : AppScreen(Routes.FEATURE_SHOWCASE)
        object EnablePushNotifications : AppScreen(Routes.ENABLE_PUSH_NOTIFICATIONS)

        object SetupFavouriteFlavours : AppScreen(Routes.SETUP_FAVOURITE_FLAVOURS)

        object AllSet : AppScreen(Routes.ALL_SET)
    }
}