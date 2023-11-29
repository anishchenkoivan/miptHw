package hw2;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class Judge implements Runnable{
    Game game;
    Player[] players;

    public Judge(Game game, Player... players) {
        this.players = players;
        this.game = game;
    }

    @Override
    public void run() {
        var executor = Executors.newFixedThreadPool(players.length);
        for (Player player : players) {
            executor.submit(player);
        }
        try {
            game.latch.await();
        } catch (InterruptedException e) {
            executor.shutdownNow();
        } finally {
            executor.shutdownNow();
        }
        System.out.println(getWinner().name);
    }

    public Player getWinner() {
        Player winner = players[0];
        int winnerScore = 0;
        for (Player player : players) {
            if (player.score > winnerScore) {
                winnerScore = player.score;
                winner = player;
            }
        }
        return winner;
    }
}
