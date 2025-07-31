package com.softserve.academy.services

import com.softserve.academy.models.Army
import com.softserve.academy.models.addUnits
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class FightServiceTest {
    @Test
    fun `Print every warrior state inside from first initialization`() {
        val lancer = Lancer()
        val warrior = Warrior()
        val knight = Knight()
        val defender = Defender()
        val vampire = Vampire()

        println(lancer)
        println(warrior)
        println(knight)
        println(defender)
        println(vampire)
    }



    @Test
    fun `Warrior should lose vs Knight`() {
        val carl = Warrior()
        val jim = Knight()

        val result = FightService.fight(carl, jim)

        assertFalse(result){"Warrior should lose to Knight"}
    }

    @Test
    fun `Knight should win vs Warrior`() {
        val ramon = Knight()
        val slevin = Warrior()

        val result = FightService.fight(ramon, slevin)

        assertTrue(result){"Knight should win to Warrior"}
    }

    @Test
    fun `Warrior should win vs Warrior and first Warrior should be alive`() {
        val bob = Warrior()
        val mars = Warrior()

        FightService.fight(bob, mars)

        assertTrue(bob.isAlive)
    }

    @Test
    fun `Knight should win vs Warrior and Knight should be alive`() {
        val zeus = Knight()
        val godkiller = Warrior()

        FightService.fight(zeus, godkiller)

        assertTrue(zeus.isAlive)
    }

    @Test
    fun `Second Warrior should be dead after first Warrior win vs second Warrior`() {
        val husband = Warrior()
        val wife = Warrior()

        FightService.fight(husband, wife)

        assertFalse(wife.isAlive)
    }

    @Test
    fun `Second attacking Knight should be alive after Warrior vs Knight fight`() {
        val dragon = Warrior()
        val knight = Knight()

        FightService.fight(dragon, knight)

        assertTrue(knight.isAlive)
    }

    @Test
    fun `After first fight between Warrior vs Knight,Knight sustains damage and loses second fight Knight vs Warrior`() {
        val unit1 = Warrior()
        val unit2 = Knight()
        val unit3 = Warrior()

        FightService.fight(unit1, unit2)
        val result = FightService.fight(unit2, unit3)

        assertFalse(result)
    }

    @Test
    fun `First Army of 1 Warrior should lose to second Army of 2 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(1,{Warrior()})
        army2.addUnits(2,{Warrior()})

        val result = FightService.fight(army1, army2)

        assertFalse(result)
    }

    @Test
    fun `First army of 2 Warriors should lose to second Army of 3 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(2, { Warrior() })
        army2.addUnits(3, { Warrior() })

        val result = FightService.fight(army1, army2)

        assertFalse(result)
    }

    @Test
    fun `First army of 5 Warriors should lose to second Army of 7 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits( 5,{Warrior()})
        army2.addUnits( 7,{Warrior()})

        val result = FightService.fight(army1, army2)

        assertFalse(result)
    }

    @Test
    fun `First army of 20 Warriors should win vs second Army of 21 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(20,{Warrior()})
        army2.addUnits(21,{Warrior()})

        val result = FightService.fight(army1, army2)

        assertTrue(result)
    }

    @Test
    fun `First army of 10 Warriors should win vs second Army of 11 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(10,{Warrior()})
        army2.addUnits(11,{Warrior()})

        val result = FightService.fight(army1, army2)

        assertTrue(result)
    }

    @Test
    fun `First army of 11 Warriors should win vs second Army of 7 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(11,{Warrior()})
        army2.addUnits(7,{Warrior()})


        val result = FightService.fight(army1, army2)
        assertTrue(result)
    }

    @Test
    fun `Army of 5 Warriors and 9 Defenders should win vs Army of 4 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(5, { Warrior() })
        army1.addUnits(4, { Defender() })
        army1.addUnits(5, { Defender() })
        army2.addUnits(4, { Warrior() })

        val res = FightService.fight(army1, army2)

        assertTrue(res)
    }

    @Test
    fun `Army of 9 Defenders and 20 Warriors should win vs Army of 21 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(5, { Defender() })
        army1.addUnits(20, { Warrior() })
        army2.addUnits(21, { Warrior() })
        army1.addUnits(4, { Defender() })

        val res = FightService.fight(army1, army2)

        assertTrue(res)
    }


    @Test
    fun `Army of 10 Warriors and 15 Defenders should win vs Army of 5 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(10, { Warrior() })
        army1.addUnits(5, { Defender() })
        army2.addUnits(5, { Warrior() })
        army1.addUnits(10, { Defender() })

        val res = FightService.fight(army1, army2)

        assertTrue(res)
    }

    @Test
    fun `Army of 3 Defenders and 1 Warrior should lose vs Army of 5 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(2, { Defender() })
        army1.addUnits(1, { Warrior() })
        army1.addUnits(1, { Defender() })
        army2.addUnits(5, { Warrior() })

        val res = FightService.fight(army1, army2)

        assertFalse(res)
    }


    @Test
    fun `Army of 5 Defenders, 6 Vampires, 7 Warriors should lose vs 6 Warriors, 6 Defenders, 6 Vampires`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(5, { Defender() })
        army1.addUnits(6, { Vampire() })
        army1.addUnits(7, { Warrior() })
        army2.addUnits(6, { Warrior() })
        army2.addUnits(6, { Defender() })
        army2.addUnits(6, { Vampire() })

        val res = FightService.fight(army1, army2)

        assertFalse(res)
    }


    @Test
    fun `Army of 2 Defenders, 3 Vampires, 4 Warriors should lose vs 4 Warriors, 4 Defenders, 3 Vampires`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(2, { Defender() })
        army1.addUnits(3, { Vampire() })
        army1.addUnits(4, { Warrior() })
        army2.addUnits(4, { Warrior() })
        army2.addUnits(4, { Defender() })
        army2.addUnits(3, { Vampire() })

        val res = FightService.fight(army1, army2)

        assertFalse(res)
    }


    @Test
    fun `Army of 11 Defenders, 3 Vampires, 4 Warriors should win vs 4 Warriors, 4 Defenders, 13 Vampires`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(11, { Defender() })
        army1.addUnits(3, { Vampire() })
        army1.addUnits(4, { Warrior() })
        army2.addUnits(4, { Warrior() })
        army2.addUnits(4, { Defender() })
        army2.addUnits(13, { Vampire() })

        val res = FightService.fight(army1, army2)

        assertTrue(res)
    }

    @Test
    fun `Army of 9 Defenders, 3 Vampires, 8 Warriors should win vs 4 Warriors, 4 Defenders, 13 Vampires`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(9, { Defender() })
        army1.addUnits(3, { Vampire() })
        army1.addUnits(8, { Warrior() })
        army2.addUnits(4, { Warrior() })
        army2.addUnits(4, { Defender() })
        army2.addUnits(13, { Vampire() })

        val res = FightService.fight(army1, army2)

        assertTrue(res)
    }


    @Test
    fun `Army of 5 Lancers, 3 Vampires, 4 Warriors, 2 Defenders should lose vs 4 Warriors, 4 Defenders, 6 Vampires, 5 Lancers`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(5, { Lancer() })
        army1.addUnits(3, { Vampire() })
        army1.addUnits(4, { Warrior() })
        army1.addUnits(2, { Defender() })
        army2.addUnits(4, { Warrior() })
        army2.addUnits(4, { Defender() })
        army2.addUnits(6, { Vampire() })
        army2.addUnits(5, { Lancer() })

        val res = FightService.fight(army1, army2)

        assertFalse(res)
    }

    @Test
    fun `Army of 7 Lancers, 3 Vampires, 4 Warriors, 2 Defenders should win vs 4 Warriors, 4 Defenders, 6 Vampires, 4 Lancers`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(7, { Lancer() })
        army1.addUnits(3, { Vampire() })
        army1.addUnits(4, { Warrior() })
        army1.addUnits(2, { Defender() })
        army2.addUnits(4, { Warrior() })
        army2.addUnits(4, { Defender() })
        army2.addUnits(6, { Vampire() })
        army2.addUnits(4, { Lancer() })

        val res = FightService.fight(army1, army2)

        assertTrue(res)
    }


    @Test
    fun `Army of 2 Warriors should lose vs Army of 1 Lancer and 1 Warrior`() {
        val armyWarrior = Army()
        val armyLancer = Army()
        armyWarrior.addUnits(2, { Warrior() })
        armyLancer.addUnits(1, { Lancer() })
        armyLancer.addUnits(1, { Warrior() })

        val res = FightService.fight(armyWarrior, armyLancer)

        assertFalse(res)
    }


}