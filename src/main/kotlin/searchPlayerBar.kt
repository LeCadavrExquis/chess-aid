import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.div
import react.dom.input
import styled.css
import styled.styledDiv
import styled.styledInput

data class SearchPlayerBarState(val name: String) : RState

@JsExport
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
        }
    }
}