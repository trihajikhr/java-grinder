import java.util.Scanner;

public class Helper {
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
                scan.nextLine();
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
                scan.nextLine();
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
                scan.nextLine();
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

    // safe input batas string
    String safeInputStringLength(String prompt, int t){
        String ans;
        while(true){
            System.out.print(prompt);
            ans = scan.nextLine();
            if(ans.length() <= t){
                break;
            }

            System.out.println("Nama terlalu panjang (max 20 karakter)!");
            scan.nextLine();
        }
        return ans;
    }
}