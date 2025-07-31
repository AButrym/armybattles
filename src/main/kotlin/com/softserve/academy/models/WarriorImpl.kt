package com.softserve.academy.models

open class WarriorImpl(
    health: Int = Props.Warrior.HEALTH,
    override val attack: Int = Props.Warrior.ATTACK
) : Warrior {

    private var _health = health
    override val health get() = _health

    private val initialHealth = health

    protected fun heal(points: Int) {
        if (points >= 0) _health += points

        if (_health > initialHealth) {
            _health = initialHealth
        }
    }

    override fun acceptDamage(damage: Int) {
        if (damage >= 0) _health -= damage
    }
}



