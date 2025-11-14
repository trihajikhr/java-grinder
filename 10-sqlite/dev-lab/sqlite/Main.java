package sqlite;

public class Main {
    public static void main(String[] args) {
        Handler hand = new Handler();
        SQLiteJDBC data = new SQLiteJDBC();

        System.out.println("=== PROGRAM PEMBUATAN DATABASE ===\n");
        System.out.println("1 >> Pembuatan databse...");

        // membuat database cuy..
        String dbName = hand.safeName("Masukan nama database: ", 20);
        data.createDatabase(dbName);

        // bagian buat table
        System.out.println("2 >> Pembuatan table...");
        int cnt = hand.safeInt("Banyak table: ", 1, 30);
        for(int i = 0; i < cnt; i++) {
            data.createTable();
        }
    }
}
