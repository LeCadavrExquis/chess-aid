import react.RBuilder
import react.dom.br
import react.dom.h1
import ui.movesTable
import ui.searchPlayerBar

fun RBuilder.app() {
    h1 {
        +"Chess Aid"
    }
    searchPlayerBar()
    br {  }
    movesTable {
        currentPosition = ""
        games = emptyList()
    }
    child(ChessBoard::class) {
        attrs {
            game = "a4a5 gówno"
        }
    }
}