import java.util.ArrayList;

public class Deret {
    ArrayList<Integer> fibonaci(int n){
        ArrayList<Integer> fibo = new ArrayList<>(n);
        if(n <= 0) return fibo;
        fibo.add(0);
        if(n == 1) return fibo;
        fibo.add(1);
        for(int i=2; i<n; i++){
            fibo.add(fibo.get(i-1) + fibo.get(i-2));
        }
        return fibo;
    }
}
