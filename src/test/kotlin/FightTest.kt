import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import softserve.academy.Army
import softserve.academy.Knight
import softserve.academy.Warrior
import softserve.academy.addUnits
import softserve.academy.fight
import softserve.academy.isAlive

class FightTest {
    @Test
    fun `Warrior should win over Warrior`() {
        val chuck = Warrior()
        val bruce = Warrior()

        val res = fight(chuck, bruce)

        assertTrue(res) { "Warrior should win Warrior" }
        assertTrue(chuck.isAlive) { "Warrior winner should be alive" }
        assertFalse(bruce.isAlive) { "Warrior defeated should not be alive" }
    }

    @Test
    fun `Warrior should loose to Knight`() {
        val dave = Warrior()
        val carl = Knight()

        val res = fight(dave, carl)

        assertFalse(res) { "Warrior should lose to Knight" }
        assertTrue(carl.isAlive) { "Knight winner should be alive" }
        assertFalse(dave.isAlive) { "Warrior defeated should not be alive" }
    }

    @Test
    @DisplayName("1. Fight")
    fun `Warrior loses when fights against Knight`() {
        val carl = Warrior()
        val jon = Knight()

        val res = fight(carl, jon)

        assertFalse(res) { "Warrior should lose to Knight" }
    }

    @Test
    @DisplayName("2. Fight")
    fun `Knight wins when fights against Warrior`(){
        val ramon=Knight()
        val slevin=Warrior()

        val res=fight(ramon,slevin)

        assertTrue(res){"Expected Knight should win to Warrior"}
    }

    @Test
    @DisplayName("3. Fight")
    fun `Warrior vs Warrior - bob should be alive after fight`() {
        val bob = Warrior()
        val mars = Warrior()

        fight(bob, mars)

        assertTrue(bob.isAlive){"Expected first Warrior to be alive after fighting second Warrior"}
    }

    @Test
    @DisplayName("4. Fight")
    fun `Knight vs Warrior -Knight should be alive after fighting Warrior`(){
        val zeus = Knight()
        val godKiller = Warrior()

        fight(zeus,godKiller)

        assertTrue(zeus.isAlive){"Expected Knight to be alive after fighting Warrior"}
    }

    @Test
    @DisplayName("5. Fight")
    fun `Warrior vs Warrior - Second Warrior should be dead after fighting with first Warrior `(){
        val husband = Warrior()
        val wife = Warrior()

        fight(husband,wife)

        assertFalse(wife.isAlive){"Expected second Warrior(wife) to be dead after fighting with first Warrior(husband)"}
    }

    @Test
    @DisplayName("6. Fight")
    fun `Warrior vs Knight - Knight should be alive`(){
        val dragon = Warrior()
        val knight = Knight()

        fight(dragon,knight)

        assertTrue(knight.isAlive){"Expected Knight to be alive after fighting with Warrior"}
    }

    @Test
    @DisplayName("7. Fight")
    fun `Knight defeats Warrior, but loses to next Warrior due to sustained damage`() {
        val firstWarrior = Warrior()
        val knight = Knight()
        val secondWarrior = Warrior()

        fight(firstWarrior,knight)
        fight(knight, secondWarrior)

        assertFalse(knight.isAlive) { "Expected Knight should be dead after second fight due to damage taken in the first fight" }

    }

    @Test
    @DisplayName("1. Battle")
    fun `Army of 1 Warrior should lose to Army of 2 Warriors`() {
        val armyOf1Warrior = Army()
        val armyOf2Warrior = Army()
        armyOf1Warrior.addUnits(1) { Warrior() }
        armyOf2Warrior.addUnits(2) { Warrior() }

        val res = fight(armyOf1Warrior, armyOf2Warrior)

        assertFalse(res) { "Expected Army of 1 Warrior should lose to Army of 2 Warriors" }
    }

    @Test
    @DisplayName("2. Battle")
    fun `Army of 2 Warriors should lose to Army of 3 Warriors`() {
        val armyOf2Warrior = Army()
        val armyOf3Warrior = Army()
        armyOf2Warrior.addUnits(2) { Warrior() }
        armyOf3Warrior.addUnits(3) { Warrior() }

        val res = fight(armyOf2Warrior, armyOf3Warrior)

        assertFalse(res) { "Expected Army of 2 Warriors should lose to Army of 3 Warriors" }
    }

    @Test
    @DisplayName("3. Battle")
    fun `Army of 5 Warriors should lose to Army of 7 Warriors`() {
        val armyOf5Warrior = Army()
        val armyOf7Warrior = Army()
        armyOf5Warrior.addUnits(5) { Warrior() }
        armyOf7Warrior.addUnits(7) { Warrior() }

        val res = fight(armyOf5Warrior, armyOf7Warrior)

        assertFalse(res) { "Expected Army of 5 Warriors should lose to Army of 7 Warriors" }
    }

    @Test
    @DisplayName("4. Battle")
    fun `Army of 20 Warriors should win against Army of 21 Warriors`() {
        val armyOf20Warrior = Army()
        val armyOf21Warrior = Army()
        armyOf20Warrior.addUnits(20) { Warrior() }
        armyOf21Warrior.addUnits(21) { Warrior() }

        val result = fight(armyOf20Warrior, armyOf21Warrior)

        assertTrue(result) { "Expected Army of 20 Warriors to win against Army of 21 despite being outnumbered" }
    }

    @Test
    @DisplayName("5. Battle ")
    fun `Army of 10 Warriors should win against Army of 11 Warriors`() {
        val armyOf10Warrior = Army()
        val armyOf11Warrior = Army()
        armyOf10Warrior.addUnits(10) { Warrior() }
        armyOf11Warrior.addUnits(11) { Warrior() }

        val result = fight(armyOf10Warrior, armyOf11Warrior)

        assertTrue(result) { "Expected Army of 10 Warriors to win against Army of 11 despite being outnumbered" }
    }

    @Test
    @DisplayName("6. Battle")
    fun `Army of 11 Warriors should win against Army of 7 Warriors`() {
        val armyOf11Warriors = Army()
        val armyOf7Warriors = Army()
        armyOf11Warriors.addUnits(11) { Warrior() }
        armyOf7Warriors.addUnits(7) { Warrior() }

        val result = fight(armyOf11Warriors, armyOf7Warriors)

        assertTrue(result) { "Expected Army of 11 Warriors to win with against army of 7 Warriors overwhelming force" }
    }
}