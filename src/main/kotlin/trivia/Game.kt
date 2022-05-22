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
        println("They are player number " + players.size)
    }

    fun nextPlay() {
        setNextPlayer()
        movePlayer()
    }

    fun askQuestion() {
        println(getQuestion())
        if (Random.nextInt(2) == 1) {
            handleWrongAnswer()
        } else {
            handleCorrectAnswer()
        }
    }

    fun didPlayerNotWin(): Boolean {
        val notWins = getCurrentPlayerPursue() != 6
        if (!notWins) {
            println("${getCurrentPlayer()} Wins!!!!!!!")
        }
        return notWins
    }

    private fun movePlayer() {
        val roll = Random.nextInt(5) + 1
        println("${getCurrentPlayer()} rolled a $roll")
        places[currentPlayer] = getCurrentPlayerPlace() + roll
        if (getCurrentPlayerPlace() > 11) {
            places[currentPlayer] = getCurrentPlayerPlace() - 12
        }
        println("${getCurrentPlayer()}'s new location is ${getCurrentPlayerPlace()}")
    }

    private fun getQuestion() = when (getCurrentPlayerPlace()) {
        0, 4, 8 -> popQuestions.removeFirst()
        1, 5, 9 -> scienceQuestions.removeFirst()
        2, 6, 10 -> sportsQuestions.removeFirst()
        else -> rockQuestions.removeFirst()
    }

    private fun handleCorrectAnswer() {
        println("Answer was correct!!!!")
        purses[currentPlayer]++
        println("${getCurrentPlayer()} has ${getCurrentPlayerPursue()} Points.")
    }

    private fun handleWrongAnswer() {
        println("Question was incorrectly answered")
    }

    private fun setNextPlayer() {
        currentPlayer++
        if (currentPlayer == players.size) {
            currentPlayer = 0
        }
        println("${getCurrentPlayer()} is the current player")
    }

    private fun getCurrentPlayerPlace() = places[currentPlayer]

    private fun getCurrentPlayerPursue() = purses[currentPlayer]

    private fun getCurrentPlayer() = players[currentPlayer]
}