package com.ivyxjc.demos.netty.basic

import java.io.BufferedOutputStream
import java.net.Socket


fun main() {
    val br = byteArrayOf(0xda.toByte())
    println(br)
    println(byteArrayOf(0x1f.toByte()))
    Socket("127.0.0.1", 8124).use { socket ->
        BufferedOutputStream(socket.getOutputStream()).use { bos ->
            bos.write(br)
            bos.write("hello ".toByteArray())
            bos.write("world".repeat(10000).toByteArray())
            Thread.sleep(1000)
            bos.write(br)
        }
    }
}
