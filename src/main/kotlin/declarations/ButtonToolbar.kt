@file:JsModule("react-bootstrap")
@file:JsNonModule
package declarations

import react.RClass
import react.RProps

external interface ButtonToolbarProps: RProps {
    var vertical: Boolean
    var size: String
    var className: String
}
@JsName("ButtonToolbar")
external val buttonToolbar: RClass<ButtonToolbarProps>