package softserve.academy

class Vampire : Warrior(
    health = Props.Vampire.HEALTH,
    attack = Props.Vampire.ATTACK
) {
    val vampirism: Int
        get() = Props.Vampire.VAMPIRISM

    override fun hits(other: Warrior) {
        val startingHealth = other.health
        super.hits(other)
        val finalHealth = other.health
        val dealtDamage = startingHealth - finalHealth
        val selfHealingPoints = dealtDamage * vampirism / 100
        heal(selfHealingPoints)
    }
}

fun main() {
    // smoke test
    val chuck = Warrior()
    val bruce = Warrior()
    val carl = Knight()
    val dave = Warrior()
    val mark = Warrior()
    val bob = Defender()
    val mike = Knight()
    val rog = Warrior()
    val lancelot = Defender()
    val eric = Vampire()
    val adam = Vampire()
    val richard = Defender()
    val ogre = Warrior()

    check(fight(chuck, bruce) == true)
    check(fight(dave, carl) == false)
    check(chuck.isAlive == true)
    check(bruce.isAlive == false)
    check(carl.isAlive == true)
    check(dave.isAlive == false)
    check(fight(carl, mark) == false)
    check(carl.isAlive == false)
    check(fight(bob, mike) == false)
    check(fight(lancelot, rog) == true)
    check(fight(eric, richard) == false)
    check(fight(ogre, adam) == true)

    val myArmy = Army().apply {
        addUnits(2) { Defender() }
        addUnits(2) { Vampire() }
        addUnits(1) { Warrior() }
    }

    val enemyArmy = Army().apply {
        addUnits(2) { Warrior() }
        addUnits(2) { Defender() }
        addUnits(3) { Vampire() }
    }

    val army3 = Army().apply {
        addUnits(1) { Warrior() }
        addUnits(4) { Defender() }
    }

    val army4 = Army().apply {
        addUnits(3) { Vampire() }
        addUnits(2) { Warrior() }
    }

    check(fight(myArmy, enemyArmy) == false)
    check(fight(army3, army4) == true)
    println("OK")
}