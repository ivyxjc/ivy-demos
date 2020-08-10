package com.ivyxjc.demos.akka.helloworld

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.UntypedAbstractActor

class ObjectActor : UntypedAbstractActor() {

    override fun preStart() {
        val greeter = context.actorOf(Props.create(ObjectGreeterActor::class.java), "greeter")
        println("Greeter actor path: ${greeter.path()}")
        greeter.tell(Message(2, mutableListOf("2", "abc")), self)
    }


    override fun onReceive(message: Any?) {
        println("ObjectActor receive: $message")
    }
}

class ObjectGreeterActor : UntypedAbstractActor() {

    override fun onReceive(message: Any?) {
        println("Greeter receive $message")
        sender.tell("Greeter done", self)
    }
}

data class Message(val age: Int, val list: List<String>)


fun main() {
    val system = ActorSystem.create("Hello")
    val ar = system.actorOf(Props.create(ObjectActor::class.java), "helloWorld")
    println(ar.path())

}

