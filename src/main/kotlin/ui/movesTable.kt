package ui

import model.Game
import react.*
import react.dom.*
import reactstrap.Table
import styled.css
import styled.styledTable

external interface MovesTableProps : RProps {
    var currentPosition: String
    var games: List<Game>
}

class MovesTable(props: MovesTableProps) : RComponent<MovesTableProps, RState>() {

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
                props.games.take(5)
                    .forEach {
                        tr {
                            td { +it.moves }
                            td { +it.color }
                            td { +it.type }
                        }
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