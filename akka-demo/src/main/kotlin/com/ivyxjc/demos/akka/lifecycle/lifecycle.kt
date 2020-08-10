package com.ivyxjc.demos.akka.lifecycle

import akka.actor.*
import akka.event.Logging


enum class Msg {
    WORKING, DONE, CLOSE
}

class WorkActor : UntypedAbstractActor() {
    private val log = Logging.getLogger(
        context.system, this
    )

    override fun onReceive(msg: Any?) {
        when (msg) {
            Msg.WORKING -> {
                log.info("i am  working")
            }
            Msg.DONE -> {
                log.info("stop  working")
            }
            Msg.CLOSE -> {
                log.info("stop  close")
                sender.tell(Msg.CLOSE, self)
                context.stop(self)
            }
            else -> {
                unhandled(msg)
            }
        }
    }

    override fun preStart() {
        super.preStart()
        log.info("work preStart()")
    }

    override fun postStop() {
        super.postStop()
        log.info("work postStop()")
    }
}

class WatchActor(actorRef: ActorRef) : UntypedAbstractActor() {

    private val log = Logging.getLogger(
        context.system, this
    )

    init {
        context.watch(actorRef)
    }

    override fun onReceive(message: Any?) {
        if (message is Terminated) {
            log.error("${message.actor.path()} has terminated. now shutdown the system")

        } else {
            unhandled(message)
        }
    }
}


fun main() {

    val system = ActorSystem.create("Hello")
    val myWork = system.actorOf(Props.create(WorkActor::class.java), "MyWork")
    system.actorOf(Props.create(WatchActor::class.java, myWork), "WatchActor")

    myWork.tell(Msg.WORKING, ActorRef.noSender())
    myWork.tell(Msg.DONE, ActorRef.noSender())
    myWork.tell(PoisonPill.getInstance(), ActorRef.noSender())
}