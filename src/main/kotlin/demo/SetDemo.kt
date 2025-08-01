package softserve.academy.demo

fun main() {
    val names = setOf("John", "Denise", "Phoebe", "Adam")
    val names1 = hashSetOf("John", "Denise", "Phoebe", "Adam")
    println("John" in names)
    println("Gryff" in names)
    println(names::class.java)
    println(names1::class.java)
    println(names)
    println(names1)
}