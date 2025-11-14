# 2 | Lifecycle of a Program

Jika kamu membaca tulisan ini, kemungkinan besar kamu sudah tahu cara menulis kode Java. Itu hal yang bagus — menurutku, semua orang seharusnya tahu cara menulis kode di zaman sekarang, sama seperti semua orang perlu tahu operasi matematika dasar seperti `+`, `-`, `*`, dan `/`, meskipun kita semua punya kalkulator.

Pada tulisan sebelumnya, aku sudah membahas bagaimana kode Java pertama-tama **“dikompilasi” menjadi bytecode**, lalu diinterpretasikan dan dijalankan oleh **JVM (Java Virtual Machine)**. Namun, waktu itu aku belum menjelaskan bagaimana JVM sebenarnya melakukan proses eksekusi bytecode tersebut.

Tujuan dari artikel ini adalah untuk menjelaskan hal itu — menjawab pertanyaan:
**“Apa yang sebenarnya terjadi ketika kita menekan tombol ‘Run’ atau ‘Execute’ di IDE favorit kita?”**

Setelah membaca tulisan ini, kamu akan memahami **siklus eksekusi (execution lifecycle)** dari sebuah aplikasi Java, serta **aktivitas-aktivitas yang dilakukan JVM** selama tahap eksekusi tersebut.

## 1 | Execution Lifecycle

Siklus eksekusi dari sebuah aplikasi Java secara umum dapat dibagi menjadi tiga tahap utama:

1. **Kompilasi** – Kode sumber aplikasi dikonversi menjadi *bytecode* menggunakan *compiler* `javac`.
2. **Pemanggilan Kelas (Class Loading)** – *Bytecode* dimuat ke dalam memori, dan berkas-berkas kelas yang diperlukan dipersiapkan untuk dijalankan.
3. **Eksekusi Bytecode** – JVM mengeksekusi *bytecode* sehingga program dapat berjalan.

Tahap terakhir ini sepenuhnya menjadi tanggung jawab **Java Virtual Machine (JVM)**.
JVM menangani proses pemuatan *bytecode*, pengalokasian memori, serta konversi *bytecode* menjadi *native machine code* (kode mesin yang dapat dijalankan oleh prosesor).

Dengan kata lain, JVM bertugas menerjemahkan *bytecode* ke dalam instruksi mesin yang spesifik untuk platform tempat program dijalankan.
Proses ini cukup kompleks karena setiap arsitektur prosesor memiliki kumpulan instruksi yang berbeda—misalnya x86, ARM, MIPS, atau PowerPC.

Selain itu, JVM juga menyediakan berbagai layanan *runtime* seperti manajemen memori, sinkronisasi *thread*, dan penanganan *exception*.

Tulisan ini akan berfokus pada **tahap eksekusi bytecode**.

Diagram aktivitas berikut menggambarkan apa yang terjadi selama tahap ini:

![img.png](media/img.png)

Bagian-bagian berikut menjelaskan lebih detail setiap aktivitas yang terjadi selama tahap eksekusi *bytecode*.

### 1.1 | Loading

*Loading* adalah proses menemukan bentuk biner dari sebuah kelas atau *interface* (yaitu file dalam format `.class`) berdasarkan nama tertentu, lalu membangun sebuah objek `Class` dari bentuk biner tersebut.

JVM menggunakan **ClassLoader** untuk menemukan representasi biner dari kelas, misalnya `Main`.
Kelas `ClassLoader` dan seluruh subclass-nya bertanggung jawab atas proses *loading* ini.
Metode `defineClass` dipanggil untuk membangun objek `Class` dari representasi biner berkas `.class`.

JVM menyediakan dua jenis *class loader* bawaan:

* **Bootstrap Class Loader**, yaitu *class loader* inti yang memuat kelas-kelas dasar Java dari berkas `rt.jar`.
* **Extension Class Loader**, yang memuat kelas-kelas tambahan dari direktori `ext`.

Selain itu, ada juga **Application Class Loader**, yang digunakan untuk memuat kelas dari lokasi lain, seperti *classpath* atau server jarak jauh.
*Class loader* jenis ini biasanya merupakan subclass dari `ClassLoader` yang telah dikustomisasi, dan dapat memuat kelas melalui instance dari `java.lang.Class`.

