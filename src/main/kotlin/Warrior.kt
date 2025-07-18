package softserve.academy

open class Warrior(
    health: Int = Props.Warrior.HEALTH,
    val attack: Int = Props.Warrior.ATTACK
) {
    var health = health
        private set

    private val initialHealth = health

    protected fun heal(points: Int) {
        check(points >= 0) { "heal points should be non-negative" }
        health += points
        if (health > initialHealth) {
            health = initialHealth
        }
    }

    open infix fun hits(other: Warrior) {
        other.acceptDamage(attack)
    }
    open fun acceptDamage(damage: Int) {
        check(damage >= 0) { "damage should be non-negative" }
        health -= damage
    }
}

val Warrior.isAlive: Boolean
    get() = health > 0


class Knight : Warrior(
    health = Props.Knight.HEALTH,
    attack = Props.Knight.ATTACK)

fun Army.addUnits(n: Int, factory: () -> Warrior) {
    repeat(n) { addUnit(factory()) }
}

fun fight(first: Warrior, second: Warrior): Boolean {
    while (first.isAlive && second.isAlive) {
        first hits second
        if (second.isAlive) {
            second hits first
        }
    }
    return first.isAlive
}

//fun fight(first: Army, second: Army): Boolean {
//    while (first.isAlive and second.isAlive) {
//        fight(first.champion, second.champion)
//    }
//    return first.isAlive
//}
fun fight(first: Army, second: Army): Boolean {
    val firstIterator = first.iterator()
    if (!firstIterator.hasNext()) return false

    val secondIterator = second.iterator()
    if (!secondIterator.hasNext()) return true

    var firstChampion = firstIterator.next()
    var secondChampion = secondIterator.next()

    while (true) {
        val res = fight(firstChampion, secondChampion)
        if (res) {
            if (!secondIterator.hasNext()) { return true }
            secondChampion = secondIterator.next()
        } else {
            if (!firstIterator.hasNext()) { return false }
            firstChampion = firstIterator.next()
        }
    }
}

fun main() {
    val myArmy = Army()
    myArmy.addUnits(3) { Knight() }

    val enemyArmy = Army()
    enemyArmy.addUnits(3) { Warrior() }

    val army3 = Army()
    army3.addUnits(20) { Warrior() }
    army3.addUnits(5) { Knight() }

    val army4 = Army()
    army4.addUnits(30) { Warrior() }

    check(fight(myArmy, enemyArmy) == true)
    check(fight(army3, army4) == false)
    println("OK")
}
