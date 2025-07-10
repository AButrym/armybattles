import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import softserve.academy.Army
import softserve.academy.Warrior
import softserve.academy.addUnits
import softserve.academy.fight

class BattleTest {
    @Test
    fun `1 Battle`() {
        val army1 = Army()
        army1.addUnits(1) { Warrior() }
        val army2 = Army()
        army2.addUnits(2) { Warrior() }

        val res = fight(army1, army2)

        assertFalse(res) { "Army of 1 Warrior should loose to army of 2 Warriors" }
    }
}