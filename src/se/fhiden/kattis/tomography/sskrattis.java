package se.fhiden.kattis.tomography;

public class sskrattis {
    class node{
        int value = 0;
    }

    static int[][] matrix;
    int[] ival;
    int[] jval;
    boolean done= false;
    boolean legit = false;

    public sskrattis(int[] jval, int[] ival){
        this.matrix = new int[ival.length][jval.length];
        this.jval = jval;
        this.ival = ival;
    }

    boolean checkMatrix(final int i, final int j){
        int r = i, t= j;
        if (matrix.length-1 <= r){
            if (matrix[0].length-1 <= t){
                markMatrixSpot(r, t);
                if (valClean(ival) && valClean(jval)){
                    legit=true;
                }
                done=true;
                return true;
            }
            markMatrixSpot(r, t);
            if (valcheck(r,t)) {
                removeMarkMatrixSpot(r,t);
                return false;
            }

            r=0;
            t++;
        }
        markMatrixSpot(r, t);

        while (!checkMatrix(++r, t) && matrix.length-1 > r) ;
        if (done)
            return true;
        else{
            removeMarkMatrixSpot(i,j);
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
