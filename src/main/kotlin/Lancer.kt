package softserve.academy

class Lancer : WarriorImpl(
    health = Props.Lancer.HEALTH,
    attack = Props.Lancer.ATTACK
) {
    companion object {
        private const val PIERCING_PERCENTAGE = 50
    }

    override fun hits(other: Warrior) {
        val healthBefore = other.health
        super.hits(other)
        val dealtDamage = healthBefore - other.health
        if (other is Army.WarriorInArmy) {
            val next = other.nextBehind
            val reducedDamage = dealtDamage * PIERCING_PERCENTAGE / 100
            next?.acceptDamage(damage = reducedDamage)
        }
    }
}

fun main() {
    val chuck = Warrior();
    val bruce = Warrior();
    val carl = Knight();
    val dave = Warrior();
    val mark = Warrior();
    val bob = Defender();
    val mike = Knight();
    val rog = Warrior();
    val lancelot = Defender();
    val eric = Vampire();
    val adam = Vampire();
    val richard = Defender();
    val ogre = Warrior();
    val freelancer = Lancer();
    val vampire = Vampire();

    check(fight(chuck, bruce) == true);
    check(fight(dave, carl) == false);
    check(chuck.isAlive == true);
    check(bruce.isAlive == false);
    check(carl.isAlive == true);
    check(dave.isAlive == false);
    check(fight(carl, mark) == false);
    check(carl.isAlive == false);
    check(fight(bob, mike) == false);
    check(fight(lancelot, rog) == true);
    check(fight(eric, richard) == false);
    check(fight(ogre, adam) == true);
    check(fight(freelancer, vampire) == true);
    check(freelancer.isAlive == true);

    val myArmy = Army();
    myArmy.addUnits(2) { Defender() };
    myArmy.addUnits(2) { Vampire() };
    myArmy.addUnits(4) { Lancer() };
    myArmy.addUnits(1) { Warrior() };

    val enemyArmy = Army();
    enemyArmy.addUnits(2) { Warrior() };
    enemyArmy.addUnits(2) { Lancer() };
    enemyArmy.addUnits(2) { Defender() };
    enemyArmy.addUnits(3) { Vampire() };

    val army3 = Army();
    army3.addUnits(1) { Warrior() };
    army3.addUnits(1) { Lancer() };
    army3.addUnits(2) { Defender() };

    val army4 = Army();
    army4.addUnits(3) { Vampire() };
    army4.addUnits(1) { Warrior() };
    army4.addUnits(2) { Lancer() };

    check(fight(myArmy, enemyArmy) == true);
    check(fight(army3, army4) == false);

    println("OK")
}