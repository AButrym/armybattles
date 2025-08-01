package softserve.academy.demo

fun main() {
    val toolbox = mutableMapOf(
        "Nail" to "Hammer",
        "Hex Nut" to "Wrench",
        "Hex Bolt" to "Wrench",
        "Slotted Screw" to "Slotted Screwdriver",
        "Phillips Screw" to "Phillips Screwdriver",
    )
    toolbox["Lumber"] = "Saw"
    toolbox -= "Lumber"
    println(toolbox)
    println(toolbox::class.java)
    toolbox.forEach { (hardware, tool) ->
        println("Use a $tool on a $hardware")
    }

    val screwdrivers = toolbox.filter { (_, tool) ->
        "Screwdriver" in tool
    }

    val newToolbox = toolbox
        .mapKeys { entry -> entry.key.replace("Hex", "Flange") }
        .mapValues { entry -> entry.value.replace("Wrench", "Ratchet") }
}

fun main1() {
    val namesToAge = mapOf("John" to 30, "Denise" to 25)
    println(namesToAge["John"])
    println(namesToAge["Gryff"]) // null
    println(namesToAge.getOrDefault("Gryff", -1))
    println(namesToAge.getOrElse("Gryff") { -1 })
    println(namesToAge["Gryff"] ?: -1)
    println(namesToAge.keys)
    println(namesToAge.values)
    println(namesToAge.filter { (key, value) -> key.contains("n") && value > 30 })
    println(namesToAge + Pair("Adam", 20))
    println(namesToAge + Pair("Adam", 30))
    val namesToAgeWithDefaults = namesToAge.withDefault { it -> -1 }
    println(namesToAgeWithDefaults["Gryff"])
    println(namesToAgeWithDefaults.getValue("Gryff"))
    // println(namesToAge.getValue("Gryff")) // throws an exception if the key is not present
}