import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class DisJointSet {
    private int [] set;
    public DisJointSet(int numE){
        set = new int[numE];
        Arrays.fill(set, -1);
    }
    public void Union(int r1, int r2){
        r1 = find(r1); r2 = find(r2);
        if(r1 == r2){
            return;
        }
        if (set[r2] < set[r1]){
            set[r1] = r2;
        }else {
            if (set[r1] == set[r2]) {
                set[r1]--;
            }
            set[r2] = r1;
        }
    }

    public int find(int x){
        if (set[x] < 0) return x;
        else return set[x] = find(set[x]);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < set.length;i++){
            if (i % 11 == 1){
                sb.append("\n");
                for (int j = 0; j <= count;j++){
                    sb.append(" ");
                }
                count++;
            }
            sb.append(i + ":" + set[i] + ", ");
        }
        return sb.toString();
    }
}
