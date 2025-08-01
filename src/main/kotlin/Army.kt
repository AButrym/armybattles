package softserve.academy

import org.slf4j.Logger
import org.slf4j.LoggerFactory

sealed interface Command {
    data object ChampionHitOpponent : Command
}

fun interface CommandHandler {
    fun handle(command: Command, origin: Warrior)
}

private val log = LoggerFactory.getLogger("army")

class Army {
    private val troops = mutableListOf<Warrior>()

    interface WarriorInArmy : Warrior, CommandHandler {
        val nextBehind: Warrior?
    }

    private class WarriorInArmyDecorator(val warrior: Warrior) : Warrior by warrior, WarriorInArmy {
        var _nextBehind: Warrior? = null
        override val nextBehind: Warrior? get() = _nextBehind

        override fun handle(command: Command, origin: Warrior) {
            log.debug("{} handles command {} from {}", this, command, origin)
            if (origin != this) when (command) {
                Command.ChampionHitOpponent -> {
                    val originUnwrapped = (origin as? WarriorInArmyDecorator)?.unwrap
                    if (warrior is CanHealOthers
                        && originUnwrapped is Healable
                    ) {
                        warrior.heal(originUnwrapped)
                    }
                }
            }
            (nextBehind as? WarriorInArmy)?.handle(command, this)
        }

        val unwrap: Warrior get() = warrior

        override fun hits(other: Warrior) {
            warrior.hits(other)
            handle(Command.ChampionHitOpponent, this)
        }

    }

    fun addUnit(warrior: Warrior) {
        val wrapped = WarriorInArmyDecorator(warrior)
        if (troops.isNotEmpty()) {
            val last = troops.last() as WarriorInArmyDecorator
            last._nextBehind = wrapped
        }
        troops.add(wrapped)
    }

    val isAlive: Boolean
        get() = troops.any { it.isAlive }

    val champion: Warrior
        get() = troops.find { it.isAlive }!!

    fun iterator(): Iterator<Warrior> = troops.iterator()
}

fun Army.addUnits(n: Int, factory: () -> Warrior) {
    repeat(n) { addUnit(factory()) }
}