```java
public class CustomClassLoader extends ClassLoader {
  public CustomClassLoader(ClassLoader parent) {
    super(parent);
  }
  // Method to load a class given its name
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    if (!name.startsWith("com.example")) {
      // Delegate to the parent class loader
      return super.loadClass(name);
      }
    // The class name does start with "com.example", construct the file name
    String fileName = name.substring(name.lastIndexOf('.') + 1) + ".class";
    // Try to open an InputStream for the file
    InputStream inputStream = getClass().getResourceAsStream(fileName);
    // If the stream is null, throw a ClassNotFoundException
    if (inputStream == null) {
      throw new ClassNotFoundException();
    }
    try {
      // Create a byte array to hold the contents of the file
      byte[] bytes = new byte[inputStream.available()];
      // Read the bytes from the input stream
      inputStream.read(bytes);
      // Define the class using the class name, the byte array, and the number of bytes
      return defineClass(name, bytes, 0, bytes.length);
    } catch (IOException e) {
      throw new ClassNotFoundException();
    }
  }
}
```

Kelas ini merupakan turunan dari `ClassLoader` dan menimpa metode `loadClass` untuk memberikan perilaku khusus dalam proses pemuatan kelas.
Pertama, metode ini memeriksa apakah nama kelas dimulai dengan `com.example`.
Jika **tidak**, proses pemuatan akan diteruskan ke *parent class loader*.
Namun jika **ya**, maka metode ini akan membentuk nama file yang sesuai dan mencoba membuka `InputStream` untuk file tersebut.

Jika berhasil, *class loader* membaca data biner dari *input stream* dan memanggil metode `defineClass` untuk mendefinisikan kelas tersebut.
Jika gagal, ia akan melemparkan `ClassNotFoundException`.

Secara ringkas, proses *class loading* melakukan tiga fungsi utama berikut:

1. Membuat aliran biner (binary stream) dari berkas `.class`.
2. Melakukan parsing terhadap data biner tersebut sesuai dengan struktur data internal Java.
3. Membuat sebuah instance dari `java.lang.Class`.

Setelah ketiga langkah ini selesai, objek kelas (*class instance*) siap untuk masuk ke tahap berikutnya, yaitu **linking**.

### 1.2 | Linking

*Linking* adalah proses menggabungkan bentuk biner dari suatu kelas atau *interface* ke dalam *runtime state* JVM agar dapat dieksekusi.
Tahap ini terdiri dari tiga langkah utama: **verifikasi**, **preparasi**, dan (opsional) **resolusi** dari referensi simbolik.

1. **Verifikasi (Verification)**
   Pada tahap ini, JVM memeriksa apakah representasi kelas yang dimuat sudah terbentuk dengan benar dan memiliki *symbol table* yang valid.
   JVM juga memastikan bahwa kode yang mengimplementasikan kelas tersebut mematuhi aturan semantik dari bahasa Java dan JVM.

   Misalnya, JVM akan memeriksa bahwa:

    * setiap instruksi memiliki *opcode* yang valid,
    * setiap instruksi *branch* mengarah ke awal instruksi lain (bukan ke tengah-tengahnya),
    * setiap metode memiliki *signature* yang benar.

   Dengan kata lain, tahap ini memastikan bahwa bytecode yang dimuat aman dan dapat dijalankan tanpa melanggar aturan sistem.

2. **Persiapan (Preparation)**
   Pada tahap ini, JVM membuat *static fields* (variabel dan konstanta kelas) untuk setiap kelas atau *interface*, lalu menginisialisasi semuanya dengan nilai bawaan (default).
   Proses ini juga melibatkan pengalokasian ruang memori untuk *static storage* serta pembuatan struktur data internal yang digunakan oleh JVM, seperti *method tables*.

3. **Resolusi (Resolution)**
   Resolusi adalah proses memeriksa *symbolic references* dari suatu kelas terhadap kelas dan *interface* lain yang dirujuk di dalamnya.
   JVM akan memuat kelas atau *interface* yang disebutkan tersebut (jika belum dimuat) dan memverifikasi bahwa setiap referensi benar dan konsisten.


