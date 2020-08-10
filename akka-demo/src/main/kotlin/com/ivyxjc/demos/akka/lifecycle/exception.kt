package com.ivyxjc.demos.akka.lifecycle

import akka.actor.*
import scala.concurrent.duration.Duration
import java.util.*
import java.util.concurrent.TimeUnit


class RestartActor : UntypedAbstractActor() {
    enum class Msg {
        DONE, RESTART
    }


    override fun preStart() {
        super.preStart()
        println("preStart    hashCode=" + this.hashCode())
    }


    @Throws(Exception::class)
    override fun postStop() {
        super.postStop()
        println("postStop    hashCode=" + this.hashCode())
    }

    override fun preRestart(reason: Throwable?, message: Optional<Any>?) {
        super.preRestart(reason, message)
        println("preRestart    hashCode=" + this.hashCode())
    }


    override fun postRestart(reason: Throwable?) {
        super.postRestart(reason)
        println("postRestart    hashCode=" + this.hashCode())
    }

    override fun onReceive(o: Any) {
        when {
            o === Msg.DONE -> {
                context.stop(self)
            }
            o === Msg.RESTART -> {
                val abc = null
                abc!!
//                1/0
            }
            else -> {
                unhandled(o)
            }
        }
    }
}


class SupervisorActor : UntypedAbstractActor() {
    override fun supervisorStrategy(): SupervisorStrategy {
        return OneForOneStrategy(3, Duration.create(1, TimeUnit.MINUTES)) {
            if (it is ArithmeticException) {
                println("meet ArithmeticException, just resume")
                return@OneForOneStrategy SupervisorStrategy.resume()
            } else if (it is NullPointerException) {
                println("meet NPE, restart")
                return@OneForOneStrategy SupervisorStrategy.restart()
            } else if (it is IllegalArgumentException) {
                println("meet Illegal Argument, stop")
                return@OneForOneStrategy SupervisorStrategy.stop()
            } else {
                println("escalate")
                return@OneForOneStrategy SupervisorStrategy.escalate()
            }

        }


    }

    override fun onReceive(message: Any?) {
        if (message is Props) {
            context.actorOf(message, "restartActor")
        } else {
            unhandled(message)
        }
    }
}

fun main() {

    val system = ActorSystem.create("exception")
    val supervisorActor = system.actorOf(Props.create(SupervisorActor::class.java), "SuperVisor")
    supervisorActor.tell(Props.create(RestartActor::class.java), ActorRef.noSender())

    val actorSelection =
        system.actorSelection("akka://exception/user/SuperVisor/restartActor")


    for (i in 0..99) {
        actorSelection.tell(RestartActor.Msg.RESTART, ActorRef.noSender())
    }
}