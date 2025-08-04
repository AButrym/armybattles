package com.softserve.academy.models

fun interface CanHealOthers {
    fun heal(warrior: Healable)
}

class Healer : BaseWarrior(
    health = Props.Healer.HEALTH
), CanHealOthers {
    override val attack: Int
        get() = Props.Healer.ATTACK

    val healPower: Int
        get() = Props.Healer.HEAL

    override fun heal(warrior: Healable) {
        warrior.heal(healPower)
    }
}

fun main() {
    // smoke test
    var chuck = Warrior()
    var bruce = Warrior()
    var carl = Knight()
    var dave = Warrior()
    var mark = Warrior()
    var bob = Defender()
    var mike = Knight()
    var rog = Warrior()
    var lancelot = Defender()
    var eric = Vampire()
    var adam = Vampire()
    var richard = Defender()
    var ogre = Warrior()
    var freelancer = Lancer()
    var vampire = Vampire()
    var priest = Healer()

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
    priest.heal(freelancer)
    check(freelancer.health == 16)

    var my_army = Army().apply {
        addUnits(2) { Defender() }
        addUnits(1) { Healer() }
        addUnits(2) { Vampire() }
        addUnits(2) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(1) { Warrior() }
    }

    var enemy_army = Army().apply {
        addUnits(2) { Warrior() }
        addUnits(4) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(2) { Defender() }
        addUnits(3) { Vampire() }
        addUnits(1) { Healer() }
    }
    var army_3 = Army().apply {
        addUnits(1) { Warrior() }
        addUnits(1) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(2) { Defender() }
    }

    var army_4 = Army().apply {
        addUnits(3) { Vampire() }
        addUnits(1) { Warrior() }
        addUnits(1) { Healer() }
        addUnits(2) { Lancer() }
    }

    check(!battle(my_army, enemy_army))
    check(battle(army_3, army_4))
    println("OK")
}