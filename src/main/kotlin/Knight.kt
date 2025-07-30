package softserve.academy

class Knight : BaseWarrior(
    health = Props.Knight.HEALTH
) {
    override val attack
        get() = Props.Knight.ATTACK
}