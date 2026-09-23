package com.example.hearhere.repository.local

import android.content.Context
import android.content.SharedPreferences

class CachHelper private constructor(context: Context) {

    private val pfs: SharedPreferences = context.getSharedPreferences("learning",
        Context.MODE_PRIVATE)
    private val edit: SharedPreferences.Editor = pfs.edit()

    companion object {
        @Volatile
        private var INSTANCE: CachHelper? = null

        fun getInstance(context: Context): CachHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: CachHelper(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    fun saveToken(token: String) {
        edit.putString("token", token)
        edit.apply()
    }

    fun getToken(): String? {
        return pfs.getString("token", "not found")
    }
}