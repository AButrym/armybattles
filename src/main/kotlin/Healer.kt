package softserve.academy

import org.slf4j.LoggerFactory

fun interface CanHealOthers {
    fun heal(warrior: Healable)
}

private val log = LoggerFactory.getLogger("healer")

class Healer: BaseWarrior(
    health = Props.Healer.HEALTH
), CanHealOthers {
    override val attack: Int
        get() = Props.Healer.ATTACK

    val healPower: Int
        get() = Props.Healer.HEAL_POWER

    override fun heal(warrior: Healable) {
            log.debug("heal: {}", warrior)
            warrior.heal(healPower)
    }
}

fun simpleTest() {
    val army1 = Army().apply {
        addUnits(1) { Lancer() }
    }
    val army2 = Army().apply {
        addUnits(1) { Warrior() }
        addUnits(1) { Healer() }
    }
    val res = fight(army1, army2)
    println(res)
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
    val freelancer = Lancer()
    val vampire = Vampire()
    val priest = Healer()

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
    check(fight(freelancer, vampire) == true)
    check(freelancer.isAlive == true)
    check(freelancer.health == 14)
    priest.heal(freelancer)
    check(freelancer.health == 16)

    val my_army = Army().apply {
        addUnits(2) { Defender() }
        addUnits(1) { Healer() }
        addUnits(2) { Vampire() }
        addUnits(2) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(1) { Warrior() }
    }

    val enemy_army = Army().apply {
        addUnits(2) { Warrior() }
        addUnits(4) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(2) { Defender() }
        addUnits(3) { Vampire() }
        addUnits(1) { Healer() }
    }
    val army_3 = Army().apply {
        addUnits(1) { Warrior() }
        addUnits(1) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(2) { Defender() }
    }

    val army_4 = Army().apply {
        addUnits(3) { Vampire() }
        addUnits(1) { Warrior() }
        addUnits(1) { Healer() }
        addUnits(2) { Lancer() }
    }

    check(fight(my_army, enemy_army) == false)
    check(fight(army_3, army_4) == true)
    println("OK")
}
