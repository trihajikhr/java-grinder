package sqlite;

import java.io.File;
import java.sql.*;
import java.util.HashMap;

public class SQLiteJDBC {
    Handler hand = new Handler();
    Connection conn = null;
    Statement stmt = null;
    HashMap<Character, String> atr =  new HashMap<Character, String>();
    HashMap<Character, String> tipe = new HashMap<Character, String>();

    private void insertAtribut(){
        atr.put('p', "PRIMARY KEY");
        atr.put('u', "UNIQUE KEY");
        atr.put('n', "NOT NULL");
        atr.put('m', "NULL");
        atr.put('i', "AUTOINCREMENT"); // sqlite:autoincrement - mysql:auto_increment

        tipe.put('i', "INTEGER");
        tipe.put('d', "DECIMAL");
        tipe.put('f', "FLOAT");
        tipe.put('c', "CHAR");
        tipe.put('v', "VARCHAR");
        tipe.put('b', "BOOLEAN");
        tipe.put('t', "TEXT");
        tipe.put('s', "DATE");
        tipe.put('y', "DATETIME");
        tipe.put('z', "TIME");
    }

    private void printAtr(){
        System.out.println("Atachment: ");
        for(HashMap.Entry<Character, String> e: atr.entrySet()){
            System.out.println(e.getKey()+" : "+e.getValue());
        }
        hand.skip();
    }

    private void printTipe(){
        System.out.println("Tipe data: ");
        for(HashMap.Entry<Character, String> e: tipe.entrySet()){
            System.out.println(e.getKey()+" : "+e.getValue());
        }
        hand.skip();
    }

    public SQLiteJDBC(String dbName){
        insertAtribut();
        createDatabase(dbName);
    }

    public SQLiteJDBC(){
        insertAtribut();
    }

    private void tryCreateDatabase(String dbName) throws SQLException, ClassNotFoundException {
        File fold = new File("10-sqlite/sqlite-database");
        if(!fold.exists()) fold.mkdir();

        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:10-sqlite/sqlite-database/" + dbName  + ".db");
    }

    public Connection createDatabase(String dbName){
        try {
            tryCreateDatabase(dbName);
            System.out.println("Created database " + dbName + " ...");
            System.out.println("Opened database " + dbName + " ...");
            hand.skip();
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

    public String tryCreateTable(String tableName) throws SQLException, ClassNotFoundException {
        String query = "CREATE TABLE " + tableName + " (";
        int cnt = 1;
        hand.skip();

        while(true){
            String temp = "";
            System.out.println("Atribut ke-" + cnt);
            cnt++;

            String var =  hand.safeName("Nama atribut: ", 20);
            String atrVal;
            StringBuilder atrFinal = new StringBuilder();
            char a;
            printTipe();
            while(true){
                a =hand.safeChar("Pilih tipe data: ");
                if(!tipe.containsKey(a)){
                    System.out.println("Tidak ada!");
                } else {
                    break;
                }
            }

            printAtr();
            while(true){
                boolean pass = true;
                atrVal = hand.safeName("Pilih beberapa attachment: ", 20);
                atrVal = atrVal.toLowerCase().replace(" ", ""); // hilangkan spasi & lowercase

                // cek valid
                for(int i=0; i<atrVal.length(); i++){
                    if(!atr.containsKey(atrVal.charAt(i))){
                        System.out.println("Inputan invalid!");
                        hand.clearLine();
                        pass = false;
                        break;
                    }
                }

                if(!pass) continue;

                for(int i=0; i<atrVal.length(); i++){
                    atrFinal.append(" ").append(atr.get(atrVal.charAt(i)));
                }

                break; // keluar loop
            }

            temp += var + " " + tipe.get(a) + " " + atrFinal.toString().trim(); // pakai trim

            System.out.println("+> " + temp);
            query += temp;
            int pil = hand.safeInt("Ingin menambahkan data lagi? (1:ya, 0:tidak): ", 0,1);
            if(pil == 0){
                query += ");";
                hand.clearLine();
                hand.skip();
                break;
            } else {
                query += ", ";
                hand.clearLine();
                hand.skip();
            }
        }
        System.out.println(query);
        return query;
    }

    public void createTable()  {
        String tableName = hand.safeName("Nama table: ", 20);
        try {
            stmt = conn.createStatement();
            String sql = tryCreateTable(tableName);

            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
