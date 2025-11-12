package dasaroop;

public class Main {
    public static void main(String[] args) {

        Perpustakaan lib = new Perpustakaan();

        while(true) {
            int pil = lib.mainMenu();
            if (pil == 0) {
                System.out.println("\nAnda keluar dari Perpustakaan!");
                System.out.println("Terima kasih sudah berkunjung!");
                break;
            } else if (pil == 1){
                while(true) {
                    int a = lib.anggotaMenu();
                    if(a==0) break;

                }
            }
        }
    }
}
