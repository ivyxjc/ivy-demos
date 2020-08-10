package com.ivyxjc.demos.akka.inbox

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.UntypedAbstractActor
import akka.event.Logging

class InboxActor : UntypedAbstractActor() {
    private val log = Logging.getLogger(context.system, this)

    enum class Msg {
        WORKING, DONE, CLOSE;
    }


    override fun onReceive(message: Any?) {
        when (message) {
            Msg.WORKING -> {
                log.info("i am working.");
            }
            Msg.DONE -> {
                log.info("i am done");
            }
            Msg.CLOSE -> {
                log.info("i am close.")
                sender.tell(Msg.CLOSE, self)
                context.stop(self)
            }
            else -> {
                unhandled(message)
            }
        }
    }
}

fun main() {
    val system = ActorSystem.create("inbox")
    val inboxActor = system.actorOf(Props.create(InboxActor::class.java), "inboxActor")
}


