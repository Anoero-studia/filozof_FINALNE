import java.util.Arrays;

class SharedState {
    private final boolean[] eating;
    private final boolean[] hasLeftFork;
    private final boolean[] hasRightFork;
    private final int[] eatCounts;
    private final int[] thinkCounts;

    public SharedState(int liczbaFilozof) {
        eating = new boolean[liczbaFilozof];
        hasLeftFork = new boolean[liczbaFilozof];
        hasRightFork = new boolean[liczbaFilozof];
        eatCounts = new int[liczbaFilozof];
        thinkCounts = new int[liczbaFilozof];
    }

    public synchronized void setEating(int id, boolean isEating) {
        eating[id] = isEating;
    }

    public synchronized void setHasLeftFork(int id, boolean hasLeft) {
        hasLeftFork[id] = hasLeft;
    }

    public synchronized void setHasRightFork(int id, boolean hasRight) {
        hasRightFork[id] = hasRight;
    }

    public synchronized void incrementEatCount(int id) {
        eatCounts[id]++;
    }

    public synchronized void incrementThinkCount(int id) {
        thinkCounts[id]++;
    }

    public synchronized boolean[] getEating() {
        return Arrays.copyOf(eating, eating.length);
    }

    public synchronized boolean[] getHasLeftFork() {
        return Arrays.copyOf(hasLeftFork, hasLeftFork.length);
    }

    public synchronized boolean[] getHasRightFork() {
        return Arrays.copyOf(hasRightFork, hasRightFork.length);
    }

    public synchronized int[] getEatCounts() {
        return Arrays.copyOf(eatCounts, eatCounts.length);
    }

    public synchronized int[] getThinkCounts() {
        return Arrays.copyOf(thinkCounts, thinkCounts.length);
    }
}
