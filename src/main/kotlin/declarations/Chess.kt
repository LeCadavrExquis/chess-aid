package declarations

import kotlin.js.Json

@JsModule("chess.js")
@JsNonModule
@JsName("Chess")
external class ChessGame {
    fun fen(): String
    fun move(move: Json): JSON?
    fun moves(): List<String>?
    fun load(fen: String): Nothing
    fun reset(): Unit
    fun pgn(options: JSON): String
    fun undo(): Unit
}