# Variable Scope in Java

## 1 | Gambaran Umum

Di Java, seperti di bahasa pemrograman lainnya, setiap variabel memiliki **scope** atau ruang lingkup. Ini adalah bagian dari program di mana variabel tersebut bisa digunakan dan berlaku.

Dalam tutorial ini, kita akan memperkenalkan **ruang lingkup yang tersedia di Java** dan membahas perbedaan di antara mereka.

## 2 | Class Scope (Ruang Lingkup Kelas)

Setiap variabel yang dideklarasikan **di dalam kurung kurawal kelas (`{}`)** dengan **modifier akses `private`**, tapi **di luar metode manapun**, memiliki ruang lingkup kelas.
Akibatnya, variabel ini bisa digunakan di seluruh kelas, tetapi **tidak bisa diakses dari luar kelas**:

```java
public class ClassScopeExample {
    private Integer amount = 0;

    public void exampleMethod() {
        amount++;
    }

    public void anotherExampleMethod() {
        Integer anotherAmount = amount + 4;
    }
}
```

Di sini, `ClassScopeExample` memiliki variabel kelas `amount` yang bisa diakses dari semua metode dalam kelas tersebut.

Jika kita **tidak menggunakan `private`**, variabel ini akan dapat diakses dari **seluruh package**. Lihat artikel tentang **access modifiers** untuk informasi lebih lanjut.



## 3 | Method Scope (Ruang Lingkup Metode)

Jika sebuah variabel dideklarasikan **di dalam metode**, variabel tersebut hanya berlaku di **dalam metode yang sama**:

```java
public class MethodScopeExample {
    public void methodA() {
        Integer area = 2;
    }

    public void methodB() {
        // error compiler, area tidak bisa dikenali sebagai variabel
        area = area + 2;
    }
}
```

Di `methodA`, kita membuat variabel metode bernama `area`.
Oleh karena itu, kita hanya bisa menggunakan `area` di dalam `methodA`, dan **tidak bisa digunakan di tempat lain**.

## 4 | Loop Scope (Ruang Lingkup Loop)

Jika kita mendeklarasikan variabel **di dalam loop**, variabel tersebut hanya berlaku di **dalam loop**:

```java
public class LoopScopeExample {
    List<String> listOfNames = Arrays.asList("Joe", "Susan", "Pattrick");

    public void iterationOfNames() {
        String allNames = "";
        for (String name : listOfNames) {
            allNames = allNames + " " + name;
        }

        // error compiler, name tidak bisa dikenali sebagai variabel
        String lastNameUsed = name;
    }
}
```

Di sini, `name` adalah variabel metode yang hanya bisa digunakan **di dalam loop** dan **tidak berlaku di luar loop**.

## 5 | Bracket Scope (Ruang Lingkup Kurung)

Kita juga bisa mendefinisikan **ruang lingkup tambahan** menggunakan kurung `{}` di mana saja:

```java
public class BracketScopeExample {    
    public void mathOperationExample() {
        Integer sum = 0;
        {
            Integer number = 2;
            sum = sum + number;
        }
        // error compiler, number tidak bisa dikenali sebagai variabel
        number++;
    }
}
```

Variabel `number` hanya berlaku **di dalam kurung** tempat ia dideklarasikan.

## 6 | Scope dan Variable Shadowing

Bayangkan kita memiliki variabel kelas, dan kita ingin mendeklarasikan variabel metode dengan **nama yang sama**:

```java
public class NestedScopesExample {
    String title = "Baeldung";

    public void printTitle() {
        System.out.println(title);
        String title = "John Doe";
        System.out.println(title);
    }
}
```

* Saat pertama kali mencetak `title`, akan menampilkan `"Baeldung"`.
* Setelah itu, kita mendeklarasikan **variabel metode dengan nama sama** dan memberinya nilai `"John Doe"`.

Variabel metode `title` ini **mengaburkan (shadowing)** variabel kelas `title`. Jadi, saat dicetak kedua kali, akan menampilkan `"John Doe"`.

Membingungkan, kan? Ini disebut **variable shadowing** dan **bukan praktik yang baik**.
Lebih baik menggunakan **prefix `this`** untuk mengakses variabel kelas:

```java
System.out.println(this.title);
```

## 7 |  Kesimpulan

Kita telah mempelajari berbagai **ruang lingkup variabel** yang ada di Java.
