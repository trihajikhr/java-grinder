# 1 | Basic Syntax

Pada materi kali ini, kita akan belajar hal-hal yang sangat basic, yaitu jenis-jenis variabel, dan aturan penamaan variabel. Walaupun sederhana, namun penting untuk dipahami sebelum memulai materi Java yang lain.

---

## 1 | Variables

Seperti yang telah kamu pelajari pada bagian sebelumnya, sebuah **objek** menyimpan *state*-nya di dalam **field**.

```java
int cadence = 0;
int speed = 0;
int gear = 1;
```

Pembahasan *“What Is an Object?”* telah memperkenalkan kamu pada konsep **field**, tetapi mungkin kamu masih memiliki beberapa pertanyaan, seperti:
Apa saja aturan dan konvensi penamaan sebuah field?
Selain `int`, tipe data apa lagi yang tersedia?
Apakah sebuah field harus diinisialisasi saat dideklarasikan?
Apakah field akan otomatis memiliki nilai bawaan jika tidak diberi nilai secara eksplisit?

Kita akan membahas jawaban dari pertanyaan-pertanyaan tersebut di bagian ini. Namun sebelum itu, ada beberapa perbedaan teknis yang perlu kamu pahami terlebih dahulu. Dalam bahasa pemrograman Java, istilah **“field”** dan **“variable”** sama-sama digunakan — dan hal ini sering menjadi sumber kebingungan bagi pengembang pemula, karena keduanya tampak merujuk pada hal yang sama.

Bahasa pemrograman Java mendefinisikan beberapa jenis **variabel** sebagai berikut:


### 1.1 | Variabel Instans (*Instance Variables* / *Non-Static Fields*)

Secara teknis, objek menyimpan keadaan (*state*) masing-masing di dalam **non-static field**, yaitu field yang dideklarasikan **tanpa** menggunakan kata kunci `static`.
Non-static field juga disebut **variabel instans**, karena nilainya **unik untuk setiap instans dari suatu kelas** (atau dengan kata lain, untuk setiap objek).
Sebagai contoh, nilai `currentSpeed` pada satu objek sepeda tidak akan sama dengan `currentSpeed` pada objek sepeda lainnya.

### 1.2 | Variabel Kelas (*Class Variables* / *Static Fields*)

**Variabel kelas** adalah field apa pun yang dideklarasikan dengan **modifier `static`**.
Kata kunci ini memberi tahu kompiler bahwa **hanya ada satu salinan variabel tersebut** di seluruh program, tidak peduli berapa kali kelas tersebut dibuat instansinya.

Sebagai contoh, field yang menyimpan jumlah gigi (*gear*) untuk jenis sepeda tertentu bisa ditandai sebagai `static`, karena secara konsep jumlah gigi tersebut **sama untuk semua objek sepeda** dari kelas tersebut.

Kode berikut akan membuat field `static` seperti itu:

```java
static int numGears = 6;
```

Selain itu, kata kunci `final` dapat ditambahkan untuk menunjukkan bahwa **nilai jumlah gigi tersebut tidak akan pernah berubah**, misalnya:

```java
static final int numGears = 6;
```

### 1.3 |  Variabel Lokal (*Local Variables*)

Sama seperti objek yang menyimpan *state*-nya dalam field, sebuah **metode** sering kali menyimpan *state* sementara di dalam **variabel lokal**.
Sintaks deklarasi variabel lokal mirip dengan deklarasi field, misalnya:

```java
int count = 0;
```

Tidak ada kata kunci khusus untuk menandai sebuah variabel sebagai *local*; hal itu **ditentukan sepenuhnya oleh lokasi deklarasinya** — yaitu, di antara tanda kurung kurawal pembuka dan penutup dari suatu metode.

Karena itu, variabel lokal hanya **dapat diakses di dalam metode tempat ia dideklarasikan**, dan **tidak dapat digunakan** oleh bagian lain dari kelas tersebut.

### 1.4 | Parameter (*Parameters*)

