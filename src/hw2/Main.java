package hw2;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(100);
        Player player1 = new Player("Player1", game);
        Player player2 = new Player("Player2", game);
        Judge judge = new Judge(game, player1, player2);
        Thread judgeThread = new Thread(judge);
        judgeThread.start();
    }
}
