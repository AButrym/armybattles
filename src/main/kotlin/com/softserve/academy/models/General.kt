package com.softserve.academy.models

import kotlin.math.max

class General : BaseWarrior(
    health = Props.General.HEALTH
), HasDefense {
    override val attack: Int
        get() = Props.General.ATTACK

    override val defense: Int
        get() = Props.General.DEFENSE

    val motivation: Int
        get() = Props.General.MOTIVATION

    override fun acceptDamage(damage: Int) {
        super.acceptDamage(max(0, damage - defense))
    }
}