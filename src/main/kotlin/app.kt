import declarations.ChessGame
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.css.Display
import kotlinx.css.display
import model.GameModel
import org.w3c.fetch.*
import react.*
import react.dom.h1
import react.dom.p
import styled.styledDiv
import ui.ChessboardEventHandler
import ui.chessBoard
import ui.movesTable
import ui.searchPlayerBar
import kotlin.js.Json
import kotlin.js.json

external interface AppState : RState {
    var games: List<GameModel>
    var moves: List<String>
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        games = emptyList()
        moves = emptyList()
    }
    override fun RBuilder.render() {
        //TODO: manage proper display
        h1 {
            +"Chess Aid"
        }
        searchPlayerBar {
            searchPlayer = { username ->
                MainScope().launch {
                    val fetchedGames = fetchGames(username)
                    println("fetched!")
                    setState {
                        games = fetchedGames
                    }
                }
            }
        }
        styledDiv {
            css.display = Display.flex
            chessBoard {
                moves = state.moves
                eh = object: ChessboardEventHandler {
                    override fun movePlayed(move: String) {
                        setState {
                            moves = moves + move
                            println(move)
                        }
                    }

                    override fun restartPosition() {
                        setState {
                            moves = emptyList()
                            println(moves)
                        }
                    }
                }
            }
            movesTable {
                currentPosition = state.moves
                games = state.games
            }
        }
    }
}
suspend fun fetchGames(username: String) : List<GameModel> {
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
            GameModel(
                id = it["id"] as String,
                type = it["speed"] as String,
                moves = it["moves"] as String,
                winner = if(it["winner"] != undefined) it["winner"] as String else "draw",
                color = if ((((it["players"] as Json)["white"] as Json)["user"] as Json)["name"] == username) "white" else "black"
            )}
}
fun RBuilder.app() =  child(App::class) { }