import java.util.Scanner;
import java.util.ArrayList;

class Matematika {
    int jumlah(int a, int b){
        return a+b;
    }

    boolean isPrima (int n){
        if(n <= 1) return false;
        if(n==2 || n==3) return true;
        for(int i=2; i*i <= n; i++){
            if(n % i == 0) return false;
        }
        return true;
    }

    float average(ArrayList<Float> nilai){
        if(nilai.isEmpty()){
            System.out.println("List kosong");
            return 0;
        }

        float sum = 0;
        for(float num : nilai) {
            sum += num;
        }
        return  sum/nilai.size();
    }

    float maxData(ArrayList<Float> nilai){
        if(nilai.isEmpty()){
            System.out.println("List kosong");
            return 0;
        }

        float max = Float.MIN_VALUE;
        for(float num : nilai) {
            if (num > max) max = num;
        }
        return max;
    }

    float minData(ArrayList<Float> nilai){
        if(nilai.isEmpty()){
            System.out.println("List kosong");
            return 0;
        }

        float min = Float.MAX_VALUE;
        for(float num : nilai) {
            if (num < min) min = num;
        }
        return min;
    }
}

class Helper {
    Scanner scan = new Scanner(System.in);

    // close scanner
    void close(){
        scan.close();
    }

    // angka
    int safeInputInt(String prompt){
        int ans;
        while(true){
            System.out.print(prompt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                break;
            }
            System.out.println("Input tidak valid! Harap masukkan angka.\n");
            scan.nextLine();
        }
        return ans;
    }

    // angka pecahan (float)
    float safeInputFloat(String prompt){
        float ans;
        while(true){
            System.out.print(prompt);
            if(scan.hasNextFloat()){
                ans = scan.nextFloat();
                break;
            }
            System.out.println("Input tidak valid! Harap masukkan angka.\n");
            scan.nextLine();
        }
        return ans;
    }

    // angka positif
    int safeInputPositif(String prompt){
        int ans, border = 0;
        while(true){
            System.out.print(prompt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                if(ans < border) {
                    System.out.println("Harap masukkan bilangan positif.\n");
                    continue;
                }
                break;
            }
            System.out.println("Input tidak valid! Harap masukkan angka.\n");
            scan.nextLine();
        }
        return ans;
    }
}


