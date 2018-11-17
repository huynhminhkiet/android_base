package com.bigcake.android_base.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject

const val KEY_DATA_LOADED = "KEY_DATA_LOADED"

class SharedPreferencesSourceImpl @Inject constructor(
  private val sharedPreferences: SharedPreferences
) : SharedPreferencesSource {

  override fun setDataLoaded() {
    sharedPreferences.edit().putBoolean(KEY_DATA_LOADED, true).apply()
  }
}