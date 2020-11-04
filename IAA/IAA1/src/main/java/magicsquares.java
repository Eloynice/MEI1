import java.util.List;

public class magicsquares {
    public static int sumfor(List<Integer> per, int ini, int until, int step){
        int ret = 0;
        for(int i = ini; i < until; i = i + step){
            ret = ret + per.get(i);
        }
        return ret;
    }

    /*public static int sumfor2(List<Integer> per, int ini, int step, int n){
        for(int j = 0; j < n; j++){
            int c = 0;
            while(c < n){

            }
        }
    }*/

    public static void main(String[] args) {
        int n = 4;
        Perm p = new Perm(n*n + 1);
        for(List<Integer> per : p){
            int sum = per.get(0) + per.get(1) + per.get(2);
            if(per.get(0) + per.get(3) + per.get(6) == sum && per.get(1) + per.get(4) + per.get(7) == sum && per.get(2) + per.get(5) + per.get(8) == sum &&
                    per.get(3) + per.get(4) + per.get(5) == sum && per.get(6) + per.get(7) + per.get(8) == sum &&
                    per.get(2) + per.get(4) + per.get(6) == sum && per.get(0) + per.get(4) + per.get(8) == sum &&
                    per.get(9) == 0){
                //System.out.println(per);
            }
        }

        for(List<Integer> per : p) {
                if (per.get(n*n) == 0) {
                    boolean correct = true;
                    int sum = 0;
                    for (int i = 0; i < n; i++) {
                        sum = sum + per.get(i);
                    }
                    for (int i = 0; i < n; i++) {
                        int s1 = sumfor(per, i * n, i * n + n, 1);
                        if (s1 != sum)
                            correct = false;

                        s1 = sumfor(per, i, n * n, n);
                        if (s1 != sum)
                            correct = false;

                        s1 = sumfor(per, 0, n * n, n + 1);
                        if (s1 != sum)
                            correct = false;

                        s1 = sumfor(per, n -1 , n * n - 1, n - 1);
                        if (s1 != sum)
                            correct = false;
                    }
                    if (correct)
                        System.out.println(per);
                }
            }

        /*for(List<Integer> per : p) {
            if (per.get(9) == 0) {
                boolean correct = true;
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum = sum + per.get(i);
                }
                for (int j = 0; j < n; j++) {


                }
            }
        }*/
    }
}

//linha começar em i * N, passo +0...N-1
//coluna começar em i, passo +N
//diagonal direita, começar em 0, passo +N+1
//diagonal esquerda, começar N-1, passo +N-1
