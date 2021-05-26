package declarations

import kotlin.js.Json
import kotlin.js.json

@JsModule("chessboardjs")
@JsNonModule
@JsName("Chessboard")
external class Chessboard(divID: String, config: Json) {
    companion object {
        fun objToFen(obj: dynamic): String = definedExternally
    }
    fun start(): Nothing = definedExternally
    fun position(): Json = definedExternally
    fun position(fen: String): Nothing = definedExternally
    fun fen(): String = definedExternally
}