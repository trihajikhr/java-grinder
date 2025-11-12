# 6 | Variabel dan Tipe Data

![](media/img.png)

## 1 | Pengertian Variabel dan Tipe Data

- **Variabel** adalah nama yang digunakan untuk menyimpan nilai sementara selama program berjalan
- **Tipe data** adalah jenis nilai yang dapat disimpan dalam variabel tersebut.
  Bahasa Java termasuk *strongly typed language*, yaitu setiap variabel harus ditetapkan tipe datanya terlebih dahulu.


## 2 | Macam‑macam Tipe Data dalam Java

Berdasarkan sumber, beberapa tipe data dasar pada Java adalah:

- `char` : tipe data karakter, contoh `'Z'`.
- `int` : bilangan bulat, contoh `29`.
- `float` : bilangan desimal (presisi tunggal), contoh `2.1`.
- `double` : bilangan desimal (presisi ganda), contoh `2.1`.
- `String` : kumpulan karakter yang membentuk teks, contoh `"Hello World!"`.
- `boolean` : hanya memiliki dua nilai: `true` atau `false`.

Untuk keperluan yang lebih lanjut (terutama aplikasi besar), Java juga memiliki tipe data seperti `byte`, `short`, `long`, `long double`, array, objek, dll. Ketika menggunakan `long`, gunakan karakter L pada akhir angka untuk memberi tahu sistem, bahwa angka besar ini harus diperlakukan sebagai tipe dat `long`.

## 3 | Cara Deklarasi dan Inisialisasi Variabel

Format umum deklarasi variabel:

```java
<tipe_data> namaVariabel;
```

Contoh:

```java
int umur;
int umur = 19;
```

Variabel dengan tipe sama dapat dideklarasikan sekaligus:

```java
int a, b, c;
```
Penempatan variabel:

- Variabel yang ditulis di dalam method (misalnya `main()`) disebut **variabel lokal**. 
- Variabel yang ditulis di dalam class tetapi di luar method disebut **variabel kelas (class‑variable)** atau kadang disebut “global” dalam konteks sederhana.


## 4 | Aturan Penulisan Nama Variabel

Beberapa aturan penting dalam penamaan variabel di Java: 

- Nama variabel **tidak boleh** menggunakan kata kunci (reserved word) Java seperti `if`, `for`, `switch`, dll.
- Karakter yang diperbolehkan dalam nama variabel: huruf, angka (0‑9), underscore `_`, dan simbol dollar `$`. Namun penggunaan underscore dan `$` sebaiknya dihindari untuk kejelasan kode.
- Nama variabel **harus diawali dengan huruf kecil** (karena Java menggunakan gaya penulisan camelCase).
- Bila nama variabel terdiri dari lebih dari satu suku kata, kata kedua dan seterusnya diawali huruf kapital. Contoh: `namaVariabel`, bukan `namavariabel` atau `NamaVariabel`.

## 5 | Contoh Program Sederhana

Berikut contoh program yang menggabungkan deklarasi variabel, inisialisasi, dan menampilkan hasil output.

```java
public class DataDiri {
    public static void main(String[] args) {
        String nama = "Dian";
        int usia = 25;

        System.out.println("Nama     : " + nama);
        System.out.println("Usia     : " + usia + " tahun");
    }
}
```

Pada program di atas:

- Variabel `nama` bertipe `String`, diisi dengan `"Dian"`.
- Variabel `usia` bertipe `int`, diisi dengan `25`.
- Output ditampilkan menggunakan `System.out.println()`.

## 6 | Catatan untuk Pengguna C++ yang Beralih ke Java

Karena Anda sudah memiliki latar belakang C++, beberapa hal yang perlu diperhatikan saat menggunakan variabel dan tipe data di Java:

* Di Java, tipe data objek seperti `String` bukan tipe primitif seperti `char[]` di C++. (`String` adalah *class*, bukan primitif)
* Java tidak mendukung pointer atau akses memori secara langsung seperti C++. Konsumsi dan manajemen memori otomatis dilakukan oleh Java (garbage collector).
* Deklarasi variabel di Java harus mencantumkan tipe, dan variabel harus sesuai dengan tipe yang ditetapkan (tidak dapat tiba‑tiba berubah tipe seperti di bahasa dynamic).
* Gaya penulisan variabel (camelCase) dan aturan penamaan di Java lebih baku dibanding banyak kode C++ yang bisa beragam gaya. Disiplin di sini sangat membantu untuk kolaborasi dan kualitas kode.
* Ketika variabel diinisialisasi, pastikan nilainya sesuai dengan jenis tipe data; misalnya jangan mencoba menyimpan teks ke dalam `int`, karena akan terjadi *compile error*.
* Untuk tipe data numerik besar atau desimal, pastikan memilih tipe yang tepat (`float`, `double`, `long`), selain hanya `int`. Kebutuhan aplikasi (misalnya hasil perhitungan presisi) akan menentukan tipe.

## 7 | Tambahan Dari Programmiz