Kamu sebenarnya sudah melihat contoh **parameter**, baik pada kelas `Bicycle` maupun pada metode `main` di aplikasi *“Hello World!”*.
Ingat kembali, *signature* dari metode `main` adalah:

```java
public static void main(String[] args)
```

Di sini, variabel `args` merupakan **parameter** dari metode tersebut.

Hal penting yang perlu diingat adalah bahwa **parameter selalu diklasifikasikan sebagai “variabel”**, bukan *field*.
Prinsip ini juga berlaku untuk konstruk yang menerima parameter lainnya — seperti **konstruktor** dan **penangan pengecualian (exception handler)** — yang akan kamu pelajari lebih lanjut di bagian-bagian berikutnya dari tutorial ini.

---

Jika pembahasan mengacu pada *field* secara umum (tidak termasuk *local variable* dan *parameter*), maka istilah yang digunakan cukup *field*.
Jika pembahasan berlaku untuk semua jenis variabel, maka akan digunakan istilah *variable*.
Apabila konteks membutuhkan pembedaan yang lebih spesifik, maka akan disebutkan secara jelas—misalnya *static field*, *local variable*, dan sebagainya.

Kadang kamu juga akan menjumpai istilah *member*.
Sebuah *type* (tipe data atau kelas) memiliki *member* yang mencakup *field*, *method*, dan *nested type* (tipe yang dideklarasikan di dalam tipe lain).

---

## 2 | Naming Variables

Setiap bahasa pemrograman memiliki aturan dan konvensinya sendiri untuk penamaan, dan Java tidak berbeda.
Aturan serta konvensi dalam menamai variabel di Java dapat diringkas sebagai berikut:

1. **Nama variabel bersifat *case-sensitive***, artinya huruf besar dan huruf kecil dianggap berbeda.
   Sebuah nama variabel dapat berupa pengenal (*identifier*) apa pun yang sah—yakni urutan huruf dan angka Unicode dengan panjang tak terbatas, diawali dengan huruf, tanda dolar (`$`), atau garis bawah (`_`).
   Namun, konvensinya adalah selalu memulai nama variabel dengan huruf, bukan dengan `$` atau `_`.

   Tanda dolar hampir tidak pernah digunakan dalam penamaan variabel.
   Kamu mungkin menemukannya pada nama yang dibuat otomatis oleh compiler atau framework, tapi sebaiknya hindari penggunaannya saat menulis kode sendiri.
   Hal yang sama berlaku untuk garis bawah (`_`): secara teknis boleh, tapi secara gaya penulisan tidak disarankan.
   Selain itu, spasi tidak diizinkan dalam nama variabel.

2. Karakter berikutnya setelah karakter pertama boleh berupa huruf, angka, tanda dolar, atau garis bawah.
   Meski begitu, gunakan akal sehat dan konvensi umum saat memilih nama. Gunakan **kata yang jelas dan lengkap** daripada singkatan yang tidak jelas.
   Langkah ini membuat kode lebih mudah dibaca dan dipahami, bahkan bisa membuatnya *self-documenting*.

   Sebagai contoh, nama variabel seperti `cadence`, `speed`, dan `gear` jauh lebih intuitif dibandingkan versi singkat seperti `c`, `s`, dan `g`.
   Ingat juga, nama variabel tidak boleh menggunakan kata kunci (*keyword*) atau kata yang sudah dipesan (*reserved word*) oleh Java.


3. Jika nama variabel hanya terdiri dari satu kata, tulislah seluruhnya dengan huruf kecil.
   Jika terdiri dari lebih dari satu kata, kapitalisasi huruf pertama dari setiap kata berikutnya.
   Contohnya: `gearRatio` dan `currentGear`.
   Konvensi ini dikenal sebagai *camelCase*.

   Namun, jika variabel menyimpan nilai konstan (misalnya `static final int NUM_GEARS = 6;`), maka konvensinya sedikit berbeda: semua huruf ditulis **kapital**, dan setiap kata dipisahkan dengan garis bawah (`_`).
   Perlu diingat, garis bawah sebaiknya hanya digunakan dalam konteks seperti ini.