package com.softserve.academy.models

interface HasHealth {
    val health: Int
}

val HasHealth.isAlive: Boolean
    get() = health > 0

interface HasAttack {
    val attack: Int
}

interface Warrior : HasHealth, HasAttack {
    infix fun hits(other: Warrior) {
        other.acceptDamage(attack)
    }
    fun acceptDamage(damage: Int)

    companion object {
        operator fun invoke() = WarriorImpl()
    }
}
