package ui

import declarations.Table
import model.Game
import react.*
import react.dom.*

external interface MovesTableProps : RProps {
    var currentPosition: PGN
    var games: List<Game>
}

class MovesTable : RComponent<MovesTableProps, RState>() {
    override fun RBuilder.render() {
        div {
            p { +"Current position :${props.currentPosition}"}
            p {
                + if (!props.currentPosition.isNullOrEmpty()) props.currentPosition.toMoveList().reduce { acc, s -> "$acc $s" } else " empty array"
            }
            Table {
                attrs {
                    striped = true
                    bordered = true
                    hover = true
                }

                thead {
                    tr {
                        th { +"move" }
                        th { +"played" }
                        th { +"percentage" }
                    }
                }
                tbody {
                    props.games.map {
                        it to it.moves.split(" ").elementAt(props.currentPosition.toMoveList().size)
                    }.groupBy { (game, lastMove) ->
                        lastMove
                    }.filter {
                        !it.key.isNullOrEmpty()
                    }.map { map ->
                        println("object mp")
                        object {
                            val move = map.key
                            val count = map.value.count()
                            val percentage = map.value.groupBy {
                                it.first.winner
                            }.map {
                                it.key to it.value.count()
                            }
                        }
                    }.take(5).forEach {
                            tr {
                                td { +it.move!! }
                                td { +it.count.toString() }
                                td {
                                    +it.percentage.map { "${it.first} - ${it.second}" }
                                        .reduce { acc, s -> "$acc : $s" }
                                }
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

typealias PGN = String

fun PGN.toMoveList() : List<String> {
    return this.split(" ").filterIndexed { idx, _ -> idx.rem(3) != 0}
}