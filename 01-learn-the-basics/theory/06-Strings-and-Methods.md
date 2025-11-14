# Java String 

Tipe data String dalam Java dapat berisi serangkaian karakter (string), seperti mutiara pada sutas tali. String adalah cara Anda bekerja dengan teks di Java. Setelah String dalam Java dibuat, Anda dapat mencari di dalamnya, membuat substring darinya, membuat string baru berdasarkan string pertama dengan beberapa bagian yang dignati, dan banyak hal lainya.

## 1 | Representasi String Internal

String Java (sebelum Java 9) direpresentasikan secara internal di JVM menggunakan byte, yang dikodekan sebagai UTF-16. UTF-16 menggunakan 2 byte untuk merepresentasikan satu karakter. Dengan demikian, karakter-karakter dalam String Java direpresentasikan menggunakan `char` array.

UTF adalah pengkodena karakter yang dapat merepresentasikan karakter dari berbagai bahasa (alfabet). Oleh karena itu, diperlukan 2 byte per karakter agar semua karakter yang berbeda ini dapat direpresentasikan dalam satu String.

### 1.1 | Compact String

Mulai Java 9 dan seterusnya, JVM dapat mengoptimalkan string menggunakan fitur Java baru yang disebut _compact strings_. Fitur compact strings memungkinkan Java VM mendeteksi apakah suatu string hanya berisi karakter ISO-8859-1/Latin-1. Jika ya, String hanya akan menggunakan 1 byte per karakter secara internal. Dengan demikian, karakter dari String Java yang ringkas dapat direpresentasikan oleh `byte` array, bukan `char` array.

Keterwakilan sebuah String sebagai _compact string_ akan terdeteksi saat string tersebut dibuat. String tidak dapat diubah setelah dibuat - jadi hal ini aman untuk dilakukan.

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

## 3 | Menggabungkan String

