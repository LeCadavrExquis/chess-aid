import ui.searchPlayerBar
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            app()
        }
    }
}
