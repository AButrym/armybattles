package com.softserve.academy.models

import com.softserve.academy.models.FightService.fight

class Vampire : WarriorImpl(
    health = Props.Vampire.HEALTH,
    attack = Props.Vampire.ATTACK
) {
    val vampirism: Int
        get() = Props.Vampire.VAMPIRISM

    override fun hits(other: Warrior) {
        val initialHealth = other.health
        super.hits(other)
        val currentHealth = other.health
        val healingPoints = (initialHealth - currentHealth) * vampirism / 100
        heal(healingPoints)
    }
}

fun main() {
    val vampire = Vampire()
    val defender = Defender()

    println(fight(vampire, defender))
}