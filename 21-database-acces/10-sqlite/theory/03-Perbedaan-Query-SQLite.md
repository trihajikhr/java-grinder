# 3 | Perbedaan Query SQLite

SQLite merupakan *lightweight relational database engine* yang dirancang untuk kesederhanaan, portabilitas, dan tanpa konfigurasi server. Walaupun tetap menggunakan bahasa SQL, SQLite memiliki sejumlah karakteristik dan perilaku yang berbeda dari sistem basis data lain seperti MySQL, PostgreSQL, atau SQL Server. Berikut adalah perbedaan yang perlu dipahami:

---

## 1 | Tipe Data yang Minimal dan Fleksibel

SQLite menggunakan konsep **Manifest Typing**, berbeda dari DBMS lain yang menggunakan **Strict Typing**.

### 1.1 | SQLite:

- Hanya memiliki empat kategori tipe data:
  - INTEGER
  - REAL
  - TEXT
  - BLOB
- Deklarasi tipe seperti `VARCHAR(50)`, `CHAR(10)`, atau `BOOLEAN` tetap dianggap sebagai alias, dan pada akhirnya diperlakukan sebagai TEXT atau INTEGER sesuai aturan afinitas.

### 1.2 | DBMS lain:

- Tipe data bersifat **presisi** dan **ketat**.
- `VARCHAR(50)` berarti panjang maksimal 50 karakter, dan melebihi itu menghasilkan error.

**Implikasi:**
Validasi tipe data di SQLite lebih longgar dan banyak bergantung pada aplikasi.

---

## 2 | Tidak Mendukung ENUM

SQLite tidak menyediakan tipe data **ENUM**, yang umum digunakan pada MySQL.

### 2.1 | SQLite:

Gunakan kombinasi TEXT + CHECK:

```sql
sesi TEXT CHECK(sesi IN ('pagi', 'siang', 'malam'))
```

---

## 3 | AUTOINCREMENT Bukan Fitur Umum

SQLite hanya mengenali autoincrement pada bentuk berikut:

```sql
INTEGER PRIMARY KEY AUTOINCREMENT
```

### 3.1 | Perbedaan dengan DBMS lain:

* MySQL: `AUTO_INCREMENT`
* PostgreSQL: `SERIAL` atau `GENERATED AS IDENTITY`
* SQL Server: `IDENTITY(1,1)`

**Catatan:**
Tanpa kata `AUTOINCREMENT`, SQLite tetap menghasilkan nilai otomatis; kata tersebut hanya memastikan nilai tidak pernah didaur ulang.

---

## 4 | Keterbatasan ALTER TABLE

SQLite memiliki dukungan **ALTER TABLE** yang jauh lebih terbatas dibanding DBMS lain.  SQLite hanya mendukung:

- Menambah kolom (`ADD COLUMN`)
- Mengganti nama tabel
- Mengganti nama kolom

Dan tidak didukung:

- Mengubah tipe kolom
- Menghapus kolom
- Menambah/drops constraint secara langsung

Sebagai gantinya, SQLite menyarankan **rekonstruksi tabel** (membuat tabel baru, memindahkan data, menghapus tabel lama).

---

## 5 | Tidak Ada User Management atau Permission

SQLite tidak memiliki konsep:

- User
- Role
- Permission per tabel

Karena seluruh database adalah **file tunggal**, hak akses ditentukan oleh **hak akses file** pada sistem operasi.

---

## 6 | Transaksi dan Concurrency yang Terbatas

SQLite memakai mekanisme **file locking**, bukan server dengan multiple worker threads.  Dampaknya:

- Hanya satu proses yang dapat melakukan *write* pada satu waktu.
- Kinerja tinggi pada *read-heavy workloads*, tetapi tidak cocok untuk beban transaksi tinggi.

---

## 7 | Tidak Ada Stored Procedure, Function, atau Trigger Kompleks

SQLite mendukung trigger sederhana, tetapi:

- Tidak ada **stored procedure**.
- Tidak mendukung **function PL/SQL**, **T-SQL**, atau **PL/pgSQL**.
- Hanya mendukung fungsi bawaan atau fungsi custom melalui bahasa pemrograman host (C, Java, Python, dll).

---

## 8 | Dukungan Index yang Lebih Sederhana

SQLite mendukung index dasar, tetapi:

- Tidak mendukung index partial yang kompleks (seperti PostgreSQL).
- Tidak mendukung *functional index* tingkat lanjut (meskipun versi terbaru mendukung sebagian).

---

## 9 | Tidak Mendukung Fitur-Fitur SQL Lanjutan

SQLite tidak menyediakan beberapa fitur umum di DBMS besar, misalnya:

- **Materialized Views**
- **Stored Generated Columns** (versi terbaru mendukung sebagian)
- **Partitioning**
- **Sequences** (digantikan INTEGER PRIMARY KEY)
- **Replication**
- **Clustering / Sharding**

---

## 10 | Check Constraint yang Baru Stabil Setelah Versi 3.3

DBMS besar seperti PostgreSQL dan MySQL memiliki check constraint stabil sejak lama.
SQLite baru menerapkan check constraint secara penuh di versi yang relatif lebih baru.
Pada versi lama, CHECK sering diabaikan.

---

## 11 | Foreign Key Tidak Aktif Secara Default (Versi Lama)

Di SQLite versi lama (sebelum 3.6.19), foreign key **tidak aktif secara default** dan harus diaktifkan manual:

```sql
PRAGMA foreign_keys = ON;
```

Versi modern mengaktifkannya secara default, tetapi ini penting jika bekerja dengan versi lama.

---

# **Kesimpulan**

SQLite menggunakan SQL, tetapi dengan filosofi yang berbeda: sederhana, ringan, dan fleksibel. Perbedaan paling signifikan meliputi:

* Sistem tipe data yang longgar
* Keterbatasan fitur (ENUM, ALTER TABLE, stored procedure, user management)
* Mekanisme autoincrement yang unik
* Ketergantungan pada konvensi file tunggal

SQLite ideal untuk aplikasi desktop, mobile, embedded system, prototipe, atau proyek kecil hingga menengah.
Namun, untuk sistem berskala besar dengan kebutuhan transaksi tinggi, MySQL, PostgreSQL, atau SQL Server tetap lebih unggul.

---

Kalau mau, aku bisa buatkan versi *cheat sheet* ringkas dengan tabel perbedaan SQL umum vs SQLite.
