package reactstrap

import react.RClass
import react.RProps

@JsModule("react-bootstrap/Table")
@JsNonModule
external val TableImport: dynamic

val Table: RClass<TableProps> = TableImport.default

external interface TableProps : RProps {
    var args: String? get() = definedExternally; set(value) = definedExternally
}