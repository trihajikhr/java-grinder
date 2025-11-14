# SQLite for Java

Pada chapter ini, kita akan belajar bagaimana caranya menggunakan SQLite untuk program Java kita. Mari kita mulai dari tahap pertama, yaitu instalasi.

---

## 1 | Installasi

Sebelum menggunakan SQLite pada program Java kita, kita perlu memastikan bahwa SQLite JDBC Server dan Java sudah terinstall pada komputer. Oke, karena materi tentang SQLite bukan materi pemula, anggap saja Java sudah terpasang di komputer kita, sehingga yang perlu dilakukan selanjutnya adalah menginstal SQLite JDBC Server.

- Unduh veri terbaru sqlite-jdbc-(VERSI).jar dari repository [sqlite-jdbc](http://www.java2s.com/Code/Jar/s/Downloadsqlitejdbc372jar.htm).
- Tambahkan file jar tadi ke jalur class path, atau dapat menggunakanya bersama dengan opsi `-classpath` seperti yang dijelaskan dalam contoh berikut.


### 1.1 | Catatan pribadi:

Tapi karena aku menggunakan build tool berupa Maven, maka untuk memasang [sqlite-jdbc](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc) adalah cukup memasang dependency-nya saja di file `pom.xml`. Tapi jika tidak menggunakan build tool, bisa import manual file jar library SQLite JDBC Server ke folder dimana library-library Java disimpan.

Kode dependency untuk versi terbaru ketika catatan ini dibuat:

```xml
<!-- https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc -->
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.51.0.0</version>
</dependency>
```

---

## 2 | Hubungkan ke Basis Data

Program Java berikut menunjukan bagaimana mengkoneksikan program Java ke database yang sudah ada. Jika database belum dibuat, basis data tersebut akan dibuat dan akhirnya objek basis data akan dikembalikan (_return_).

```java
import java.sql.*;

public class SQLiteJDBC {
  public static void main( String args[] ) {
      Connection c = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }
}
```

Sekarang, mari kita compile dan jalankan program diatas untuk membuat database berupa `test.db` di lokasi direktory saat ini. Sebenarnya bisa saja memindahkan path-nya. 

### 2.1 | Perubahan awal

Program yang aku gunakan aku ubah menjadi seperti ini:

```java
import java.sql.*;

public class SQLiteJDBC {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch  (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex){
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
    }
}
```

Kode diatas akan membuat database bernama `test.db` yang berada di working directory. Supaya lokasi dari `test.db` bisa diatur lokasi (_untuk kerapian_), aku ubah menjadi seperti ini:

```java
package sqlite;

import java.sql.*;

public class SQLiteJDBC {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:10-sqlite/sqlite-database/test.db");
        } catch  (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex){
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
    }
}
```

Path yang bisa digunakan bisa _Absolute path_, atau _Relatif path_. Tapi karena path relatif lebih singkat dan sederhana, maka aku menggunakan path relatif, seperti contoh diatas. Beberapa tips:

- Gunakan forward slash `/` walaupun di windows, biar aman
- Jika folder tujuan belum ada, maka SQLite tidak bisa membuatnya secara otomatis. Jadi pastikan folder tujuan sudah ada terlebih dahulu.

### 2.2 | Menggunakan Class

Well, aku merasa kode pertama bisa ditingkatkan dengan menerapkan OOP yang lebih baik. Jadi, berikut rancanganku:

File `SQLiteJDBC.java`:

```java
package sqlite;

import java.io.File;
import java.sql.*;

public class SQLiteJDBC {
    Connection conn = null;

    public SQLiteJDBC(String dbName){
        createDatabase(dbName);
    }

    public SQLiteJDBC(){}

    public void tryCreateDatabase(String dbName) throws SQLException, ClassNotFoundException {
        File fold = new File("10-sqlite/sqlite-database");
        if(!fold.exists()) fold.mkdir();

        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:10-sqlite/sqlite-database/" + dbName  + ".db");
    }

    public Connection createDatabase(String dbName){
        try {
            tryCreateDatabase(dbName);
            System.out.println("Opened database " + dbName + " successfully");
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }  catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
```

Lalu di `main.java`:

```java
package sqlite;

public class Main {
    public static void main(String[] args) {
        SQLiteJDBC data = new SQLiteJDBC("mhs");
    }
}
```

---

## 3 | Membuat Table

Dengan menggunakan aturan penulisan Java, kita bisa membuat table pada database yang sudah dibuat dengan cara berikut:

```java
import java.sql.*;

public class SQLiteJDBC {

   public static void main( String args[] ) {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE COMPANY " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " + 
                        " AGE            INT     NOT NULL, " + 
                        " ADDRESS        CHAR(50), " + 
                        " SALARY         REAL)"; 
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
   }
}
```

Ketika program diatas dikompile dan dijalankan, maka akan membuat table `COMPANY` pada database `test.db`. Berikut adalah daftar file yang ada sekarang, sepanjang mengikuti step ini:

```
-rw-r--r--. 1 root root 3201128 Jan 22 19:04 sqlite-jdbc-3.7.2.jar
-rw-r--r--. 1 root root    1506 May  8 05:43 SQLiteJDBC.class
-rw-r--r--. 1 root root     832 May  8 05:42 SQLiteJDBC.java
-rw-r--r--. 1 root root    3072 May  8 05:43 test.db
```

### 3.1 | Menggunakan Class

Pendekatan diatas bsia ditingkatkan lagi, jadi aku mencoba membuat implementasi didalam class, untuk menjadikan operasi penambahan table di database menjadi lebih fleksibel. Sebenarnya aku hanya ingin latihan sederhana saja, karena proses pembuatan table sebenarnya akan jauh lebih cepat jika kita langsung menulis query pembuatan table. Pembuatan table pada proses perancangan database awal biasanya hanya dilakukan sekali, sehingga tidak masalah jika dibuat secara _hardcoded_. Tips tambahan, gunakan multiple line string (`"""..."""`) jika perlu.

