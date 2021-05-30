package declarations

import react.RClass
import react.RProps

@JsModule("react-bootstrap/Button")
@JsNonModule
//TODO: proper declaration
external val ButtonImport: dynamic

external interface ButtonProps: RProps {
    var active: Boolean
    var disabled: dynamic
    var href: String
    var size: String
    var type: String
    var variant: String
    var onClick: dynamic
}

val Button: RClass<ButtonProps> = ButtonImport.default