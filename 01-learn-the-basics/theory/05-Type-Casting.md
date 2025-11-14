# 5 | Type Casting

Sebelum mempelajari **Java Type Casting**, pastikan kamu sudah paham tentang **Tipe Data di Java**.

---

## 1 | Type Casting

Proses mengubah nilai dari satu tipe data (mis. `int`, `float`, `double`, dll.) menjadi tipe data lain disebut **typecasting**.

Di Java, ada 13 jenis konversi tipe data, tetapi pada tutorial ini kita fokus pada **dua tipe utama**:

1. **Widening Type Casting**
2. **Narrowing Type Casting**

Untuk mempelajari jenis konversi lainnya, lihat dokumentasi resmi Java: [Java Type Conversion](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html).

---

## 2 | Widening Type Casting

Pada **Widening Type Casting**, Java **secara otomatis** mengonversi satu tipe data ke tipe data lain.

**Contoh: Mengubah `int` menjadi `double`**

```java
class Main {
  public static void main(String[] args) {
    // membuat variabel tipe int
    int num = 10;
    System.out.println("The integer value: " + num);

    // mengonversi menjadi tipe double
    double data = num;
    System.out.println("The double value: " + data);
  }
}
```

**Output:**

```
The integer value: 10
The double value: 10.0
```

Di contoh di atas, variabel `num` (tipe `int`) **secara otomatis dikonversi** menjadi `double` dan kemudian disimpan di variabel `data`.

* **Aturan:** tipe data yang lebih kecil (lower data type) dikonversi menjadi tipe data yang lebih besar (higher data type).
* Tidak ada kehilangan data, sehingga konversi ini terjadi **otomatis**.
* Juga dikenal sebagai **Implicit Type Casting**.

---

## 3 | Narrowing Type Casting

Pada **Narrowing Type Casting**, kita **secara manual** mengonversi tipe data menggunakan tanda kurung `( )`.

**Contoh: Mengubah `double` menjadi `int`**

```java
class Main {
  public static void main(String[] args) {
    // membuat variabel tipe double
    double num = 10.99;
    System.out.println("The double value: " + num);

    // mengonversi menjadi tipe int
    int data = (int)num;
    System.out.println("The integer value: " + data);
  }
}
```

**Output:**

```
The double value: 10.99
The integer value: 10
```

* Baris penting:

```java
int data = (int)num;
```

* Tanda `(int)` menunjukkan bahwa variabel `num` **dikonversi secara eksplisit** menjadi tipe `int`.
* **Aturan:** tipe data yang lebih besar (higher data type) dikonversi menjadi tipe data yang lebih kecil (lower data type), sehingga **ada kemungkinan kehilangan data**.
* Juga dikenal sebagai **Explicit Type Casting**.

---

## 4 | Contoh konversi tipe lain

### 4.1 | Contoh 1: `int` → `String`

```java
class Main {
  public static void main(String[] args) {
    int num = 10;
    System.out.println("The integer value is: " + num);

    // konversi int menjadi string
    String data = String.valueOf(num);
    System.out.println("The string value is: " + data);
  }
}
```

**Output:**

```
The integer value is: 10
The string value is: 10
```

* Baris penting:

```java
String data = String.valueOf(num);
```

* Menggunakan metode `valueOf()` dari kelas `String` untuk mengubah `int` menjadi `String`.

### 4.2 | Contoh 2: `String` → `int`

```java
class Main {
  public static void main(String[] args) {
    String data = "10";
    System.out.println("The string value is: " + data);

    // konversi string menjadi int
    int num = Integer.parseInt(data);
    System.out.println("The integer value is: " + num);
  }
}
```

**Output:**

```
The string value is: 10
The integer value is: 10
```

* Baris penting:

```java
int num = Integer.parseInt(data);
```

* Menggunakan metode `parseInt()` dari kelas `Integer` untuk mengubah `String` menjadi `int`.

**Catatan:** Jika string tidak bisa dikonversi menjadi integer, akan muncul **NumberFormatException**.

