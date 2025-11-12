package dasaroop;

public class Anggota {
    public String nama;
    public String idAnggota;

    public Anggota(String nama, String idAnggota){
        this.nama = nama;
    }

    public Anggota(){}

    public void infoAnggota(){
        System.out.println("Nama : " + this.nama);
        System.out.println("Id   : " + this.idAnggota);
        System.out.println("\n");
    }


    public void pinjamBuku(Buku b) {

    }

    public void kembalikanBuku(Buku b) {

    }
}
