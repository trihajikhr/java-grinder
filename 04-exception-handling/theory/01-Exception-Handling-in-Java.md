# Exception Handling in Java

Pada tutorial kali ini, kita akan belajar materi basic dari _exception handling_ pada Java. Jadi, bersiaplah...

---

## 1 | Aturan Pertama

### 1.1 | Apa itu _Exception handling_?

Untuk lebih memahami _exception_ (pengecualian) dan _exception handling_ (penanganan pengecualian), kita perlu membuat sebuah analogi nyata. Bayangkan kita melakukan sebuah pemesanan produk secara online, tetapi dijalan, rute yang biasa dilalui terdapat masalah . Perusahaan pengiriman yang baik akan mengatasi masalah ini dengan cara mencari atau mengalihkan rute pengiriman, sehingga produk akan tetap sampai tepat pada waktunya.

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

Kode diatas tidak memiliki penangan `IOException`, dan langsung mengirimkannya ke _call stack_ secara langsung. Pada lingkungan yang baik, kode tersebut mungkin bekerja.

Tapi, apa jadinya jika selama produksi, file `player.dat` ternyata hilang?

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

Tanpa _exception handling_, kode yang seharusnya bekerja dengan baik akan berhenti bekerja sama sekali. Kode yang bagus seharusnya bisa mengatasi masalah ini, kita sebagai penulis kode harus memiliki rencana jika terdapat sesuatu yang salah.

Catat juga, satu keuntungan dari adanya _exception handling_, adalah kita bisa membuat _stack trace_. Dengan _stack trace_, kita bisa mengetahui bagian mana dari kode yang salah, tanpa perlu menggunakan debugger.

---

## 2 | Hierarki Exception

Pada dasarnya, _exception_ hanyalah objek Java, dengan semua _exception_ mewarisi dari `Throwable`:

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

_Runtime exception_ dan _unchecked exception_ merujuk pada hal yang sama, sehingga seringkali bisa digunakan secara bergantian.


### 2.1 | Checked Exception (Pengecualian Terperiksa)

**Checked exception** adalah jenis pengecualian yang wajib ditangani oleh programmer, karena kompiler Java memeriksanya saat proses kompilasi. Artinya, kita harus menangani pengecualian tersebut secara langsung (menggunakan `try-catch`) atau menyatakannya akan dilempar ke luar metode dengan kata kunci `throws`.

Menurut dokumentasi Oracle, _checked exception_ digunakan ketika pemanggil metode masih mungkin melakukan pemulihan (recovery) dari kesalahan yang terjadi.

Contoh _checked exception_ yang umum adalah: 
- `IOException`
- `ServletException`


### 2.3 | Unchecked Exception (Pengecualian Tidak Terperiksa)

**Unchecked exception** adalah jenis pengecualian yang tidak diwajibkan oleh kompiler Java untuk ditangani.

Secara sederhana, jika kita membuat kelas _exception_ yang mewarisi `RuntimeException`, maka _exception_ tersebut termasuk _unchecked_; sedangkan jika tidak, maka ia tergolong _checked_.

Meskipun tampak lebih praktis, dokumentasi Oracle menegaskan bahwa kedua jenis _exception_ memiliki tujuan yang berbeda — _checked_ digunakan untuk kesalahan situasional yang dapat dipulihkan, sedangkan _unchecked_ digunakan untuk kesalahan akibat penggunaan kode yang salah (usage error).

Beberapa contoh _unchecked exception_:

- `NullPointerException`
- `IllegalArgumentException`
- `SecurityException`

### 2.3 | Error (Kesalahan Fatal)

**Error** mewakili kondisi serius dan umumnya tidak dapat dipulihkan, seperti ketidakcocokan pustaka, rekursi tak berujung, atau kebocoran memori.

Meskipun kelas `Error` tidak mewarisi `RuntimeException`, ia tetap termasuk dalam kategori _unchecked_, artinya kompiler tidak memaksa kita untuk menanganinya.

