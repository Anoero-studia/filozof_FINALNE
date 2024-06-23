import java.util.concurrent.locks.Lock;

class Filozof extends Thread {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;
    private final SharedState sharedState;

    public Filozof(int id, Lock leftFork, Lock rightFork, SharedState sharedState) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.sharedState = sharedState;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                pickUpForks();
                eat();
                putDownForks();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        sharedState.setEating(id, false);
        sharedState.incrementThinkCount(id);
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void pickUpForks() {
        leftFork.lock();
        sharedState.setHasLeftFork(id, true);
        rightFork.lock();
        sharedState.setHasRightFork(id, true);
    }

    private void eat() throws InterruptedException {
        sharedState.setEating(id, true);
        sharedState.incrementEatCount(id);
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void putDownForks() {
        leftFork.unlock();
        sharedState.setHasLeftFork(id, false);
        rightFork.unlock();
        sharedState.setHasRightFork(id, false);
    }
}
