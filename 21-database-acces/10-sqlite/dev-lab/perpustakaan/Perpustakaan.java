package perpustakaan;

public class Perpustakaan {
    public String nama;
    Handler hand = new Handler();
    Database data = new Database();

    public Perpustakaan(String name) {
        this.nama = name;
    }

    public void mainMenu(){
        while(true) {
            System.out.println("===== PERPUSTAKAAN " + this.nama + " =====");
            String menu = """
                    1. Daftar Pustakawan
                    2. Tambah Pustakawan 
                    3. Daftar Mahasiswa
                    4. Tambah Mahasiswa
                    5. Daftar Buku
                    6. Tambah Buku
                    7. Peminjaman
                    8. Pengembalian
                    0. Keluar
                    """;
            System.out.println(menu);
            int pil = hand.safeInt("Pilihan Anda: ", 0, 8);

            if (pil == 0) {
                break;
            } else if(pil == 1) {
                data.tampilPustakawan();
            } else if(pil == 2){
                data.tambahPustakawan();
            } else if(pil == 3){
                data.tampilMahasiswa();
            } else if(pil == 4){
                data.tambahMahasiswa();
            } else if(pil == 5){
                data.daftarBuku();
            } else if(pil == 6){
                data.tambahBuku();
            }
        }
    }
}