Dalam praktiknya, menangani, membuat instance, atau menurunkan kelas `Error` adalah hal yang tidak lazim. Biasanya, error dibiarkan mengalir hingga ke level tertinggi agar program berhenti, karena ini menandakan adanya masalah serius pada sistem.

Contoh error yang umum:

- `StackOverflowError`
- `OutOfMemoryError`

---

## 3 | Handling Exceptions

Dalam Java API, terdapat banyak bagian kode yang berpotensi menimbulkan kesalahan, dan beberapa di antaranya ditandai dengan _exception_, baik di deklarasi metode (signature) maupun di dokumentasi Javadoc, seperti berikut:

```java
/**
 * @exception FileNotFoundException ...
 */
public Scanner(String fileName) throws FileNotFoundException {
   // ...
}
```

Seperti yang sudah dijelaskan sebelumnya, ketika kita memanggil metode yang “berisiko” semacam ini, kita wajib menangani _checked exception_, sedangkan _unchecked exception_ boleh ditangani atau tidak — tergantung kebutuhan.

Java menyediakan beberapa cara untuk menangani _exception_ tersebut.

### 3.1 | `throws`

Cara paling sederhana untuk "menangani" sebuah _exception_ adalah dengan **melemparkannya kembali** menggunakan kata kunci `throws`:

```java
public int getPlayerScore(String playerFile)
  throws FileNotFoundException {
 
    Scanner contents = new Scanner(new File(playerFile));
    return Integer.parseInt(contents.nextLine());
}
```

Karena `FileNotFoundException` merupakan _checked exception_, inilah cara paling sederhana untuk memenuhi aturan kompiler. Namun, akibatnya, setiap kode yang memanggil metode tersebut juga harus menangani _exception_ itu!

Sementara itu, `parseInt` dapat melempar `NumberFormatException`, tetapi karena jenisnya _unchecked_, kita tidak diwajibkan untuk menanganinya.

### 3.2 | `try-catch`

Jika kita ingin menangani exception secara langsung, kita dapat menggunakan blok `try-catch`. Penanganannya bisa dilakukan dengan dua cara:

1. Melempar kembali exception dalam bentuk lain:

```java
public int getPlayerScore(String playerFile) {
    try {
        Scanner contents = new Scanner(new File(playerFile));
        return Integer.parseInt(contents.nextLine());
    } catch (FileNotFoundException noFile) {
        throw new IllegalArgumentException("File not found");
    }
}
```

2. Melakukan langkah pemulihan (recovery):

```java
public int getPlayerScore(String playerFile) {
    try {
        Scanner contents = new Scanner(new File(playerFile));
        return Integer.parseInt(contents.nextLine());
    } catch (FileNotFoundException noFile) {
        logger.warn("File not found, resetting score.");
        return 0;
    }
}
```

### 3.3 | `finally`

Ada kalanya kita memiliki kode yang harus dijalankan terlepas dari apakah terjadi exception atau tidak, dan di sinilah kata kunci `finally` digunakan.

Dalam contoh sebelumnya, sebenarnya ada bug tersembunyi: secara default, Java tidak otomatis mengembalikan file handle ke sistem operasi.
Karena itu, baik file berhasil dibaca atau tidak, kita harus memastikan proses pembersihan (cleanup) dilakukan dengan benar.

Contoh paling sederhana:

```java
public int getPlayerScore(String playerFile)
  throws FileNotFoundException {
    Scanner contents = null;
    try {
        contents = new Scanner(new File(playerFile));
        return Integer.parseInt(contents.nextLine());
    } finally {
        if (contents != null) {
            contents.close();
        }
    }
}
```

Pada contoh di atas, blok `finally` menunjukkan kode yang **akan selalu dijalankan**, apa pun yang terjadi saat mencoba membaca file.
Bahkan jika `FileNotFoundException` dilempar ke luar metode, isi dari blok `finally` tetap akan dijalankan terlebih dahulu.

