package com.softserve.academy.models

import com.softserve.academy.models.FightService.battle

class Army {
    val warriors = mutableListOf<Warrior>()

    interface WarriorInArmy : Warrior {
        val nextBehind: Warrior?
    }

    private class WarriorInArmyDecorator(val warrior: Warrior) : Warrior by warrior, WarriorInArmy {
        var _nextBehind: Warrior? = null
        override val nextBehind: Warrior? get() = _nextBehind
    }

    fun addUnits(amount: Int, warrior: () -> Warrior) {
        repeat(amount) {
            val wrapped = WarriorInArmyDecorator(warrior())

            if (warriors.isNotEmpty()) {
                val last = warriors.last() as WarriorInArmyDecorator
                last._nextBehind = wrapped
            }

            warriors.add(wrapped)
        }
    }
}

fun main() {
    val army1 = Army()
    val army2 = Army()

    army1.addUnits(2) { Knight() }
    army2.addUnits(2) { Warrior() }

    println(battle(army1, army2))
}