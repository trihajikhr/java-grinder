package sqlite;

import java.util.Scanner;

public class Handler {
    Scanner scan = new Scanner(System.in);

    void skip(){
        System.out.println("\n");
    }

    void clearLine(){
        scan.nextLine();
    }

    void skip(int n){
        for(int i=0;i<n;i++){
            System.out.println("\n");
        }
    }

    // merubah nama table menjadi kecil, merubah spasi menjadi -
    String safeName(String promt, int n) {
        String ans;
        while(true){
            System.out.print(promt);
            ans = scan.nextLine();
            if(ans.length() > n){
                System.out.println("Terlalu panjang!");
                clearLine();
            } else {
                clearLine();
                break;
            }
        }

        ans = ans.toLowerCase();
        StringBuilder rest = new StringBuilder();
        boolean lastWasSpace = false;

        for(int i = 0; i < ans.length(); i++){
            char c = ans.charAt(i);
            if(c == ' '){
                if(!lastWasSpace){
                    rest.append('_');
                    lastWasSpace = true;
                }
            } else {
                rest.append(c);
                lastWasSpace = false;
            }
        }

        return rest.toString();
    }

    // inputan karakter
    public Character safeChar(String promt) {
        char c;
        while(true){
            System.out.print(promt);
            c = scan.next().charAt(0);
            if(Character.isLetter(c)){
                break;
            } else{
                System.out.println("Inputan invalid!");
                clearLine();
            }
        }
        return c;
    }

    // inputan angka
    public int safeInt(String promt, int begin, int end) {
        int ans;
        while(true){
            System.out.print(promt);
            if(scan.hasNextInt()){
                ans = scan.nextInt();
                if(ans < begin || ans > end){
                    System.out.println("Inputan invalid!");
                } else {
                    break;
                }
            } else {
                System.out.println("Inputan invalid!");
            }
            clearLine();
        }
        return ans;
    }
}
