package perpustakaan;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Handler {
    Scanner scan = new Scanner(System.in);

    public Handler() {}

    public void skip(){
        System.out.println("\n");
    }

    public void skip(int n){
        for(int i=0; i<n; i++){
            System.out.println("\n");
        }
    }

    public void clearLine(){
        scan.nextLine();
    }

    // inputan angka aman (int)
    public int safeInt(String prompt, int begin, int end){
        int ans;
        while(true){
            System.out.print(prompt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                clearLine();
                if(ans < begin || ans > end){
                    System.out.println("Input diluar pilihan!");
                    clearLine();
                } else {
                    break;
                }
            } else {
                System.out.println("Input invalid!");
                clearLine();
            }
        }
        return ans;
    }

    // inputan angka aman (float)
    public float safeFloat(String prompt, float begin, float end){
        float ans;
        while(true){
            System.out.print(prompt);
            if(scan.hasNextFloat()){
                ans = scan.nextFloat();
                if(ans < begin || ans > end){
                    System.out.println("Input diluar pilihan!");
                    clearLine();
                } else {
                    break;
                }
            } else {
                System.out.println("Input invalid!");
                clearLine();
            }
        }
        return ans;
    }

    // inputan String memiliki batas
    public String safeString(String prompt, int n){
        String ans;
        while(true){
            System.out.print(prompt);
            ans = scan.nextLine();
            if(ans.length() > n){
                System.out.println("Inputan terlalu panjang!");
                clearLine();
            } else {
                break;
            }
        }
        return ans;
    }

    // safe pengecekan arraylist
    public int safeArrayInput(String prompt, ArrayList<Integer> data) {
        int ans;
        while(true){
            System.out.print(prompt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                boolean found = false;
                for(int i=0; i< data.size(); i++){
                    if(data.get(i) == ans){
                        found = true;
                        break;
                    }
                }

                if(!found){
                    System.out.println("Input diluar pilihan!");
                    clearLine();
                } else {
                    break;
                }
            } else {
                System.out.println("Input tidak valid");
                clearLine();
            }
        }
        return ans;
    }
}
