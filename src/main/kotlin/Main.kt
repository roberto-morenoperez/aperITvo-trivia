import trivia.Game

fun main() {
    val game = Game()

    game.addPlayer("Chet")
    game.addPlayer("Pat")
    game.addPlayer("Sue")

    do game.nextPlay() while (game.didPlayerNotWin())
}