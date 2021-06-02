@file:JsModule("react-bootstrap")
@file:JsNonModule
package declarations

import react.RClass
import react.RProps

external interface ToggleButtonProps: RProps {
    var value: Int
}
@JsName("ToggleButton")
external val toggleButton: RClass<ToggleButtonProps>