Kita juga bisa menangani _exception_ sekaligus memastikan sumber daya ditutup dengan aman:

```java
public int getPlayerScore(String playerFile) {
    Scanner contents;
    try {
        contents = new Scanner(new File(playerFile));
        return Integer.parseInt(contents.nextLine());
    } catch (FileNotFoundException noFile) {
        logger.warn("File not found, resetting score.");
        return 0; 
    } finally {
        try {
            if (contents != null) {
                contents.close();
            }
        } catch (IOException io) {
            logger.error("Couldn't close the reader!", io);
        }
    }
}
```

Karena metode `close()` juga termasuk “berisiko”, kita harus menangani _exception_ yang mungkin muncul darinya.

Meskipun terlihat rumit, setiap bagian dari kode ini memiliki peran penting untuk menangani setiap kemungkinan kesalahan dengan tepat.


### 3.4 | `try`-with-resources

Sejak Java 7, penulisan kode seperti contoh sebelumnya bisa dibuat jauh lebih sederhana saat bekerja dengan objek yang mengimplementasikan `AutoCloseable`.

Contohnya:

```java
public int getPlayerScore(String playerFile) {
    try (Scanner contents = new Scanner(new File(playerFile))) {
        return Integer.parseInt(contents.nextLine());
    } catch (FileNotFoundException e) {
        logger.warn("File not found, resetting score.");
        return 0;
    }
}
```

Dengan menempatkan objek yang bersifat `AutoCloseable` di dalam deklarasi `try`, kita tidak perlu lagi menutup resource secara manual — Java akan otomatis menutupnya setelah blok `try` selesai dieksekusi, baik berhasil maupun gagal.

Kita tetap bisa menambahkan blok `finally` jika masih ada proses pembersihan lain yang perlu dilakukan.

Untuk pembahasan lebih lengkap, bisa melihat artikel khusus yang membahas tentang *try-with-resources*.

### 3.5 | Multiple catch blocks

Kadang, sebuah blok kode bisa melempar lebih dari satu jenis _exception_, dan kita bisa menanganinya dengan beberapa blok `catch` yang berbeda:

```java
public int getPlayerScore(String playerFile) {
    try (Scanner contents = new Scanner(new File(playerFile))) {
        return Integer.parseInt(contents.nextLine());
    } catch (IOException e) {
        logger.warn("Player file wouldn't load!", e);
        return 0;
    } catch (NumberFormatException e) {
        logger.warn("Player file was corrupted!", e);
        return 0;
    }
}
```

Beberapa blok `catch` memungkinkan kita menangani setiap _exception_ secara terpisah sesuai kebutuhan.

Perhatikan bahwa pada contoh di atas, `FileNotFoundException` tidak ditangani secara eksplisit, karena kelas tersebut merupakan turunan dari `IOException`.
Dengan menangani `IOException`, Java secara otomatis menganggap semua subclass-nya juga sudah ditangani.

Namun, jika kita ingin memperlakukan `FileNotFoundException` secara berbeda dari `IOException` yang lebih umum, kita bisa menulisnya seperti ini:

```java
public int getPlayerScore(String playerFile) {
    try (Scanner contents = new Scanner(new File(playerFile))) {
        return Integer.parseInt(contents.nextLine());
    } catch (FileNotFoundException e) {
        logger.warn("Player file not found!", e);
        return 0;
    } catch (IOException e) {
        logger.warn("Player file wouldn't load!", e);
        return 0;
    } catch (NumberFormatException e) {
        logger.warn("Player file was corrupted!", e);
        return 0;
    }
}
```

Java memungkinkan kita menangani subclass _exception_ secara terpisah, tetapi pastikan urutannya benar — _exception_ yang lebih spesifik harus ditangani terlebih dahulu sebelum yang lebih umum.

### 3.6 | Union `catch` blocks

