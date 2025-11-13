# Exception Handling in Java

Pada tutorial kali ini, kita akan belajar materi basic dari _exception handling_ pada Java. Jadi, bersiaplah...

## 1 | Aturan Pertama

### 1.1 | Apa itu _Exception handling_?

Untuk lebih memahami exception (pengecualian) dan exception handling (penanganan pengecualian), kita perlu membuat sebuah analogi nyata. Bayangkan kita melakukan sebuah pemesanan produk secara online, tetapi dijalan, rute yang biasa dilalui terdapat masalah . Perusahaan pengiriman yang baik akan mengatasi masalah ini dengan cara mencari atau mengalihkan rute pengiriman, sehingga produk akan tetap sampai tepat pada waktunya.

Seperti halnya pada Java, kode bisa mengalami error ketika dieksekusi dan dijalankan. _Exception handling_ yang baik harus bisa menangani error dan tetap membuat program berjalan dengan baik, memberikan penggunanya pengalaman yang memuaskan.

### 1.2 | Kenapa Menggunakanya?

Kita bisanya menulis kode pada lingkungan yang baik, file di sistem kita menyediakan semua yang kita cari dan butuhkan, dan jaringan mungkin selalu tersedia, dan JVM selalu memiliki cukup memory yang tersedia. Terkadang, kita menyebutnya dengan **"Happy path"**, atau _jalur yang menyenangkan_.

Tapi pada kode skala produksi, file sistem bisa mengalami _corrupt_, jaringan mungkin down, dan JVM kehabisan memory untuk bekerja. Kode kita harus dirancang untuk mampu menghadapi situasi dari **"Unhappy path"** atau _jalan yang tidak menyenangkan_ ini.

Kita harus bisa menangani kondisi seperti ini karena bisa mengganggu kelancaran dan alur kerja program, dan berdampak negatif pada kinerja aplikasi.

```java
public static List<Player> getPlayers() throws IOException {
    Path path = Paths.get("players.dat");
    List<String> players = Files.readAllLines(path);

    return players.stream()
      .map(Player::new)
      .collect(Collectors.toList());
}
```

Kode diatas tidak memiliki penangan `IOException`, dan langsung mengirimkannya ke call stack secara langsung. Pada lingkungan yang baik, kode tersebut mungkin bekerja.

Tapi, apa jadinya jika selama produk, file `player.dat` ternyata hilang?

```java
Exception in thread "main" java.nio.file.NoSuchFileException: players.dat <-- players.dat file doesn't exist
    at sun.nio.fs.WindowsException.translateToIOException(Unknown Source)
    at sun.nio.fs.WindowsException.rethrowAsIOException(Unknown Source)
    // ... more stack trace
    at java.nio.file.Files.readAllLines(Unknown Source)
    at java.nio.file.Files.readAllLines(Unknown Source)
    at Exceptions.getPlayers(Exceptions.java:12) <-- Exception arises in getPlayers() method, on line 12
    at Exceptions.main(Exceptions.java:19) <-- getPlayers() is called by main(), on line 19
```

Tanpa exception handling, kode yang seharusnya bekerja dengan baik akan berhenti bekerja sama sekali. Kode yang bagus seharusnya bisa mengatasi masalah ini, kita sebagai penulis kode harus memiliki rencana jika terdapat sesuatu yang salah.

Catat juga, satu keuntungan dari adalahnya exception handling, adalah kita bisa membuat _stack trace_. Dengan _stack trace_, kita bisa mengetahui bagian mana dari kode yang salah, tanpa perlu menggunakan debugger.

## 2 | Hierarki Exception

Pada dasarnya, exception hanyalah objek Java, dengan semua exception mewarisi dari `Throwable`:

```
              ---> Throwable <--- 
              |    (checked)     |
              |                  |
              |                  |
      ---> Exception           Error
      |    (checked)        (unchecked)
      |
RuntimeException
  (unchecked)
```

Ada tiga kategori utama dari kondisi exception:

1. Checked exceptions
2. Unchecked exceptions / Runtime exceptions
3. Errors

Runtime exception dan unchecked exception merujuk pada hal yang sama, sehingga seringkali bisa digunakan secara bergantian.
