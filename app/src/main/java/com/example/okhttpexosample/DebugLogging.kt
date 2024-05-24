package com.example.okhttpexosample

import okhttp3.internal.concurrent.TaskRunner
import okhttp3.internal.http2.Http2
import okhttp3.internal.platform.android.AndroidLogHandler
import java.io.Closeable
import java.util.concurrent.CopyOnWriteArraySet
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.reflect.KClass

object OkHttpDebugLogging {
    // Keep references to loggers to prevent their configuration from being GC'd.
    private val configuredLoggers = CopyOnWriteArraySet<Logger>()

    fun enableHttp2() = enable(Http2::class)

    fun enableTaskRunner() = enable(TaskRunner::class)

    val logHandler = AndroidLogHandler

    fun enable(
        loggerClass: String,
        handler: Handler = logHandler,
    ): Closeable {
        val logger = Logger.getLogger(loggerClass)
        if (configuredLoggers.add(logger)) {
            logger.addHandler(handler)
            logger.level = Level.FINEST
        }
        return Closeable {
            logger.removeHandler(handler)
        }
    }

    fun enable(loggerClass: KClass<*>) = enable(loggerClass.java.name)
}
