# Kesalahan umum penulisan Tipe Data

Saat mencoba menyimpan nilai ke variabel `float` atau `long`, Java memberikan error seperti:

```bash
incompatible types: possible lossy conversion from double to float
```

atau

```bash
integer number too large
```

Ketika aku menulis kode seperti ini:

```java
float suhu = 36.5; 
long populasi = 10000000000;
```
Ternyata disebabkan oleh tidak adanya penanda untuk tipe data tersebut. Penulisan yang tepat, adalah dengan menambahkan _suffix literal_ untuk menunjukan tipe data dengan explisit, seperti ini:

```java
float suhu = 36.5f;
double berat = 15.5;
long pupulasi = 10000000000;
```

Semua literal numeruk tanpa huruf tambahan dianggap:
- `int` jika bilangan bulat
- `double` jika ada titik desimal

`float` mmeiliki presisi sekitar 6-7 digit, sedangkan `double` bisa sampai 15 digit.

Jadi, intinya untuk float, tambahkan suffix `f`, dan untuk long, tambahkan suffix `L`. Double dan int tidak terlalu masalah.