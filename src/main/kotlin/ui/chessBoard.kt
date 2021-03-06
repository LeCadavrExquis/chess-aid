package ui

import declarations.Button
import declarations.Chessboard
import kotlinx.css.px
import kotlinx.css.width
import kotlinx.html.id
import react.*
import react.dom.div
import styled.styledDiv
import kotlin.js.json

external interface ChessBoardProps : RProps {
    var eh: ChessboardEventHandler
}

external interface ChessBoardState : RState {
    var board: Chessboard
}


class ChessBoard : RComponent<ChessBoardProps, ChessBoardState>() {
    override fun RBuilder.render() {
        div {
            styledDiv {
                attrs.id = "chessBoard"
                css.width = 450.px
            }
            Button {
                +"Restart"
                attrs.variant = "dark"
                attrs.onClick =  {
                    props.eh.restartPosition()
                    state.board.start()
                }
            }
            Button {
                +"Undo"
                attrs.variant = "dark"
                attrs.onClick = {
                    state.board.position(props.eh.undo())
                }
            }
        }
    }

    override fun componentDidMount() {
        state.board = Chessboard("chessBoard", json(
            "draggable" to true,
            "position" to "start",
            "onDrop" to { source: String, target: String, _: String, _: dynamic, _: dynamic, _: String ->
                props.eh.onPieceDrop(source, target)
            }))
    }
}

interface ChessboardEventHandler {
    fun onPieceDrop(source: String, target: String): String
    fun restartPosition()
    fun undo() : FEN
}

fun RBuilder.chessBoard(handler: ChessBoardProps.() -> Unit) : ReactElement {
    return child(ChessBoard::class){
        this.attrs(handler)
    }
}
typealias FEN = String