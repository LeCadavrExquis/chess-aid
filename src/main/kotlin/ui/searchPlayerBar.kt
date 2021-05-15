package ui

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.div
import react.dom.input

data class SearchPlayerBarState(val name: String) : RState

class SearchPlayerBar : RComponent<RProps, SearchPlayerBarState>() {

    init {
        state = SearchPlayerBarState("type user name")
    }

    override fun RBuilder.render() {
        div{
            input {
                attrs {
                    type = InputType.text
                    value = state.name
                    onChangeFunction = { event ->
                        setState(
                            SearchPlayerBarState(name = (event.target as HTMLInputElement).value)
                        )
                    }
                }
            }
        }
        button {
            +"Search"
            // DEBUG
            attrs.onClickFunction = { console.log("searchBar state = ${state.name}") }
            //
        }
    }
}

fun RBuilder.searchPlayerBar() = child(SearchPlayerBar::class) { }