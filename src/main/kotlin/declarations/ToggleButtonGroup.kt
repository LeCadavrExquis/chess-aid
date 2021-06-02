@file:JsModule("react-bootstrap")
@file:JsNonModule
package declarations

import react.RClass
import react.RProps

external interface ToggleButtonGroupProps: RProps {
    var type: String
    var value: Array<Int>
    var defaultValue: Array<Int>
    var className: String
    var onChange: (Array<Int>) -> Unit
}
@JsName("ToggleButtonGroup")
external val toggleButtonGroup: RClass<ToggleButtonGroupProps>