package softserve.academy

interface HasHealth {
    val health: Int
}

fun interface Healable {
    fun heal(points: Int)
}

val HasHealth.isAlive: Boolean
    get() = health > 0

interface HasAttack {
    val attack: Int
//        get() = 0
}

fun interface CanAcceptDamage {
    fun acceptDamage(damage: Int)
}

interface Warrior : HasHealth, HasAttack, CanAcceptDamage {
    infix fun hits(other: Warrior) {
        other.acceptDamage(attack)
    }

    companion object {
        operator fun invoke() = WarriorImpl()
    }

    override fun toString(): String
}
