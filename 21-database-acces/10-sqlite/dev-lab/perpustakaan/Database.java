package perpustakaan;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Database {
    Handler hand = new Handler();
    Connection con = null;
    Statement stat = null;
    ArrayList<String> sesion =  new ArrayList<String>(3);

    private void openDatabase() throws SQLException, ClassNotFoundException {
        this.con = DriverManager.getConnection("jdbc:sqlite:21-database-acces/10-sqlite/sqlite-database/perpustakaan.db");
        this.stat = con.createStatement();
        con.setAutoCommit(false);
        sesion.add("pagi");
        sesion.add("siang");
        sesion.add("malam");
        Class.forName("org.sqlite.JDBC");
    }

    public Database (){
        try {
            openDatabase();
            System.err.println("Database connection established!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void tampilPustakawan() {
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM pustakawan");

            while(rs.next()) {
                int id = rs.getInt("id_pustakawan");
                String name = rs.getString("nama");
                float gaji = rs.getFloat("gaji");
                String sesi = rs.getString("sesi");

                System.out.println("ID    : " + id);
                System.out.println("Nama  : " + name);
                System.out.println("Gaji  : " + gaji);
                System.out.println("Sesi  : " + sesi);
                hand.skip();
            }
            rs.close();
            stat.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void tambahPustakawan() {
        PreparedStatement stmt = null;
        try {
            String name = hand.safeString("Masukan nama: ", 20);
            int gaji = hand.safeInt("Gaji        : ", 0, 1_000_000);
            String sesi;

            while(true){
                hand.clearLine();
                sesi = hand.safeString("Masukan sesi: ", 10);
                boolean found = false;

                for(String s: sesion){
                    if(s.equals(sesi)){
                        found = true;
                        break;
                    }
                }

                if(!found){
                    System.out.println("Sesi tidak sesuai!");
                    hand.clearLine();
                } else {
                    break;
                }
            }

            String sql = "INSERT INTO pustakawan (nama, gaji, sesi) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setInt(2, gaji);
            stmt.setString(3, sesi);
            stmt.executeUpdate();
            con.commit();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.err.println("Error bang");
            System.exit(0);
        }
        System.err.println("Records created successfully");
    }

    public void tampilMahasiswa() {
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM mahasiswa");

            while(rs.next()) {
                int id = rs.getInt("id_mahasiswa");
                String name = rs.getString("nama");
                String tanggal_regis = rs.getString("tanggal_regis");
                int buku_dipinjamg = rs.getInt("buku_dipinjam");
                int total_denda = rs.getInt("total_denda");

                System.out.println("ID          : " + id);
                System.out.println("Nama        : " + name);
                System.out.println("Registrasi  : " + tanggal_regis);
                System.out.println("Pinjaman    : " + buku_dipinjamg);
                System.out.println("Total denda : " +total_denda);
                hand.skip();
            }
            rs.close();
            stat.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void tambahMahasiswa() {
        PreparedStatement stmt = null;
        try {
            String name = hand.safeString("Masukan nama: ", 20);
            LocalDateTime temp = LocalDateTime.now();
            String regis = temp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String sql = "INSERT INTO mahasiswa (nama, tanggal_regis) VALUES (?, ?)";
            stmt = con.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, regis);
            stmt.executeUpdate();
            con.commit();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.err.println("Error bang");
            System.exit(0);
        }
        System.err.println("Records created successfully");
    }

    public void daftarBuku(){
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM buku");

            while(rs.next()) {
                int id = rs.getInt("id_buku");
                String judul = rs.getString("judul");
                String penulis = rs.getString("penulis");
                int tahun_rilis = rs.getInt("tahun_rilis");
                String bahasa = rs.getString("bahasa");
                int kuota = rs.getInt("kuota");

                System.out.println("ID          : " + id);
                System.out.println("Judul       : " + judul);
                System.out.println("Penulis     : " + penulis);
                System.out.println("Tahun rili  : " + tahun_rilis);
                System.out.println("Bahasa      : " + bahasa);
                System.out.println("Kuota       : " + kuota);
                hand.skip();
            }
            rs.close();
            stat.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void tambahBuku() {
        PreparedStatement stmt = null;
        try {
            String judul = hand.safeString("Masukan judul: ", 20);
            String penulis = hand.safeString("Penulis: ", 20);
            int tahun_rilis = hand.safeInt("Tahun rilis: ", 0, 3_000);
            String bahasa = hand.safeString("Bahasa: ", 20);
            int kuota = hand.safeInt("Kuota: ", 0, 1_000);

            String sql = "INSERT INTO buku (judul, penulis, tahun_rilis, bahasa, kuota) VALUES (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);

            stmt.setString(1, judul);
            stmt.setString(2, penulis);
            stmt.setInt(3, tahun_rilis);
            stmt.setString(4, bahasa);
            stmt.setInt(5, kuota);

            stmt.executeUpdate();
            con.commit();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.err.println("Error bang");
            System.exit(0);
        }
        System.err.println("Records created successfully");
    }

    public void peminjaman() {
        System.out.println("Masukan");
        PreparedStatement stmt = null;
        try {

        }
    }
}
