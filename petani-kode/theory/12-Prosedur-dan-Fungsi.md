# 12 | Prosedur dan Fungsi pada Java

![](media/img.png)

Fungsi `main()` adalah fungsi utama dalam program Java. Semua kode yang kita tulis didalamnya akan langsung dieksekusi.

Tapi program yang besar membutuhkan yang namanya modularitas, sehingga menuliskan semua kode program pada fungsi `main()` jelas bukan merupakan praktik yang baik.

Oleh karena itu, digunakanlah prosedur / fungsi. Berguna untuk memecah program menjadi beberapa sub-program, menjadikan kode program lebih efisien, modular, mudah dipelihara, _reusability_, dan mudah diperluas. Intinya lebih banyak keunggulan lah.

## 1 | Pengertian

**Prosedur** adalah sebutan untuk fungsi yang tidak mengembalikan nilai. Fungsi ini biasanya ditandai dengan kata kunci `void` sebelum menuliskan nama fungsi tersebut.

**Fungsi** adalah sebutan untuk fungsi yang mengembalikan nilai.

**Method** adalah **fungsi** yang berada didalam Class. Sebutan ini biasanya digunakan pada OOP.

Tapi untuk kemudahan, kita sebut saja sebagai **fungsi**.

## 2 | Membuat Fungsi di Java

Fungsi harus dibuat atau ditulis didalam class. Contoh:

```java
static TypeDataKembalian namaFungsi(){
    // statement atau kode fungsi
}
```
Penjelasan:

Kata kunci `static`, artinya kita membuat fungsi yang dapat dipanggil tanpa harus membuat instansiasi objek. Lebih jelasnya akan dijelaskan dibawah. Konsepnya sama seperti C++ jika sebelumnay sudah pernah menggunakan OOP dengan C++.

`TypeDataKembalian` adalah tipe data dari nilai yang dikembalikan setelah fungsi dieksekusi.

`namaFungsi()` adalah nama fungsinya. Biasanya ditulis dengan huruf kecil di awalnya. Lalu, kalau terdapat lebih dari satu suku kata, huruf awal di kata kedua ditulis kapital.

Contoh:

```java
static void ucapSalam(){
    System.out.println("Selamat Pagi");
}
```

Tipe data `void` artinya kosong, fungsi tersebut tidak mengembalikan nilai apa-apa.

## 3 | Memanggil Fungsi

Fungsi dapat dipanggil dari fungsi `main` atau dari fungsi yang lainya.

Contoh pemanggilan fungsi dari `main`:

```java
public static void main(String[] args){
    ucapSalam();
}
```

Berikut kode lengkapnya:

```java
class BelajarFungsi {
    
    // membuat fungsi ucapSalam()
    static void ucapSalam(){
        System.out.println("Selamat Pagi");
    }

    // membuat fungsi main()
    public static void main(String[] args){
        // memanggil/eksekusi fungsi ucapSalam()
        ucapSalam();
    }
}
```

## 4 | Fungsi dengan Paramter

Parameter adalah variabel yang menampung nilai untuk diproses didalam fungsi. Parameter berperan sebagai inputan untuk fungsi.

Struktur dasarnya seperti ini:

```java
static TipeData namaFungsi(TipeData namaParameter, TipeData namaParameterLain){
    // kode fungsi
}
```

Penjelasan:

- Parameter ditulis di antara tanda kurung `(...)`
- Parameter harus diberikan tipe data
- Bila terdapat lebih dari satu parameter, maka dipisah dengan tanda koma.

Contoh fungsi yang memiliki parameter:

```java
static void ucapin(String ucapan){
    System.out.println(ucapan);
}
```

Cara pemanggilan fungsi yang memiliki parameter:

```java
ucapin("Hallo!");
ucapin("Selamat datang di pemrograman Java");
ucapin("Saya kira ini bagian terakhir");
ucapin("Sampai jumpa lagi, ya!");
```
## 5 | Fungsi yang Mengembalikan Nilai

