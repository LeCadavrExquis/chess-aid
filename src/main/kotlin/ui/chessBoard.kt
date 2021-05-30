package ui

import declarations.Button
import declarations.ChessGame
import declarations.Chessboard
import kotlinx.css.px
import kotlinx.css.width
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import react.dom.div
import styled.styledDiv
import kotlin.js.json

external interface ChessBoardProps : RProps {
    var moves: List<String>
    var eh: ChessboardEventHandler
}

external interface ChessBoardState : RState {
    var board: Chessboard
}


class ChessBoard : RComponent<ChessBoardProps, ChessBoardState>() {
    companion object {
        val game = ChessGame()
    }
    override fun RBuilder.render() {
        div {
            styledDiv {
                attrs.id = "chessBoard"
                css.width = 500.px
            }
            Button {
                +"Restart"
                attrs.variant = "dark"
                attrs.onClick = {
                    props.eh.restartPosition()
                    state.board.start()
                }
            }
            Button {
                +"Undo [todo]"
                attrs.variant = "warning"
                //TODO: implement undo button
            }
        }
    }

    override fun componentDidMount() {
        state.board = Chessboard("chessBoard", json(
            "draggable" to true,
            "position" to "start",
            "onDrop" to { source: String, target: String, piece: String,
                             newPos: dynamic, oldPos: dynamic, orientation: String->
                //TODO: send move with piece info PNG alike
                console.log("changed")
                console.log(Chessboard.objToFen(newPos))
                props.eh.movePlayed(target)
            }
        ))
    }
}

interface ChessboardEventHandler {
    fun movePlayed(move: String)
    fun restartPosition()
}

fun RBuilder.chessBoard(handler: ChessBoardProps.() -> Unit) : ReactElement {
    return child(ChessBoard::class){
        this.attrs(handler)
    }
}