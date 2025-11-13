package statistika;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Helper help = new Helper();
        Statistika stats = new Statistika();
        ArrayList<Float> data = new ArrayList<>();

        while(true) {
            System.out.println("===== PROGRAM STATISTIKA =====");
            System.out.println("1. Masukan data");
            System.out.println("2. Perbarui data");
            System.out.println("3. Isi Data");
            System.out.println("0. Keluar");
            int pil = help.safeInputInt("Pilihan anda: ", 0, 3);

            if(pil == 0) {
                break;
            } else if(pil == 1){
                help.readData(data);
            } else if(pil == 2){
                data.clear();
                help.readData(data);
            } else if(pil == 3){
                help.printData(data);
            }

            help.skip();

            while(true){
                System.out.println("===== RUMUS STATISTIKA =====");
                System.out.println("1. Cari nilai mean");
                System.out.println("2. Cari Median");
                System.out.println("3. Modus");
                System.out.println("4. Range");
                System.out.println("5. Variasi");
                System.out.println("6. Simpangan Bakuk");
                System.out.println("7. Koefisien Variasi");
                System.out.println("0. Keluar");
                int c = help.safeInputInt("Pilihan anda: ", 0, 7);

                if(c == 0) {
                    break;
                } else if(c == 1){
                    stats.mean(data);
                } else if(c == 2){
                    stats.median(data);
                } else if(c == 3){
                    stats.modus(data);
                } else if(c == 4) {
                    stats.range(data);
                }

                help.skip();
            }
            help.skip();
        }
    }
}
