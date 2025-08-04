package com.softserve.academy.models

interface HasHealth {
    val health: Int
}

fun interface CanAcceptDamage {
    fun acceptDamage(damage: Int)
}

fun interface Healable {
    fun heal(points: Int)
}

val HasHealth.isAlive: Boolean
    get() = health > 0

interface HasAttack {
    val attack: Int
}

interface Warrior : HasHealth, HasAttack, CanAcceptDamage {
    infix fun hits(other: Warrior) {
        other.acceptDamage(attack)
    }

    companion object {
        operator fun invoke() = WarriorImpl()
    }
}