>Dalam implementasi sederhana bahasa C, proses *static linkage* menghasilkan program yang sudah sepenuhnya tertaut (*fully linked*).
Artinya, semua tautan ke rutin pustaka (*library routines*) yang digunakan program sudah diselesaikan secara penuh, dan salinannya disertakan langsung di dalam berkas hasil kompilasi, seperti `a.out`.
>
>Sebaliknya, dalam Java, referensi simbolik (*symbolic references*) **tidak langsung diselesaikan** pada saat kompilasi atau *linking*.
Proses ini dilakukan **hanya ketika referensi tersebut benar-benar digunakan** — pendekatan ini disebut *lazy resolution*.
>
>Sebagai contoh, jika sebuah kelas memiliki beberapa referensi simbolik ke kelas lain, maka tiap referensi bisa saja diselesaikan satu per satu saat digunakan, atau bahkan tidak pernah diselesaikan sama sekali, apabila referensi tersebut tidak pernah dipakai selama program dijalankan.


Secara ringkas, proses *linking* terdiri dari tiga tahap:

1. Verifikasi (*Verification*)
2. Persiapan (*Preparation*)
3. Resolusi (*Resolution*) — bersifat opsional

Setelah ketiga tahap ini selesai, kelas siap untuk masuk ke tahap **inisialisasi** (*Initialization*).

### 1.3 | Initialization

*Initialization* adalah proses mengeksekusi *static initializer* dan *initializer* untuk *static fields* (variabel kelas) yang dideklarasikan di dalam kelas.
Semua *static initializer* dijalankan sesuai urutan kemunculannya di dalam kode sumber.

Perhatikan contoh berikut: ketika JVM melakukan inisialisasi terhadap kelas `Main`, ia terlebih dahulu menginisialisasi semua *superclass*-nya, dimulai dari `Object`.
Karena `Object` tidak memiliki *superclass*, proses rekursi berhenti di sana.

Setelah itu, JVM melanjutkan inisialisasi kelas `Main` dengan mengeksekusi *class variable initializer* dan *static initializer* dalam urutan yang sama seperti di kode sumber.

Dalam contoh ini:

* *initializer* untuk variabel kelas `x` dijalankan terlebih dahulu,
* kemudian blok *static initializer*,
* dan terakhir *initializer* untuk variabel kelas `z`.

Setelah seluruh kelas selesai diinisialisasi, barulah metode `main` dapat dijalankan.

```java
class Main extends Object {
  // Class variable initializers and static initializers are executed in this order
  static int x = 1;  // Initializer untuk field statik x
  static int y;      // Field statik y tanpa initializer

  // Static initializer block
  static {
    y = x + 1;
  }

  static int z = x + y;  // Initializer untuk field statik z

  public static void main(String[] args) {
    // Metode main dijalankan setelah kelas selesai diinisialisasi
    System.out.println("x = " + x);
    System.out.println("y = " + y);
    System.out.println("z = " + z);
  }
}
```

Secara umum, inisialisasi sebuah kelas atau antarmuka `T` akan terjadi ketika salah satu dari kondisi berikut terpenuhi:

* Sebuah **instans dari `T` dibuat**.
* Sebuah **metode statik dari `T` dipanggil**.
* Sebuah **field statik dari `T` diubah nilainya** (assigned).
* Sebuah **field statik dari `T` digunakan**, dan field tersebut **bukan konstanta** (`final static` dengan nilai tetap).

Selain itu, **pemanggilan metode melalui refleksi** (misalnya menggunakan `java.lang.reflect.Method`) juga akan memicu inisialisasi kelas.

Setelah semua kelas yang diperlukan telah diinisialisasi, **JVM melanjutkan ke tahap instansiasi**, yaitu pembuatan objek dari kelas-kelas tersebut.

### 1.4 | Instantiating

Sebuah **instans kelas baru** secara eksplisit dibuat ketika ekspresi pembentukan objek dievaluasi — misalnya saat menggunakan **operator `new`**.

Namun, **instans juga bisa terbentuk secara implisit**, dalam beberapa situasi berikut:

