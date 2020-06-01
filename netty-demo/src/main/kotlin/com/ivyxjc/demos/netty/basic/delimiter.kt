package com.ivyxjc.demos.netty.basic

import com.ivyxjc.demos.netty.handler.PureLogByteBufHandler
import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder


fun main() {
    val bootstrap = ServerBootstrap()
    val boss = NioEventLoopGroup(1)
    val worker = NioEventLoopGroup()
    try {
        bootstrap.group(boss, worker)

        bootstrap.channel(NioServerSocketChannel::class.java)
                .childHandler(DelimiterInitializer())
        val channelFuture = bootstrap.bind(8124).sync()
        channelFuture.channel().closeFuture().sync()
    } finally {
        boss.shutdownGracefully()
        worker.shutdownGracefully()
    }
}

class DelimiterInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel?) {
        val pipeline = ch!!.pipeline()
        val magic = Unpooled.wrappedBuffer(byteArrayOf(0xda.toByte()))
        pipeline.addLast(DelimiterBasedFrameDecoder(1024 * 1024, magic))
        pipeline.addLast(PureLogByteBufHandler())
    }
}


