
import com.softserve.academy.models.*
import com.softserve.academy.models.FightService.battle
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BattleTest {

    @Test
    fun `1 Battle`() {
        val army1 = Army()
        val army2 = Army()

        army1.addUnits(1) { Warrior() }
        army2.addUnits(2) { Warrior() }

        assertFalse(battle(army1, army2))
    }

    @Test
    fun `2 Battle`() {
        val army1 = Army()
        val army2 = Army()

        army1.addUnits(2) { Warrior() }
        army2.addUnits(3) { Warrior() }

        assertFalse(battle(army1, army2))
    }

    @Test
    fun `3 Battle`() {
        val army1 = Army()
        val army2 = Army()

        army1.addUnits(5) { Warrior() }
        army2.addUnits(7) { Warrior() }

        assertFalse(battle(army1, army2))
    }

    @Test
    fun `4 Battle`() {
        val army1 = Army()
        val army2 = Army()

        army1.addUnits(20) { Warrior() }
        army2.addUnits(21) { Warrior() }

        assertTrue(battle(army1, army2))
    }

    @Test
    fun `5 Battle`() {
        val army1 = Army()
        val army2 = Army()

        army1.addUnits(10) { Warrior() }
        army2.addUnits(11) { Warrior() }

        assertTrue(battle(army1, army2))
    }

    @Test
    fun `6 Battle`() {
        val army1 = Army()
        val army2 = Army()

        army1.addUnits(11) { Warrior() }
        army2.addUnits(7) { Warrior() }

        assertTrue(battle(army1, army2))
    }

    @Test
    fun `7 Battle - Mixed Warrior and Defenders vs Warriors`() {
        val army1 = Army().apply {
            addUnits(5) { Warrior() }
            addUnits(4) { Defender() }
            addUnits(5) { Defender() }
        }
        val army2 = Army().apply { addUnits(4) { Warrior() } }
        assertTrue(battle(army1, army2))
    }

    @Test
    fun `8 Battle - Defender and Warriors vs 21 Warriors`() {
        val army1 = Army().apply {
            addUnits(5) { Defender() }
            addUnits(20) { Warrior() }
            addUnits(4) { Defender() }
        }
        val army2 = Army().apply { addUnits(21) { Warrior() } }
        assertTrue(battle(army1, army2))
    }

    @Test
    fun `9 Battle - Mixed large Defender and Warrior army vs smaller army`() {
        val army1 = Army().apply {
            addUnits(10) { Warrior() }
            addUnits(5) { Defender() }
            addUnits(10) { Defender() }
        }
        val army2 = Army().apply { addUnits(5) { Warrior() } }
        assertTrue(battle(army1, army2))
    }

    @Test
    fun `10 Battle - Small mixed army loses to 5 Warriors`() {
        val army1 = Army().apply {
            addUnits(2) { Defender() }
            addUnits(1) { Warrior() }
            addUnits(1) { Defender() }
        }
        val army2 = Army().apply { addUnits(5) { Warrior() } }
        assertFalse(battle(army1, army2))
    }

    @Test
    fun `11 Battle - Mixed army loses to another mixed army`() {
        val army1 = Army().apply {
            addUnits(5) { Defender() }
            addUnits(6) { Vampire() }
            addUnits(7) { Warrior() }
        }
        val army2 = Army().apply {
            addUnits(6) { Warrior() }
            addUnits(6) { Defender() }
            addUnits(6) { Vampire() }
        }
        assertFalse(battle(army1, army2))
    }

    @Test
    fun `12 Battle - Smaller mixed army loses`() {
        val army1 = Army().apply {
            addUnits(2) { Defender() }
            addUnits(3) { Vampire() }
            addUnits(4) { Warrior() }
        }
        val army2 = Army().apply {
            addUnits(4) { Warrior() }
            addUnits(4) { Defender() }
            addUnits(3) { Vampire() }
        }
        assertFalse(battle(army1, army2))
    }

    @Test
    fun `13 Battle - Defender-heavy army wins against Vampire-heavy army`() {
        val army1 = Army().apply {
            addUnits(11) { Defender() }
            addUnits(3) { Vampire() }
            addUnits(4) { Warrior() }
        }
        val army2 = Army().apply {
            addUnits(4) { Warrior() }
            addUnits(4) { Defender() }
            addUnits(13) { Vampire() }
        }
        assertTrue(battle(army1, army2))
    }

    @Test
    fun `14 Battle - Mixed army defeats Vampire-heavy army`() {
        val army1 = Army().apply {
            addUnits(9) { Defender() }
            addUnits(3) { Vampire() }
            addUnits(8) { Warrior() }
        }
        val army2 = Army().apply {
            addUnits(4) { Warrior() }
            addUnits(4) { Defender() }
            addUnits(13) { Vampire() }
        }
        assertTrue(battle(army1, army2))
    }

    @Test
    fun `15 Battle - Mixed Lancer army loses to another mixed army`() {
        val army1 = Army().apply {
            addUnits(5) { Lancer() }
            addUnits(3) { Vampire() }
            addUnits(4) { Warrior() }
            addUnits(2) { Defender() }
        }
        val army2 = Army().apply {
            addUnits(4) { Warrior() }
            addUnits(4) { Defender() }
            addUnits(6) { Vampire() }
            addUnits(5) { Lancer() }
        }

        assertFalse(battle(army1, army2))
    }

    @Test
    fun `16 Battle - Larger Lancer army wins`() {
        val army1 = Army().apply {
            addUnits(7) { Lancer() }
            addUnits(3) { Vampire() }
            addUnits(4) { Warrior() }
            addUnits(2) { Defender() }
        }
        val army2 = Army().apply {
            addUnits(4) { Warrior() }
            addUnits(4) { Defender() }
            addUnits(6) { Vampire() }
            addUnits(4) { Lancer() }
        }

        assertTrue(battle(army1, army2))
    }

    @Test
    fun `17 Battle - Lancer and Warrior vs 2 Warriors`() {
        val armyWarrior = Army().apply {
            addUnits(2) { Warrior() }
        }
        val armyLancer = Army().apply {
            addUnits(1) { Lancer() }
            addUnits(1) { Warrior() }
        }

        assertFalse(battle(armyWarrior, armyLancer))
    }
}