package declarations

import react.RClass
import react.RProps

@JsModule("react-bootstrap/Table")
@JsNonModule
external val TableImport: dynamic
//TODO: proper declaration
val Table: RClass<TableProps> = TableImport.default

external interface TableProps : RProps {
    var bordered: Boolean
    var borderless: Boolean
    var hover: Boolean
    var size: String
    var striped: Boolean
    var variant: String
}