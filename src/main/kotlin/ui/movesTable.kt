package ui

import react.*
import react.dom.*
import reactstrap.Table
import styled.css
import styled.styledTable

external interface MovesTableProps : RProps {
    var currentPosition: String
    var games: List<String>
}

class MovesTable : RComponent<MovesTableProps, RState>() {

    override fun RBuilder.render() {
        p { +"Current move :${props.currentPosition}" }
            Table {
            attrs {
                args = "striped bordered hover"
            }

            thead {
                tr {
                    th { +"move" }
                    th { +"played" }
                    th { +"percentage" }
                }
            }
            tbody {
                tr {
                    td { +"e4" }
                    td { +"67" }
                    td { +"xxx------0" }
                }
                tr {
                    td { +"d4" }
                    td { +"27" }
                    td { +"xxxxxx--00" }
                }
                tr {
                    td { +"Kf4" }
                    td { +"27" }
                    td { +"xxxx----0" }
                }
            }
        }
    }
}

fun RBuilder.movesTable(handler: MovesTableProps.() -> Unit) : ReactElement {
    return child(MovesTable::class){
        this.attrs(handler)
    }
}