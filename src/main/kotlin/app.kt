
import declarations.Col
import declarations.Container
import declarations.Row
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.css.*
import model.GameModel
import org.w3c.fetch.*
import react.*
import react.dom.*
import styled.css
import styled.styledBody
import styled.styledDiv
import styled.styledH1
import ui.*
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
        styledDiv {
            css.margin = "50px"
            Container {
//            css {
//                display = Display.flex
//                flexDirection = FlexDirection.row

//            }
                Row {
                    Col {
                        h1 {

                            +"Chess Aid"
                        }
                    }
                    Col {}
                    Col {
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
                            moves = state.moves
                            eh = object: ChessboardEventHandler {
                                override fun movePlayed(move: String) {
                                    setState {
                                        moves = moves + move
                                    }
                                }

                                override fun restartPosition() {
                                    setState {
                                        moves = emptyList()
                                    }
                                }
                            }
                        }
                    }
                    Col{
                        Container{
                            Row{
                                Col{
                                    filterPanel()
                                }
                            }
                            Row {
                                Col {
                                    movesTable {
                                        currentPosition = state.moves
                                        games = state.games
                                    }
                                }
                            }
                        }
                    }
                }
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