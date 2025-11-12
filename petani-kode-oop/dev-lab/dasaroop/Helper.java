package dasaroop;

import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
    Scanner scan = new Scanner(System.in);

    Helper() {}

    // safeinput angka
    public int safeInputAngka(String prompt, int begin, int end){
        int ans;
        while(true) {
            System.out.print(prompt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                scan.nextLine();

                if(ans < begin || ans > end) {
                    System.out.println("Input diluar jangkauan!");
                    continue;
                }

                break;
            } else {
                System.out.println("Input tidak valid!");
            }
        }
        return ans;
    }

    // safeinput panjang string
    public String safeInputStringLen(String prompt, int border){
        String ans;
        while(true) {
            System.out.print(prompt);
            ans = scan.nextLine();
            scan.nextLine();

            if(ans.length() <= border){
                break;
            } else {
                System.out.println("Terlalu panjang!");
            }
        }
        return ans;
    }

    // safeinput range Array
    public <T> int safeInputArray(String prompt, ArrayList<T> arr){
        int ans;
        while(true) {
            System.out.print(prompt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                ans--;
                scan.nextLine();
                if(ans < 0 || ans >= arr.size()){
                    System.out.println("Diluar jangkauan");
                    continue;
                }

                break;
            } else {
                System.out.println("Input tidak valid!");
            }
        }
        return ans;
    }

    // safeinput id (Anggota)
    public int safeInputIdAnggota(String prompt, ArrayList<Anggota> arr){
        int ans;
        while(true) {
            System.out.print(prompt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                scan.nextLine();
                for(Anggota t : arr){
                    if(t.id == ans) {
                        return ans;
                    }
                }
            } else {
                System.out.println("Input tidak valid!");
            }
        }
    }

    // safeinput id (Buku)
    public int safeInputIdBuku(String prompt, ArrayList<Buku> arr){
        int ans;
        while(true) {
            System.out.print(prompt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                scan.nextLine();
                for(Buku t : arr){
                    if(t.id == ans) {
                        return ans;
                    }
                }
            } else {
                System.out.println("Input tidak valid!");
            }
        }
    }
}
