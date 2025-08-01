package softserve.academy.demo

fun main() {
    val names = mutableListOf("John", "Denise", "Phoebe", "Adam")
    println(names)
    names += "Gryff"
    println(names)
    names -= "Adam"
    println(names)
    val list2 = names.toList()
    val list3 = list2.toMutableList()
    list2[0] == list2.get(0)
    list2.last()
    names.forEach { println(it) }
    val sortableTitles = mutableListOf<String>()
}