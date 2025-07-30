package softserve.academy

open class WarriorImpl : BaseWarrior(Props.Warrior.HEALTH) {
    override val attack: Int
        get() = Props.Warrior.ATTACK
}