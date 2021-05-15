import ui.searchPlayerBar
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.br
import react.dom.h1

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
//            child(Welcome::class) {
//                attrs {
//                    name = "Kotlin/JS"
//                }
//            }
            h1 {
                +"Chess Aid"
            }
            searchPlayerBar()
            br {  }
            child(ChessBoard::class) {
                attrs {
                    game = "a4a5 gówno"
                }
            }
        }
    }
}