* **Saat memuat kelas atau antarmuka** yang berisi *string literal* atau *text block*, JVM dapat membuat objek `String` baru.
* **Saat terjadi *boxing conversion***, misalnya mengubah `int` menjadi `Integer`, JVM membuat objek dari kelas pembungkus (*wrapper class*).
* **Saat melakukan operasi konkatenasi string**, JVM dapat membuat objek `String` baru untuk menampung hasilnya.
* **Saat mengevaluasi ekspresi *method reference* atau *lambda***, JVM membuat objek dari *functional interface* yang sesuai.

Contoh eksplisitnya seperti berikut:

```java
Point magicPoint = new Point(42, 42);
```

Selama proses **instansiasi**, JVM menjalankan langkah-langkah berikut:

1. **Mengalokasikan memori di heap** untuk menyimpan objek baru.
2. **Memanggil konstruktor kelas** untuk menginisialisasi objek tersebut.
3. **Mengembalikan referensi** ke objek baru yang telah dibuat.

### 1.5 | Finalizing

**Finalization** adalah proses untuk membersihkan sumber daya yang digunakan oleh sebuah objek dan mempersiapkannya sebelum dilakukan *garbage collection*.

Kelas `Object` di Java mendefinisikan sebuah metode bernama `finalize()`, yang akan dipanggil oleh *garbage collector* saat sebuah objek akan dihapus dari memori. Metode ini dapat di-*override* oleh subclass untuk menjalankan aksi pembersihan tertentu sebelum objek benar-benar dihapus.

Contoh berikut menunjukkan kelas `TempFile` yang menimpa (`override`) metode `finalize()` agar file sementara dihapus otomatis saat objeknya dikoleksi oleh *garbage collector*:

```java
public class TempFile {
  private File file;

  public TempFile(String filename) {
    file = new File(filename);
  }

  @Override
  protected void finalize() throws Throwable {
    // Hapus file ketika objek TempFile dikoleksi oleh garbage collector
    file.delete();
    super.finalize();
  }
}
```

Contoh di atas bisa berguna jika kamu ingin memastikan bahwa file sementara selalu dihapus ketika tidak lagi dibutuhkan.
Namun, **penting untuk diingat** bahwa metode `finalize()` **tidak dijamin akan selalu dipanggil**. Karena itu, metode ini **tidak boleh diandalkan untuk tugas penting** seperti menutup koneksi database, menulis data terakhir ke file, atau melepaskan sumber daya kritis lainnya.

Sebagai gantinya, praktik modern di Java lebih menyarankan untuk menggunakan:

* **`try-with-resources`** (untuk menutup sumber daya otomatis), atau
* **blok `finally`** (untuk menjamin pembersihan manual tetap dilakukan).

**Unloading** adalah proses menghapus kelas atau *interface* dari *runtime state* JVM — biasanya terjadi ketika *class loader* yang mendefinisikan kelas tersebut dikoleksi oleh *garbage collector*.
Tujuan utamanya adalah **menghemat memori**, dan biasanya hanya terasa manfaatnya pada aplikasi besar yang sering memuat banyak kelas sementara (misalnya server aplikasi modular atau plugin-based).

Berikut contoh kode yang menggambarkan *user-defined garbage collection*, dengan kelas `LargeClass` yang berisi array berukuran besar dan mengonsumsi banyak memori:

```java
import java.lang.ref.WeakReference;

public class LargeClass {
  // Array besar yang memakan banyak memori
  private int[] data = new int[Integer.MAX_VALUE / 10];

  public static void main(String[] args) {
    // Membuat objek LargeClass dan menyimpannya dalam weak reference
    LargeClass largeObject = new LargeClass();
    WeakReference<LargeClass> weakRef = new WeakReference<>(largeObject);
    largeObject = null;  // Tidak lagi memiliki referensi kuat

    // Memicu garbage collector secara manual
    System.gc();

    // Mengecek apakah objek LargeClass telah dikoleksi
    if (weakRef.get() == null) {
      System.out.println("The LargeClass object has been collected");
    } else {
      System.out.println("The LargeClass object has not been collected");
    }
  }
}
```

Jika objek `LargeClass` telah dikoleksi oleh *garbage collector*, maka output-nya akan:

