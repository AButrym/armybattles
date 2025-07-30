package softserve.academy.others

sealed interface Action {
    class Move(val x: Int, val y: Int) : Action
    object Idle : Action
    object Unknown : Action
}

fun processAction(action: Action) {
    when (action) {
        is Action.Idle -> println("Idle")
        is Action.Move -> println("Move to (${action.x}, ${action.y})")
        is Action.Unknown -> println("Unknown action")
    }
}