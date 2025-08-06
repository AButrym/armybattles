package softserve.academy.demo

import kotlin.math.sqrt

fun main() {
    //       this       it     no
    // obj   apply     also
    // res   run,with  let    run

    var list1 = listOf(1, 2, 3)
    val list2 = mutableListOf(4, 5, 6)
    list1 += list2

    val v1: Vector = Vector(1, 2)
    val v2 = Vector(3, -4)
    with(v1) {
        println("v1.x = $x")
        println("v1.y = $y")
    }
    v1.run {
        println("v1.x = $x")
        println("v1.y = $y")
    }
    val res = run {
        println("v1.x = ${v1.x}")
        println("v1.x = ${v1.x}")
    }

    val res1 = v1.let { println(it) }
    val res2 = v1.also { println(it) }

    var a = 1
    var b = 2
    a = b.also { b = a }

    println("v1.x = ${v1[0]}")
    println(v1)
    println(v2)
    println("v1 + v2 = ${v1 + v2}")
    println("3 * v1 = ${3 * v1}")
    println("v1 * 3 = ${v1 * 3}")
    println("v1 > v2 = ${v1 > v2}")
}

data class Vector(val x: Int, val y: Int) {
    override fun toString(): String {
        return "v($x,$y)"
    }

    operator fun get(ix: Int): Int = when(ix) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("Invalid coordinate $ix")
    }

    val abs: Double get() = sqrt(x * x + y * y.toDouble())

    operator fun compareTo(other: Vector): Int =
        this.abs.compareTo(other.abs)
}

operator fun Vector.plus(other: Vector) =
    Vector(x + other.x, y + other.y)
operator fun Int.times(other: Vector): Vector =
    Vector(this * other.x, this * other.y)
operator fun Vector.times(other: Int): Vector =
    Vector(this.x * other, this.y * other)