package exception;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Handler {
    Scanner scan = new Scanner(System.in);

    int coba() throws InputMismatchException, AngkaKelewatBatas {
        System.out.println("Masukkan dua angka: ");
        int a = scan.nextInt();

        if(a > 1_000_000){
            throw new AngkaKelewatBatas("Angka melebihi batas!");
        }

        int b = scan.nextInt();

        if(b > 1_000_000){
            throw new AngkaKelewatBatas("Angka melebihi batas!");
        }

        return a + b;
    }

    void tambah() {
        int ans;
        try {
            ans = coba();
            System.out.println("Hasil: " + ans);
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid, hanya angka yang diperbolehkan.");
        } catch (AngkaKelewatBatas e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class AngkaKelewatBatas extends Exception {
    public AngkaKelewatBatas(String msg) {
        super(msg);
    }
}