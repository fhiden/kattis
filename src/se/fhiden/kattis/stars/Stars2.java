package se.fhiden.kattis.stars;

import java.util.Scanner;

public class Stars2 {

    node[][] starMap;
    Scanner sc;
    int currentGroupCount = 0;


    public static void main(String[] args) {
        Stars2 mn = new Stars2();
        mn.sc = new Scanner(System.in);
        int mapCase = 0;

        while (mn.sc.hasNextInt()){
            mapCase++;

            int y = mn.sc.nextInt(), x = mn.sc.nextInt();
            mn.loadMap(x, y);

            int stars = mn.countStars();
            mn.printStarCount(mapCase, stars);
            mn.currentGroupCount = 0;
        }
    }
    private void loadMap(final int xs, final int ys){
        sc.nextLine();
        createMap(xs, ys);
        for (int y = 0; y < ys; y++) {
            char[] line = sc.nextLine().toCharArray();
            for (int x = 0; x < xs; x++) {
                fillMap(line[x], x, y);

            }
        }
    }
    private void createMap(final int xs, final int ys){
        starMap = new node[xs][ys];
        for (int x = 0; x < xs; x++) {
            for (int y = 0; y < ys; y++) {
                starMap[x][y] = new node();
            }
        }
    }
    private void fillMap(final char s, final int x, final int y){
        starMap[x][y].setCh(s);
        if (0<x)
            starMap[x][y].setUp(starMap[x-1][y]);
        if(x<starMap.length-1)
            starMap[x][y].setDown(starMap[x+1][y]);
        if (0<y)
            starMap[x][y].setLeft(starMap[x][y-1]);
        if(y<starMap[0].length-1)
            starMap[x][y].setRight(starMap[x][y+1]);
        starMap[x][y].setGroup(0);
    }
    private int countStars(){
        int starCount=0;
        boolean inStar=false;
        boolean alreadySelected = false;

        for (int y = 0; y < starMap[0].length; y++) {
           for (int x = 0; x <  starMap.length; x++) {
               if (starMap[x][y].getCh() == '-' && starMap[x][y].getGroup() ==0){
                    currentGroupCount++;
                    setStarGroup(starMap[x][y], currentGroupCount);
                    setAdjacentNodesToGroup(starMap[x][y], currentGroupCount);
               }
           }
        }
        return starCount;
    }

    /**
     * looks up the groups in the adjacent nodes.
     * @param star
     */
    private void setStarGroup(node star, int currentGroup){
        star.setGroup(currentGroup);
    }
    private void setAdjacentNodesToGroup(node star, int group){
        if (star.getUp() != null)
            if (star.getUp().getCh() == '-' && star.getUp().getGroup() == 0) {
                setStarGroup(star.getUp(),group);
                setAdjacentNodesToGroup(star.getUp(), group);
            }
        if (star.getDown() != null)
            if (star.getDown().getCh() == '-' && star.getDown().getGroup() == 0) {
                setStarGroup(star.getDown(),group);
                setAdjacentNodesToGroup(star.getDown(), group);
            }
        if (star.getLeft() != null)
            if (star.getLeft().getCh() == '-' && star.getLeft().getGroup() == 0) {
                setStarGroup(star.getLeft(),group);
                setAdjacentNodesToGroup(star.getLeft(), group);
            }
        if (star.getRight() != null)
            if (star.getRight().getCh() == '-' && star.getRight().getGroup() == 0) {
                setStarGroup(star.getRight(),group);
                setAdjacentNodesToGroup(star.getRight(), group);
            }
    }
    private void printStarCount(int ucase ,int starCount){
        System.out.println("Case " + ucase+": "+currentGroupCount);
    }
    void printMap(){
        for (int y = 0; y < starMap[0].length; y++) {
            for (int x = 0; x < starMap.length; x++) {
                System.out.print(starMap[x][y].getGroup());
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    class node{
        private node up, down, left, right;
        private char ch =0;
        private int group =0;

        public node(){
            this.ch = 0;
            this.group = 0;
        }
        public void setUp(node up) {
            this.up = up;
        }
        public void setDown(node down) {
            this.down = down;
        }
        public void setLeft(node left) {
            this.left = left;
        }
        public void setRight(node right) {
            this.right = right;
        }
        public void setCh(char ch) {
            this.ch = ch;
        }
        public char getCh() {
            return ch;
        }
        public node getRight() {
            return right;
        }
        public node getLeft() {
            return left;
        }
        public node getDown() {
            return down;
        }
        public node getUp() {
            return up;
        }
        public int  getGroup() {
            return group;
        }
        public void setGroup(int group) {
            this.group = group;
        }
    }
}
