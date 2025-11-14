# 3 | Data types and Variables

Java punya **empat** jenis variabel utama: non-static fields (instance variables), static fields (class variables), local variables, dan parameters. Tiap jenis punya *scope* dan lifetime berbeda — sangat menentukan kapan variabel ada dan kapan harus diinisialisasi.

---

## 1 | Deklarasi & inisialisasi

* Bentuk umum: `type name;` atau `type name = value;`. Contoh:

  ```java
  int myInt;          // deklarasi
  int myOther = 5;    // deklarasi + inisialisasi
  String s = "Hi";
  ```
* Untuk tipe primitif: `byte, short, char, int, long, float, double, boolean`. Untuk tipe objek: `String`, wrapper class (`Integer`, `Double`, ...), dan kelas user-defined.

---

## 2 | Membaca dan menulis (assignment/reading)

* Assignment: `variableName = value;`
* Baca variabel di ekspresi, parameter, dsb.
* Contoh kecil:

  ```java
  int a = 10;
  int b = a + 20;
  System.out.println(b);
  ```

(Ini dasar, tapi sering jadi sumber bug kalau lupa urutan inisialisasi.)

---

## 3 | Naming rules & conventions (praktikal)

* **Aturan formal:** case-sensitive; harus dimulai huruf, `$`, atau `_`; karakter setelahnya boleh huruf/angka/`$`/`_`; tidak boleh pakai keyword Java.
* **Konvensi gaya (ikuti ini supaya kode enak dibaca):**

    * variabel biasa: `camelCase` (mis. `currentSpeed`)
    * konstanta (`static final`): `ALL_CAPS_WITH_UNDERSCORES` (mis. `MAX_SIZE`)
    * hindari memulai nama dengan `$` atau `_` (secara teknis boleh tapi jelek gaya)

---

## 4 | Scope & lifetime — hal yang sering bikin pusing

* **Instance field**: ada selama objek hidup (heap).
* **Static field**: ada selama kelas dimuat (satu salinan per kelas).
* **Local variable**: hanya ada di dalam metode/block tempat dideklarasikan; compiler mewajibkan inisialisasi sebelum dipakai (lihat bagian berikut).
* **Parameter**: variabel lokal yang nilainya dikirim saat pemanggilan metode.

---

## 5 | Default values vs wajib inisialisasi

* **Fields (instance & static)** yang tidak diberi nilai akan otomatis mendapat *default value* (mis. `0`, `false`, `null`) — tapi mengandalkan default sering dianggap gaya buruk; lebih baik inisialisasi eksplisit.
* **Local variables** *tidak* mendapat default — compiler mengharuskan kamu menginisialisinya sebelum digunakan. Ini mencegah bug akibat membaca memori tak terdefinisi. (Aturan JLS / compiler). 

---

## 6 | Tipe inference untuk variabel lokal (`var`)

* Sejak Java 10 kamu bisa pakai `var` untuk variabel lokal:

  ```java
  var s = "hello";     // compiler tahu ini String
  var list = new ArrayList<String>();
  ```
* Batasannya: cuma untuk variabel lokal (termasuk for-loop index); **bukan** untuk fields atau parameter. Gunakan `var` untuk mengurangi verbosity, tapi jangan pakai kalau tipe jadi tidak jelas — itu malah menurunkan keterbacaan.

---

## 7 | Primitive vs Reference & autoboxing

* Variabel primitif menyimpan nilai langsung (mis. `int a = 5;`). Variabel referensi menyimpan alamat objek (mis. `String s = "x";`).
* Java mendukung **autoboxing/unboxing**: otomatis konversi antara primitif dan wrapper (`int` ↔ `Integer`). Hati-hati: boxing bikin objek tambahan → overhead dan potensi `NullPointerException` saat unboxing dari `null`.

---

## 8 | Common pitfalls & practical tips (baca kalau mau cepat mahir)

* **Jangan andalkan default field values** untuk logika program — inisialisasi eksplisit membuat intent jelas. 
* **Local variables harus diinisialisasi** sebelum dipakai — compiler cek alur kontrol; ini mencegah bug subtle.
* **Gunakan `final`** untuk variabel yang tidak berubah — ini dokumentasi yang bagus dan membantu compiler/optimizer.
* **Hati-hati dengan `var`**: bagus untuk local tersingkat, jelek bila tipe jadi tak jelas (mis. `var x = map.get("k");` tanpa konteks).
* **Boxing implicit**: `List<Integer> list = Arrays.asList(1,2,3); int x = list.get(0);` → automatic unboxing; tapi `Integer i = null; int j = i;` → NPE.
* **Thread safety:** static mutable fields shared antar thread → potensi race condition. Gunakan synchronization/atomic types jika diperlukan.

---

## 9 | Contoh cheat-sheet singkat

```java
// fields
public class Bike {
  private int speed = 0;             // instance field
  private static final int MAX = 10; // class constant
}

// local variable + var
public void demo() {
  int a = 5;
  var s = "hello";  // local, type inferred -> String
}

// final usage
final int x = 10;   // tidak bisa di-reassign
```
---

## 10 | Quick checklist sebelum commit / PR

- Nama variabel jelas dan konsisten? (camelCase / CONSTANT_STYLE)
- Variable scope minimal (jangan buat field kalau hanya dipakai local).
- Local variable terinisialisasi sebelum dipakai? (compiler biasanya nolak, tapi cek alur kompleks).
- Apakah boxing/unboxing bisa dihindari? (loop besar -> prefer primitif).
- Mutable static fields aman antar-thread?

---

## 11 | Sumber & bacaan lanjut (penting)

* Jenkov — Java Variables (halaman sumber utama ringkasan ini). ([jenkov.com](https://jenkov.com/tutorials/java/variables.html))
* Oracle Java Tutorial — Default values & primitives. ([Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html?utm_source=chatgpt.com))
* Oracle Java Tutorial — Autoboxing & Unboxing. ([Oracle Docs](https://docs.oracle.com/javase/specs/jls/se18/html/jls-14.html?utm_source=chatgpt.com))
* Java Language Specification / Oracle docs — aturan inisialisasi local variables. ([Oracle Docs](https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html?utm_source=chatgpt.com))