> `The LargeClass object has been collected`
> Sebaliknya, jika belum dikoleksi:
> `The LargeClass object has not been collected`

Perlu diingat:

* Kelas dan *interface* yang dimuat oleh **bootstrap class loader** **tidak pernah di-*unload***.
* Pada aplikasi biasa (non-modular), proses *unloading* jarang terjadi karena *system class loader* tetap aktif sepanjang umur aplikasi.

Berbeda dari *garbage collection* yang menghapus **objek**, *class unloading* menghapus **definisi kelas** (objek `Class` dan metadata-nya dari memori JVM).
Ini hanya bisa terjadi bila:

1. *Class loader* yang memuat kelas tersebut sudah tidak direferensikan lagi.
2. *Garbage collector* menandainya untuk dikoleksi.

Contoh di bawah ini memperlihatkan situasi di mana *class unloading* sangat mungkin terjadi — biasanya dengan menggunakan *custom class loader* yang dimuat dan dibuang secara dinamis.

```java
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassUnloadingExample {
  public static void main(String[] args) throws Exception {
    // Jalur menuju file .class (pastikan sudah diatur dengan benar)
    URL classUrl = new URL("file:///path/to/LargeClass.class");

    // Membuat class loader khusus untuk memuat "LargeClass"
    URLClassLoader customClassLoader = new URLClassLoader(new URL[]{classUrl});

    // Memuat "LargeClass" menggunakan class loader khusus
    Class<?> largeClass = Class.forName("LargeClass", true, customClassLoader);

    // Membuat weak reference ke class loader khusus
    WeakReference<ClassLoader> weakClassLoaderRef = new WeakReference<>(customClassLoader);

    // Menghapus semua referensi kuat ke class loader dan class tersebut
    customClassLoader = null;
    largeClass = null;

    // Menyarankan JVM untuk menjalankan garbage collector
    System.gc();

    // Menunggu sebentar untuk meningkatkan kemungkinan GC dijalankan
    Thread.sleep(1000);

    // Mengecek apakah class loader khusus sudah dikoleksi oleh garbage collector
    if (weakClassLoaderRef.get() == null) {
      System.out.println("Custom class loader telah dikoleksi oleh garbage collector, menandakan bahwa LargeClass mungkin telah di-unload");
    } else {
      System.out.println("Custom class loader masih ada di memori");
    }
  }
}
```

Pada contoh sebelumnya, *custom class loader* digunakan untuk memuat `LargeClass.class`, kemudian semua referensi terhadap *class loader* tersebut dihapus, sehingga ia memenuhi syarat untuk dikoleksi oleh *garbage collector*. Program kemudian memeriksa apakah *custom class loader* tersebut sudah dikoleksi dengan cara mengecek objek `WeakReference`. Jika `customClassLoader` telah dikoleksi, maka besar kemungkinan kelas yang dimuatnya (`LargeClass`) juga telah di-*unload*.

---

### Program Exit

*Program exit* mengacu pada proses penghentian eksekusi program. Ini berarti semua *thread* yang bukan *daemon thread* akan dihentikan, atau ada *thread* yang memanggil metode `exit()` dari kelas `Runtime`.
Metode ini menghentikan JVM dan keluar dengan kode keluar (*exit code*) yang ditentukan. Namun, penggunaan metode ini dibatasi oleh *security manager*. Jika ada *security manager* dan ia tidak mengizinkan program untuk keluar, maka pemanggilan `exit()` akan melempar `SecurityException`.

---

### Kesimpulan

Dalam artikel ini, kita telah membahas lebih dalam tentang **siklus eksekusi aplikasi Java**. Seperti yang dijelaskan, ada banyak tahap yang dilakukan sebelum metode `main()` dijalankan. Mulai dari **class loading**, **linking**, **initializing**, hingga **unloading**, JVM melakukan serangkaian langkah kompleks untuk memastikan program berjalan dengan benar.

Pemahaman ini penting bagi pengembang agar lebih mengerti bagaimana JVM bekerja di balik layar dan bagaimana cara mengoptimalkan aplikasi Java.
Semoga artikel ini membantu kamu memahami lebih dalam — dan semoga kamu menikmati proses belajarnya 😎.
