package com.softserve.academy.models

class Knight : BaseWarrior(
    health = Props.Knight.HEALTH
) {
    override val attack: Int
        get() =  Props.Knight.ATTACK
}