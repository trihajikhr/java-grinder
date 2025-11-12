package dasaroop;

import java.util.ArrayList;

public class Buku {
    public String judul;
    public String penulis;
    public int stok;

    ArrayList<String> aJudul = new ArrayList<String>() {{
        add("Atomic Habbits");
        add("The dip");
        add("The Storm and the Fear");
        add("How to become Great");
        add("Your Potentials");
    }};

    ArrayList<String> aPenulis = new ArrayList<String>() {{
        add("James Clear");
        add("Daniel Kahnehman");
        add("Sun Tzu");
        add("Black Jack");
        add("Tarjan");
    }};

    ArrayList<Integer> aStok = new ArrayList<Integer>() {{
        add(9);
        add(11);
        add(23);
        add(5);
        add(2);
    }};

    public Buku(String judul, String penulis, int stok) {
        this.judul = judul;
        this.penulis = penulis;
        this.stok = stok;
    }

    public Buku(int num, Perpustakaan lib){
        for(int i = 0; i < num; i++){
            Buku b = new Buku(aJudul.get(i),aPenulis.get(i),aStok.get(i));
            lib.tamabhBuku(b);
        }
    }

    void tampilkanInfo() {
        System.out.println("Judul   : " + judul);
        System.out.println("Penulis : " + penulis);
        System.out.println("Stok    : " + stok);
        System.out.println("\n");
    }

    void kurangiStok(){
        if(this.stok > 0) this.stok = this.stok - 1;
    }

    void tambahStok(){
        this.stok = this.stok + 1;
    }
}
