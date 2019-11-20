public class Code {
    public static boolean equals(String a, String b) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a == b;
    }
}