import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import model.Game
import org.w3c.fetch.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import react.dom.h1
import ui.movesTable
import ui.searchPlayerBar
import kotlin.js.Json
import kotlin.js.json

interface EventsHandler {
    fun getGames(username: String)
}

external interface AppState : RState {
    var games: List<Game>
}

class App : RComponent<RProps, AppState>(), EventsHandler {
    init {
        state.games = emptyList()
    }
    override fun RBuilder.render() {
        h1 {
            +"Chess Aid"
        }
        searchPlayerBar {
            eh = this@App
        }
        br {  }
        movesTable {
            currentPosition = ""
            games = state.games
        }
        child(ChessBoard::class) {
            attrs {
                game = "a4a5 gówno"
            }
        }
    }

    override fun getGames(username: String) {
        MainScope().launch {
            val fetchedGames = fetchGames(username)
            setState(state) {
                state.games = fetchedGames
            }
        }
    }
}
suspend fun fetchGames(username: String) : List<Game> {
    val request = object : RequestInit {
        override var method: String? = "GET"
        override var headers = json("Accept" to "application/x-ndjson")
    }
    val responsePromise = window.fetch("https://lichess.org/api/games/user/$username", request)
    val response = responsePromise.await()
    if (!response.ok) {
        console.log("API request failed for $username")
    }
    val games: String = response.text().await()

    return games.split("}\n")
        .map { "$it}" }
        .dropLast(1)
        .map { JSON.parse<Json>(it) }
        .map {
            Game(
                id = it["id"] as String,
                type = it["speed"] as String,
                moves = it["moves"] as String,
                winner = if(it["winner"] != undefined) it["winner"] as String else "draw",
                color = if ((((it["players"] as Json)["white"] as Json)["user"] as Json)["name"] == username) "white" else "black"
            )}
}
fun RBuilder.app() =  child(App::class) { }