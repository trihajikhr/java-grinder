import java.util.ArrayList;

public class Fibonacci {
    public static void main(String[] args) {
        Helper h = new Helper();
        Deret d = new Deret();
        int n = h.safeInputPositif("Masukan jumlah suku: ");

        ArrayList<Integer> fibo = d.fibonaci(n);
        System.out.println("Deret Fibonaci: " + fibo);
    }
}
