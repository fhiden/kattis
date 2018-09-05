package se.fhiden.kattis.stars;

import java.util.Scanner;

public class Stars {

    char[][] starMap;
    Scanner sc;

    public static void main(String[] args) {
        Stars mn = new Stars();
        mn.sc = new Scanner(System.in);
        int mapCase = 0;
        while (mn.sc.hasNextInt()){
            mapCase++;

            int y = mn.sc.nextInt(), x = mn.sc.nextInt();
            mn.loadMap(x, y);

            int stars = mn.countStars();
            mn.printStarCount(mapCase, stars);
        }
    }
    private void loadMap(final int x, final int y){
        sc.nextLine();
        createMap(x, y);
        for (int i = 0; i < y; i++) {
            char[] line = sc.nextLine().toCharArray();
            for (int j = 0; j < x; j++) {
                fillMap(line[j], j, i);
            }
        }
    }
    private void createMap(final int x, final int y){
        starMap = new char[x][y];
    }
    private void fillMap(final char s, final int x, final int y){
        starMap[x][y] = s;
    }
    private int countStars(){
        int starCount=0;
        boolean inStar=false;
        boolean alreadySelected = false;
        int length = starMap[0].length;

        for (int i = 0; i < length; i++) {
           for (int j = 0; j <  starMap.length; j++) {
                if (starMap[j][i] =='-'){
                    inStar = true;
                }
                if (inStar){

                    if ( i != 0 && starMap[j][i] == '-' && starMap[j][i-1] == '-'){
                        alreadySelected= true;
                    }
                    if (starMap[j][i] == '#' || j == starMap.length-1){
                        if (!alreadySelected){
                            starCount++;
                         }
                        inStar = false;
                        alreadySelected = false;
                    }
                }
           }
        }
        return starCount;
    }
    private void printStarCount(int ucase ,int starCount){
        System.out.println("Case " + ucase+": "+starCount);
    }
    void printMap(){
        for (char[] a: starMap) {
            for (char as: a) {
                System.out.print(as);
            }
            System.out.println();
        }
    }
}
