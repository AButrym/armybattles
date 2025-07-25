package softserve.academy

fun fight(first: Warrior, second: Warrior): Boolean {
    while (first.isAlive && second.isAlive) {
        first hits second
        if (second.isAlive) {
            second hits first
        }
    }
    return first.isAlive
}

//fun fight(first: Army, second: Army): Boolean {
//    while (first.isAlive and second.isAlive) {
//        fight(first.champion, second.champion)
//    }
//    return first.isAlive
//}

fun fight(first: Army, second: Army): Boolean {
    val firstIterator = first.iterator()
    if (!firstIterator.hasNext()) return false

    val secondIterator = second.iterator()
    if (!secondIterator.hasNext()) return true

    var firstChampion = firstIterator.next()
    var secondChampion = secondIterator.next()

    while (true) {
        val res = fight(firstChampion, secondChampion)
        if (res) {
            if (!secondIterator.hasNext()) { return true }
            secondChampion = secondIterator.next()
        } else {
            if (!firstIterator.hasNext()) { return false }
            firstChampion = firstIterator.next()
        }
    }
}