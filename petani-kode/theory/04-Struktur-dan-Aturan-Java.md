# 4 | Memahami Struktur dan Aturan Penulisan Sintaks Java

![](media/img.png)

Setiap bahasa pemrograman memiliki struktur dan aturan penulisan sintaks yang berbeda-beda.

Java merupakan bahasa pemrograman yang dikembangkan dari bahasa C dan tentunya akan banyak mengikuti gaya penulisan C.

Perhatikan kode program berikut:

```java
package com.petanikode.program;
import java.util.Scanner;

class Program {
    public static void main(String args[]){
        System.out.println("Hello World");
    }
}
```
## 1 | Struktur Dasar

Penjelasan syntax dasar dari kode program yang ditulis dengan bahasa Java diatas akan dijelaskan dibawah. Dimana kode diatas sebenarnya adalah struktur paling dasar dan umum dari penulisan program Java, yang mana dibagi menjadi 4 bagian:

1. Deklarasi Package
2. Import Library
3. Bagian Class
4. Method Main

```java
package com.petanikode.program; //<- 1. deklarasi package

import java.util.Scanner; //<- 2. Impor library

class Program { //<- 3. Bagian class
    
    public static void main(String args[]){ //<- 4. Method main
        System.out.println("Hello World");
    }
}
```

### 1.1 | Deklarasi Package

**Package** merupakan folder yang berisi sekumpulan berkas program Java.

Deklarasi *package* biasanya dilakukan ketika mengembangkan program atau aplikasi berskala besar.

Contoh deklarasi:

```java
package com.petanikode.program;
```

Penamaan *package* umumnya mengikuti nama domain pengembang, dengan urutan terbalik, kemudian diikuti nama program.
Pada contoh di atas, `com.petanikode` berasal dari domain **petanikode.com**.

Jika *package* tidak dideklarasikan, program tetap dapat dijalankan.
Namun, pada tahap produksi—misalnya saat membuat aplikasi Android—pendeklarasian *package* menjadi **wajib**.

### 1.2 | Import Library

Pada bagian ini, kita mengimpor *library* yang diperlukan oleh program.

*Library* merupakan kumpulan *class* dan *method* yang dapat digunakan untuk membantu proses pengembangan program.

Contoh pernyataan impor:

```java
import java.util.Scanner;
```

Pada contoh tersebut, *class* `Scanner` diimpor dari *package* `java.util`.

### 1.3 | Bagian Class

Java merupakan bahasa pemrograman yang menerapkan paradigma *Object-Oriented Programming* (OOP).

Setiap program Java harus ditempatkan di dalam sebuah *class* agar dapat direpresentasikan sebagai objek.

Bagi yang belum memahami konsep OOP secara mendalam, *class* dapat dianggap sebagai deklarasi utama dari sebuah program.

Contoh:

```java
class NamaProgram {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

Kode di atas merupakan sebuah blok *class*.
Blok tersebut diawali dengan tanda kurung kurawal `{` dan diakhiri dengan `}`.

Di dalam blok *class* dapat didefinisikan *method* (fungsi) dan variabel.
Pada contoh tersebut, terdapat *method* `main()` yang berfungsi sebagai titik awal eksekusi program Java.

### 1.4 | Method Main

*Method* `main()` merupakan blok program yang dieksekusi pertama kali saat program dijalankan.
Bagian ini berfungsi sebagai **titik masuk (entry point)** dari sebuah program Java.

*Method* `main()` wajib dideklarasikan; tanpa adanya *method* ini, program tidak dapat dieksekusi.

Contoh penulisan:

```java
public static void main(String[] args) {
    System.out.println("Hello World");
}
```

Penulisan *method* `main()` harus mengikuti format di atas.
Parameter `String[] args` berfungsi untuk menampung argumen yang dikirim melalui *command line* saat program dijalankan.

Di dalam *method* `main()` terdapat pernyataan:

```java
System.out.println("Hello World");
```

Pernyataan tersebut digunakan untuk menampilkan teks ke layar monitor.

## 2 | Statement dan Ekspresi

**Statement** dan **ekspresi** merupakan unit terkecil dalam suatu program.
Setiap *statement* dan *ekspresi* dalam bahasa Java harus diakhiri dengan tanda titik koma (`;`).

Contoh *statement* dan *ekspresi*:

```java
System.out.println("Hello World");
System.out.println("Apa kabar?");
var x = 3;
var y = 8;
var z = x + y;
```

*Statement* dan *ekspresi* berfungsi sebagai instruksi yang akan dijalankan oleh komputer.

Pada contoh di atas, program memberikan instruksi untuk menampilkan teks `"Hello World"` dan `"Apa kabar?"`,
serta melakukan operasi penjumlahan terhadap variabel `x` dan `y`.

## 3 | Blok Program Java

Blok program merupakan sekumpulan *statement* dan *ekspresi* yang dikelompokkan menjadi satu kesatuan logis.

Setiap blok program diawali dengan tanda kurung kurawal pembuka `{` dan diakhiri dengan tanda kurung kurawal penutup `}`.

Contoh blok program:

```java
// Blok program main
public static void main(String[] args) {
    System.out.println("Hello World");
    System.out.println("Hello Kode");

    // Blok program if
    if (true) {
        System.out.println("True");
    }

    // Blok program for
    for (int i = 0; i < 10; i++) {
        System.out.println("Perulangan ke " + i);
    }
}
```

Dengan demikian, setiap kali ditemukan pasangan tanda `{` dan `}`, bagian tersebut dapat dianggap sebagai sebuah blok program.

Sebuah blok program juga dapat berisi blok lain di dalamnya (*nested block*).
Pada contoh di atas, blok *main()* berisi blok *if* dan blok *for*.

Pembahasan mengenai struktur dan penggunaan blok program akan dijelaskan lebih lanjut pada materi terpisah.

## 4 | Penulisan Komentar pada Java

Komentar merupakan bagian dari program yang tidak akan dieksekusi oleh komputer.
Komentar digunakan untuk memberikan penjelasan pada kode, menonaktifkan bagian program tertentu, atau menulis dokumentasi.

Dalam bahasa Java, penulisan komentar mengikuti gaya penulisan pada bahasa C, yaitu:

* **`//`** digunakan untuk komentar satu baris.
* **`/* ... */`** digunakan untuk komentar yang terdiri atas beberapa baris.

