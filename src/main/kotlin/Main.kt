import trivia.Game

fun main() {
    Game()
        .addPlayer("Chet")
        .addPlayer("Pat")
        .addPlayer("Sue")
        .addCategories("Rock", "Science", "Pop", "Geography", "Art")
        .run()
}