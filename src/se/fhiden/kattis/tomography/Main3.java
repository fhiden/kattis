package se.fhiden.kattis.tomography;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main3 main = new Main3();
        int m = sc.nextInt(), n = sc.nextInt();
        sc.nextLine();
        String r = sc.nextLine();

        int[] jval = new int[n], ival = new int[m];
        int q=0, totali = 0, totalj = 0;

        for (String s: r.split(" ")) {
            int i = Integer.parseInt(s);
            ival[q] = i;
            totali += i;
            q++;
        }
        String c = sc.nextLine();
        q=0;
        for (String s: c.split(" ")) {
            int j = Integer.parseInt(s);
            totalj  += j;
            jval[q] = j;
            q++;
        }
        if((totali+totalj) % 2 != 0){
            System.out.println("No");
            return;
        }
        for (int v : ival){
            if(1 > v || n < v){
                System.out.println("No");
                return;
            }
        }
        for (int v : jval){
            if(1 > v || m < v){
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}
