package com.ivyxjc.demos.nettyrpc.protocol

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler


//class RpcDecoder : ByteToMessageDecoder() {
//    override fun decode(ctx: ChannelHandlerContext?, input: ByteBuf?, out: MutableList<Any>?) {
//        if (input!!.readableBytes() < 4) {
//            return
//        }
//        input.markReaderIndex()
//        TODO("Not yet implemented")
//    }
//}
class RpcDecoder : SimpleChannelInboundHandler<ByteBuf>() {

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: ByteBuf?) {

    }
}
