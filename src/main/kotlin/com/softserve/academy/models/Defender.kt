package com.softserve.academy.models

import com.softserve.academy.models.FightService.fight
import com.softserve.academy.models.FightService.battle
import kotlin.math.max

class Defender : WarriorImpl(
    health = Props.Defender.HEALTH,
    attack = Props.Defender.ATTACK
) {
    val defense: Int
        get() = Props.Defender.DEFENSE

    override fun acceptDamage(damage: Int) {
        super.acceptDamage(max(0, damage - defense))
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

    check(fight(chuck, bruce))
    check(!fight(dave, carl))
    check(chuck.isAlive)
    check(!bruce.isAlive)
    check(carl.isAlive)
    check(!dave.isAlive)
    check(!fight(carl, mark))
    check(!carl.isAlive)
    check(!fight(bob, mike))
    check(fight(lancelot, rog))

    val myArmy = Army()
    myArmy.addUnits(1) { Defender() }

    val enemyArmy = Army()
    enemyArmy.addUnits(2) { Warrior() }

    val army3 = Army()
    army3.addUnits(1) { Warrior() }
    army3.addUnits(1) { Defender() }

    val army4 = Army()
    army4.addUnits(2) { Warrior() }

    check(!battle(myArmy, enemyArmy))
    check(battle(army3, army4))
    println("OK")
}