# Arraylist Autoboxing

Jika ArrayList itu seperti vector di C++, seharusnya dia mampu menggunakan tipe data primitif seperti `int`, `double`, `float`, dan `char`. Tapi ternyata tidak bisa!

Kode ini error:

```java
ArrayList<int> nilai = new ArrayList<int>();
```

Tapi kode ini benar, alias tidak ada error:

```java
class Data {
    int nilai;
    String nama;
    float ipk;
}

ArrayList<Data> mhs = new ArrayList<>();
```

Ternyata, ketika dicari tahu di Google, berikut penjelasanya:

ArrayList tidak bisa digunakan untuk menangani tipe data primitif, karena ArrayList hanya dapat menyimpan objek, bukan tipe data primitif seperti `int`, `double`, atau `boolean`. Untuk menggunakan tipe data primitif dengan ArrayList, Anda harus menggunakan kelas pembungkus (wrapper classes) yang sesuai, seperti `Integer` untuk int atau `Double` untuk double. 

Oleh karena itu, kelas pembungkus diperlukan untuk menyimpan nilai primitif, dimana _wrapper class_ ini membungkus tipe data primitif menjadi objek class:


| Tipe Primitif | Kelas Pembungkus |
|---------------|------------------|
| boolean |	Boolean |
|byte	|Byte|
|char |	Character|
|short |	Short|
|int |	Integer|
|long |	Long|
|float|	Float|
|double |	Double|

Java juga memiliki fitur yang disebut "_Autoboxing_", yang memungkinkan kompilator secara otomatis mengonversi tipe data primitif ke kelas pembungkusnya saat Anda menambahkannya ke ArrayList. Contoh: Anda bisa langsung menambahkan `int` ke `ArrayList<Integer>`. 

Berikut adalah contoh menggunakan _wrapper class_ pada ArrayList:

```java
import java.util.ArrayList;

public class ContohArrayList {
    public static void main(String[] args) {
        // Deklarasi ArrayList untuk Integer (menggantikan int)
        ArrayList<Integer> daftarAngka = new ArrayList<Integer>();

        // Menambahkan nilai int (akan dibungkus secara otomatis menjadi Integer)
        daftarAngka.add(10);
        daftarAngka.add(20);

        // Mengambil nilai dari ArrayList
        int angkaPertama = daftarAngka.get(0); // Autounboxing akan mengonversi Integer ke int
        System.out.println(angkaPertama);
    }
}

```