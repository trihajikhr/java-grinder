public class Prima {
    public static void main(String[] args){
        Helper h = new Helper();
        Matematika m = new Matematika();

        int n = h.safeInputPositif("Masukan angka: ");
        if(m.isPrima(n)){
            System.out.println(n + " adalah bilangan prima\n");
        } else {
            System.out.println(n + " bukan bilangan prima\n");
        }

        h.close();
    }
}
