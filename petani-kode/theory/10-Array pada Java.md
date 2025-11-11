# 10 | Array pada Java

![](media/array.png)

Ingat sekali lagi, Java mengikuti banyak gaya penulisan dari bahasa C++, sehingga jika sudah paham array pada C++, harusnya tidak terlalu sulit untuk memahami array pada Java.

Materi kali ini hanya akan membahas poin-poin penting dan dasar dari array pada Java, selebihnya memiliki sifat yang sama seperti array pada C++, misal indeks pertama dimulai dari 0, dan diakses dengan menggunakan parameter `[]`.

## 1 | Apa itu Array

Array adalah variabl yang bisa menyimpan banyak data, dan diakses dengan menggunakan indeks, yang dimulai dari 0.

Aku tidak akan terlalu detail disini, terlalu basic untuk dijelaskan terlalu panjang!

## 2 | Membuat array pada Java

Berikut adalah cara untuk membuat array pada Java:

```java
// cara pertama
String[] nama;

// cara kedua
String nama[];

// cara ketiga dengan kata kunci new
String[] nama = new String[5];
```

- Digunakan kurung siku `[]` untuk membuat array.
- Kurung siku bisa diletakan setelah tipe data atau nama array.
- Angka `5` dalam kurung siku artinya adalah ukuran array yang dibuat, yang berarti indeks `0` sampai `4`.

Cara pengisian array ada beberapa cara, sama seperti C++ yaitu seperti berikut:

```java
nama[0] = "Linda";
nama[1] = "Santi";
nama[2] = "Susan";
nama[3] = "Mila";
nama[4] = "Ayu";
```
```java
String[] nama = {"Linda", "Santi", "Susan", "Mila", "Ayu"};
```

## 3 | Mengambil Data dari Array
Gunakan saja indeks untuk mengakses data pada array, yaitu diantara `0` hingga `n-1`, dimana `n` adalah ukuran array.

Program berikut akan menghasilkan output berupa `Susan`:

```java
// membuat array
String[] nama = {"Linda", "Santi", "Susan", "Mila", "Ayu"};

// mengambil data array
System.out.println(teman[2]);
```

## 4 | Mengambil Perulangan

Konsepnyanya sama seperti C++, tidak jauh berbeda, yang mana biasanya menggunakan perulangan `for`.

Tapi karena tutorial ini tidak ada `for each`, jadi aku akan menjelaskan tentang perulangan yang baru ini:

Perulangan `for each` adalah perulangan yang akan mengambil ukuran dari array secara langsung, dan melakukan iterasi dari awal hingga akhir. 

Kelebihanya adalah lebih simple, tapi kekuranganya adalah tidak bisa menambahkan beberapa manipulasi pada proses iterasi.

Perulangan ini bisa digunakan untuk array biasa, Arraylist, Map, dan semacamnya.

```java
public class ContohForEach {
    public static void main(String[] args) {
        int[] angka = {10, 20, 30, 40, 50};

        // Menggunakan for-each
        for (int nilai : angka) {
            System.out.println(nilai);
        }
    }
}

```

Gunakan dengan bijak, sesuaikan dengan kondisi saja.

## 5 | Array Multidimensi

Artinya array yang memiliki lebih dari satu dimensi. Jika sudah pernah menggunakan C++, cukup diketahui penggunaanya tidak jauh berbeda, mungkin akan ditampilkan saja cara pendeklarasian awalnya saja:

```java
String[][] kontak = {
    {"Lili","08111"},
    {"Lala","08122"},
    {"Maya","08133"}
};
```
## 6 | Array List

Array biasa memiliki beberapa kekurangan, seperti:
- Tidak mampu menyimpan data dengan tipe yang berbeda.
- Ukuranya tidak dinamis, alias statis. Artinya ukuran array tidak akan berubah sepanjang runtime program.

`ArrayList` merupakan sebuah class yang memungkinkan kita membuat sebuah objek untuk menampung apapun. Untuk menggunakan ArrayList, import classnya terlebih dahulu:

```java
import java.util.ArrayList;
```

Setelah itu, buat objek ArrayList seperti ini:

```java
 ArrayList al = new ArrayList();
```

Cara diatas adalah cara membuat ArrayList dengan tipe data acak.

Tapi, ArrayList tidak bisa langsung digunakan untuk tipe data primitif seperti int atau double. Melainkan harus menggunakan kelas pembungkus (wrapper class) seperti `Integer` atau `Double` karena ArrayList hanya bisa menyimpan objek. Penggunaan kelas pembungkus ini disebut `autoboxing`.

```java
// tipe data autoboxing
Arraylist<Integer> usia = new ArrayList<>();
Arraylist<Double> ipk = new ArrayList<>();
Arraylist<Character> kelas = new ArrayList<>();
Arraylist<Float> ipk_semester = new ArrayList<>();
Arraylist<String> nama = new ArrayList<>();

// class objek
Arraylist<Mahasiswa> mhs = new ArrayList<>();
```

> Jika sudah paham struktur data Vector pada C++, maka Arraylist ibarat Vector-nya Java. Gampang kan.. 😀

ArrayList memiliki beberapa method bawaan yang berguna untuk bekerja bersama Arraylist, contohnya adalah sebagai berikut:

1. Fungsi `add(Obj x)` untuk menambahkan data `x` kedalam ArrayList, data ditaruh diujung belakang ArrayList. Tapi dengan menambahkan parameter seperti `add(idx, Obj x)`, maka data `x` akan ditaruh atau di disisipkan tepat setelah index `idx` pada ArrayList.
2. Fungsi `remove(Obj x)` untuk menghapus data yang objeknya sama dengan `x`. Bisa juga diisi parameter lain seperti `remove(idx)`, dimana menghapus data pada index `idx` pada ArrayList.
3. Fungsi `size()`, mengembalikan ukuran dari ArrayList.
4. Fungsi `get(idx)`, untuk mengambil item dalam ArrayList yang berada tepat di index `idx`.

Dan masih banyak lagi sih... 

Tapi 4 fungsi diatas adalah yang paling dasar dan penting. Pelajari sendiri jika perlu.
