package com.softserve.academy.models

import com.softserve.academy.models.FightService.battle
import com.softserve.academy.models.FightService.fight

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

    check(fight(chuck, bruce));
    check(!fight(dave, carl));
    check(chuck.isAlive);
    check(!bruce.isAlive);
    check(carl.isAlive);
    check(!dave.isAlive);
    check(!fight(carl, mark));
    check(!carl.isAlive);
    check(!fight(bob, mike));
    check(fight(lancelot, rog));
    check(!fight(eric, richard));
    check(fight(ogre, adam));
    check(fight(freelancer, vampire));
    check(freelancer.isAlive);

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

    check(battle(myArmy, enemyArmy));
    check(!battle(army3, army4));
}