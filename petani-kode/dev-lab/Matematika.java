import java.util.ArrayList;

class Matematika {
    int jumlah(int a, int b){
        return a+b;
    }

    boolean isPrima (int n){
        if(n <= 1) return false;
        if(n==2 || n==3) return true;
        for(int i=2; i*i <= n; i++){
            if(n % i == 0) return false;
        }
        return true;
    }

    float average(ArrayList<Float> nilai){
        if(nilai.isEmpty()){
            System.out.println("List kosong");
            return 0;
        }

        float sum = 0;
        for(float num : nilai) {
            sum += num;
        }
        return  sum/nilai.size();
    }

    float maxData(ArrayList<Float> nilai){
        if(nilai.isEmpty()){
            System.out.println("List kosong");
            return 0;
        }

        float max = Float.MIN_VALUE;
        for(float num : nilai) {
            if (num > max) max = num;
        }
        return max;
    }

    float minData(ArrayList<Float> nilai){
        if(nilai.isEmpty()){
            System.out.println("List kosong");
            return 0;
        }

        float min = Float.MAX_VALUE;
        for(float num : nilai) {
            if (num < min) min = num;
        }
        return min;
    }
}