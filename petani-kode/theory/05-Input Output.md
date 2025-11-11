# 5 | Cara Mengambil Input dan Menampilkan Output

![](media/img.png)

Secara umum, sebuah program komputer terdiri atas tiga komponen utama, yaitu **input**, **proses**, dan **output**.

* **Input**: data atau nilai yang dimasukkan ke dalam program.
* **Proses**: serangkaian langkah atau instruksi yang mengolah data input menjadi informasi yang berguna.
* **Output**: hasil dari proses pengolahan data tersebut.

Seluruh bahasa pemrograman menyediakan mekanisme untuk melakukan proses *input* dan *output*.

Dalam bahasa Java, terdapat tiga *class* utama yang digunakan untuk menerima input melalui antarmuka teks (*console*), yaitu:

1. `Scanner`
2. `BufferedReader`
3. `Console`

Ketiga *class* tersebut digunakan untuk menangani input pada program berbasis teks.
Sementara itu, untuk program berbasis antarmuka grafis (*GUI*), Java menyediakan *class* lain seperti `JOptionPane` atau komponen *input box* pada *form*.

Untuk menghasilkan keluaran (*output*), Java menyediakan metode bawaan seperti `print()`, `println()`, dan `format()`.

## 1 | Input pada Java
### 1.1 | Mengambil Input dengan Class Scanner

Class `Scanner` menyediakan fungsi-fungsi untuk membaca input dari keyboard.
Untuk dapat menggunakan `Scanner`, *class* ini harus diimpor terlebih dahulu:

```java
import java.util.Scanner;
```

Contoh penggunaan dalam program:

```java
package pertemuan2;

// Mengimpor Scanner ke program
import java.util.Scanner;

public class DataKaryawan {

    public static void main(String[] args) {
        // Deklarasi variabel
        String nama, alamat;
        int usia, gaji;

        // Membuat objek Scanner
        Scanner keyboard = new Scanner(System.in);

        // Menampilkan output ke pengguna
        System.out.println("### Pendataan Karyawan PT. Petani Kode ###");
        System.out.print("Nama karyawan: ");
        nama = keyboard.nextLine();

        System.out.print("Alamat: ");
        alamat = keyboard.nextLine();

        System.out.print("Usia: ");
        usia = keyboard.nextInt();

        System.out.print("Gaji: ");
        gaji = keyboard.nextInt();

        // Menampilkan data yang telah disimpan
        System.out.println("--------------------");
        System.out.println("Nama Karyawan: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("Usia: " + usia + " tahun");
        System.out.println("Gaji: Rp " + gaji);
    }

}
```

Beberapa hal penting terkait penggunaan `Scanner`:

1. Fungsi yang dipanggil bergantung pada **tipe data** variabel:

    * `String` → `nextLine()`
    * `int` → `nextInt()`
    * `double` → `nextDouble()`
    * dsb.

2. Objek `Scanner` dapat digunakan untuk membaca berbagai tipe data, tetapi perlu diperhatikan bahwa **kombinasi pemanggilan `nextLine()` setelah `nextInt()` atau `nextDouble()`** dapat menyebabkan *newline character* tersisa, sehingga input berikutnya terbaca kosong. Solusi: menambahkan `keyboard.nextLine();` setelah pemanggilan `nextInt()` atau `nextDouble()` untuk membersihkan *buffer*.
3. Untuk program yang lebih kompleks, pertimbangkan **penanganan error** menggunakan *exception handling* (`try-catch`) untuk menghindari *InputMismatchException* ketika pengguna memasukkan tipe data yang tidak sesuai.

Dengan pemahaman ini, `Scanner` dapat digunakan tidak hanya untuk program sederhana berbasis teks, tetapi juga untuk aplikasi yang membutuhkan validasi input dan kontrol alur eksekusi yang lebih baik.

### 1.2 | Mengambil Input dengan Class `BufferedReader`

Class `BufferedReader` tidak hanya digunakan untuk mengambil input dari keyboard, tetapi juga dapat membaca data dari file maupun jaringan.
Class ini berada dalam paket `java.io` dan harus diimpor sebelum digunakan:

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
```

Contoh implementasi dalam program:

```java
package pertemuan2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContohBufferedReader {

    public static void main(String[] args) throws IOException {

        String nama;

        // Membuat objek InputStreamReader
        InputStreamReader isr = new InputStreamReader(System.in);

        // Membuat objek BufferedReader
        BufferedReader br = new BufferedReader(isr);

        // Mengisi variabel 'nama' menggunakan BufferedReader
        System.out.print("Inputkan nama: ");
        nama = br.readLine();

        // Menampilkan output
        System.out.println("Nama kamu adalah " + nama);
    }
}
```

Beberapa hal penting terkait `BufferedReader`:

1. `BufferedReader` **tidak dapat berdiri sendiri**. Ia membutuhkan *teman*, yaitu:

    * `InputStreamReader` untuk membaca aliran input dari keyboard atau sumber lain.
    * Penanganan *exception* `IOException`, karena metode `readLine()` dapat menimbulkan kesalahan input/output.

2. Perbedaan utama dengan `Scanner`:

    * `Scanner` menggunakan metode `next()`, `nextInt()`, `nextDouble()`, dsb.
    * `BufferedReader` menggunakan metode `readLine()` untuk membaca *String*. Untuk tipe data lain seperti *integer*, nilai harus dikonversi secara eksplisit, misalnya:

      ```java
      int angka = Integer.parseInt(br.readLine());
      ```

3. Tips: gunakan **Ctrl+Spasi** di IDE untuk menampilkan *hint autocomplete*, sehingga mempermudah penulisan kode.

`BufferedReader` lebih efisien dibanding `Scanner` untuk membaca input berukuran besar, terutama saat membaca file atau data dari jaringan, karena menggunakan *buffer* internal yang mengurangi overhead baca/tulis.

### 1.3 | Mengambil Input dengan Class `Console`

Class `Console` mirip dengan `BufferedReader` karena menggunakan metode `readLine()` untuk mengambil input.

Namun, terdapat beberapa perbedaan penting:

1. `Console` **hanya dapat digunakan di lingkungan console**, seperti Terminal atau CMD.
2. Class ini **tidak dapat digunakan langsung** di IDE seperti NetBeans; kompilasi dan eksekusi harus dilakukan secara manual melalui terminal.

Sebelum digunakan, class ini perlu diimpor:

```java
import java.io.Console;
```

Contoh implementasi:

```java
import java.io.Console;

