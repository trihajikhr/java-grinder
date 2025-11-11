# 11 | Class Hashmap pada Java

![](media/img.png)

Karena aku sudah pernah belajar C++ dan menggunakan struktur data STL seperti `unordered_map` dan `map`, maka aku bisa katakan dengan mudah, bahwa cara kerja Hashmap di Java mirip dengan 2 struktur data tadi.

Well.., mungkin cukup dilakukan beberapa penyesuaian sedikit, karena ada beberapa penulisan syntax berbeda yang harus dipelajari, tapi konsepnya sama.

## 1 | Apa itu Hashmap

Class `HashMap` merupakan class turunan dari class `AbstractMap` dan implementasi dari interface `Map`.

HashMap adalah sebuah class yang berisi sekumpulan pasangan _key_ dan _value_. Nilainya bisa dalam bentuk tipe data primitif, atau objek seperti class.

HashMap bisa dibilang seperti Array asosiatif dalam Java.

> Aku sudah pernah belajar Map dan Unordered_map di C++, jadi aku tidak menjelaskan panjang lebar disini!

## 2 | Cara membuat HashMap

Sebelum menggunakan HashMap, kita harus mengimpornya terlebih dahulu:

```java
import java.util.HashMap;
```

Setelah diimpor, buat objek HashMap untuk menggunakanya:

```java
HashMap<Integer, String> days = new HashMap<Integer,String>
```

Pada contoh di atas, kita membuat objek HashMap bernama `days`. Objek ini dapat kita gunakan untuk menyimpan koleksi data.

Tipe data yang digunakan untuk _key_ adalah Integer dan _value_ adalah String.

Artinya: _key_ harus bertipe Integer dan _value_ yang tersimpan harus dalam bertipe String.

## 3 | Mengisi data HashMap

Untuk mengisi data pada HasMap, bisa digunakan method `put()`, seperti ini:

```java
days.put(1, "Minggu");
days.put(2, "Senin");
days.put(3, "Selasa");
days.put(4, "Rabu");
days.put(5, "Kamis");
days.put(6, "Jum'at");
days.put(7, "Sabtu");
```

Objek HashMap `days` berisi nama-nama hari dengan key `1` - `7`. Bisa dilihat dengan cara menuliskan kode ini:

```java
import java.util.HashMap;

public class CobaHashMap {
    public static void main(String[] args) {
        // membuat objek hashmap
        HashMap<Integer, String> days = new HashMap<Integer,String>();
        
        // mengisi nilai ke objek days
        days.put(1, "Minggu");
        days.put(2, "Senin");
        days.put(3, "Selasa");
        days.put(4, "Rabu");
        days.put(5, "Kamis");
        days.put(6, "Jum'at");
        days.put(7, "Sabtu");
        
        // mencetak semua isi dari objek days
        System.out.println("Isi objek days: " + days);
    }
}
```

Jalankan saja, dan semua _key_ dan _value_ akan dioutputkan semua sekaligus.

## 4 | Mengambil Nilai dari HashMap

Untuk mengambil nilai dari HashMap, kita bisa menggunakan method `get()` dengan parameter _key_ nya:

```java
days.get(2);
```

Ini artinya kita akan mengambil data yang memiliki _key_ yaitu `2`, yaitu data berupa `Senin`.

## 5 | Menghapus data dari HashMap

Ada dua method yang dapat digunakan untuk menghapus nilai dari HashMap:

1. `remove(key)` menghapus salah satu data yang memiliki _key_ yang sama.
2. `clear()` menghapus semua nilai, atau mengosongkan HashMap.

## 6 | Mengubah _Value_ dan _Key_ dari HashMap

Ada dua method yang bisa digunakan untuk mengubah nilai di dalam HashMap:

1. Method `put()`, akan menabmbahkan data baru jika sebelumnya belum ada di HashMap.
2. Method `replace()`, nilai yang akan diubah harus sudah ada didalam HashMap.


## 7 | Method lain di HashMap

Ada banyak method untuk class HashMap, berikut beberapa yang penting:

Berikut ini penjelasan beberapa method:

1. `clear()` untuk membersihkan isi HashMap;
2. 1 untuk mengecek apakah HashMap dalam keadaan kosong atau tidak;
3. `size()` untuk mengambil ukuran HashMap (jumlah item di dalam hashmap);
4. `values()` untuk mengambil semua nilai yang ada di dalam HashMap;
5. `keySet()` untuk mengambil semua key yang ada di dalam HashMap;
6. `clone()` untuk menggandakan objek HashMap;

dll.