package softserve.academy

import org.slf4j.LoggerFactory
import kotlin.math.min

private val log = LoggerFactory.getLogger(BaseWarrior::class.java)

abstract class BaseWarrior(
    health: Int
) : Warrior, Healable {
    private var _health = health
        set(value) {
            log.debug("{} set health to {}", this, value)
            field = min(value, initialHealth)
        }

    override val health get() = _health

    private val initialHealth = health

    override fun heal(points: Int) {
        check(points >= 0) { "heal points should be non-negative" }
        _health += points
    }

    override fun acceptDamage(damage: Int) {
        check(damage >= 0) { "damage should be non-negative" }
        _health -= damage
    }

    override fun toString(): String {
        return "${this::class.simpleName?.removeSuffix("Impl")}(hp:$health, iHp:$initialHealth, attack:$attack)"
    }
}