package dasaroop;

import java.util.ArrayList;

public class Perpustakaan {
    public ArrayList<Buku> book = new ArrayList<>();
    public ArrayList<Anggota> member = new ArrayList<>();
    Helper help = new  Helper();

    public Perpustakaan() {
        Buku book = new Buku(5, this);
    }

    public int mainMenu(){
        System.out.println("\n=== PERPUSTAKAAN ===");
        System.out.println("1. Menu Anggota");
        System.out.println("2. Menu Buku");
        System.out.println("0. Keluar");
        int pil = help.safeInputAngka("Pilihan anda: ", 0, 2);
        return pil;
    }

    public int anggotaMenu(){
        int pil = 0;
        while(true) {
            System.out.println("\n=== MENU ANGGOTA ===");
            System.out.println("1. Daftar Anggota");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Kurangi Anggota");
            System.out.println("4. Pinjam Buku");
            System.out.println("5. Kembalikan Buku");
            System.out.println("0. Keluar");
            pil = help.safeInputAngka("Pilihan anda: ", 0, 5);

            if (pil == 0){
                break;
            } else if (pil == 1) {
                tampilkanSemuaAnggota();
            } else if (pil == 2) {
                Anggota a = new Anggota();
                a.nama = help.safeInputStringLen("Masukan nama: ", 20);
                a.idAnggota = help.safeInputStringLen("Masukan id: ", 20);
                System.out.println("Menambahkah: ");
                a.infoAnggota();
                tambahAnggota(a);
            } else if(pil == 3) {
                if(member.isEmpty()){
                    System.out.println("Daftar Kosong");
                    continue;
                }
                while(true) {
                    tampilkanSemuaAnggota();
                    String id = help.safeInputStringLen("Masukan id: ",  1000);
                    boolean found = false;
                    for(Anggota a : member){
                        if(id.equals(a.idAnggota)){
                            System.out.println("Menyingkirkan " + a.nama + "!");
                            member.remove(a);
                            found = true;
                            break;
                        }
                    }
                    if(found) break;
                    else {
                        System.out.println("Id tidak ditemukan!");
                    }
                }
            } else if(pil == 4) {

            }
        }
        return pil;
    }

    public void bukuMenu(){

    }

    public void tamabhBuku(Buku b){
        book.add(b);
    }

    public void tambahAnggota(Anggota a){
        member.add(a);
    }

    public void kurangiAnggota(int id){

    }

    public void tampilkanSemuaBuku(){
        for(Buku b: book){
            b.tampilkanInfo();
        }
    }

    public void tampilkanSemuaAnggota(){
        for(Anggota a: member){
            a.infoAnggota();
        }
    }
}
