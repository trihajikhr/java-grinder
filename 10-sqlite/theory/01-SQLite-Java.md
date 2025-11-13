# SQLite for Java

Pada chapter ini, kita akan belajar bagaimana caranya menggunakan SQLite untuk program Java kita. Mari kita mulai dari tahap pertama, yaitu instalasi.

## 1 | Installasi

Sebelum menggunakan SQLite pada program Java kita, kita perlu memastikan bahwa SQLite JDBC Server dan Java sudah terinstall pada komputer. Oke, karena materi tentang SQLite bukan materi pemula, anggap saja Java sudah terpasang di komputer kita, sehingga yang perlu dilakukan selanjutnya adalah menginstal SQLite JDBC Server.

- Unduh veri terbaru sqlite-jdbc-(VERSI).jar dari repository [sqlite-jdbc](http://www.java2s.com/Code/Jar/s/Downloadsqlitejdbc372jar.htm).
- Tambahkan file jar tadi ke jalur class path, atau dapat menggunakanya bersama dengan opsi `-classpath` seperti yang dijelaskan dalam contoh berikut.


_Catatan pribadi:_

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

_Catatan penulis:_

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