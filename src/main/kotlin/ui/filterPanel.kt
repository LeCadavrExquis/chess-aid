package ui

import declarations.*
import kotlinx.css.margin
import kotlinx.css.padding
import model.FilterSettings
import react.*
import react.dom.*
import styled.styledDiv

external interface FilterPanelProps: RProps {
    var updateFilter: (String, List<String>) -> Unit
}

external interface FilterPanelState: RState {
    var color: List<String>
    var result: List<String>
    var type: List<String>
}

class FilterPanel : RComponent<FilterPanelProps, FilterPanelState>() {

    override fun FilterPanelState.init() {
        color = emptyList()
        result = emptyList()
        type = emptyList()
    }
    override fun RBuilder.render() {
        styledDiv {
            css.margin = "20px"
            h4 { +"Filter:" }
            Container {
                Row {
                    toggleButtonGroup {
                        attrs {
                            type = "checkbox"
                            className = "filterBW"
                            onChange = { activeButtonsIdx ->
                                props.updateFilter( "color",
                                    activeButtonsIdx.map {
                                        when(it) {
                                            1 -> "white"
                                            2 -> "black"
                                            else -> ""
                                        }
                                    }.toList()
                                )
                            }
                        }
                        toggleButton {
                            attrs.value = 1
                            +"white"
                        }
                        toggleButton {
                            attrs.value = 2
                            +"black"
                        }
                    }
                }
                Row {
                    toggleButtonGroup {
                        attrs {
                            type = "checkbox"
                            className = "ifWin"
                            onChange = { activeButtonsIdx ->
                                props.updateFilter( "winner",
                                    activeButtonsIdx.map {
                                        when(it) {
                                            1 -> "white"
                                            2 -> "black"
                                            3 -> "draw"
                                            else -> ""
                                        }
                                    }.toList()
                                )
                            }
                        }
                        toggleButton {
                            attrs.value = 1
                            +"win"
                        }
                        toggleButton {
                            attrs.value = 2
                            +"lose"
                        }
                        toggleButton {
                            attrs.value = 3
                            +"draw"
                        }
                    }
                }
                Row {
                    toggleButtonGroup {
                        attrs {
                            type = "checkbox"
                            className = "gameType"
                            onChange = { activeButtonsIdx ->
                                props.updateFilter( "type",
                                    activeButtonsIdx.map {
                                        when(it) {
                                            1 -> "rapid"
                                            2 -> "blitz"
                                            3 -> "bullet"
                                            else -> ""
                                        }
                                    }.toList()
                                )
                            }
                        }
                        toggleButton {
                            attrs.value = 1
                            +"rapid"
                        }
                        toggleButton {
                            attrs.value = 2
                            +"blitz"
                        }
                        toggleButton {
                            attrs.value = 3
                            +"bullet"
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.filterPanel(handler: FilterPanelProps.() -> Unit) : ReactElement {
    return child(FilterPanel::class) {
        this.attrs(handler)
    }
}