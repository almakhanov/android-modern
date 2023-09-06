package kz.veter420.android_modern.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import kz.veter420.android_modern.utils.getCurrentYear


class PrefManager(context: Context) {

    companion object {
        const val PREF_NAME = "zhasa_pref"
    }

    private val gson = Gson()
    private var sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    var phoneNumber: String?
        set(value) = sharedPreferences.edit().putString("phone", value).apply()
        get() = sharedPreferences.getString("phone", null)

    var token: String?
        set(value) = sharedPreferences.edit().putString("token", value).apply()
        get() = sharedPreferences.getString("token", null)

    var year: Int
        set(value) = sharedPreferences.edit().putInt("year", value).apply()
        get() = sharedPreferences.getInt("year", getCurrentYear())



    fun clear() {
        token = null
        year = getCurrentYear()
    }
}
