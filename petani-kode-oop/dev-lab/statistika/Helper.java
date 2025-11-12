package statistika;

import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
    Scanner scan = new Scanner(System.in);

    public Helper() {}

    void debug(String msg){
        System.out.println(msg);
    }

    void skip(){
        System.out.println("\n");
    }

    void skip(int n){
        for(int i=0; i<n; i++){
            System.out.println("\n");
        }
    }

    void clear(){
        scan.close();
    }

    void readData(ArrayList<Float> data){
        int n = safeInputInt("Masukan banyak data: ", 0, 10000);
        data.ensureCapacity(n);

        for(int i=0; i<n; i++){
            System.out.print("Data " + (i+1) + ": ");
            float x = safeInputFloat("",-10000,10000);
            data.add(x);
        }
    }

    void printData(ArrayList<Float> data){
        System.out.print(data);
    }

    // safe input int
    public int safeInputInt(String prompt, int begin, int end){
        int ans;
        while(true){
            System.out.print(prompt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                scan.nextLine();
                if(ans < begin || ans > end){
                    System.out.println("Inputan diluar jangkauan!");
                    continue;
                }

                return ans;
            } else {
                System.out.println("Inputan tidak valid!");
                scan.nextLine();
            }
        }
    }

    // safe input float
    public float safeInputFloat(String prompt, float begin, float end){
        float ans;
        while(true){
            System.out.print(prompt);
            if(scan.hasNextFloat()){
                ans = scan.nextFloat();
                scan.nextLine();
                if(ans < begin || ans > end){
                    System.out.println("Inputan diluar jangkauan!");
                    continue;
                }

                return ans;
            } else {
                System.out.println("Inputan tidak valid!");
                scan.nextLine();
            }
        }
    }
}
