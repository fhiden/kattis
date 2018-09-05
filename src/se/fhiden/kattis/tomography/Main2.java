package se.fhiden.kattis.tomography;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main2 {
    InputStream input;
    OutputStream outputStream;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main2 main = new Main2();
        int m = sc.nextInt(), n = sc.nextInt();
        sc.nextLine();
        String r = sc.nextLine();

        ArrayList<Integer> jv = new ArrayList<>();
        ArrayList<Integer> iv = new ArrayList<>();
        int[] jval = new int[m], ival = new int[n];

        for (String s: r.split(" ")) {
            jv.add(Integer.parseInt(s));
        }
        String c = sc.nextLine();
        for (String s: c.split(" ")) {
            iv.add(Integer.parseInt(s));
        }
        Collections.sort(jv,Collections.reverseOrder());
        Collections.sort(iv,Collections.reverseOrder());
        Integer[] j = jv.toArray(new Integer[0]);
        int i=0;
        for (Integer a: iv.toArray(new Integer[0])) {
            ival[i] = a.intValue();
            i++;
        }
        for (int k = 0; k < j.length; k++) {
            jval[k] = j[k].intValue();
        }

        main.callskrattis(jval, ival);
    }
    void callskrattis(int[] jval, int[] ival){

        skrattis sk = new skrattis(jval, ival);

         int j=0;
        int i = -1;
        while(!sk.checkMatrix(++i,j) && ival.length-1 > i);
        System.out.println(sk.legit? "Yes": "No");
    }
    class skrattis {
        class node{
            int value = 0;
        }

        int[][] matrix;
        int[] ival;
        int[] jval;
        boolean done= false;
        boolean legit = false;

        public skrattis(int[] jval, int[] ival){
            this.matrix = new int[ival.length][jval.length];
            this.jval = jval;
            this.ival = ival;
        }

        boolean checkMatrix(final int i, final int j){
            int r = i, t= j;
            if (matrix.length-1 <= r){
                markMatrixSpot(r, t);
                if (valcheck(r,t)) {
                    removeMarkMatrixSpot(r,t);
                    return false;
                }
                if (matrix[0].length-1 <= t){
                    markMatrixSpot(r, t);
                    if (valClean(ival) && valClean(jval)){
                        legit=true;
                    }
                    done=true;
                    return true;
                }
                r=0;
                t++;
            }
            markMatrixSpot(r, t);

            while (!checkMatrix(++r, t) && matrix.length-1 > r) ;
            if (done)
                return true;
            else{
                removeMarkMatrixSpot(r,t);
                return false;
            }
        }
        void markMatrixSpot(final int i, final int j){
            if (jval[j] > 0 && ival[i] > 0 ){
                jval[j]--;
                ival[i]--;
                matrix[i][j] = 1;
            }
        }
        void removeMarkMatrixSpot(final int i, final int j){
            if (matrix[i][j] == 0 ) return;
            jval[j]++;
            ival[i]++;
            matrix[i][j] = 0;
        }
        boolean valcheck(int i, int j){
            return jval[j] != 0? true: false;
        }
        boolean valClean(int[] val){
            for (int c: val) {
                if(c!=0)
                    return false;
            }
            return true;
        }
    }
}
