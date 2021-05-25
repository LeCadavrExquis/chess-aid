package declarations

import kotlin.js.Json
import kotlin.js.json

@JsModule("chessboardjs")
@JsNonModule
@JsName("Chessboard")
external class Chessboard(divID: String, config: Json)