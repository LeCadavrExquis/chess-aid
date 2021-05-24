package ui

import EventsHandler
import kotlinx.html.InputType
import kotlinx.html.RP
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.button
import react.dom.div
import react.dom.input

interface SearchPlayerBarProps : RProps {
    var eh: EventsHandler
}

data class SearchPlayerBarState(val name: String) : RState

class SearchPlayerBar(props: SearchPlayerBarProps) : RComponent<SearchPlayerBarProps, SearchPlayerBarState>() {

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
            attrs.onClickFunction = {
                console.log("searchBar state = ${state.name}")
                props.eh.getGames("LeCadavrExquis")
            }
            //
        }
    }
}

fun RBuilder.searchPlayerBar(handler: SearchPlayerBarProps.() -> Unit) : ReactElement {
    return child(SearchPlayerBar::class) {
        this.attrs(handler)
    }
}