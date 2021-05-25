package ui

import EventsHandler
import kotlinx.browser.document
import kotlinx.css.px
import kotlinx.css.width
import kotlinx.html.id
import kotlinx.html.style
import react.*
import react.dom.img
import styled.styledDiv
import styled.styledImg
import kotlin.js.Json
import kotlin.js.json

external interface ChessBoardProps : RProps {
    var currentPosition: String
    var eh: EventsHandler
}

class ChessBoardState : RState


class ChessBoard : RComponent<ChessBoardProps, ChessBoardState>() {
    override fun RBuilder.render() {
        styledDiv {
            attrs.id = "chessBoard"
            css.width = 400.px

            styledImg {
                attrs.src = "chess.png"
                css.width = 400.px
            }
        }
    }
}

fun RBuilder.chessBoard(handler: ChessBoardProps.() -> Unit) : ReactElement {
    return child(ChessBoard::class){
        this.attrs(handler)
    }
}