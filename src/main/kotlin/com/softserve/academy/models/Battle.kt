package com.softserve.academy.models

import kotlin.math.min

fun fight(firstWarrior: Warrior, secondWarrior: Warrior): Boolean {
    while (firstWarrior.isAlive && secondWarrior.isAlive) {
        firstWarrior.hits(secondWarrior)

        if (secondWarrior.isAlive) {
            secondWarrior.hits(firstWarrior)
        }
    }
    return firstWarrior.isAlive
}

fun battle(firstArmy: Army, secondArmy: Army): Boolean {
    var warrior1 = firstArmy.warriors[0]
    var warrior2 = secondArmy.warriors[0]

    while (firstArmy.warriors.isNotEmpty() && secondArmy.warriors.isNotEmpty()) {

        val isFirstWarriorAlive = fight(warrior1, warrior2)

        if (!isFirstWarriorAlive) {
            firstArmy.warriors.removeFirst()
            if (firstArmy.warriors.size == 0) {
                break
            }
            warrior1 = firstArmy.warriors[0]

        } else {
            secondArmy.warriors.removeFirst()
            if (secondArmy.warriors.size == 0) {
                break
            }
            warrior2 = secondArmy.warriors[0]
        }
    }
    return firstArmy.warriors.isNotEmpty()
}

fun straightFight(firstArmy: Army, secondArmy: Army): Boolean {
    while (firstArmy.warriors.isNotEmpty() && secondArmy.warriors.isNotEmpty()) {
        val initialDuels = min(firstArmy.warriors.size, secondArmy.warriors.size)

        for (i in 0 until initialDuels) {
            val warrior = firstArmy.warriors[i]
            val warrior2 = secondArmy.warriors[i]

            fight(warrior, warrior2)
        }
        firstArmy.warriors.removeIf { !it.isAlive }
        secondArmy.warriors.removeIf { !it.isAlive }
    }
    return firstArmy.warriors.isNotEmpty()
}


fun main() {
// smoke test for Straight Fight
    val chuck = Warrior()
    val bruce = Warrior()
    val carl = Knight()
    val dave = Warrior()
    val mark = Warrior()
    val bob = Defender()
    val mike = Knight()
    val rog = Warrior()
    val lancelot = Defender()
    val eric = Vampire()
    val adam = Vampire()
    val richard = Defender()
    val ogre = Warrior()
    val freelancer = Lancer()
    val vampire = Vampire()
    val priest = Healer()

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
    check(!fight(eric, richard))
    check(fight(ogre, adam))
    check(fight(freelancer, vampire))
    check(freelancer.isAlive)
    check(freelancer.health == 14)
//    priest.heal(freelancer)
//    check(freelancer.health == 16)

    val myArmy = Army().apply {
        addUnits(2) { Defender() }
        addUnits(1) { Healer() }
        addUnits(2) { Vampire() }
        addUnits(2) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(1) { Warrior() }
    }

    val enemyArmy = Army().apply {
        addUnits(2) { Warrior() }
        addUnits(4) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(2) { Defender() }
        addUnits(3) { Vampire() }
        addUnits(1) { Healer() }
    }

    val army3 = Army().apply {
        addUnits(1) { Warrior() }
        addUnits(1) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(2) { Defender() }
    }

    val army4 = Army().apply {
        addUnits(3) { Vampire() }
        addUnits(1) { Warrior() }
        addUnits(1) { Healer() }
        addUnits(2) { Lancer() }
    }

    val army5 = Army().apply {
        addUnits(10) { Warrior() }
    }

    val army6 = Army().apply {
        addUnits(6) { Warrior() }
        addUnits(5) { Lancer() }
    }

    check(!battle(myArmy, enemyArmy))
//    check(battle(army3, army4))
    check(!straightFight(army5, army6))
}