package softserve.academy

import java.time.Year
import java.util.UUID

fun main() {
    val acc: Person.Account = Person.Account()
    val person = Person("John")
    val bankAccount = BankAccount(100)
    bankAccount.Transaction().pay(10)
    bankAccount.Transaction().pay(20)
    val transaction = bankAccount.Transaction()
    transaction.pay(30)
    transaction.pay(30)
}

sealed interface I

class AI : I

class BankAccount(private var balance: Int) {
    private val id: UUID = UUID.randomUUID()

    inner class Transaction {
        private val id: UUID = UUID.randomUUID()

        fun pay(amount: Int) {
            balance -= amount
            println("paying $amount from account #${this@BankAccount.id} as" +
                    " transaction #${this.id} ")
        }
    }
}

class Person(val firstName: String) {
    class Account {
        fun foo(person: Person) {
            println(person.firstName)
        }
    }
}


interface CanPassExam {
    fun passExam()
    fun foo() {}
    var grade: Int
}

interface B

abstract class Human(val name: String) {
    abstract fun hello()
    abstract val age: Int
}

class Student(
    name: String,
    private val yearOfBirth: Year = Year.now()
) : Human(name), CanPassExam, B {

    override var grade: Int
        get() = TODO("Not yet implemented")
        set(value) {
            TODO()
        }

    override fun passExam() {
        TODO("Not yet implemented")
    }

    override val age: Int
        get() = Year.now().value - yearOfBirth.value

    override fun hello() {
        println("Hello, my name is $name, I like programming!")
    }
}