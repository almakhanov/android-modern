package kz.veter420.android_modern.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson


class PrefManager(context: Context) {

    companion object {
        const val PREF_NAME = "my_pref"
    }

    private val gson = Gson()
    private var sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

}
