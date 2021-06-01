package declarations

@JsModule("chess.js")
@JsNonModule

@JsName("Chess")
//TODO: change declaration for consistency
external class ChessGame {
    fun fen(): String = definedExternally
    fun move(move: String): Nothing = definedExternally
    fun moves(): List<String>? = definedExternally
    fun load(fen: String): Nothing = definedExternally
    fun reset(): Nothing = definedExternally
}