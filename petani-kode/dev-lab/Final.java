import java.util.HashMap;

public class Final {
    public static void main(String[] args){
        Helper h = new Helper();
        Statistik stat = new Statistik();

        System.out.println("=== Input Nilai Siswa ===");
        HashMap<String, Float> siswa = new HashMap<>();
        while(true){
            String input = h.safeInputStringLength("Masukkan nama siswa (atau 'exit' untuk selesai): ", 20);
            if(input.equalsIgnoreCase("exit")){
                break;
            }

            float f = h.safeInputFloat("Masukan nilai " + input + ": ");
            siswa.put(input, f);
        }

        System.out.println("\n=== Statistik Nilai ===");
        System.out.println("Jumlah siswa     : " + siswa.size());
        System.out.println("Rata-rata nilai  : " + stat.average(siswa));
        System.out.println("Nilai tertinggi  : " + stat.maxData(siswa));
        System.out.println("Nilai terendah   : " + stat.minData(siswa));
    }
}