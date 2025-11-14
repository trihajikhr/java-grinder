# Maven Getting Started Guide


Panduan ini ditujukan sebagai referensi bagi mereka yang baru pertama kali bekerja dengan Maven, sekaligus berfungsi sebagai *cookbook* yang berisi referensi dan solusi mandiri untuk berbagai kebutuhan umum.
Bagi pengguna baru, disarankan untuk mempelajari materi ini secara berurutan. Namun, bagi pengguna yang sudah lebih familiar dengan Maven, panduan ini berupaya menyediakan solusi cepat untuk kebutuhan yang sedang dihadapi.

Pada tahap ini diasumsikan bahwa Anda telah mengunduh dan menginstal Maven pada komputer Anda. Jika belum, silakan merujuk pada bagian [Download and Installation](https://maven.apache.org/download.html) terlebih dahulu.

Baik, Anda kini telah menginstal Maven dan siap untuk mulai. Sebelum masuk ke contoh-contoh yang ada, kita akan membahas secara singkat mengenai apa itu Maven dan bagaimana Maven dapat membantu Anda dalam pekerjaan sehari-hari maupun kolaborasi dengan anggota tim.
Maven tentu dapat digunakan pada proyek berskala kecil, namun kekuatan utamanya terlihat saat membantu tim bekerja lebih efektif dengan memungkinkan setiap anggota fokus pada kebutuhan pemangku kepentingan (*stakeholder*) proyek. Infrastruktur build dapat Anda serahkan kepada Maven.

---

## 1 | What is Maven?

Sekilas, Maven mungkin terlihat seperti banyak hal, tetapi secara singkat Maven adalah upaya untuk menerapkan pola (*patterns*) pada infrastruktur *build* sebuah proyek, dengan tujuan meningkatkan pemahaman dan produktivitas melalui penyediaan jalur yang jelas untuk menerapkan praktik terbaik.
Pada dasarnya, Maven adalah alat untuk manajemen dan pemahaman proyek, dan karena itu menyediakan cara untuk membantu mengelola:

* *Builds*
* Dokumentasi
* Pelaporan
* Dependensi
* SCMs (Source Control Management)
* Rilis (*Releases*)
* Distribusi

Jika Anda menginginkan informasi latar belakang lebih lanjut mengenai Maven, Anda dapat melihat [The Philosophy of Maven](https://maven.apache.org/background/philosophy-of-maven.html) dan [The History of Maven](https://maven.apache.org/background/history-of-maven.html). Sekarang mari beralih pada bagaimana Anda, sebagai pengguna, dapat memperoleh manfaat dari penggunaan Maven.

---

## 2 | How can Maven benefit my development process?

Maven dapat memberikan berbagai keuntungan bagi proses *build* Anda dengan menerapkan konvensi dan praktik standar untuk mempercepat siklus pengembangan, sekaligus membantu Anda mencapai tingkat keberhasilan yang lebih tinggi.

Setelah membahas sedikit mengenai sejarah dan tujuan Maven, sekarang saatnya melihat beberapa contoh nyata untuk membantu Anda mulai bekerja dengan Maven.

---

## 3 | How do I setup Maven?

Pengaturan default Maven umumnya sudah mencukupi. Namun, jika Anda perlu mengubah lokasi *cache* atau berada di belakang *HTTP proxy*, maka Anda perlu membuat konfigurasi tambahan. Untuk informasi lebih lengkap, lihat [Guide to Configuring Maven](https://maven.apache.org/guides/mini/guide-configuring-maven.html).

---

## 4 | How do I make my first Maven project?

Kita akan langsung terjun membuat proyek Maven pertama Anda! Untuk membuat proyek pertama ini, kita akan menggunakan mekanisme *archetype* milik Maven. Sebuah *archetype* didefinisikan sebagai pola atau model asli yang menjadi dasar pembuatan hal-hal lain yang sejenis.

Dalam konteks Maven, *archetype* adalah sebuah template proyek yang digabungkan dengan input pengguna untuk menghasilkan proyek Maven yang siap digunakan dan telah disesuaikan dengan kebutuhan pengguna.

Sekarang kita akan menunjukkan bagaimana mekanisme *archetype* bekerja. Namun, jika Anda ingin mempelajari lebih lanjut mengenai *archetype*, silakan merujuk pada [Introduction to Archetypes](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html).

Selanjutnya, mari mulai membuat proyek pertama Anda!
Untuk membuat proyek Maven paling sederhana, jalankan perintah berikut melalui *command line*:

```bash
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
```

Setelah Anda menjalankan perintah tersebut, Anda akan melihat beberapa hal terjadi. Pertama, Anda akan mendapati sebuah direktori bernama `my-app` telah dibuat sebagai direktori proyek baru, dan di dalamnya terdapat sebuah berkas bernama `pom.xml` yang kurang lebih akan terlihat seperti berikut:


```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.mycompany.app</groupId>
  <artifactId>my-app</artifactId>
  <version>1.0-SNAPSHOT</version>
  <name>my-app</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.release>17</maven.compiler.release>
  </properties>
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.junit</groupId>
        <artifactId>junit-bom</artifactId>
        <version>5.11.0</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <scope>test</scope>
    </dependency>
    <!-- Optionally: parameterized tests support -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-params</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <build>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
       ... lots of helpful plugins
    </pluginManagement>
  </build>
</project>
```

`pom.xml` berisi *Project Object Model* (POM) untuk proyek ini. POM adalah unit dasar dalam proses kerja Maven. Hal ini penting untuk diingat karena Maven bersifat *project-centric*, di mana seluruh mekanismenya berputar pada konsep sebuah proyek.
Singkatnya, POM memuat seluruh informasi penting mengenai proyek Anda, sehingga menjadi satu-satunya tempat untuk menemukan apa pun yang berkaitan dengan proyek tersebut. Memahami POM merupakan hal yang penting, dan pengguna baru dianjurkan untuk membaca [Introduction to the POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html).

POM ini masih sangat sederhana, tetapi sudah menampilkan elemen-elemen kunci yang terdapat pada setiap POM. Mari kita bahas satu per satu untuk membiasakan Anda dengan bagian-bagian pentingnya:

- **project** — Elemen tingkat teratas (*top-level element*) dalam semua berkas `pom.xml` Maven.

- **modelVersion** — Menunjukkan versi *object model* yang digunakan POM ini. Versi model jarang berubah, tetapi elemen ini wajib ada untuk menjamin stabilitas ketika pengembang Maven perlu memperbarui model di masa mendatang.

- **groupId** — Menunjukkan pengenal unik untuk organisasi atau kelompok yang membuat proyek.
`groupId` merupakan salah satu identitas utama proyek dan biasanya mengikuti pola *fully qualified domain name* organisasi.
Contoh: `org.apache.maven.plugins` digunakan untuk semua plugin Maven resmi.

- **artifactId** — Menunjukkan nama dasar unik dari *artifact* utama yang dihasilkan oleh proyek.
Secara umum, *artifact* utama berupa berkas **JAR**, tetapi *artifact* sekunder seperti *source bundle* juga menggunakan `artifactId` sebagai bagian dari namanya. Contoh format output: `<artifactId>-<version>.<extension>` (Misalnya: `myapp-1.0.jar`).

- **version** — Menunjukkan versi *artifact* yang dihasilkan oleh proyek. Maven memberikan banyak dukungan terkait manajemen versi.
Anda akan sering melihat penanda **SNAPSHOT**, yang berarti proyek sedang dalam tahap pengembangan. Pembahasan mengenai [snapshot](https://maven.apache.org/guides/getting-started/index.html#what-is-a-snapshot-version) akan dijelaskan lebih lanjut di panduan ini.

- **name** — Nama tampilan proyek, biasa digunakan dalam dokumentasi yang dihasilkan Maven.

- **url** — Menunjukkan lokasi situs proyek. Elemen ini juga digunakan saat Maven membuat dokumentasi otomatis.

- **properties** — Berisi nilai-nilai properti yang dapat digunakan di mana saja di dalam POM.

- **dependencies** — Menampung daftar [dependensi](https://maven.apache.org/pom.html#dependencies) proyek. Ini adalah salah satu bagian paling penting dalam POM.

- **build** — Mengatur struktur direktori proyek dan konfigurasi plugin yang digunakan.

Baik, ini terjemahannya — tetap dengan gaya lugas dan sedikit skeptis seperti biasa:



Untuk referensi lengkap mengenai elemen-elemen apa saja yang tersedia untuk digunakan di dalam POM, silakan lihat [POM Reference](https://maven.apache.org/ref/current/maven-model/maven.html). Sekarang, mari kita kembali ke proyek yang sedang kita kerjakan.

Setelah proses *archetype generation* untuk proyek pertamamu selesai, kamu juga akan melihat bahwa struktur direktori berikut telah dibuat:

```java
my-app
|-- pom.xml
`-- src
    |-- main
    |   `-- java
    |       `-- com
    |           `-- mycompany
    |               `-- app
    |                   `-- App.java
    `-- test
        `-- java
            `-- com
                `-- mycompany
                    `-- app
                        `-- AppTest.java
```

Seperti yang bisa kamu lihat, proyek yang dibuat dari *archetype* memiliki sebuah POM, sebuah pohon direktori untuk source aplikasi, dan satu lagi untuk source pengujian. Ini adalah *layout* standar untuk proyek Maven (source aplikasi berada di `${project.basedir}/src/main/java` dan source pengujian berada di `${project.basedir}/src/test/java`, di mana `${project.basedir}` adalah direktori tempat `pom.xml` berada).

Jika kamu membuat proyek Maven secara manual, inilah struktur direktori yang kami rekomendasikan gunakan. Ini adalah konvensi Maven, dan untuk mempelajarinya lebih jauh kamu bisa membaca [Introduction to the Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html).

Sekarang setelah kita punya sebuah POM, beberapa source aplikasi, dan beberapa source pengujian, kamu mungkin bertanya-tanya…

---

## 5 | How do I compile my application sources?


Masuklah ke direktori tempat `pom.xml` hasil `archetype:generate` berada, lalu jalankan perintah berikut untuk meng-*compile* source aplikasimu:

```
mvn compile
```

Setelah menjalankan perintah ini, kamu akan melihat output kurang lebih seperti ini:

```java
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------------< com.mycompany.app:my-app >----------------------
[INFO] Building my-app 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ my-app ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory <dir>/my-app/src/main/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ my-app ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to <dir>/my-app/target/classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.899 s
[INFO] Finished at: 2020-07-12T11:31:54+01:00
[INFO] ------------------------------------------------------------------------
```


Saat pertama kali anda menjalankan perintah ini (atau perintah Maven lainnya), Maven harus mengunduh semua plugin dan dependency yang dibutuhkan untuk menjalankan perintah tersebut. Dari instalasi Maven yang benar-benar baru, proses ini bisa makan waktu lumayan lama (pada contoh output sebelumnya, hampir 4 menit). Tapi jika anda menjalankan perintah yang sama lagi, Maven sudah punya semua yang dibutuhkannya, jadi tidak perlu mengunduh ulang dan perintahnya akan berjalan jauh lebih cepat.

Dari output-nya, terlihat bahwa hasil kompilasi diletakkan di `${project.basedir}/target/classes`, yang juga merupakan *standard convention* di Maven. Jadi jika anda memperhatikan polanya, hanya dengan mengikuti konvensi standar Maven, POM yang anda punya tetap kecil dan anda tidak perlu memberi tahu Maven secara eksplisit di mana letak sumber kodenya atau di mana hasil kompilasinya harus ditempatkan. Dengan kata lain: patuhi konvensi Maven, hidupmu jauh lebih mudah.

Sebagai perbandingan santai, coba lihat apa yang harus anda lakukan di [Ant](http://ant.apache.org/) untuk mencapai [hal](https://maven.apache.org/ant/build-a1.xml) yang sama.

Itu baru untuk meng-*compile* satu pohon sumber aplikasi, dan skrip Ant-nya sudah hampir sebesar POM sederhana tadi. Dan nanti anda akan lihat betapa banyak hal yang bisa dilakukan hanya dengan POM kecil itu!

---

## 6 | How do I compile my test sources and run my unit tests?

