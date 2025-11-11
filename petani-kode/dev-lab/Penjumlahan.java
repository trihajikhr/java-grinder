public class Penjumlahan {
    public static void main(String[] args) {
        Matematika m = new Matematika();
        Helper h = new Helper();

        int a = h.safeInputInt("Masukan nilai a: ");
        int b = h.safeInputInt("Masukan nilai b: ");
        System.out.println("Hasil penjumlahan antara " + a + " dan " + b + " adalah " + m.jumlah(a,b) + "\n");

        h.close();
    }
}