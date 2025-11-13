# RUMUS STATISTIKA POKOK (KULIAH)

## 1 | Ukuran Pemusatan Data (Measure of Central Tendency)

| Nama                      | Rumus                                             | Keterangan                                                                                                                      |
| ------------------------- |---------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
| **Mean (Rata-rata)**      | $(\bar{x} = \frac{\sum x_i}{n})$                  | Data tunggal                                                                                                                    |
| **Mean data berkelompok** | $(\bar{x} = \frac{\sum f_i x_i}{\sum f_i})$       | $(f_i)$ = frekuensi, $(x_i)$ = titik tengah                                                                                     |
| **Median (data tunggal)** | Urutkan data → ambil nilai tengah                 | Jika genap, rata-rata dua nilai tengah                                                                                          |
| **Median (berkelompok)**  | $( Me = L + \frac{\frac{n}{2} - F}{f} \times c )$ | ($L$)=batas bawah kelas median, ($F$)=frekuensi kumulatif sebelum kelas median, ($f$)=frekuensi kelas median, ($c$)=lebar kelas |
| **Modus (berkelompok)**   | $( Mo = L + \frac{d_1}{d_1 + d_2} \times c )$     | $(d_1=f_m-f_{sebelum})$, $(d_2=f_m-f_{sesudah})$                                                                                |



## 2 | Ukuran Penyebaran Data (Measure of Dispersion)

| Nama                                 | Rumus                                            | Keterangan                          |
| ------------------------------------ |--------------------------------------------------|-------------------------------------|
| **Range (Jangkauan)**                | $( R = x_{max} - x_{min} )$                      | Selisih data terbesar dan terkecil  |
| **Variansi (data tunggal)**          | $( s^2 = \frac{\sum (x_i - \bar{x})^2}{n - 1} )$ | Populasi: dibagi ($n$)              |
| **Simpangan baku (standar deviasi)** | $( s = \sqrt{s^2} )$                             | Akar dari variansi                  |
| **Koefisien variasi**                | $( KV = \frac{s}{\bar{x}} \times 100% )$         | Ukur seberapa besar variasi relatif |

## 3 | Distribusi Frekuensi & Probabilitas Dasar

| Nama                         | Rumus                                         | Keterangan                     |
| ---------------------------- |-----------------------------------------------|--------------------------------|
| **Peluang kejadian A**       | $( P(A) = \frac{n(A)}{n(S)} )$                | Jumlah kejadian / ruang sampel |
| **Peluang gabungan (A ∪ B)** | $( P(A \cup B) = P(A) + P(B) - P(A \cap B) )$ | Hindari dobel hitung           |
| **Peluang bersyarat**        | $( P(A \mid B) = \frac{P(A \cap B)}{P(B)} )$  | Peluang $A$ jika $B$ terjadi   |
| **Aturan perkalian**         | $( P(A \cap B) = P(A) \times P(B \| A) )$     | Untuk dua kejadian berurutan   |

---

## 4 | Distribusi Peluang (Distribusi Diskrit & Kontinu)

| Jenis                | Rumus                                              | Keterangan                            |
| -------------------- |----------------------------------------------------|---------------------------------------|
| **Binomial**         | $( P(X = k) = \binom{n}{k} p^k (1-p)^{n-k} )$      | $n$ = percobaan, $p$ = peluang sukses |
| **Poisson**          | $( P(X = k) = \frac{e^{-\lambda} \lambda^k}{k!} )$ | $λ$ = rata-rata kejadian              |
| **Normal (Z-score)** | $( Z = \frac{X - \mu}{\sigma} )$                   | Untuk data normal standar             |

## 5 | Ukuran Letak Data (Positional Measures)

| Nama                    | Rumus                                                        | Keterangan  |
| ----------------------- |--------------------------------------------------------------|-------------|
| **Kuartil ke-k (Qk)**   | $( Q_k = L + \frac{\frac{k \cdot n}{4} - F}{f} \times c )$   | $k = 1,2,3$ |
| **Desil ke-k (Dk)**     | $( D_k = L + \frac{\frac{k \cdot n}{10} - F}{f} \times c )$  | $k = 1–9$   |
| **Persentil ke-k (Pk)** | $( P_k = L + \frac{\frac{k \cdot n}{100} - F}{f} \times c )$ | $k = 1–99$  |

## 6 | Korelasi & Regresi Linear Sederhana

| Nama                           | Rumus                                                                                                 | Keterangan           |
| ------------------------------ |-------------------------------------------------------------------------------------------------------|----------------------|
| **Korelasi Pearson (r)**       | $( r = \frac{n\sum xy - (\sum x)(\sum y)}{\sqrt{[n\sum x^2 - (\sum x)^2][n\sum y^2 - (\sum y)^2]}} $) | $-1 ≤ r ≤ 1$         |
| **Persamaan regresi Y atas X** | $( Y = a + bX )$                                                                                      | Bentuk garis regresi |
| **Koefisien b**                | $( b = \frac{n\sum xy - \sum x \sum y}{n\sum x^2 - (\sum x)^2} )$                                     | Slope                |
| **Konstanta a**                | $( a = \bar{y} - b\bar{x} )$                                                                          | Intercept            |

## 7 | Uji Statistik (uji hipotesis singkat)

| Jenis                       | Rumus / Statistik                                 | Keterangan                    |
| --------------------------- |---------------------------------------------------|-------------------------------|
| **Z-test (populasi besar)** | $( Z = \frac{\bar{X} - \mu}{\sigma / \sqrt{n}} )$ | $σ$ diketahui                 |
| **t-test (populasi kecil)** | $( t = \frac{\bar{X} - \mu}{s / \sqrt{n}} )$      | $σ$ tidak diketahui           |
| **Chi-square**              | $( \chi^2 = \sum \frac{(O - E)^2}{E} )$           | Uji kesesuaian / independensi |
| **F-test (ANOVA)**          | $( F = \frac{S^2_1}{S^2_2} )$                     | Banding dua variansi          |


## 8 | Statistik Deskriptif Cepat

| Simbol        | Arti                    |
|---------------| ----------------------- |
| $( \bar{x} )$ | Mean sampel             |
| $( \mu )$     | Mean populasi           |
| $( s )$       | Simpangan baku sampel   |
| $( \sigma )$  | Simpangan baku populasi |
| $( n )$       | Jumlah sampel           |
| $( f )$       | Frekuensi               |