public class InputConsole {
    public static void main(String[] args) {

        String nama;
        int usia;

        // Membuat objek Console
        Console con = System.console();

        // Mengisi variabel nama dan usia melalui Console
        System.out.print("Inputkan nama: ");
        nama = con.readLine();

        System.out.print("Inputkan usia: ");
        usia = Integer.parseInt(con.readLine());

        // Menampilkan isi variabel
        System.out.println("Nama kamu adalah: " + nama);
        System.out.println("Saat ini berusia " + usia + " tahun");
    }
}
```

Beberapa catatan penting:

* Fungsi `readLine()` hanya mengembalikan tipe *String*. Untuk mengambil input numerik, diperlukan konversi eksplisit, misalnya:

  ```java
  int usia = Integer.parseInt(con.readLine());
  ```
* Karena `Console` terbatas pada lingkungan *console*, penggunaan di IDE biasanya menghasilkan *null object*, sehingga program tidak dapat berjalan secara langsung di sana.

#### Cara Kompilasi dan Eksekusi:

1. Buka terminal atau CMD.
2. Masuk ke direktori tempat file `InputConsole.java` disimpan.
3. Kompilasi file:

   ```bash
   javac InputConsole.java
   ```
4. Jalankan program:

   ```bash
   java InputConsole
   ```

Langkah-langkah ini memastikan program dapat berjalan menggunakan class `Console` di lingkungan *command line*.

## 2 | Menampilkan Output di Java

Setelah mengenal berbagai cara mengambil input dari keyboard untuk program berbasis teks, penting juga memahami mekanisme **output**.

Java menyediakan beberapa metode untuk menampilkan output ke layar:

1. `System.out.print()`
2. `System.out.println()`
3. `System.out.format()`

### 2.1 | Perbedaan `print()` dan `println()`

Kedua metode digunakan untuk menampilkan teks, namun terdapat perbedaan dalam cara pencetakan:

* `print()`: menampilkan teks **apa adanya** tanpa menambahkan baris baru.
* `println()`: menampilkan teks kemudian **menambahkan baris baru** secara otomatis.

Contoh penggunaan:

```java
package eksperimen;

public class PrintVsPrintln {

    public static void main(String[] args) {

        System.out.print("Ini teks yang dicetak dengan print()");
        System.out.println(" sedangkan ini teks yang dicetak dengan println()");
        System.out.print("Pakai print() lagi");

    }
}
```

Hasil eksekusi:

```
Ini teks yang dicetak dengan print() sedangkan ini teks yang dicetak dengan println()
Pakai print() lagi
```

Dengan memahami perbedaan ini, penempatan teks dan format output dapat dikontrol dengan lebih presisi.

#### Menggabungkan String di Java

Dalam beberapa kasus, saat menggunakan `print()` atau `println()`, perlu menggabungkan teks dari variabel dengan teks lain.

Contoh:

```java
String namaDepan = "Petani";
String namaBelakang = "Kode";
```

Untuk menampilkannya, dapat menggunakan beberapa pernyataan `print()`:

```java
System.out.print(namaDepan);
System.out.print(namaBelakang);
```

Hasilnya:

```
PetaniKode
```

Namun, cara yang lebih efisien adalah **menggabungkan string** menggunakan operator `+`:

```java
System.out.print(namaDepan + namaBelakang);
```

Jika ingin menambahkan spasi antar kata:

```java
System.out.print(namaDepan + " " + namaBelakang);
```

Hasilnya:

```
Petani Kode
```

Penggabungan string dengan operator `+` mempermudah penulisan kode dan membuatnya lebih rapi, terutama saat menampilkan beberapa variabel sekaligus.

### 2.2 | Format String di Java

Untuk penggabungan string yang lebih kompleks, Java menyediakan metode `format()`, yang memungkinkan pengaturan teks dengan *placeholder*.

Contoh penggunaan:

```java
package eksperimen;

public class FormatString {

    public static void main(String[] args) {

        String namaDepan = "Petani";
        String namaBelakang = "Kode";

        System.out.format("Nama saya %s %s %n", namaDepan, namaBelakang);

    }
}
```

Penjelasan:

* `%s` digunakan sebagai *placeholder* untuk tipe data string.
* `%d` untuk bilangan bulat (desimal).
* `%f` untuk bilangan pecahan (*floating point*).
* `%n` untuk membuat baris baru (alternatif: `\n`).

Metode `format()` memungkinkan penggabungan teks dan variabel dengan lebih terstruktur, terutama saat menampilkan beberapa nilai sekaligus.

Untuk informasi lebih lengkap mengenai *format specifiers*, dapat merujuk ke [dokumentasi resmi Java](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Formatter.html).
