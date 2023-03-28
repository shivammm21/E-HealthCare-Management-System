public class Processbar extends Thread {
    public void run() {
        for (int i = 0; i < 25; i++) {
            System.out.print("--");
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();
    }
}
