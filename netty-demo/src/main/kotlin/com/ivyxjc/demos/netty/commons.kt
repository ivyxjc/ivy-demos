package com.ivyxjc.demos.netty

import io.netty.buffer.ByteBuf

fun byteBufToByteArray(buf: ByteBuf): ByteArray {
    val bytes = ByteArray(buf.readableBytes())
    val readerIndex = buf.readerIndex();
    buf.getBytes(readerIndex, bytes)
    return bytes
}