Jika kita tahu bahwa beberapa _exception_ akan ditangani dengan cara yang sama, sejak Java 7 kita bisa menggunakan satu blok `catch` untuk menangkap lebih dari satu jenis _exception_ sekaligus:

```java
public int getPlayerScore(String playerFile) {
    try (Scanner contents = new Scanner(new File(playerFile))) {
        return Integer.parseInt(contents.nextLine());
    } catch (IOException | NumberFormatException e) {
        logger.warn("Failed to load score!", e);
        return 0;
    }
}
```

Dengan sintaks seperti ini, Java akan menangkap salah satu dari _exception_ yang disebutkan di dalam tanda pemisah `|`, lalu menanganinya menggunakan blok kode yang sama.

Fitur ini membantu membuat kode lebih ringkas dan mudah dibaca ketika penanganan untuk beberapa jenis _exception_ identik.

---

## 4 | Throwing Exceptions

Jika kita tidak ingin menangani _exception_ secara langsung, atau ingin membuat _exception_ sendiri agar bisa ditangani oleh kode lain, maka kita perlu memahami penggunaan kata kunci `throw`.

Misalnya, kita membuat sendiri sebuah checked exception bernama `TimeoutException`:

```java
public class TimeoutException extends Exception {
    public TimeoutException(String message) {
        super(message);
    }
}
```

Lalu kita memiliki sebuah metode yang mungkin membutuhkan waktu lama untuk diselesaikan:

```java
public List<Player> loadAllPlayers(String playersFile) {
    // ... potentially long operation
}
```

### 4.1 | Throwing a checked exception

Sama seperti pernyataan `return`, kita bisa menggunakan `throw` kapan saja di dalam metode.

Biasanya, kita melempar _exception_ untuk menandakan bahwa **terjadi kesalahan atau kondisi yang tidak normal**:

```java
public List<Player> loadAllPlayers(String playersFile) throws TimeoutException {
    while (!tooLong) {
        // ... potentially long operation
    }
    throw new TimeoutException("This operation took too long");
}
```

Karena `TimeoutException` merupakan checked _exception_, kita juga harus menambahkan kata kunci `throws` pada deklarasi metode agar pemanggil metode mengetahui bahwa _exception_ tersebut perlu ditangani.


### 4.2 | Throwing an unchecked exception

Jika kita ingin melakukan sesuatu seperti memvalidasi input, kita bisa menggunakan unchecked _exception_:

```java
public List<Player> loadAllPlayers(String playersFile) throws TimeoutException {
    if (!isFilenameValid(playersFile)) {
        throw new IllegalArgumentException("Filename isn't valid!");
    }

    // ...
}
```

Karena `IllegalArgumentException` termasuk unchecked _exception_, kita **tidak wajib menandai metode dengan `throws`**, meskipun tetap boleh dilakukan jika ingin.

Beberapa developer tetap menuliskannya sebagai bentuk dokumentasi agar lebih jelas bahwa metode tersebut dapat melempar _exception_ tertentu.

### 4.4 | Wrapping and rethrowing

Kita juga bisa memilih untuk melempar kembali _exception_ yang sudah kita tangkap:

```java
public List<Player> loadAllPlayers(String playersFile) 
  throws IOException {
    try { 
        // ...
    } catch (IOException io) { 		
        throw io;
    }
}
```

Atau, kita bisa membungkusnya dalam _exception_ baru sebelum melemparkannya kembali:

```java
public List<Player> loadAllPlayers(String playersFile) 
  throws PlayerLoadException {
    try { 
        // ...
    } catch (IOException io) { 		
        throw new PlayerLoadException(io);
    }
}
```

Pendekatan ini berguna ketika kita ingin **menyatukan berbagai jenis _exception_ menjadi satu tipe yang lebih umum**, sehingga penanganannya menjadi lebih sederhana di level yang lebih tinggi.

### 4.5 | Rethrowing Throwable atau Exception

