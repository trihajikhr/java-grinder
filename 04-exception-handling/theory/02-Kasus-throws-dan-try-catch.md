# Penggunaan throws

Kata kunci `throws` digunakan pada saat deklarasi method, untuk memberi tahu Java dan juga programmer lain bahwa:

_"Hei, method ini bisa melempar error jenis X, tolong kamu yang manggil siap-siap tangkap ya."_

Biasanya penggunaan `throws` memiliki struktur umum seperti ini:

```java
void namaMethod() throws JenisException {
    // kode yang bisa bikin error
}
```

## 1 | Kasus 1 - `throws` Basic

Misal aku memiliki kode Java, berikut adalah isi dari `Main.java`:

```java
package exception;

public class Main {
    public static void main(String[] args) {
        Handler hand = new Handler();

        hand.tambah();
    }
}
```

Dan ini adalah isi dari `Handler.java`:

```java
package exception;

import java.util.Scanner;

public class Handler {
    Scanner scan = new Scanner(System.in);
    
    int tambah() {
       int a = scan.nextInt();
       int b = scan.nextInt();
       return a + b;
    }
}
```
Jika inputan yang diberikan keduanya adalah angka, maka program akan berjalan tanpa error. Tapi jika kita memasukan huruf atau karakter apapun yang bukan angka ketika program berjalan, maka akan muncul error seperti ini:

```bash
Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:964)
	at java.base/java.util.Scanner.next(Scanner.java:1619)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2284)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2238)
	at exception.Handler.tambah(Handler.java:9)
	at exception.Main.main(Main.java:7)

Process finished with exit code 1
```

Terdapat kesalahan inputan, dimana tipe data integer justru menerima karakter selain angka, sehingga program berhenti karena error. 

Sekarang mari kita aplikasikan penggunaan `throws` untuk menghandle kasus ini. Sekarang, rubah kode program di file `Handler.java` menjadi seperti ini:

```java
package exception;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Handler {
    Scanner scan = new Scanner(System.in);

    int coba() throws InputMismatchException {
        System.out.println("Masukkan dua angka: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        return a + b;
    }

    void tambah() {
        int ans;
        try {
            ans = coba();
            System.out.println("Hasil: " + ans);
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid, hanya angka yang diperbolehkan.");
        }
    }
}
```

Kita membuat fungsi `tambah()` untuk melakukan pemanggilan secara aman pada fungsi `coba()`. Ketika fungsi `coba()` dideklarasikan dengan tambahan `throws`, maka fungsi tersebut menjadi memiliki kemampuan untuk bisa melempar exception atau error yang dialami pada fungsi tersebut, pada kasus ini yaitu `InputMismatchException`. Ketika hal ini terjadi, maka harus ada _penangkapnya_, sesuatu yang akan menangkap exceptions dari fungsi tersebut adalah `try-catch`.

Oleh karena itu, fungsi `tambah()` harus memanggil fungsi `coba()` didalam blok `try`. Ketika program berjalan dengan baik, yaitu inputan sesuai, maka output berikut yang akan ditampilkan:

```bash
9
10
Hasil: 19
```

Tetapi ketika inputan yang dimasukan tidak sesuai, maka fungsi `coba()` akan melemparkan  exception tersebut, dan blok `catch` akan menangkapnya. Ketika blok `catch` yang bekerja, maka output yang dihasilkan akan berbeda, yaitu seperti ini:

```bash
8
p
Error: Input tidak valid, hanya angka yang diperbolehkan.
```

## 2 | Kasus 2 - Custom _Exceptions_

Sekarang, selain fungsi tambah bisa mengatasi inputan yang tidak sesuai, sekarang kita akan merancang agar fungsi tersebut juga bisa mengatasi ketika inputan melebihi batas tertentu dengan cara yang lebih elegan, misal ketika inputan yang dimasukan adalah tidak boleh melebihi `1000000`.

Pada kasus ini, perlu diketahui bahwa Java tidak memiliki exception untuk kasus seperti ini. Sehingga yang akan kita lakukan adalah dengan membuat `custom exception` sendiri.

Berikut adalah perubahan file `Handler.java`:

```java
package exception;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Handler {
    Scanner scan = new Scanner(System.in);

    int coba() throws InputMismatchException, AngkaKelewatBatas {
        System.out.println("Masukkan dua angka: ");
        int a = scan.nextInt();

        if(a > 1_000_000){
            throw new AngkaKelewatBatas("Angka melebihi batas!");
        }

        int b = scan.nextInt();

        if(b > 1_000_000){
            throw new AngkaKelewatBatas("Angka melebihi batas!");
        }

        return a + b;
    }

    void tambah() {
        int ans;
        try {
            ans = coba();
            System.out.println("Hasil: " + ans);
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid, hanya angka yang diperbolehkan.");
        } catch (AngkaKelewatBatas e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class AngkaKelewatBatas extends Exception {
    public AngkaKelewatBatas(String msg) {
        super(msg);
    }
}
```

Apa yang kita lakukan? Kita membuat custom exceptions kita sendiri dengan membuat class dengan nama `AngkaKelewatBatas()`. Class ini akan kita set sebagai sub-class dari class `Exception`,  yang sudah ada di Java. Dengan menjadikannya subclass dari `Exception`, berarti `AngkaKelewatBatas` termasuk ke dalam kategori _checked exception_ yang harus ditangani menggunakan `try-catch` atau dideklarasikan dengan `throws`.

Class tersebut memiliki constructor yang menerima pesan dalam bentuk String (`msg`) dan meneruskanya ke constructor milik superclass `Exception` agar pesan tersebut bisa diakses melalui `getMessage()`.

Pada fungsi `coba()`, kita menambahkan dua exception. Jadi jika kamu memiliki pertanyaan _"Apakah mungkin untuk suatu fungsi memiliki lebih dari satu exception"_, maka disini jawabanya adalah **bisa**. Bisa juga ditaruh oleh exception bawaan Java, namun karena disini fokus kita adalah belajar custom exception, maka contoh ini bisa digunakan.

Exception `AngkaKelewatBatas` akan terpicu ketika blok `if` mendeteksi bahwa terdapat nilai dari variabel `a` atau `b` yang nilainya ada diatas `1000000`. Jika hal itu terjadi, maka blok `if` akan dijalankan, dan melemparkan exception dengan cara membuat objek class baru berupa `AngkaKelewatBatas()` sekalian memberikan message didalamnya.

Ketika terjadi exception, alur program akan langsung berpindah ke block `catch` yang sesuai. Karena yang mentrigger exception adalah exception `AngkaKelewatBatas`, maka blok `catch` terakhir akan dieksekusi, menghasilkan output berupa:

```bash
1
10000000
Error: Angka melebihi batas!
```