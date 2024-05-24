package com.example.okhttpexosample

import okhttp3.Call
import okhttp3.EventListener
import okhttp3.Handshake
import okhttp3.Protocol
import okhttp3.Response
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.logging.Logger

object LoggingEventListener : EventListener() {
    override fun connectEnd(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy,
        protocol: Protocol?
    ) {
        logger.info("$protocol")
    }

    override fun secureConnectEnd(call: Call, handshake: Handshake?) {
        logger.info("$handshake")
    }

    override fun responseHeadersEnd(call: Call, response: Response) {
        logger.info("${response.headers}")
    }

    val logger: Logger = Logger.getLogger(LoggingEventListener::class.java.name)
}