Sekarang masuk ke kasus khusus.

Jika di dalam suatu blok kode **hanya ada kemungkinan munculnya unchecked exception**, maka kita bisa menangkap dan melempar kembali (`rethrow`) `Throwable` atau `Exception` **tanpa perlu menambahkannya ke deklarasi metode** dengan `throws`:

```java
public List<Player> loadAllPlayers(String playersFile) {
    try {
        throw new NullPointerException();
    } catch (Throwable t) {
        throw t;
    }
}
```

Meskipun tampak sederhana, kode di atas **tidak dapat melempar checked exception**, sehingga walaupun kita menulis `throw t`, kompiler tidak mengharuskan adanya klausa `throws` pada deklarasi metode.

Teknik ini sering digunakan dalam **proxy class atau metode dinamis**, di mana kita perlu menangani dan melempar ulang berbagai jenis _exception_ tanpa mendefinisikan semuanya satu per satu.

### 4.5 | Inheritance

Ketika kita menandai suatu metode dengan kata kunci `throws`, hal itu akan memengaruhi bagaimana subclass boleh melakukan override terhadap metode tersebut.

Jika metode di kelas induk melempar checked _exception_:

```java
public class Exceptions {
    public List<Player> loadAllPlayers(String playersFile) 
      throws TimeoutException {
        // ...
    }
}
```

Maka subclass masih boleh membuat versi override dengan **signature yang lebih aman** (tidak melempar checked _exception_ apa pun):

```java
public class FewerExceptions extends Exceptions {	
    @Override
    public List<Player> loadAllPlayers(String playersFile) {
        // overridden
    }
}
```

Namun subclass **tidak boleh** membuat versi override yang **lebih berisiko**, yaitu yang menambahkan checked _exception_ baru:

```java
public class MoreExceptions extends Exceptions {		
    @Override
    public List<Player> loadAllPlayers(String playersFile) throws MyCheckedException {
        // overridden
    }
}
```

Alasannya adalah karena **kontrak metode ditentukan pada waktu kompilasi berdasarkan tipe referensi, bukan tipe objek sebenarnya**.

Contohnya:

```java
Exceptions exceptions = new MoreExceptions();
exceptions.loadAllPlayers("file");
```

Kompiler hanya akan mengharuskan kita menangani `TimeoutException`, padahal implementasi di subclass `MoreExceptions` justru melempar exception lain (`MyCheckedException`).

Kesimpulannya: subclass boleh melempar **lebih sedikit** checked _exception_ daripada superclass-nya, **tapi tidak boleh lebih banyak**.

---

## 5 | Anti-Patterns

### 5.1 | Swallowing exceptions

Ada satu cara lain untuk membuat kode kita tetap lolos dari pemeriksaan kompiler, yaitu dengan menangkap dan mengabaikan _exception_ sepenuhnya:

```java
public int getPlayerScore(String playerFile) {
    try {
        // ...
    } catch (Exception e) {} // <== catch and swallow
    return 0;
}
```

Teknik ini disebut *swallowing exception* — artinya kita menangkap _exception_ tapi tidak melakukan apa pun terhadapnya. Biasanya ini bukan praktik yang baik, karena masalahnya tidak diselesaikan, dan kode lain pun tidak bisa tahu bahwa ada kesalahan yang terjadi.

Ada kalanya memang kita tahu bahwa _exception_ tersebut **tidak mungkin terjadi**, misalnya karena kondisi yang sudah terjamin oleh logika program. Dalam kasus seperti itu, sebaiknya tetap beri komentar yang menjelaskan bahwa _exception_ tersebut sengaja diabaikan:

```java
public int getPlayerScore(String playerFile) {
    try {
        // ...
    } catch (IOException e) {
        // this will never happen
    }
}
```

Cara lain untuk “menelan” _exception_ adalah dengan hanya mencetaknya ke error stream:

