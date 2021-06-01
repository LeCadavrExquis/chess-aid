@file:JsModule("react-bootstrap")
@file:JsNonModule
package declarations

import react.RClass
import react.RProps

external interface ToggleButtonGroupProps: RProps {
    var type: String
    var defaultValue: Array<Int>
    var className: String
}
@JsName("ToggleButtonGroup")
external val toggleButtonGroup: RClass<ToggleButtonGroupProps>