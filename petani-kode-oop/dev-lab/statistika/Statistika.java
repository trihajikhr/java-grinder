package statistika;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistika {
    Helper help = new Helper();

    public void mean(ArrayList<Float> list) {
        System.out.println("\nDari data berikut: ");
        float sum = 0;
        for(int i=0; i < list.size(); i++){
            System.out.print(list.get(i));
            sum += list.get(i);
            if(i != list.size() - 1) {
                System.out.print(" + ");
            } else if(i == list.size() - 1){
                System.out.print(" = " + sum);
            }
        }

        System.out.println("\nMean: " + sum + "/" + list.size() + " = " + sum/list.size());
        help.skip();
    }

    public void median(ArrayList<Float> list) {
        System.out.println("\nDari data berikut: ");
        System.out.println(list);

        help.skip();

        System.out.println("Data diurutkan menjadi: ");
        list.sort(Float::compareTo);
        System.out.println(list);

        help.skip();

        if(list.size() % 2 == 0){
            System.out.print("Data ke " + (list.size()/2) + " + " + (list.size()/2 + 1) + " = " + list.get(list.size()/2-1) + " + " + list.get(list.size()/2 ) + " = ");
            System.out.print(((list.get(list.size()/2-1)) + list.get(list.size()/2)) + "/2 = ");
            System.out.println((list.get(list.size()/2-1) + list.get(list.size()/2 ))/2f);
        } else {
            System.out.println("Data ke-" + (list.size()/2) + " = " +  list.get(list.size()/2));
        }
    }

    public void modus(ArrayList<Float> list) {
        HashMap<Float, Integer> map = new HashMap<>();
        for (Float key : list) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        float max = Float.MIN_VALUE;
        float maxVal = 0;
        int cnt = 0;
        for (Float key : map.keySet()) {
            if (map.get(key) >= max) {
                max = map.get(key);
                maxVal = key;
            }
        }

        for(Float f : list){
            cnt += (f == maxVal ? 1 : 0);
        }

        if(cnt == list.size()){
            System.out.println("Tidak ada modus, semua data muncul tepat " + cnt + " kali");
        } else {
            System.out.println();
        }
    }
}
