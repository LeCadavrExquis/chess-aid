import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h1
import react.dom.img

external interface ChessBoardProps : RProps {
    var game: String
}

class ChessBoard(props:  ChessBoardProps) : RComponent<ChessBoardProps, RState>(props) {
    override fun RBuilder.render() {
        img{
            attrs {
                src = "chess.png"
                width = "500"
                height = "600"
            }
        }
    }
}