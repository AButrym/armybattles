package com.softserve.academy.models

abstract class BaseWarrior(
    health: Int
) : Warrior, Healable {

    private var _health = health
    override val health get() = _health

    private val initialHealth = health

    override fun heal(points: Int) {
        if (points >= 0) _health += points

        if (_health > initialHealth) {
            _health = initialHealth
        }
    }
    protected fun reduceHealth(points: Int) {
        _health -= points
    }

    override fun acceptDamage(damage: Int) {
        if (damage >= 0) reduceHealth(damage)
    }
}