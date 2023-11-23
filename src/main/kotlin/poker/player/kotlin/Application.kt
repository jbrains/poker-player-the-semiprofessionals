package poker.player.kotlin

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.json.JSONObject
import java.lang.Exception

fun main(args: Array<String>) {
    val playerNew = PlayerNew()
    embeddedServer(Netty, getPort()) {
        routing {
            get("/") {
                call.respondText("Hello, world!", ContentType.Text.Html)
            }
            post {
                val formParameters = call.receiveParameters()
                val action = formParameters["action"].toString()

                val result = when (action) {
                    "bet_request" -> {
                        val gameState = formParameters["game_state"]

                        if (gameState == null) {
                            "Missing game_state!"
                        } else {
                            val mapper = ObjectMapper().registerKotlinModule()
                            playerNew.betRequest(mapper.readValue<Game>(gameState)).toString()
                        }
                    }

                    "showdown" -> {
                        playerNew.showdown()
                        "OK"
                    }

                    "version" -> playerNew.version()
                    else -> "Unknown action '$action'!"
                }

                call.respondText(result)
            }
        }
    }.start(wait = true)
}

private fun getPort(): Int {
    val port = System.getenv("PORT") ?: "8080"

    return Integer.parseInt(port)
}
