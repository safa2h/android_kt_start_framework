package com.devsa.notify_app.framework

import android.util.Log

class Debug {


    companion object{

        enum class LOG_LEVEL(val  ord:Int){
            Verbose(1),
            Debug(2),
            Info(3),
            Warn(4),
            Error(5),


        }
        private var logTag="LOG"
        private var level= LOG_LEVEL.Verbose

        fun verbose(message:Any){
            log(LOG_LEVEL.Verbose,message)
        }


        fun debug(message:Any){
            log(LOG_LEVEL.Debug,message)
        }

        fun error(message:Any){
            log(LOG_LEVEL.Error,message)
        }

        fun info(message:Any){
            log(LOG_LEVEL.Info,message)
        }
        fun warn(message:Any){
            log(LOG_LEVEL.Warn,message)
        }

        fun setLogLevel(levelLog: LOG_LEVEL){
            level =levelLog
        }

        fun  setLOgTag(tag:String){
            logTag =tag
        }

        private fun  log(level: LOG_LEVEL, message: Any){
            if( level.ord < Companion.level.ord){
                return
            }
            when(level){
                LOG_LEVEL.Verbose -> Log.v(logTag, ""+ message)
                LOG_LEVEL.Debug -> Log.d(logTag, ""+ message)
                LOG_LEVEL.Info -> Log.i(logTag, ""+ message)
                LOG_LEVEL.Warn -> Log.w(logTag, ""+ message)
                LOG_LEVEL.Error -> Log.e(logTag, ""+ message)
            }
        }
    }

}