Contoh:

```java
public static void main(String[] args) {
    // Komentar satu baris
    System.out.println("Hello World");
    
    // Komentar tidak akan dieksekusi oleh komputer
    // Contoh fungsi yang dinonaktifkan dengan komentar:
    // System.out.println("Hello World");

    /*
     Ini adalah contoh komentar
     yang ditulis dalam beberapa baris
    */
}
```

> Programmiz: Komentar yang baik adalah komentar yang menjelaskan `why`, alih-alih `how`.

## 5 | Penulisan String dan Karakter

*String* merupakan kumpulan karakter yang biasanya digunakan untuk merepresentasikan teks.

Contoh *string*:

```java
"Hello World"
```

Dalam bahasa Java, *string* harus ditulis dengan tanda petik ganda (`"`).

Apabila teks ditulis dengan tanda petik tunggal (`'`), maka dianggap sebagai sebuah **karakter tunggal** (*char*).

Contoh:

```java
'X'
```

Dengan demikian, perlu dibedakan antara:

* Tanda petik ganda (`"..."`) digunakan untuk membuat *string*.
* Tanda petik tunggal (`'...'`) digunakan untuk membuat *karakter*.

## 6 | Case Sensitive

Bahasa pemrograman Java bersifat *case sensitive*, artinya huruf besar (kapital) dan huruf kecil dianggap berbeda.

Contoh:

```java
String nama = "Petani Kode";
String Nama = "petanikode";
String NAMA = "Petani Kode.com";

System.out.println(nama);
System.out.println(Nama);
System.out.println(NAMA);
```

Ketiga variabel di atas merupakan variabel yang berbeda, meskipun memiliki nama yang tampak serupa.

Kesalahan umum yang sering terjadi pada pemula adalah tidak memperhatikan perbedaan antara huruf besar dan huruf kecil dalam penulisan variabel.

Sebagai contoh, jika variabel dideklarasikan sebagai:

```java
String jenisKelamin = "Laki-laki";
```

maka pemanggilan yang benar adalah:

```java
System.out.println(jenisKelamin);
```

dan **bukan**:

```java
System.out.println(jeniskelamin);
```

Perhatikan bahwa huruf **K** pada `jenisKelamin` ditulis dalam huruf kapital.

## 7 | Gaya Penulisan *Case* dalam Java

Bahasa Java menggunakan tiga gaya penulisan (*case style*), yaitu **camelCase**, **PascalCase**, dan **ALL_UPPER_CASE**.

### 7.1 | camelCase

Gaya penulisan **camelCase** digunakan untuk penamaan variabel, objek, dan *method*.
Ciri khasnya adalah huruf pertama ditulis dengan huruf kecil, sedangkan huruf pertama dari setiap kata berikutnya ditulis dengan huruf kapital.

Contoh:

```java
String namaSaya = "Dian";
int jumlahMahasiswa = 25;
```

### 7.2 | PascalCase

Gaya **PascalCase** digunakan untuk penamaan *class*.
Setiap kata diawali dengan huruf kapital.

Contoh:

```java
class HelloWorld {
    // ...
}
```

Perhatikan bahwa huruf pertama (`H`) dan huruf pertama dari kata kedua (`W`) ditulis dalam huruf kapital.

### 7.3 | ALL_UPPER_CASE

Gaya **ALL_UPPER_CASE** digunakan untuk penamaan konstanta.
Seluruh huruf ditulis dalam huruf kapital, dan jika terdiri atas lebih dari satu kata, dipisahkan dengan garis bawah (`_`).

Contoh:

```java
public final String DB_NAME = "petanikode";
public static final int MAX_USER = 100;
```

### 7.4 | Kepatuhan terhadap Konvensi

Java tidak akan menghasilkan kesalahan (*error*) apabila gaya penulisan tidak mengikuti konvensi ini.
Namun, penggunaan gaya penulisan yang tidak sesuai akan membuat kode tampak tidak rapi dan sulit dibaca, serta tidak mengikuti pedoman penulisan yang telah menjadi standar komunitas Java.
