# Java String 

Tipe data String dalam Java dapat berisi serangkaian karakter (string), seperti mutiara pada sutas tali. String adalah cara Anda bekerja dengan teks di Java. Setelah String dalam Java dibuat, Anda dapat mencari di dalamnya, membuat substring darinya, membuat string baru berdasarkan string pertama dengan beberapa bagian yang dignati, dan banyak hal lainya.

---

## 1 | Representasi String Internal

String Java (sebelum Java 9) direpresentasikan secara internal di JVM menggunakan byte, yang dikodekan sebagai UTF-16. UTF-16 menggunakan 2 byte untuk merepresentasikan satu karakter. Dengan demikian, karakter-karakter dalam String Java direpresentasikan menggunakan `char` array.

UTF adalah pengkodena karakter yang dapat merepresentasikan karakter dari berbagai bahasa (alfabet). Oleh karena itu, diperlukan 2 byte per karakter agar semua karakter yang berbeda ini dapat direpresentasikan dalam satu String.

### 1.1 | Compact String

Mulai Java 9 dan seterusnya, JVM dapat mengoptimalkan string menggunakan fitur Java baru yang disebut _compact strings_. Fitur compact strings memungkinkan Java VM mendeteksi apakah suatu string hanya berisi karakter ISO-8859-1/Latin-1. Jika ya, String hanya akan menggunakan 1 byte per karakter secara internal. Dengan demikian, karakter dari String Java yang ringkas dapat direpresentasikan oleh `byte` array, bukan `char` array.

Keterwakilan sebuah String sebagai _compact string_ akan terdeteksi saat string tersebut dibuat. String tidak dapat diubah setelah dibuat - jadi hal ini aman untuk dilakukan.

---

## 2 | Membuat String

String dalam Java adalah objek. Oleh karena itu, Anda perlu menggunakan operator `new` untuk membuat objek String Java baru. Berikut adalah contoh pembuatan (instansiasi) String Java:

```java
String myString = new String("Halo Dunia");
```

Teks di dalam tanda kutip adalah teks yang akan dimuat dalam objek String.

### 2.1 | Literal String Java

Java memiliki cara yang lebih singkat untuk membuat String baru:

```java
String myString = "Halo Dunia";
```

Alih-alih meneruskan teks "Hello World" sebagai parameter ke konstruktor String, Anda cukup menuliskan teks itu sendiri di dalam tanda kutip ganda. Ini disebut literal String. Kompiler Java akan secara internal mencari cara untuk membuat String Java baru yang mewakili teks yang diberikan.

### 2.2 | Escape Karakter

Literal String Java menerima serangkaian karakter escape yang diterjemahkan menjadi karakter khusus dalam String yang dibuat. Karakter escape ini meliputi:

| Esc. Karakter | Keterangan                                                        |
|---------------|-------------------------------------------------------------------|
| `\\`          | Diterjemahkan menjadi karakter tunggal backslash `\` dalam String |
| `\t`          | Diterjemahkan menjadi karakter tab tunggal dalam string           |
| `\r`          | Diterjemahkan menjadi karakter _carriege return_ dalam String     |
| `\n `          | Diterjemahkan menjadi karakter baris baru tunggal dalam String    |

Berikut adalah contoh pembuatan String Java menggunakan karakter escape:

```java
String teks = "\tTeks ini ada di satu tab.\r\n";
```

Literal String ini akan menghasilkan String yang dimulai dengan karakter tab dan diakhiri dengan carriage return dan karakter baris baru.

### 2.3 |  String Literals sebagai Konstanta atau Singleton

Jika kamu menggunakan string yang sama (misal `"Hello World"`) di deklarasi variabel String lain, **Java Virtual Machine (JVM)** mungkin hanya akan membuat **satu instance String** di memori.
Dengan demikian, literal string tersebut secara praktis menjadi **konstanta atau singleton**. Berbagai variabel yang diinisialisasi dengan string konstanta yang sama akan menunjuk ke **instance String yang sama** di memori.

**Contoh:**

```java
String myString1 = "Hello World";
String myString2 = "Hello World";
```

Dalam kasus ini, JVM akan membuat `myString1` dan `myString2` menunjuk ke objek String yang sama.

Lebih tepatnya, objek yang mewakili literal String Java diperoleh dari constant String pool yang disimpan secara internal oleh JVM.
Artinya, bahkan kelas dari proyek berbeda yang dikompilasi secara terpisah, tetapi digunakan di aplikasi yang sama, dapat berbagi objek String konstanta. Proses berbagi ini terjadi pada saat runtime, bukan saat compile time.

Jika kamu ingin memastikan bahwa dua variabel String menunjuk ke objek String yang berbeda, gunakan operator `new`:

```java
String myString1 = new String("Hello World");
String myString2 = new String("Hello World");
```

Meskipun nilai (teks) kedua String sama, JVM akan membuat dua objek berbeda di memori untuk mewakili keduanya.

### 2.4 |  Java Text Blocks

Java text blocks, atau dikenal juga sebagai **Java multi-line strings**, adalah fitur yang ditambahkan di Java 13 (saat preview) yang memudahkan kita untuk mendeklarasikan literal String yang membentang ke beberapa baris dalam kode Java.

Contoh sintaks Java text block:

```java
String textblock = """
                   This is a text inside a
                   text block
                   """;
