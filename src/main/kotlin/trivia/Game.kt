package trivia

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.List
import kotlin.random.Random

class Game {
    private val BOARD_SIZE = 12
    private var players = ArrayList<String>()
    private var places = IntArray(BOARD_SIZE)
    private var purses = IntArray(6)
    private var currentPlayer = 0
    private var categories = ArrayList<LinkedList<String>>()

    fun addCategories(categoryName1: String,categoryName2: String,categoryName3: String,categoryName4: String,categoryName5: String): Game {
        categories.add(addCategory(categoryName1))
        categories.add(addCategory(categoryName2))
        categories.add(addCategory(categoryName3))
        categories.add(addCategory(categoryName4))
        categories.add(addCategory(categoryName5))
        return this
    }

    fun addCategory(categoryName : String) : LinkedList<String> {
        var category = LinkedList<String>()
        for (i in 0..49) {
            category.addLast("$categoryName Question $i")
        }
        println("$categoryName was added")
        return category
    }

    fun run() {
        do nextPlay() while (thereIsNotWinner())
    }

    fun addPlayer(playerName: String): Game {
        players.add(playerName)
        places[players.size] = 0
        purses[players.size] = 0
        println("$playerName was added")
        println("They are player number ${players.size}")
        return this
    }

    private fun nextPlay() {
        nextPlayer()
        movePlayer()
        askQuestion()
    }

    private fun thereIsNotWinner(): Boolean {
        val isNotWinner = currentPlayerPursue != 6
        if (!isNotWinner) println("$currentPlayerName Wins!!!!!!!")
        return isNotWinner
    }

    private fun askQuestion() {
        println(getQuestion())
        if (Random.nextInt(2) == 1) {
            handleWrongAnswer()
        } else {
            handleCorrectAnswer()
        }
    }

    private fun movePlayer() {
        val roll = Random.nextInt(1, 7)
        println("$currentPlayerName rolled a $roll")
        places[currentPlayer] = (currentPlayerPlace + roll ) % BOARD_SIZE

        println("$currentPlayerName's new location is $currentPlayerPlace")
    }

    private fun getQuestion() = when (currentPlayerPlace) {
        0, 6-> categories[0].removeFirst()
        1, 7 -> categories[1].removeFirst()
        2, 8 -> categories[2].removeFirst()
        3, 9 -> categories[3].removeFirst()
        4, 10-> categories[4].removeFirst()
        else -> getRandomQuestion()
    }

    private fun getRandomQuestion(): String? {
        var randomTopicIndex = Random.nextInt(categories.size)
        return categories[randomTopicIndex].removeFirst()
    }

    private fun handleCorrectAnswer() {
        println("Answer was correct!!!!")
        purses[currentPlayer]++
        println("$currentPlayerName has $currentPlayerPursue points")
    }

    private fun handleWrongAnswer() {
        println("Question was incorrectly answered")
    }

    private fun nextPlayer() {
        currentPlayer++
        if (currentPlayer == players.size) {
            currentPlayer = 0
        }
        println("$currentPlayerName is the current player")
    }

    private val currentPlayerPlace get() = places[currentPlayer]

    private val currentPlayerPursue get() = purses[currentPlayer]

    private val currentPlayerName get() = players[currentPlayer]
}