Setelah fungsi memproses data yang diinputkan melalui parameter, selanjutnya fungsi harus mengembalikan nilai agar dapat diolah pada proses berikutnya.

Pengembalian nilai pada fungsi dapat menggunakan kata kunci `return`.

Contoh:

```java
static int luasPersegi(int sisi){
    int luas = sisi * sisi;
    return luas;
}
```

Pada contoh tersebut, kita membuat sebuah parameter bernama `sisi`. Kemudian fungsi akan mengembalikan nilai dengan tipe `int` (integer) dari variabel `luas`.

Contoh pemanggilan adalah:

```java
System.out.println("Luas Persegi dengan panjang sisi 5 adalah " + luasPersegi(5));
```

## 6 | Pemanggilan Fungsi di Fungsi

Fungsi-fungsi dapat saling memanggil untuk memproses data.

Contoh, sebuah program Kalkulator Bangun Ruang memiliki fungsi: `luasPersegi()`, `luasPersegiPanjang()`, `luasSegitiga()`, `luasBalok()`, `luasKubus()`, dsb.

Fungsi-fungsi tersebut dapat saling membantu, contoh fungsi `luasKubus()` membutuhkan fungsi `luasPersegi()`.

Maka programnya dapat dibuat seperti ini:

```java
public class BangunRuang {

    public static void main(String[] args) {
        int s = 12;
        int luas = luasKubus(s);

        System.out.println(luas);
    }

    // membuat fungsi luasPersegi()
    static int luasPersegi(int sisi){
        return sisi * sisi;
    }

    // membuat fungsi luasKubus()
    static int luasKubus(int sisi){
        
        // memanggil fungsi luasPersegi
        return 6 * luasPersegi(sisi);
    }

}
```

## 7 | Fungsi Static dan Non-Static

Pada contoh-contoh diatas, kita menggunakan kata kunci `static` sebelum membuat fungsi.

Kata kunci `static` akan membuat fungsi dapat dieksekusi langsung, tanpa harus membuat instansiasi objek dari class.

```java
public class FungsiStatic {
    
    // Fungsi non-static
    void makan(String makanan){
        System.out.println("Hi!");
        System.out.println("Saya sedang makan " + makanan);
    }
    
    // fungsi static
    static void minum(String minuman){
        System.out.println("Saya sedang minum " + minuman);
    }
    
    // fungsi main
    public static void main(String[] args) {
        
        // pemanggilan fungsi static
        minum("Kopi");
        
        
        // mambuat instansiasi objek saya dari class FungsiStatic
        FungsiStatic saya = new FungsiStatic();
        // pemanggilan fungsi non-static
        saya.makan("Nasi Goreng");
        
    }
    
}
```

Pada contoh tersebut, fungsi `makan()` adalah fungsi non-static. Sedangkan fungsi `minum()` adalah fungsi static.

## 8 | Variabel Global dan Lokal

Variabel global adalah variabel yang bisa diakses dari semua fungsi. Sedangkan variabel lokal adalah variabel yang hanya bisa diakses dari dalam fungsi tempat variabel itu berada. 

Contoh:

```java
class ProgramKu{

    // ini variabel global
    static String nama = "Programku";
    static String version = "1.0.0";

    static void help(){

        // ini variabel lokal
        String nama = "Petani Kode";

        // mengakses variabel global di dalam fungsi help()
        System.out.println("Nama: " + nama);
        System.out.println("Versi: " + version);
    }

    public static void main(String args[]){
        
        // panggil fungsi help()
        help();
        
        System.out.println("Nama: " + nama);
        System.out.println("Versi: " + version);

    }
}
```

Saat pemanggilan fungsi `help()` kita membuat ulang variabel `nama`. Sehingga variabel `nama` menjadi variabel lokal pada fungsi `help()` dan nilainya berubah menjadi `Petani Kode`.

Sedangkan, saat kita akses lagi variabel `nama` melalui fungsi `main()` nilainya tetap sama seperti yang didefinisikan.