```

Perhatikan **dua set delimiter (`"""`)** di baris pertama dan terakhir. Tiga karakter kutip berturut-turut ini memberitahu Java compiler bahwa yang sedang dideklarasikan adalah **text block**.

* Kedua set karakter kutip harus berada di baris sendiri, di atas dan di bawah teks yang ingin dimasukkan ke text block.
* Hanya teks di antara baris dengan delimiter yang akan menjadi bagian dari hasil String Java.

Di antara delimiter, kamu bisa menulis **multi-line String** tanpa perlu melakukan escape untuk **newline** atau karakter kutip.

Contoh penggunaan kutip di dalam text block:

```java
String textblock = """
                   This is a text inside a
                   text block.
                   You can use "quotes" in here
                   without escaping them.
                   """;
```

Perhatikan kutip di sekitar kata `"quotes"`.

* Di literal String Java biasa, kamu harus melakukan escape untuk kutip ini.
* Di text block, escape tidak diperlukan, kecuali jika kamu ingin menyertakan tiga kutip berturut-turut (`"""`) sebagai bagian dari teks.
* Dalam kasus itu, kamu harus melakukan escape setidaknya satu kutip supaya compiler dapat membedakannya dari delimiter akhir text block.

### 2.5 | Indentasi pada Java Text Block

Pada contoh Java text block sebelumnya, teks di antara dua baris dengan tiga delimiter kutip (`"""`) diindentasikan agar **posisinya sejajar secara horizontal dengan delimiter**. Dengan kata lain, teks dimulai di posisi horizontal yang sama dengan delimiter. Hal ini dilakukan semata-mata untuk alasan **formatting kode**. Biasanya, kita tidak ingin semua karakter indentasi (spasi atau tab) menjadi bagian dari String yang sebenarnya dihasilkan dari text block ini.

Yang terjadi sebenarnya adalah Java compiler menghapus semua karakter indentasi dari String yang dihasilkan oleh deklarasi text block. Cara compiler menentukan berapa banyak karakter indentasi yang dihapus adalah dengan melihat **baris terakhir** dari text block, yaitu baris yang berisi tiga karakter kutip terakhir. Indentasi dari karakter kutip pada baris terakhir ini menentukan berapa banyak karakter indentasi yang dihapus dari teks di dalam text block.

Berikut contoh tiga Java text block dengan tingkat indentasi berbeda, dikontrol oleh posisi delimiter kutip terakhir:

```java
String textblock1 = """
                   This is a Java text block
                   """;

String textblock2 = """
                   This is a Java text block
                 """;

String textblock3 = """
                   This is a Java text block
               """;

System.out.println(textblock1);
System.out.println(textblock2);
System.out.println(textblock3);
```

Perhatikan perbedaan posisi delimiter kutip terakhir:

* Pada contoh pertama, delimiter kutip terakhir berada **sejajar dengan teks**, sehingga semua karakter indentasi dihapus dari String.
* Pada contoh kedua, delimiter kutip terakhir berada **2 karakter lebih ke kiri** dibanding teks. Akibatnya, compiler menyisakan 2 karakter indentasi di String yang dihasilkan.
* Pada contoh ketiga, delimiter kutip terakhir berada **4 karakter lebih ke kiri** dibanding teks, sehingga 4 karakter indentasi disertakan di String.

Output yang dihasilkan:

```
This is a Java text block

  This is a Java text block

    This is a Java text block
```

Seperti terlihat, String yang dihasilkan memiliki tingkat indentasi yang berbeda.

Intinya, perbedaan posisi horizontal karakter delimiter terakhir dan karakter paling kiri teks di text block menentukan seberapa banyak indentasi yang disertakan dalam String yang dihasilkan. Java compiler menggunakan perbandingan ini untuk menentukan indentasi yang harus dimasukkan.

---

## 3 | Menggabungkan String

Menggabungkan *String* berarti menambahkan satu *string* ke *string* lainnya. *String* dalam Java bersifat *immutable*, yang berarti nilai di dalamnya tidak dapat diubah setelah objek tersebut dibuat. Oleh karena itu, ketika dua objek *String* digabungkan, hasil penggabungannya sebenarnya disimpan pada objek *String* baru yang ketiga.

Berikut contoh penggabungan *String* dalam Java:

```java
String one = "Hello";
String two = "World";

String three = one + " " + two;
```

Isi *String* yang direferensikan oleh variabel `three` adalah `Hello World`. Sementara itu, kedua objek *String* lainnya tidak mengalami perubahan.

### 3.1 | Kinerja Penggabungan String

Saat menggabungkan *String*, Anda perlu mewaspadai potensi masalah kinerja. Penggabungan dua *String* di Java akan diterjemahkan oleh *compiler* Java menjadi sesuatu seperti berikut:

```java
String one = "Hello";
String two = " World";

String three = new StringBuilder(one)
                    .append(two).toString();
```

Seperti terlihat, sebuah objek `StringBuilder` baru dibuat, dengan *String* pertama diteruskan ke konstrukturnya, dan *String* kedua ditambahkan melalui metode `append()`, sebelum akhirnya memanggil metode `toString()`. Kode ini sebenarnya membuat dua objek: sebuah instance `StringBuilder` dan sebuah objek *String* baru yang dikembalikan oleh `toString()`.

Jika dijalankan sebagai satu pernyataan tunggal, beban pembuatan objek tambahan ini tidak terlalu signifikan. Namun, bila digunakan di dalam sebuah *loop*, situasinya berubah.

Berikut contoh *loop* yang memuat pola penggabungan *String* seperti di atas:

```java
String[] strings = 
  new String[]{"one", "two", "three", "four", "five"};

String result = null;
for(String string : strings) {
    result = result + string;
}
```

Kode ini akan dikompilasi menjadi sesuatu yang kurang lebih seperti:

```java
String[] strings = 
  new String[]{"one", "two", "three", "four", "five"};

String result = null;
for(String string : strings) {
    result = new StringBuilder(result)
                    .append(string).toString();
}
```

Pada setiap iterasi dalam *loop* tersebut, sebuah objek `StringBuilder` baru dibuat. Selain itu, sebuah objek *String* juga dibuat melalui pemanggilan metode `toString()`. Hal ini menghasilkan sedikit beban pembuatan objek pada setiap iterasi: satu objek `StringBuilder` dan satu objek `String`. Namun, beban ini bukanlah penyebab utama menurunnya kinerja. Masalah sebenarnya muncul dari proses yang terjadi selama pembuatan objek-objek tersebut.

Setiap kali kode `new StringBuilder(result)` dieksekusi, konstruktor `StringBuilder` menyalin seluruh karakter dari *String* `result` ke dalam `StringBuilder`. Semakin banyak iterasi yang dijalankan, semakin panjang *String* `result`. Semakin panjang *String* tersebut, semakin lama waktu yang dibutuhkan untuk menyalin karakter ke `StringBuilder`, dan kemudian menyalin kembali karakter-karakter tersebut ke *String* sementara yang dihasilkan oleh `toString()`. Dengan kata lain, semakin banyak iterasi, semakin lambat setiap iterasinya.

Cara tercepat untuk menggabungkan *String* adalah dengan membuat satu `StringBuilder` saja, lalu menggunakannya kembali di dalam *loop*. Contohnya sebagai berikut:

```java
String[] strings = 
  new String[]{"one", "two", "three", "four", "five"};

StringBuilder temp  = new StringBuilder();
for(String string : strings) {
    temp.append(string);
}
String result = temp.toString();
```

Kode ini menghindari pembuatan objek `StringBuilder` dan objek *String* di dalam *loop*, sehingga juga menghindari proses penyalinan karakter dua kali—pertama ke dalam `StringBuilder`, dan kemudian kembali ke *String* baru.

---

## 4 | Panjang String

Anda dapat memperoleh panjang sebuah *String* dengan menggunakan metode `length()`. Panjang sebuah *String* adalah jumlah karakter yang dikandung oleh *String* tersebut—bukan jumlah byte yang digunakan untuk merepresentasikannya. Berikut contohnya:

```java
String string = "Hello World";
int length = string.length();
```

---

## 5 | Substrings

Anda dapat mengambil sebagian dari sebuah *String*. Proses ini disebut *substring*. Untuk melakukannya, Anda menggunakan metode `substring()` milik kelas `String`. Berikut contohnya:

```java
String string1 = "Hello World";

String substring = string1.substring(0,5);
```

Setelah kode tersebut dijalankan, variabel `substring` akan berisi teks `Hello`.

Metode `substring()` menerima dua parameter. Parameter pertama adalah indeks karakter pertama yang akan disertakan dalam *substring*. Parameter kedua adalah indeks karakter setelah karakter terakhir yang akan disertakan. Perhatikan baik-baik: parameternya berarti *“dari – termasuk, sampai – tidak termasuk”*. Pola ini mungkin terlihat membingungkan sampai Anda terbiasa.

Karakter pertama dalam sebuah *String* memiliki indeks 0, karakter kedua memiliki indeks 1, dan seterusnya. Karakter terakhir memiliki indeks `String.length() - 1`.

---

## 6 | Mencari dalam String dengan `indexOf()`

Anda dapat mencari *substring* di dalam sebuah *String* dengan menggunakan metode `indexOf()`. Contohnya sebagai berikut:

```java
String string1 = "Hello World";

int index = string1.indexOf("World");
```

Setelah kode tersebut dijalankan, variabel `index` akan bernilai 6. Metode `indexOf()` mengembalikan indeks karakter pertama dari *substring* yang cocok. Dalam contoh ini, huruf **W** pada kata *World* ditemukan pada indeks 6.

Jika *substring* yang dicari tidak ditemukan di dalam *String*, metode `indexOf()` akan mengembalikan nilai `-1`.

Terdapat juga versi lain dari metode `indexOf()` yang menerima parameter tambahan berupa indeks awal pencarian. Dengan cara ini, Anda dapat mencari lebih dari satu kemunculan *substring* dalam sebuah *String*. Contohnya:

```java
String theString = "is this good or is this bad?";
String substring = "is";

int index = theString.indexOf(substring);
while(index != -1) {
    System.out.println(index);
    index = theString.indexOf(substring, index + 1);
}
```

Kode tersebut akan mencetak semua posisi di mana *substring* `"is"` ditemukan.


Kode tersebut melakukan pencarian di dalam *string* `"is this good or is this bad?"` untuk menemukan semua kemunculan *substring* `"is"`. Pencarian dilakukan menggunakan metode `indexOf(substring, index)`. Parameter `index` menentukan dari posisi karakter mana pencarian harus dimulai. Pada contoh ini, pencarian dimulai dari satu karakter setelah posisi kemunculan sebelumnya. Hal ini memastikan bahwa pencarian tidak terus-menerus menemukan kemunculan yang sama.

Keluaran yang dihasilkan dari kode tersebut adalah:

```
0
5
16
21
```

*Substring* `"is"` ditemukan sebanyak empat kali: dua kali sebagai kata tersendiri `"is"`, dan dua kali sebagai bagian dari kata `"this"`.

Kelas `String` dalam Java juga menyediakan metode `lastIndexOf()` yang digunakan untuk menemukan kemunculan terakhir dari sebuah *substring*. Contohnya:

```java
String theString = "is this good or is this bad?";
String substring = "is";

int index = theString.lastIndexOf(substring);
System.out.println(index);
```

Keluaran dari kode tersebut adalah `21`, yaitu indeks kemunculan terakhir dari *substring* `"is"`.

---
## 7 | Mencocokkan String dengan Ekspresi Reguler dengan `matches()`

Metode `matches()` pada kelas `String` menerima sebuah *regular expression* sebagai parameter, dan mengembalikan nilai `true` jika *regular expression* tersebut cocok dengan isi *string*, serta `false` jika tidak cocok.

Berikut contoh penggunaan `matches()`:

```java
String text = "one two three two one";

boolean matches = text.matches(".*two.*");
```
---

## 8 | Comparing Strings

Kelas `String` dalam Java menyediakan sejumlah metode untuk membandingkan *String*. Metode-metode tersebut antara lain:

* `equals()`
* `equalsIgnoreCase()`
* `startsWith()`
* `endsWith()`
* `compareTo()`

### 8.1 | equals()

Metode `equals()` digunakan untuk memeriksa apakah dua *String* benar-benar identik. Jika keduanya sama persis, `equals()` mengembalikan `true`. Jika tidak sama, metode ini mengembalikan `false`. Berikut contohnya:

```java
String one   = "abc";
String two   = "def";
String three = "abc";
String four  = "ABC";

System.out.println( one.equals(two) );
System.out.println( one.equals(three) );
System.out.println( one.equals(four) );
```

*String* `one` dan `three` bernilai sama, tetapi `one` tidak sama dengan `two` maupun `four`. Perlu diperhatikan bahwa perbandingan ini bersifat *case-sensitive*—huruf kecil dianggap berbeda dari huruf besar.

Keluaran dari kode tersebut adalah:

```
false
true
false
```

### 8.2 | equalsIgnoreCase()

Kelas `String` juga memiliki metode `equalsIgnoreCase()` yang membandingkan dua *String* tanpa memperhatikan perbedaan huruf besar dan huruf kecil. Dengan demikian, huruf kapital dianggap sama dengan huruf kecil yang bersesuaian.


### 8.3 | startsWith() dan endsWith()

Metode `startsWith()` dan `endsWith()` digunakan untuk memeriksa apakah sebuah *String* diawali atau diakhiri dengan *substring* tertentu. Berikut contohnya:

```java
String one = "This is a good day to code";

System.out.println( one.startsWith("This")    );
System.out.println( one.startsWith("This", 5) );

System.out.println( one.endsWith  ("code")    );
System.out.println( one.endsWith  ("shower")  );
```

Contoh ini membuat sebuah *String* dan memeriksa apakah *String* tersebut diawali atau diakhiri oleh beberapa *substring*.

* Baris pertama (setelah deklarasi *String*) memeriksa apakah *String* diawali oleh `"This"`. Karena cocok, metode `startsWith()` mengembalikan `true`.

* Baris kedua memeriksa apakah *String* diawali oleh `"This"` jika pemeriksaan dimulai dari karakter pada indeks 5. Hasilnya `false`, karena karakter pada indeks 5 adalah `"i"`.

* Baris ketiga memeriksa apakah *String* diakhiri dengan `"code"`. Karena sesuai, metode `endsWith()` mengembalikan `true`.

* Baris keempat memeriksa apakah *String* diakhiri dengan `"shower"`. Karena tidak sesuai, hasilnya `false`.


### 8.4 | compareTo()

Metode `compareTo()` membandingkan sebuah *String* dengan *String* lain dan mengembalikan nilai bertipe `int` yang menunjukkan apakah *String* tersebut lebih kecil, sama, atau lebih besar dibandingkan *String* yang menjadi pembanding.

* Jika *String* berada **sebelum** *String* pembanding dalam urutan penyortiran, `compareTo()` mengembalikan nilai negatif.
* Jika keduanya berada pada posisi penyortiran yang **sama**, metode ini mengembalikan `0`.
* Jika *String* berada **setelah** *String* pembanding dalam urutan penyortiran, metode ini mengembalikan nilai positif.

Contohnya:

```java
String one   = "abc";
String two   = "def";
String three = "abd";

System.out.println( one.compareTo(two)   );
System.out.println( one.compareTo(three) );
```

Contoh ini membandingkan *String* `one` dengan dua *String* lainnya. Keluaran dari kode tersebut adalah:

```
-3
-1
```

Nilai negatif muncul karena *String* `one` berada lebih awal dalam urutan penyortiran dibandingkan kedua *String* lainnya.

Metode `compareTo()` sebenarnya berasal dari antarmuka `Comparable`, yang dibahas lebih mendalam dalam materi tentang penyortiran.

Perlu diperhatikan bahwa `compareTo()` tidak selalu menghasilkan hasil yang benar untuk *String* dalam bahasa selain bahasa Inggris. Untuk melakukan penyortiran *String* secara benar dalam bahasa tertentu, gunakan kelas `Collator`.
