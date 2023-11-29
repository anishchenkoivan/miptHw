package hw2;

import java.util.HashSet;
import java.util.concurrent.CountDownLatch;

public class Game{
    final CountDownLatch latch;
    final HashSet<Integer> set = new HashSet<Integer>();
    final int n;

    public Game (int n) {
        this.n = n;
        latch = new CountDownLatch(n);
    }
}
