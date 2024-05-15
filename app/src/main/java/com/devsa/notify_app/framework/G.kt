package com.devsa.notify_app.framework

import android.app.Application
import android.content.Context
import com.devsa.notify_app.framework.lang.FaLang
import com.devsa.notify_app.framework.lang.Lang


val l = G.language


class G : Application() {


    companion object {
        lateinit var currentActivity: XActivity
        lateinit var language: Lang
        lateinit var context: Context

    }

    private fun config() {
        Debug.setLOgTag("TAG")
        Debug.setLogLevel(Debug.Companion.LOG_LEVEL.Verbose)
        language = FaLang()
    }

    override fun onCreate() {
        super.onCreate()
        config()

    }


}