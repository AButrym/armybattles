package softserve.academy

class Army {
    private val troops = mutableListOf<Warrior>()

    fun addUnit(warrior: Warrior) {
        troops.add(warrior)
    }

    val isAlive: Boolean
        get() = troops.any { it.isAlive }

    val champion: Warrior
        get() = troops.find { it.isAlive }!!

    fun iterator(): Iterator<Warrior> = troops.iterator()
}