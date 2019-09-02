package sqlinesrt;

public class choose {
    public static  void main(String []args){

        Comparable[] before={1,5,3,6,7,89,76,56,4,53,534,46};
        int[] before1={1,5,3,6,7,89,76,56,4,53,534,46};
        Comparable[] after1=soultion(before);
        int[] after=xr(before1);
        for (Comparable i:after) {
            System.out.println(i);
        }
    }

    private static Comparable[] soultion(Comparable[] before) {
        int n=before.length;
        for(int i=0;i<n;i++){
            int min=i;
            for(int j=i+1;j<n;j++){
                if(less(before[j],before[min]))
                    min=j;
                ecah(before,i,min);
            }
        }
        return before;
    }

    private static void ecah(Comparable[] before, int i, int min) {
        Comparable k=before[i];
        before[i]=before[min];
        before[min]=k;
    }


    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w)<0;
    }
    public static Integer[] cr(Integer[] a){
        int n=a.length;
        int p;
        if(n==0) return a;
        //1,5,3,6,7,89,76,56,4,53,534,46
        for(int i=0;i<n-1;i++){
           p=a[i+1];
           int y=i;
           while (y>=0 && p < a[y]){
               a[y+1]=a[y];
               y--;
           }
           a[y+1]=p;
        }
        return a;
    }
    public  static  int[] xr(int[] a){

        int len = a.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = a[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && a[preIndex] > temp) {
                    a[preIndex + gap] = a[preIndex];
                    preIndex -= gap;
                }
                a[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return a;
    }

}
