package dasaroop;

import java.util.ArrayList;

public class Anggota {
    public int id;
    public String nama;
    ArrayList<Buku> buku = new ArrayList<>();

    public Anggota(String nama, String id){
        this.nama = nama;
    }

    public Anggota(){}

    public void infoAnggota(){
        System.out.println("Nama : " + this.nama);
        System.out.println("Id   : " + this.id);
        System.out.println("\n");
    }


    public void pinjamBuku(Buku b) {
        buku.add(b);
    }

    public void kembalikanBuku(int id) {
        if(this.buku.isEmpty()){
            System.out.println("Member ini tidak meminjam satupun buku!");
            return;
        }

        for(Buku t : buku){
            if(id == t.id){
                System.out.println("Member " + this.nama + " mengembalikan " + t.judul);
                buku.remove(t);
                break;
            }
        }
    }
}
