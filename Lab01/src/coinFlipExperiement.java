public class coinFlipExperiement {
    public static void main(String[] args) {
        int amount = coinFlipExperiment();
        System.out.println("Win/Loss amount: " + amount);
    }
    public static int coinFlipExperiment() {
        int win = 0;
        for(int i = 0; i < 100; i++) {
            double flip = Math.random();
            if (flip < 0.505) {
                win++;
            } else {
                win--;
            }
        }
        return win;
    }
}
//ethan