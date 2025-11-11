# Program Tidak Berhenti Saat Input “exit”

**Deskripsi Masalah:**
Pada saat pengguna memasukkan kata `exit` sebagai penanda untuk menghentikan input data, program tidak berhenti sebagaimana mestinya. Sistem tetap meminta input nilai siswa berikutnya, sehingga alur program menjadi tidak sesuai dengan yang diharapkan.

**Kode yang Terlibat:**
Masalah ditemukan pada metode `safeInputStringLength()` di kelas `Helper`.

```java
String safeInputStringLength(String prompt, int t){
    String ans;
    while(true){
        System.out.print(prompt);
        ans = scan.nextLine();
        if(ans.length() <= t){
            break;
        }

        System.out.println("Nama terlalu panjang (max 20 karakter)!");
        scan.nextLine();
    }
    return ans;
}
```

**Analisis Penyebab:**
Kesalahan terjadi karena penggunaan `scan.nextLine()` ganda di dalam loop.
Ketika kondisi panjang string tidak valid, baris `scan.nextLine()` tambahan menyebabkan pembacaan input berikutnya dilewati oleh `Scanner`, sehingga nilai `exit` tidak terbaca dengan benar oleh `main`.

**Solusi yang Diterapkan:**
Menghapus pemanggilan `scan.nextLine()` kedua agar pembacaan input konsisten. Versi yang benar:

```java
String safeInputStringLength(String prompt, int t){
    String ans;
    while(true){
        System.out.print(prompt);
        ans = scan.nextLine();
        if(ans.length() <= t){
            break;
        }
        System.out.println("Nama terlalu panjang (max 20 karakter)!");
    }
    return ans;
}
```

**Hasil Setelah Perbaikan:**
Program berhasil berhenti saat pengguna memasukkan kata `exit`. Input nilai siswa berjalan normal tanpa melewati pembacaan atau skip baris.

**Catatan Pembelajaran:**
Penggunaan `Scanner` dalam Java harus hati-hati terhadap kombinasi metode `nextInt()` dan `nextLine()`, karena `nextInt()` tidak mengonsumsi karakter newline (`\n`) di buffer input. Dalam kasus seperti ini, disarankan untuk konsisten menggunakan `nextLine()` dan melakukan konversi manual bila diperlukan.
