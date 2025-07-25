package softserve.academy

class Army {
    private val troops = mutableListOf<Warrior>()

    interface WarriorInArmy : Warrior {
        val nextBehind: Warrior?
    }

    private class WarriorInArmyDecorator(val warrior: Warrior)
        : Warrior by warrior, WarriorInArmy
    {
        var _nextBehind: Warrior? = null
        override val nextBehind: Warrior? get() = _nextBehind
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