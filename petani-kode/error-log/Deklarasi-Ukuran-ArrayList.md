# Kesalahan Inisialisasi `ArrayList`

Program pembuatan deret Fibonacci gagal dijalankan karena terjadi **`IndexOutOfBoundsException`** saat menambahkan elemen baru ke dalam `ArrayList`.
Kesalahan muncul pada baris berikut:

```java
fibo.add(i, fibo.get(i-1) + fibo.get(i-2));
```

Padahal sebelumnya `ArrayList` sudah dideklarasikan dengan ukuran awal:

```java
ArrayList<Integer> fibo = new ArrayList<>(n);
```

Namun tetap error saat mengakses indeks `i-1` dan `i-2`.

## 1 | Penyebab Utama

Konstruktor `new ArrayList<>(n)` **tidak membuat `ArrayList` berisi `n` elemen**, melainkan hanya **mengatur kapasitas awal (initial capacity)**.
Artinya, `size()` masih **0** sampai elemen benar-benar ditambahkan menggunakan `.add()`.

Saat kode mencoba mengakses `fibo.get(i-1)` atau menambahkan pada posisi indeks tertentu (`.add(i, value)`), `ArrayList` masih kosong — menyebabkan error indeks di luar batas.

## 2 | Solusi Perbaikan

Inisialisasikan isi `ArrayList` terlebih dahulu sebelum mengakses indeks apa pun:

```java
ArrayList<Integer> fibo = new ArrayList<>(n);
fibo.add(0);
fibo.add(1);
for (int i = 2; i < n; i++) {
    fibo.add(fibo.get(i - 1) + fibo.get(i - 2));
}
```

Atau, jika ingin inisialisasi penuh dengan nilai default:

```java
ArrayList<Integer> fibo = new ArrayList<>(n);
for (int i = 0; i < n; i++) {
    fibo.add(0);
}
```

## 3 | Pelajaran yang Didapat

* **`ArrayList<>(n)` bukan berarti berisi `n` elemen.**
  Ia hanya menyiapkan ruang untuk menampung hingga `n` elemen tanpa perlu realokasi ulang.
* Ukuran aktual `ArrayList` ditentukan oleh jumlah elemen yang telah ditambahkan (`size()`).
* Gunakan `.add()` untuk menambah, dan `.set()` untuk mengganti nilai di indeks yang sudah ada.