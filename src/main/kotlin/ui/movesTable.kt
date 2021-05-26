package ui

import declarations.Table
import model.GameModel
import react.*
import react.dom.*


external interface MovesTableProps : RProps {
    var currentPosition: List<String>
    var games: List<GameModel>
}

class MovesTable : RComponent<MovesTableProps, RState>() {
    override fun RBuilder.render() {
        div {
            p { +"Current position :${props.currentPosition}"}
            Table {
                attrs.args = "striped bordered hover"

                thead {
                    tr {
                        th { +"move" }
                        th { +"played" }
                        th { +"percentage" }
                    }
                }
                tbody {
                    //TODO : implement table body
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
}

fun RBuilder.movesTable(handler: MovesTableProps.() -> Unit) : ReactElement {
    return child(MovesTable::class){
        this.attrs(handler)
    }
}