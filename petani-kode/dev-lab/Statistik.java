import java.util.HashMap;

public class Statistik {
    float average (HashMap<String, Float> siswa){
        if(siswa.isEmpty()) return 0f;
        float sum = 0;
        for(Float f : siswa.values()){
            sum += f;
        }

        return sum / siswa.size();
    }

    float maxData(HashMap<String, Float> siswa){
        float max = Float.MIN_VALUE;
        for(Float f : siswa.values()){
            if(f > max) max = f;
        }
        return max;
    }

    float minData(HashMap<String, Float> siswa){
        float min = Float.MAX_VALUE;
        for(Float f : siswa.values()){
            if(f < min) min = f;
        }
        return min;
    }
}
