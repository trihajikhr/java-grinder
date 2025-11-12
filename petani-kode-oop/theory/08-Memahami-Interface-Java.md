# 8 | Memahami Interface di Java (dan Contohnya)

![](media/img.png)

## 1 | Apa itu Interface?

Istilah “interface” berasal dari dua kata latin/Inggris: *inter* = antar, *face* = muka. Atau bisa dibilang bahwa interface adalah “antarmuka”

Secara konseptual: interface adalah penghubung antara sesuatu yang abstrak dengan sesuatu yang nyata. Contoh: tombol pada handphone sebagai “antarmuka” ke dalam sistem kompleks yang ada di dalam HP.

Jadi dalam konteks OOP (Object‑Oriented Programming): interface menyediakan kontrak (method signatures) yang kemudian di‑implementasikan oleh class‑class konkret.

## 2 | Contoh Program Interface

### 2.1 | Membuat Interface

```java
public interface Phone {
    int MAX_VOLUME = 100;
    int MIN_VOLUME = 0;

    void powerOn();
    void powerOff();
    void volumeUp();
    void volumeDown();
}
```

Penjelasan:

* Ada konstanta (MAX_VOLUME, MIN_VOLUME) – di interface boleh konstanta.
* Ada method signatures tanpa body: `void powerOn();`, dll.

### 2.2 | Membuat Class User yang Menggunakan Interface

```java
public class PhoneUser {
    private Phone phone;
    public PhoneUser(Phone phone) {
        this.phone = phone;
    }

    void turnOnThePhone() {
        this.phone.powerOn();
    }
    void turnOffThePhone() {
        this.phone.powerOff();
    }
    void makePhoneLouder() {
        this.phone.volumeUp();
    }
    void makePhoneSilent() {
        this.phone.volumeDown();
    }
}
```

Penjelasan:

* Class `PhoneUser` menerima objek bertipe `Phone` (interface) di konstruktor → menunjukkan **dependency injection/abstraction**.

### 2.3 | Membuat Kelas Konkret yang Mengimplementasikan Interface

```java
public class Xiaomi implements Phone {
    private int volume;
    private boolean isPowerOn;

    public Xiaomi() {
        this.volume = 50;
    }
    @Override
    public void powerOn() {
        isPowerOn = true;
        System.out.println("Handphone menyala...");
        System.out.println("Selamat datang di XIAOMI");
        System.out.println("Android version 29");
    }
    @Override
    public void powerOff() {
        isPowerOn = false;
        System.out.println("Handphone dimatikan");
    }
    @Override
    public void volumeUp() {
        if (isPowerOn) {
            if (this.volume == MAX_VOLUME) {
                System.out.println("Volume FULL!!");
                System.out.println("sudah " + this.getVolume() + "%");
            } else {
                this.volume += 10;
                System.out.println("Volume sekarang: " + this.getVolume());
            }
        } else {
            System.out.println("Nyalakan dulu donk HP‑nya!!");
        }
    }
    @Override
    public void volumeDown() {
        if (isPowerOn) {
            if (this.volume == MIN_VOLUME) {
                System.out.println("Volume = 0%");
            } else {
                this.volume -= 10;
                System.out.println("Volume sekarang: " + this.getVolume());
            }
        } else {
            System.out.println("Nyalakan dulu donk HP‑nya!!");
        }
    }
    public int getVolume() {
        return this.volume;
    }
}
```

Penjelasan:

* Kelas `Xiaomi` meng‑override semua method dari interface `Phone`.
* Terdapat logika tambahan seperti status `isPowerOn`, pengecekan `MAX_VOLUME`/`MIN_VOLUME`, dan memberikan keluaran (output) ke console.
* Ini memperlihatkan bagaimana interface memungkinkan banyak implementasi berbeda nanti.

### 2.4 | Class `Main` untuk Menjalankan Program

Artikel memberikan contoh class `Main` yang memasukkan objek `Xiaomi` ke `PhoneUser`, lalu memberikan menu aksi kepada user untuk: nyalakan HP, matikan, naikkan volume, kecilkan volume. Hal ini menunjukkan bagaimana interface + implementasi bekerja dalam alur program nyata.

Coba sendiri program yang dijelaskan diatas nanti.


## 3 | Mengapa Harus Pakai Interface?

Beberapa poin penting:

* Supaya class pengguna (misalnya `PhoneUser`) **tidak dikunci** ke satu implementasi tertentu. Jika hanya menggunakan `Xiaomi`, maka nanti bila ingin diganti ke `IPhone`, akan sulit atau harus banyak ubahan. Artikel menjelaskan bahwa jika parameter konstruktor `PhoneUser` hanya `Xiaomi`, maka objek impl. lain tidak bisa digunakan.
* Interface meningkatkan fleksibilitas — “program terhadap interface, bukan implementasi”.
* Membantu menerapkan prinsip‐prinsip OOP seperti Abstraksi dan Enkapsulasi. Artikel menyebut bahwa interface adalah abstrak dan berhubungan dengan abstraksi & enkapsulasi.

## 4 | Hal‑Hal yang **Tidak Boleh** Dilakukan di Interface

Berikut beberapa aturan yang perlu diingat:

* Jangan buat variabel biasa di dalam interface; hanya **konstanta** yang diperbolehkan (variabel static final implicit).
* Jangan isi body method biasa di interface (kecuali method default atau static yang diperkenalkan Java 8 ke atas) – artikel menyebut “isi method‑nya kosong dan nanti akan diimplementasikan pada class lain”.
* Tidak boleh menggunakan modifier `private` atau `protected` pada method atau konstanta interface (di konteks sebelum Java 9; atau secara umum prinsipnya).
* Interface **tidak bisa** dibuat objek instance dengan kata kunci `new` langsung: `new Phone()` akan error karena interface bukan class konkrit.


## 5 | Ringkasan Kesimpulan

* Interface adalah antarmuka antara pengguna dan implementasi konkret, menyediakan kontrak method yang harus ditetapkan oleh class yang mengimplementasikannya.
* Dengan interface, kode kita menjadi lebih fleksibel karena implementasi dapat diganti tanpa mengubah pengguna.
* Interface membantu mematuhi prinsip OOP dan mendukung desain yang lebih baik.
* Dalam praktek Java: definisikan interface, implementasikan di class tertentu, lalu pengguna hanya berhubungan dengan interface – bukan langsung dengan class konkret.
* Patuhi aturan berikut: hanya konstanta di interface, method signatures tanpa body (kecuali default/static), dan interface tidak dapat di‑instantiate.

