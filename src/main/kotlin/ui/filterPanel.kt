package ui

import declarations.Button
import declarations.toggleButton
import declarations.toggleButtonGroup
import kotlinx.css.margin
import kotlinx.css.padding
import react.RBuilder
import react.ReactElement
import react.dom.*
import styled.styledDiv

fun RBuilder.filterPanel() {
    styledDiv {
        css.margin = "20px"
        h4 {+"Filter:"}
        toggleButtonGroup {
            attrs {
                type = "checkbox"
                className = "filterBW"
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
        br {  }
        toggleButtonGroup {
            attrs {
                type = "checkbox"
                className = "ifWin"
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
        br {  }
        toggleButtonGroup {
            attrs {
                type = "checkbox"
                defaultValue = arrayOf(1,2,3)
                className = "gameType"
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