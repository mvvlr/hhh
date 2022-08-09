package com.example.newsapp

import android.content.Context

class Prefs(context : Context) {

    private val preference = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun saveState() {
        preference.edit().putBoolean("isShow", true).apply()
    }

    fun isShow(): Boolean {
        return preference.getBoolean("isShow", false)
    }

    fun saveNames(toString: String) {
        preference.edit().putString("name", null).apply()
    }

    fun getName(): String? {
        return preference.getString("name", null)
    }

    fun saveImageViev(image: String?) {
        preference.edit().putString("image", image).apply()
    }
    fun isImageView(): String? {
        return preference.getString("image", "")
    }
}