```java
public int getPlayerScore(String playerFile) {
    try {
        // ...
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
```

Pendekatan ini sedikit lebih baik, karena setidaknya kita menulis pesan kesalahan untuk diagnosis di kemudian hari.
Namun, cara yang lebih disarankan adalah dengan menggunakan logger:

```java
public int getPlayerScore(String playerFile) {
    try {
        // ...
    } catch (IOException e) {
        logger.error("Couldn't load the score", e);
        return 0;
    }
}
```

Menangani _exception_ dengan cara ini memang praktis, tapi kita perlu memastikan bahwa **tidak ada informasi penting yang hilang**, terutama jika pemanggil metode membutuhkan informasi itu untuk memperbaiki masalah.

Kita juga bisa *tanpa sengaja* menelan _exception_ dengan tidak menyertakannya sebagai penyebab ketika melempar exception baru:

```java
public int getPlayerScore(String playerFile) {
    try {
        // ...
    } catch (IOException e) {
        throw new PlayerScoreException();
    }
}
```

Sekilas terlihat benar, karena kita melempar _exception_ baru untuk memberi tahu bahwa ada kesalahan.
Namun, kita kehilangan konteks asli (`IOException`) yang bisa membantu melacak akar masalahnya.

Solusi yang benar adalah menyertakan _exception_ asli sebagai penyebab:

```java
public int getPlayerScore(String playerFile) {
    try {
        // ...
    } catch (IOException e) {
        throw new PlayerScoreException(e);
    }
}
```

Perbedaan kecil ini sangat penting — dengan menyertakan `IOException` sebagai penyebab, kita tetap mempertahankan jejak asal kesalahan sehingga lebih mudah untuk melakukan debug atau logging di kemudian hari.


### 5.2 | Using return in a finally block

Cara lain yang tanpa sadar bisa *menelan* _exception_ adalah dengan menggunakan `return` di dalam blok `finally`.
Ini berbahaya karena ketika `finally` mengembalikan nilai secara tiba-tiba, JVM akan **mengabaikan _exception_ yang dilempar** dari blok `try`.

Contoh:

```java
public int getPlayerScore(String playerFile) {
    int score = 0;
    try {
        throw new IOException();
    } finally {
        return score; // <== IOException diabaikan
    }
}
```

Dalam kasus di atas, `IOException` tidak akan pernah terlihat oleh pemanggil metode, karena perintah `return` di dalam `finally` menimpa aliran eksekusi sebelumnya.

Menurut *Java Language Specification*:

> Jika eksekusi blok `try` berakhir secara tiba-tiba karena suatu alasan R (misalnya karena exception), maka blok `finally` akan dijalankan terlebih dahulu.
>
> * Jika blok `finally` berakhir secara normal, maka eksekusi `try` juga berakhir secara tiba-tiba karena alasan R.
> * Namun, jika blok `finally` juga berakhir secara tiba-tiba karena alasan lain S (misalnya `return`, `throw`, atau `break`), maka alasan R akan **dibuang**, dan `try` akan berakhir karena alasan S.

Artinya: jika kamu melakukan `return`, `throw` baru, atau keluar paksa dari `finally`, maka _exception_ yang seharusnya muncul akan hilang total.
Itu sebabnya, menempatkan `return` di dalam `finally` adalah praktik yang sebaiknya **dihindari sepenuhnya**.

### 5.3 | Menggunakan `throw` di dalam `finally` block

Mirip seperti penggunaan `return` di dalam blok `finally`, melempar _exception_ (`throw`) di sana juga **menggantikan _exception_ sebelumnya** yang mungkin muncul dari blok `try` atau `catch`.

Akibatnya, _exception_ asli yang seharusnya muncul akan **hilang**, dan yang tersisa hanya _exception_ dari blok `finally`.

Contoh:

```java
public int getPlayerScore(String playerFile) {
    try {
        // ...
    } catch (IOException io) {
        throw new IllegalStateException(io); // <== tertimpa oleh finally
    } finally {
        throw new OtherException();
    }
}
```

