package com.sebastijanzindl.galore.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.sebastijanzindl.galore.R

private object  Routes {

    const val SPLASH = "splash"
    /**
     * Auth Graph Routes
     */
    const val AUTH = "auth"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val WELECOME = "welcome"
    const val FORGOT_PASSWORD = "forgot-password"

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

    const val COCKTAIL_SECTION ="cocktail-section/{section-title}"

    const val COCKTAIL_DETAILS= "cocktail/{cocktail-id}"
    const val GENERATED_COCKTAIL_DETAILS ="generated-cocktail/{cocktail-id}"

    const val GENERATE_SELECT_FLAVOURS = "generate/flavours"
    const val GENERATE_SELECT_COCKTAILS = "generate/cocktails"
    const val GENERATE_WAITING = "generate/waiting"

    /**
     * Settings Graph Routes
     */
    const val SETTINGS = "settings"
    const val SETTINGS_OVERVIEW = "settings-overview"
    const val ACCOUNT_SETTINGS = "account-settings"
    const val PASSWORD_SECURITY = "password-and-security"
    const val NOTIFICATION_SETTINGS = "notification-settings"

    const val PRIVACY_POLICY = "privacy-policy"
    const val TERMS_AND_CONDITIONS = "terms-and-conditions"
}


sealed class BottomBarDesination (
    val route: String,
    val title:  String,
    @DrawableRes val selectedIconResource: Int,
    @DrawableRes val unselectedIconResource: Int,
    val navArguments: List<NamedNavArgument> = emptyList()
)

sealed class StackDestination (
    val route: String,
    val titleResourceID: Int,
    val navArguments: List<NamedNavArgument> = emptyList()
)

sealed class AppScreen(val route: String, val navArguments: List<NamedNavArgument> = emptyList()
) {
    object SplashScreen: AppScreen(Routes.SPLASH);
    object Auth: AppScreen(Routes.AUTH) {
        object Welcome: AppScreen(Routes.WELECOME)
        object Login : AppScreen(Routes.LOGIN)
        object Register : AppScreen(Routes.REGISTER)

        object ForgotPassword: AppScreen(Routes.FORGOT_PASSWORD)
    }
    object Main: AppScreen(Routes.MAIN) {
        object Home : BottomBarDesination(
            route = Routes.HOME,
            title = "Home",
            selectedIconResource = R.drawable.home_24,
            unselectedIconResource = R.drawable.home_filled
        )
        object Search : BottomBarDesination(
            route = Routes.SEARCH,
            title = "Search",
            selectedIconResource = R.drawable.search_24,
            unselectedIconResource = R.drawable.search_24,
        )
        object Generate : BottomBarDesination(
            route = Routes.GENERATE,
            title = "Generate",
            selectedIconResource = R.drawable.sparkles_filled,
            unselectedIconResource = R.drawable.sparkles,
        )

        object GenerateSelectFlavours : AppScreen(
            Routes.GENERATE_SELECT_FLAVOURS
        )

        object GenerateSelectCocktails : AppScreen (
            Routes.GENERATE_SELECT_COCKTAILS
        )

        object GenerateLoading : AppScreen (
            Routes.GENERATE_WAITING
        )

        object Library : BottomBarDesination(
            route = Routes.LIBRARY,
            title = "Library",
            selectedIconResource = R.drawable.book_filled,
            unselectedIconResource = R.drawable.book_24px
        )
        object CocktailDetails : AppScreen(Routes.COCKTAIL_DETAILS, navArguments = listOf(
            navArgument("cocktail-id") {
                type = NavType.Companion.StringType
            }
        ));

        object GeneratedCocktailDetails : AppScreen(
            Routes.GENERATED_COCKTAIL_DETAILS,
            navArguments = listOf(
                navArgument("cocktail-id") {
                    type = NavType.Companion.StringType
                }
            )
        )

        object CocktailSection: AppScreen(
            route = Routes.COCKTAIL_SECTION,
            navArguments = listOf(
                navArgument("section-title") {
                    type = NavType.Companion.StringType
                }
            )
        )
    }
    object Settings : AppScreen(Routes.SETTINGS) {

        object SettingsOverview: StackDestination(
            route = Routes.SETTINGS_OVERVIEW,
            titleResourceID = R.string.settings_overview_title
        )
        object AccountSettings : StackDestination(
            route = Routes.ACCOUNT_SETTINGS,
            titleResourceID = R.string.account_settings_title
        )

        object NotificationSettings: StackDestination(
            route = Routes.NOTIFICATION_SETTINGS,
            titleResourceID = R.string.notification_settings_title
        )

        object PasswordAndSecurity: StackDestination(
            route = Routes.PASSWORD_SECURITY,
            titleResourceID = R.string.password_and_security_title
        )
        object PrivacyPolicy: StackDestination(
            route = Routes.PRIVACY_POLICY,
            titleResourceID = R.string.privacy_policy_title
        )
        object TermsAndConditions: StackDestination(
            route = Routes.TERMS_AND_CONDITIONS,
            titleResourceID = R.string.terms_and_conditions_title
        )

    }

    object Onboarding : AppScreen(Routes.ONBOARDING) {
        object FeatureShowcase : AppScreen(Routes.FEATURE_SHOWCASE)
        object EnablePushNotifications : AppScreen(Routes.ENABLE_PUSH_NOTIFICATIONS)

        object SetupFavouriteFlavours : AppScreen(Routes.SETUP_FAVOURITE_FLAVOURS)

        object AllSet : AppScreen(Routes.ALL_SET)
    }
}