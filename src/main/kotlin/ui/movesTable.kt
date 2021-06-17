package ui

import declarations.Container
import declarations.Table
import kotlinx.css.*
import model.Game
import react.*
import react.dom.*
import styled.styledDiv
import kotlin.math.roundToInt

external interface MovesTableProps : RProps {
    var currentPosition: PGN
    var games: List<Game>
}

class MovesTable : RComponent<MovesTableProps, RState>() {
    override fun RBuilder.render() {
        div {
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
                                    barKDA(it.percentage)
                                }
                            }
                    }
                }
            }
        }
    }
    private fun RBuilder.barKDA(stats: List<Pair<String, Int>>) {
        val sum = stats.sumOf { it.second }
        styledDiv {
            css.display = Display.flex
            stats.sortedByDescending { it.first }
                .map {
                    it.first to (it.second.toDouble() / sum * 100).roundToInt()
                }.forEach {
                    println("${it.first} -> ${it.second}")
                    styledDiv {
                        css.background = when(it.first) {
                            "white" -> "white"
                            "black" -> "black"
                            else -> "gray"
                        }
                        css.borderStyle = BorderStyle.solid
                        css.width = LinearDimension("${it.second}%")
                        +"${it.second}%"
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