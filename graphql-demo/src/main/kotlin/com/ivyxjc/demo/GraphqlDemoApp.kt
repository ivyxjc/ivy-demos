package com.ivyxjc.demo


import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import java.net.InetAddress
import java.net.UnknownHostException

@SpringBootApplication
class GraphqlDemoApp(private val env: Environment) {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        /**
         * Main method, used to run the application.
         *
         * @param args the command line arguments.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val env = runApplication<GraphqlDemoApp>(*args) { }.environment
            logApplicationStartup(env)
        }

        @JvmStatic
        private fun logApplicationStartup(env: Environment) {
            val log = LoggerFactory.getLogger(GraphqlDemoApp::class.java)

            val protocol =
                if (env.getProperty("server.ssl.key-store") != null) {
                    "https"
                } else "http"
            val serverPort = env.getProperty("server.port")
            val contextPath =
                env.getProperty("server.servlet.context-path") ?: "/"
            var hostAddress = "localhost"
            try {
                hostAddress = InetAddress.getLocalHost().hostAddress
            } catch (e: UnknownHostException) {
                log.warn("The host name could not be determined, using `localhost` as fallback")
            }
            log.info(
                """

                ----------------------------------------------------------
                Application '${env.getProperty("spring.application.name")}' is running! Access URLs:
                Local:      $protocol://localhost:$serverPort$contextPath
                External:   $protocol://$hostAddress:$serverPort$contextPath
                Profile(s): ${env.activeProfiles.joinToString(",")}
                ----------------------------------------------------------
                """.trimIndent()
            )
        }
    }
}
