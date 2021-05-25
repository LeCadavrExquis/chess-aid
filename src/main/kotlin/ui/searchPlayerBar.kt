package ui

import EventsHandler
import kotlinx.html.InputType
import kotlinx.html.RP
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.button
import react.dom.defaultValue
import react.dom.div
import react.dom.input

external interface SearchPlayerBarProps : RProps {
    var eh: EventsHandler
}

external interface SearchPlayerBarState : RState {
    var username: String
}

class SearchPlayerBar : RComponent<SearchPlayerBarProps, SearchPlayerBarState>() {
    init {
        state.username = "type username"
    }
    override fun RBuilder.render() {
        div{
            input {
                attrs {
                    type = InputType.text
                    value = state.username
                    onChangeFunction = { event ->
                        val target = event.target as HTMLInputElement
                        setState {
                            username = target.value
                        }
                    }
                }
            }
            button {
                +"Search"
                attrs.onClickFunction = {
                    props.eh.getGames(state.username)
                }
            }
        }
    }
}

fun RBuilder.searchPlayerBar(handler: SearchPlayerBarProps.() -> Unit) : ReactElement {
    return child(SearchPlayerBar::class) {
        this.attrs(handler)
    }
}