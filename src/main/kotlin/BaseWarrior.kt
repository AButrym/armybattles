package softserve.academy

abstract class BaseWarrior(
    health: Int
) : Warrior, Healable {
    private var _health = health
    override val health get() = _health

    private val initialHealth = health

    override fun heal(points: Int) {
        check(points >= 0) { "heal points should be non-negative" }
        _health += points
        if (_health > initialHealth) {
            _health = initialHealth
        }
    }

    override fun acceptDamage(damage: Int) {
        check(damage >= 0) { "damage should be non-negative" }
        _health -= damage
    }
}