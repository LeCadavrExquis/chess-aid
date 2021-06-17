package declarations

import kotlin.js.Json
import kotlin.js.json

@JsModule("chessboardjs")
@JsNonModule
@JsName("Chessboard")
//TODO: change declaration for consistency
external class Chessboard(divID: String, config: Json) {
    companion object {
        fun objToFen(obj: dynamic): String
    }
    fun start(): Nothing
    fun position(): Json
    fun position(fen: String): Nothing
    fun fen(): String
    fun resize()
}