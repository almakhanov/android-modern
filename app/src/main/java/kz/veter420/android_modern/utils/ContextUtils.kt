package kz.veter420.android_modern.utils

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Context.isPermissionGranted(name: String): Boolean {
    return ContextCompat.checkSelfPermission(
        this, name
    ) == PackageManager.PERMISSION_GRANTED
}

fun Activity.shouldShowRationale(name: String): Boolean {
    return shouldShowRequestPermissionRationale(name)
}

fun Context.hasPickMediaPermission(): Boolean {

    return when {
        // If Android Version is Greater than Android Pie!
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> true

        else -> isPermissionGranted(name = READ_EXTERNAL_STORAGE)
    }
}


fun Context.gotoApplicationSettings() {
    startActivity(Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.parse("package:${packageName}")
    })
}

fun Context.findActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> {
            baseContext.findActivity()
        }

        else -> null
    }
}

fun SnackbarHostState.showSnackBar(
    message: String? = null,
    action: String? = null,
    duration: SnackbarDuration = SnackbarDuration.Short,
    coroutineScope: CoroutineScope,
    onSnackBarAction: () -> Unit = {},
    onSnackBarDismiss: () -> Unit = {},
) {
    if (!message.isNullOrEmpty()) {
        coroutineScope.launch {
            when (showSnackbar(
                message = message,
                duration = duration,
                actionLabel = action,
                withDismissAction = duration == SnackbarDuration.Indefinite,
            )) {
                SnackbarResult.Dismissed -> onSnackBarDismiss.invoke()
                SnackbarResult.ActionPerformed -> onSnackBarAction.invoke()
            }
        }
    }
}