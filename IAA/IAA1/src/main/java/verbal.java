import java.util.List;

public class verbal {

    public static void main(String[] args)  {
        //class comb
        //class perm
        String s = "TWO + TWO = FOUR";
        Perm p = new Perm(10);
        for(List<Integer> per : p){
            if((per.get(0) * 100 + per.get(1) * 10 + per.get(2) + per.get(0) * 100 + per.get(1) * 10 + per.get(2)) ==
                    (per.get(3) * 1000 + per.get(2) * 100 + per.get(4) * 10 + per.get(5)) && per.get(0) != 0 && per.get(3)!=0){
                //System.out.println(per);
            }
        }
        Comb c = new Comb(10);
        for(List<Integer> com : c){
            if((com.get(0) * 100 + com.get(1) * 10 + com.get(2) + com.get(0) * 100 + com.get(1) * 10 + com.get(2))==
            (com.get(3) * 1000 + com.get(2) * 100 + com.get(4) * 10 + com.get(5)) && com.get(0) != 0 && com.get(3)!=0){
               if(com.get(6) != 1)
                   break;
               System.out.println(com);
            }
        }
    }
}
