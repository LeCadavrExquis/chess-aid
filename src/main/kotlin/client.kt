import ui.searchPlayerBar
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.br
import react.dom.h1
import ui.movesTable

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
//            child(Welcome::class) {
//                attrs {
//                    name = "Kotlin/JS"
//                }
//            }
            app()
        }
    }
}
