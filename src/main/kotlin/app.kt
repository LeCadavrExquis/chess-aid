
import declarations.*
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.css.*
import model.FilterSettings
import model.Game
import org.w3c.fetch.*
import react.*
import react.dom.*
import styled.styledDiv
import ui.*
import kotlin.js.Json
import kotlin.js.json

external interface AppState : RState {
    var userGames: List<Game>
    var position: PGN
    var filter: FilterSettings
}

class App : RComponent<RProps, AppState>(), ChessboardEventHandler {
    val game = ChessGame()
    override fun AppState.init() {
        userGames = emptyList()
        position = ""
        filter = FilterSettings(emptyList(), emptyList(), emptyList())
    }
    override fun RBuilder.render() {
        styledDiv {
            css.margin = "50px"
            Container {
                Row {
                    Col {
                        h1 { +"Chess Aid" }
                    }
                    Col {}
                    Col {
                        searchPlayerBar {
                            searchPlayer = { username ->
                                MainScope().launch {
                                    val fetchedGames = fetchGames(username)
                                    setState {
                                        userGames = fetchedGames
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        styledDiv {
            css.margin = "20px"
            Container {
                Row{
                    Col{
                        chessBoard {
                            eh = this@App
                        }
                    }
                    Col{
                        Container{
                            Row{
                                Col{
                                    filterPanel {
                                        attrs {
                                            updateFilter = this@App::updateFilter
                                        }
                                    }
                                }
                            }
                            Row {
                                Col {
                                    movesTable {
                                        currentPosition = state.position
                                        games = filterGames()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateFilter(category: String, values: List<String>) {
        val filterSettings = when(category) {
            "type" -> FilterSettings(state.filter.color, values, state.filter.winner)
            "color" -> FilterSettings(values, state.filter.type, state.filter.winner)
            "winner" -> FilterSettings(state.filter.color, state.filter.type, values)
            else -> null
        }
        setState {
            if (filterSettings != null) {
                filter = filterSettings
            }
        }
    }
    private fun filterGames(): List<Game> {
        //TODO: implement move filtering
        return state.userGames.filter {
            if(state.filter.type.isNotEmpty()) state.filter.type.contains(it.type) else true
        }.filter {
            if(state.filter.color.isNotEmpty()) state.filter.color.contains(it.color) else true
        }.filter {
            if(state.filter.winner.isNotEmpty()) state.filter.winner.contains(it.winner) else true
        }.filter { game ->
            if(state.position.isNotBlank()) game.moves.filter { it == ' ' }.contains(state.position.toMoveList().toString()) else true
        }
    }

    override fun onPieceDrop(source: String, target: String): String {
        val move = game.move(json("from" to source, "to" to target, "promotion" to "q"))
        return if(move != null) {
            setState {
                position = game.pgn(js("{}"))
            }
            "ok"
        } else {
            "snapback"
        }
    }

    override fun restartPosition() {
        setState {
            position = ""
        }
        game.reset()
    }
    override fun undo(): FEN{
        game.undo()
        setState {
            position = game.pgn(js("{}"))
        }
        return game.fen()
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