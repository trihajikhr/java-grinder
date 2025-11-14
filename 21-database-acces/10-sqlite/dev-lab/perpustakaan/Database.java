package perpustakaan;

import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Database {
    Handler hand = new Handler();
    private Connection con = null;
    private Statement stat = null;
    private ArrayList<String> sesion =  new ArrayList<String>(3);

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

    public void closeApp() {
        try {
            if (con != null && !con.isClosed()) {
                con.commit();
                con.close();
                System.out.println("Koneksi ditutup. Program dihentikan.");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menutup koneksi: " + e.getMessage());
        }
        System.exit(0);
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

    private String getCurrentSession() {
        LocalTime now = LocalTime.now();

        if(!now.isBefore(LocalTime.of(6,0)) && now.isAfter(LocalTime.of(12,0))) {
            return "pagi";
        } else if(!now.isBefore(LocalTime.of(12,0)) && now.isAfter(LocalTime.of(18,0))) {
            return "siang";
        } else {
            return "malam";
        }
    }

    private ArrayList<Integer> tampilPustakawanWaktu(){
        ArrayList<Integer> waktu = new ArrayList<>();
        String session = getCurrentSession();
        System.out.println("Sesi saat ini: " + session);
        try {
            String sql = "SELECT * FROM pustakawan WHERE sesi = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, session);

            ResultSet rs = stmt.executeQuery();
            System.out.println("Daftar pustakawan tersedia: ");
            while(rs.next()){
                waktu.add(rs.getInt("id_pustakawan"));
                System.out.println("[" + rs.getInt("id_pustakawan") + "]: " + rs.getString("nama"));
            }

            rs.close();
            stmt.close();
            hand.skip();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return waktu;
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

    private ArrayList<Integer> dataMahasiswa() {
        ArrayList<Integer> siswa = new ArrayList<>();
        try {
            String sql = "SELECT * FROM mahasiswa";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println("["+ rs.getInt("id_mahasiswa") + "]: " + rs.getString("nama"));
                siswa.add(rs.getInt("id_mahasiswa"));
            }

            rs.close();
            stmt.close();
            hand.skip();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return siswa;
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

    private ArrayList<Integer> dataBuku(){
        ArrayList<Integer> buku = new ArrayList<>();
        try {
            String sql = "SELECT * FROM buku";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println("["+ rs.getInt("id_buku") + "]: " + rs.getString("judul"));
                buku.add(rs.getInt("id_buku"));
            }

            rs.close();
            stmt.close();
            hand.skip();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return buku;
    }

    public void peminjaman() {
        PreparedStatement stmt = null;

        ArrayList<Integer> waktu = tampilPustakawanWaktu();
        if(waktu.isEmpty()){
            System.out.println("Perpustakaan tidak memiliki pustakawan yang tersedia!");
            System.out.println("Daftarkan beberapa pustakawan padda sesi ini!");
            return;
        }

        int id_pustakawan = hand.safeArrayInput("Masukan id_pustakawan: ", waktu);
        ArrayList<Integer> siswa =  dataMahasiswa();

        if(siswa.isEmpty()){
            System.out.println("Tidak ada mahasiswa terdaftar!");
            return;
        }

        int id_mahasiswa = hand.safeArrayInput("Masukan id_mahasiswa: ", siswa);
        ArrayList<Integer> buku = dataBuku();

        if(buku.isEmpty()){
            System.out.println("Tidak ada buku terdaftar!");
            System.out.println("Daftarkan beberapa buku!");
            return;
        }

        int id_buku =  hand.safeArrayInput("Masukan id_buku: ", buku);

        try {
            LocalDateTime temp = LocalDateTime.now();
            String tanggal = temp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String sql = "INSERT INTO peminjaman (id_pustakawan, id_mahasiswa, id_buku, tanggal) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, id_pustakawan);
            stmt.setInt(2, id_mahasiswa);
            stmt.setInt(3, id_buku);
            stmt.setString(4, tanggal);
            stmt.executeUpdate();
            con.commit();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.err.println("Error bang");
            System.exit(0);
        }
        System.err.println("Records created successfully");
    }

    public void daftarPeminjaman() {
        PreparedStatement stmt = null;

    }
}
