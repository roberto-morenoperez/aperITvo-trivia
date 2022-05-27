package trivia

import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class Game {
    private var players = ArrayList<String>()
    private var places = IntArray(12)
    private var purses = IntArray(6)
    private var popQuestions = LinkedList<String>()
    private var scienceQuestions = LinkedList<String>()
    private var sportsQuestions = LinkedList<String>()
    private var rockQuestions = LinkedList<String>()
    private var currentPlayer = 0

    init {
        for (i in 0..49) {
            popQuestions.addLast("Pop Question $i")
            scienceQuestions.addLast("Science Question $i")
            sportsQuestions.addLast("Sports Question $i")
            rockQuestions.addLast("Rock Question $i")
        }
    }

    fun addPlayer(playerName: String) {
        players.add(playerName)
        places[players.size] = 0
        purses[players.size] = 0
        println("$playerName was added")
        println("They are player number ${players.size}")
    }

    fun nextPlay() {
        nextPlayer()
        movePlayer()
        askQuestion()
    }

    fun didPlayerNotWin(): Boolean {
        val notWins = currentPlayerPursue != 6
        if (!notWins) {
            println("$currentPlayerName Wins!!!!!!!")
        }
        return notWins
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
        places[currentPlayer] = currentPlayerPlace + roll
        if (currentPlayerPlace > 11) {
            places[currentPlayer] = currentPlayerPlace - 12
        }
        println("$currentPlayerName's new location is $currentPlayerPlace")
    }

    private fun getQuestion() = when (currentPlayerPlace) {
        0, 4, 8 -> popQuestions.removeFirst()
        1, 5, 9 -> scienceQuestions.removeFirst()
        2, 6, 10 -> sportsQuestions.removeFirst()
        else -> rockQuestions.removeFirst()
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