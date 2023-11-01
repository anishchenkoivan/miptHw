package hw2;

public class Player implements Runnable {
    Game game;
    String name;
    int score = 0;

    public Player (String name, Game game) {
        this.game = game;
        this.name = name;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (game.set) {
                int generatedNumber = generateNumber();
                if (!game.set.contains(generatedNumber)) {
                    game.set.add(generatedNumber);
                    this.score++;
                }
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public int generateNumber() {
        return 1 + (int) (Math.random() * game.n);
    }
}
