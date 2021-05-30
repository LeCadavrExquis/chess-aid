package ui

import declarations.Button
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
    var searchPlayer: (String) -> Unit
}

external interface SearchPlayerBarState : RState {
    var username: String
}

class SearchPlayerBar : RComponent<SearchPlayerBarProps, SearchPlayerBarState>() {
    override fun SearchPlayerBarState.init() {
        username = "type username"
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
            Button {
                //TODO: loading button
                //NTH: click on press ENTER while input is active
                +"Search"
                attrs.variant = "primary"
                attrs.onClick = {
                    props.searchPlayer(state.username)
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
