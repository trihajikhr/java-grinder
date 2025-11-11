import java.util.ArrayList;

public class Rerata {
    public static void main(String[] args){
        Matematika m =  new Matematika();
        Helper h =  new Helper();

        int n = h.safeInputInt("Masukan jumlah siswa: ");
        ArrayList<Float> nilai = new ArrayList<>();
        for(int i=0; i < n; i++){
            nilai.add(h.safeInputFloat("Masukan nilai ke-" + (i+1) + ": "));
        }

        System.out.print("\nNilai siswa      : " + nilai + "\n");
        System.out.println("Nilai tertinggi  : " + m.maxData(nilai));
        System.out.println("Nilai terendah   : " + m.minData(nilai));
        System.out.println("Rata-rata        : " + m.average(nilai));
    }
}