package ui

import declarations.Table
import model.Game
import react.*
import react.dom.*


external interface MovesTableProps : RProps {
    var currentPosition: String
    var games: List<Game>
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
                props.games.take(5)
                    .forEach {
                        tr {
                            td { +it.moves.slice(0..2) }
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