package com.softserve.academy.models

object FightService {

    fun fight(firstWarrior: Warrior, secondWarrior: Warrior): Boolean {
        while (firstWarrior.isAlive && secondWarrior.isAlive) {
            firstWarrior.hits(secondWarrior)

            if (secondWarrior.isAlive) {
                secondWarrior.hits(firstWarrior)
            }
        }
        return firstWarrior.isAlive
    }

    fun battle(firstArmy: Army, secondArmy: Army): Boolean {
        var warrior1 = firstArmy.warriors[0]
        var warrior2 = secondArmy.warriors[0]

        while (firstArmy.warriors.isNotEmpty() && secondArmy.warriors.isNotEmpty()) {

            val isFirstWarriorAlive = fight(warrior1, warrior2)

            if (!isFirstWarriorAlive) {
                firstArmy.warriors.removeFirst()
                if (firstArmy.warriors.size == 0) {
                    break
                }
                warrior1 = firstArmy.warriors[0]

            } else {
                secondArmy.warriors.removeFirst()
                if (secondArmy.warriors.size == 0) {
                    break
                }
                warrior2 = secondArmy.warriors[0]
            }
        }
        return firstArmy.warriors.isNotEmpty()
    }
}