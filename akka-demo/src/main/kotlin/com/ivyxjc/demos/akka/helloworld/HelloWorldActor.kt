package com.ivyxjc.demos.akka.helloworld

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.UntypedAbstractActor

class HelloWorldActor : UntypedAbstractActor() {

    override fun preStart() {
        val greeter = context.actorOf(Props.create(GreeterActor::class.java), "greeter")
        greeter.tell(GreeterActor.Msg.GREET, self)
    }

    override fun onReceive(message: Any?) {
        if (message == GreeterActor.Msg.DONE) {
            context.stop(self)
        } else {
            unhandled(message)
        }
    }
}

class GreeterActor : UntypedAbstractActor() {

    enum class Msg {
        GREET, DONE
    }

    override fun onReceive(message: Any?) {
        if (message == GreeterActor.Msg.GREET) {
            println("Hello World")
            Thread.sleep(1000)
            println("sender path ${sender.path()}")
            sender.tell(Msg.DONE, self)
        } else {
            unhandled(message)
        }
    }
}

fun main() {
    val actorSystem = ActorSystem.create("Hello")
    val ar = actorSystem.actorOf(Props.create(HelloWorldActor::class.java), "helloWorld")
//    println(ar.path())
}