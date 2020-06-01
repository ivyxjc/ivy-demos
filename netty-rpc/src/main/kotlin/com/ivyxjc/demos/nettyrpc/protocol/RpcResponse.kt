package com.ivyxjc.demos.nettyrpc.protocol

class RpcResponse {
    lateinit var requestId: String
    var error: String? = null
    var result: Any? = null
}