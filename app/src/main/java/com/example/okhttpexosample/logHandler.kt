@file:OptIn(ExperimentalOkHttpApi::class)

package com.example.okhttpexosample

import okhttp3.Call
import okhttp3.Connection
import okhttp3.ConnectionListener
import okhttp3.ExperimentalOkHttpApi
import okhttp3.internal.http2.FlowControlListener
import okhttp3.internal.http2.flowcontrol.WindowCounter
import java.util.logging.Logger

class Http2FlowControlConnectionListener : ConnectionListener(), FlowControlListener {
  override fun receivingStreamWindowChanged(
    streamId: Int,
    windowCounter: WindowCounter,
    bufferSize: Long,
  ) {
    // unacked is the consumed bytes (read by the app), but not yet acknowledged to the server
//    logger.info("$streamId,${windowCounter.unacknowledged},$bufferSize")
  }

  override fun receivingConnectionWindowChanged(windowCounter: WindowCounter) {
//    logger.info("Connection,${windowCounter.unacknowledged},")
  }

  override fun connectionAcquired(connection: Connection, call: Call) {
//    logger.info("connectionAcquired")
  }

  override fun connectionReleased(connection: Connection, call: Call) {
//    logger.info("connectionReleased")
  }

  companion object {
    val logger: Logger = Logger.getLogger(Http2FlowControlConnectionListener::class.java.name)
  }
}