package com.ivyxjc.demos.netty.handler

import com.ivyxjc.demos.common.loggerFor
import com.ivyxjc.demos.netty.byteBufToByteArray
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler


class PureLogByteBufHandler : SimpleChannelInboundHandler<ByteBuf>() {
    companion object {
        private val log = loggerFor(PureLogByteBufHandler::class.java)
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: ByteBuf?) {
        log.info("msg: {}", String(byteBufToByteArray(msg!!)))
    }
}