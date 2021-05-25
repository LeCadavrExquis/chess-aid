package ui

import EventsHandler
import declarations.Chessboard
import kotlinx.css.px
import kotlinx.css.width
import kotlinx.html.id
import react.*
import react.dom.img
import styled.styledDiv
import kotlin.js.json

external interface ChessBoardProps : RProps {
    var currentPosition: String
    var eh: EventsHandler
}

external interface ChessBoardState : RState {
    var board: Chessboard
//    var divLoaded: Boolean
}


class ChessBoard : RComponent<ChessBoardProps, ChessBoardState>() {
    override fun RBuilder.render() {
        styledDiv {
            attrs.id = "chessBoard"
            css.width = 400.px
        }
//        if (!state.divLoaded) img { attrs.src = "chess.png" }
    }

    override fun componentDidMount() {
        state.board = Chessboard("chessBoard", json(
            "draggable" to true,
            "position" to "start"
        ))
//        state.divLoaded = true
    }
}

fun RBuilder.chessBoard(handler: ChessBoardProps.() -> Unit) : ReactElement {
    return child(ChessBoard::class){
        this.attrs(handler)
    }
}