---

## 5 | Cheat-Sheet Java Type Casting

**Type Casting** adalah proses mengubah nilai dari satu tipe data ke tipe data lain (misal `int` → `double`).
Di Java ada **dua tipe utama**:

| Jenis                         | Penjelasan              | Otomatis / Manual   |
| ----------------------------- | ----------------------- | ------------------- |
| Widening (Casting melebar)    | Tipe kecil → tipe besar | Otomatis (Implicit) |
| Narrowing (Casting menyempit) | Tipe besar → tipe kecil | Manual (Explicit)   |

Selain itu, ada konversi lain seperti `int → String`, `String → int`, dll.

### 5.1 | Widening Type Casting (Casting Melebar)

* Java **otomatis** mengonversi tipe data kecil menjadi tipe data besar.
* Tidak ada kehilangan data.
* Contoh:

```java
int num = 10;      // int
double data = num; // int → double otomatis
System.out.println(data); // Output: 10.0
```

* Aturan: `byte → short → int → long → float → double`
* Alias: **Implicit Type Casting**

**Tips:** Gunakan ini untuk operasi matematika atau penyimpanan nilai ke tipe lebih besar tanpa khawatir kehilangan data.

### 5.2 | Narrowing Type Casting (Casting Menyempit)

* Java **tidak otomatis**, kita harus manual dengan tanda `(tipe)`.
* Bisa terjadi **kehilangan data**.
* Contoh:

```java
double num = 10.99;
int data = (int) num; // double → int manual
System.out.println(data); // Output: 10
```

* Aturan: `double → float → long → int → short → byte`
* Alias: **Explicit Type Casting**

**Tips:** Selalu sadar akan kemungkinan kehilangan data saat casting tipe besar → kecil.

---

## 6 | Contoh Konversi Lain Diperluas

### 6.1 | `int` → `String`

```java
int num = 10;
String str = String.valueOf(num);
System.out.println(str); // Output: "10"
```

* Gunakan `String.valueOf()` untuk mengubah tipe primitif menjadi String.

### 6.2 | `String` → `int`

```java
String str = "10";
int num = Integer.parseInt(str);
System.out.println(num); // Output: 10
```

* Gunakan `Integer.parseInt()`.
* **Catatan:** Jika string tidak bisa dikonversi → muncul `NumberFormatException`.

### 6.3 | `int` → `char`

```java
int code = 65;
char letter = (char) code;
System.out.println(letter); // Output: 'A'
```

### 6.4 | `long` → `int`

```java
long big = 100L;
int smaller = (int) big;
System.out.println(smaller); // Output: 100
```

* Waspadai overflow jika nilai `long` terlalu besar untuk `int`.

### 6.5 | `double` → `int`

```java
double pi = 3.14;
int whole = (int) pi;
System.out.println(whole); // Output: 3
```

* Nilai di belakang koma akan hilang.

---

## 7 | Ringkasan Implicit vs Explicit

| Tipe Casting | Contoh                     | Otomatis? | Risiko Hilangnya Data? |
| ------------ | -------------------------- | --------- | ---------------------- |
| Widening     | `int → double`             | Ya        | Tidak                  |
| Narrowing    | `double → int`             | Tidak     | Ya                     |
| int → String | `String.valueOf(int)`      | Manual    | Tidak                  |
| String → int | `Integer.parseInt(String)` | Manual    | Bisa Exception         |
| int → char   | `(char) int`               | Manual    | Bisa karakter berbeda  |
| long → int   | `(int) long`               | Manual    | Bisa overflow          |

---

## 8 | Tips Praktis

1. **Gunakan Widening jika memungkinkan** → aman dan otomatis.
2. **Gunakan Narrowing dengan hati-hati** → cek kehilangan data.
3. **String ↔ int** hanya untuk input/output atau parsing.
4. **Casting numerik → char** hanya untuk karakter Unicode / ASCII.
5. Hindari **casting yang tidak perlu** → bikin kode lebih bersih dan aman.
