package com.softserve.academy.models

class Healer : WarriorImpl(
    health = Props.Healer.HEALTH,
    attack = Props.Healer.ATTACK
) {
    val heal: Int = Props.Healer.HEAL

    
}