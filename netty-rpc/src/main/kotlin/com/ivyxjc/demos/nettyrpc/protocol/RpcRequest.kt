package com.ivyxjc.demos.nettyrpc.protocol

class RpcRequest {
    lateinit var requestId: String
    lateinit var className: String
    lateinit var methodName: String
    lateinit var parameterTypes: Array<Class<Any>>
    lateinit var parameters: Array<Any?>
}