Pada kode di atas, `IllegalStateException` yang dilempar dari blok `catch` akan **terhapus**, karena blok `finally` juga melempar `OtherException`.
Akhirnya, hanya `Other_exception_` yang terlihat keluar dari metode ini.

Kesimpulannya:
Jangan pernah melempar _exception_ baru dari dalam `finally` kecuali benar-benar yakin. Jika perlu menangani kondisi khusus di `finally`, sebaiknya lakukan dengan hati-hati—misalnya log error atau bersihkan sumber daya—tanpa mengacaukan exception utama yang sedang diproses.


### 5.4 | Menggunakan `throw` sebagai `goto`

Beberapa orang tergoda untuk menggunakan `throw` seolah-olah itu adalah pernyataan `goto`:

```java
public void doSomething() {
    try {
        // sekumpulan kode pertama
        throw new MyException();
        // sekumpulan kode kedua
    } catch (MyException e) {
        // sekumpulan kode ketiga
    }		
}
```

Kode semacam ini aneh, karena `throw` di sini digunakan **untuk mengatur alur program**, bukan untuk **menangani kesalahan**.

Pendekatan seperti ini membuat logika program jadi membingungkan, sulit diikuti, dan melanggar prinsip dasar dari pengecualian (_exception_) yang seharusnya hanya digunakan untuk menangani kondisi tak terduga, bukan untuk lompat-lompat antar bagian kode seperti `goto`.

Kalau memang tujuannya hanya mengatur urutan eksekusi, gunakan kontrol alur normal seperti `if`, `return`, atau `break`, bukan `throw`.

---
## 6 | Common Exceptions and Errors

Berikut beberapa pengecualian (_exception_) dan error umum yang sering ditemui dalam Java:

### 6.1 | Checked Exceptions**
- *IOException* – Pengecualian ini biasanya menunjukkan bahwa terjadi kegagalan pada jaringan, sistem berkas (filesystem), atau database.

### 6.2 | Runtime Exceptions
- *ArrayIndexOutOfBoundsException* – Terjadi ketika kita mencoba mengakses indeks array yang tidak ada, misalnya mencoba mengambil elemen indeks ke-5 dari array yang panjangnya hanya 3.

- *ClassCastException* – Terjadi ketika kita mencoba melakukan konversi (cast) yang tidak sah, misalnya mengubah `String` menjadi `List`. Biasanya bisa dihindari dengan melakukan pemeriksaan `instanceof` sebelum melakukan casting.

- *IllegalArgumentException* – Cara umum untuk menyatakan bahwa salah satu parameter yang diberikan ke metode atau konstruktor tidak valid.

- *IllegalStateException* – Cara umum untuk menyatakan bahwa kondisi internal (seperti status objek) tidak valid.

- *NullPointerException* – Terjadi ketika kita mencoba mengakses atau menggunakan objek yang bernilai `null`. Biasanya bisa dihindari dengan pemeriksaan `null` terlebih dahulu atau dengan menggunakan `Optional`.

- *NumberFormatException* – Terjadi ketika kita mencoba mengonversi `String` menjadi angka, tetapi `String` tersebut mengandung karakter yang tidak valid, misalnya mencoba mengubah `"5f3"` menjadi angka.

### 6.3 | Errors
- *StackOverflowError* – Terjadi ketika tumpukan (stack) terlalu besar. Kadang muncul pada aplikasi yang sangat besar, namun paling sering disebabkan oleh rekursi tanpa batas.

- *NoClassDefFoundError* – Terjadi ketika sebuah kelas gagal dimuat, biasanya karena tidak ada di classpath atau gagal saat inisialisasi statis.

- *OutOfMemoryError* – Terjadi ketika JVM kehabisan memori untuk membuat objek baru. Kadang disebabkan oleh kebocoran memori (memory leak).
