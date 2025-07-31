
import com.softserve.academy.models.FightService.fight
import com.softserve.academy.models.Knight
import com.softserve.academy.models.Warrior
import com.softserve.academy.models.isAlive
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FightTest {
    // Annotation has priority in naming
    @DisplayName("Warrior should win over Warrior")
    @Test
    fun `Warrior should win over Warrior`() {
        // Arrange  Given
        val chuck = Warrior()
        val bruce = Warrior()

        // Act   When
        val res = fight(chuck, bruce)

        // Assert   Then
        assertTrue(res) { "Warrior should win Warrior" }
        assertTrue(chuck.isAlive) { "Warrior winner should be alive" }
        assertFalse(bruce.isAlive) { "Warrior defeated should not be alive" }
    }

    @Test
    fun `Warrior should lose to Knight`() {
        val dave = Warrior()
        val carl = Knight()

        val res = fight(dave, carl)

        assertFalse(res) { "Warrior should lose to Knight" }
        assertTrue(carl.isAlive) { "Knight winner should be alive" }
        assertFalse(dave.isAlive) { "Warrior defeated should not be alive" }
    }

    @Test
    fun `Warrior survives 1v1 against another Warrior`() {
        val bob = Warrior()
        val mars = Warrior()

        fight(bob, mars)

        assertTrue(bob.isAlive) { "Expected bob to be alive after fighting mars" }
        assertFalse(mars.isAlive) { "Expected mars to be dead after the fight" }
    }

    @Test
    fun `Knight should survive after fighting Warrior`() {
        val zeus = Knight()
        val godkiller = Warrior()

        fight(zeus, godkiller)

        assertTrue(zeus.isAlive) { "Knight (zeus) should be alive after fighting Warrior" }
        assertFalse(godkiller.isAlive) { "Warrior (godkiller) should be dead after the fight" }
    }

    @Test
    fun `Warrior vs Warrior - loser should be dead`() {
        val husband = Warrior()
        val wife = Warrior()

        fight(husband, wife)

        assertTrue(husband.isAlive || wife.isAlive) { "One should survive" }
        assertFalse(wife.isAlive) { "Expected wife to be dead after the fight" }
    }

    @Test
    fun `Knight should win and survive when fighting Warrior`() {
        val dragon = Warrior()
        val knight = Knight()

        fight(dragon, knight)

        assertTrue(knight.isAlive) { "Knight should be alive after fighting Warrior" }
        assertFalse(dragon.isAlive) { "Warrior should be dead after fighting Knight" }
    }

    @Test
    fun `After two fights, Warrior should not win both`() {
        val unit1 = Warrior()
        val unit2 = Knight()
        val unit3 = Warrior()

        fight(unit1, unit2) // Knight wins
        val result = fight(unit2, unit3) // Knight fights next Warrior

        assertFalse(result) { "Knight should not survive second fight against fresh Warrior" }
        assertFalse(unit2.isAlive) { "Knight should die in second fight" }
        assertTrue(unit3.isAlive) { "Final Warrior should survive" }
    }
}