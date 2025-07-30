package softserve.academy

import kotlin.math.max

interface HasDefense {
    val defense: Int
}

class Defender() : BaseWarrior(
    health = Props.Defender.HEALTH
), HasDefense {
    override val attack: Int
        get() = Props.Defender.ATTACK

    override val defense: Int
        get() = Props.Defender.DEFENSE

    override fun acceptDamage(damage: Int) {
        super.acceptDamage(max(0, damage - defense))
        //super.acceptDamage((damage - defense).coerceAtLeast(0))
    }
}

fun main() {
    // smoke test
    val chuck = Warrior()
    val bruce = Warrior()
    val carl = Knight()
    val dave = Warrior()
    val mark = Warrior()
    val bob = Defender()
    val mike = Knight()
    val rog = Warrior()
    val lancelot = Defender()

    check(fight(chuck, bruce) == true)
    check(fight(dave, carl) == false)
    check(chuck.isAlive == true)
    check(bruce.isAlive == false)
    check(carl.isAlive == true)
    check(dave.isAlive == false)
    check(fight(carl, mark) == false)
    check(carl.isAlive == false)
    check(fight(bob, mike) == false)
    check(fight(lancelot, rog) == true)

    val myArmy = Army()
    myArmy.addUnits(1) { Defender() }

    val enemyArmy = Army()
    enemyArmy.addUnits(2) { Warrior() }

    val army3 = Army()
    army3.addUnits(1) { Warrior() }
    army3.addUnits(1) { Defender() }

    val army4 = Army()
    army4.addUnits(2) { Warrior() }

    check(fight(myArmy, enemyArmy) == false)
    check(fight(army3, army4) == true)
    println("OK")
}