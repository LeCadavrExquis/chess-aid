package ui

import declarations.Button
import declarations.buttonToolbar
import kotlinx.css.margin
import kotlinx.css.padding
import react.RBuilder
import react.ReactElement
import react.dom.div
import react.dom.h3
import react.dom.h4
import react.dom.render
import styled.styledDiv

fun RBuilder.filterPanel() {
    styledDiv {
        css.margin = "20px"
        h4 {+"Filter:"}
        buttonToolbar{
            attrs.className = "elko"
            Button {
                attrs.variant = "primary"
                +"butt 1"
            }
            Button {
                attrs.variant = "secondary"
                +"butt 2"
            }
        }
        Button {
            attrs.variant = "success"
            +"butt 3"
        }
        Button {
            attrs.variant = "danger"
            +"butt 4"
        }
    }
}