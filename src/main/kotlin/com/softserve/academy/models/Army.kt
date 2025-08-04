package com.softserve.academy.models

sealed interface Command {
    object ChampionHitOpponent : Command
}

interface CommandHandler {
    fun handle(command: Command, origin: Warrior)
}

class Army {
    val warriors = mutableListOf<Warrior>()

    interface WarriorInArmy : Warrior, CommandHandler {
        val nextBehind: Warrior?
    }

    private class WarriorInArmyDecorator(val warrior: Warrior) : Warrior by warrior, WarriorInArmy {
        var _nextBehind: Warrior? = null
        override val nextBehind: Warrior? get() = _nextBehind

        override fun handle(command: Command, origin: Warrior) {
            if (origin != this) {
                when (command) {
                    Command.ChampionHitOpponent -> {
                        if (warrior is CanHealOthers
                            && origin is Healable) {
                            warrior.heal(origin)
                        }
                    }
                }
            }

            (nextBehind as? WarriorInArmy)?.handle(command, this)
        }

        override fun hits(other: Warrior) {
            warrior.hits(other)
            handle(Command.ChampionHitOpponent, this)
        }
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