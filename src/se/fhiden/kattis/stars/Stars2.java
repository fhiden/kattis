package se.fhiden.kattis.stars;

import java.util.Scanner;

public class Stars2 {

    node[][] starMap;
    Scanner sc;

    // the variable used to keep track over how many stars have been found. resets after every map.
    int currentGroupCount = 0;

    /**
     * it creates the reader instance to we can scan for incoming data.
     * holds the main loop that allows the user to just enter maps forever.
     *
     * within the loop it obtains two integers that set the dimensions of the map, then populate the map, and then
     * counts the stars
     * @param args is not used in this program
     */
    public static void main(String[] args) {
        Stars2 mn = new Stars2();
        mn.sc = new Scanner(System.in);
        int mapCase = 0;

        while (mn.sc.hasNextInt()){
            mapCase++;

            int y = mn.sc.nextInt(), x = mn.sc.nextInt();
            mn.loadMap(x, y);

            mn.countStars();
            mn.printStarCount(mapCase);
            mn.currentGroupCount = 0;
        }
    }

    /**
     * Creates a new instance of the map and then populates it through input from the scanner/commandline.
     * @param xs is the length of the matrix on what we will call the x axes. matrix.length
     * @param ys is the length of the matrix on what we will call the y aces. matrix[0].length
     */
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

    /**
     * creates the node instances on the map so all the nodes are initialized.
     * @param xs is the length of the matrix on what we will call the x axes. matrix.length
     * @param ys is the length of the matrix on what we will call the y axes. matrix[0].length
     */
    private void createMap(final int xs, final int ys){
        starMap = new node[xs][ys];
        for (int x = 0; x < xs; x++) {
            for (int y = 0; y < ys; y++) {
                starMap[x][y] = new node();
            }
        }
    }

    /**
     * populates the matrix's nodes with data and then also connects the adjacent nodes to each other.
     * @param s is the char value. in this example it is '-' or '#', it is a star or a part of a star if it has '-'.
     * @param x is the length of the matrix on what we will call the x axes. matrix.length
     * @param y is the length of the matrix on what we will call the y axes. matrix[0].length
     */
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

    /**
     * counts the stars by going through the matrix spots one by one. if it finds a star or a part of a star
     * it checks if we have found the star before -- if not it calls the SetAdjacentNodestoGroup to set the
     * rest of the star to the same group.
     */
    private void countStars(){

        for (int y = 0; y < starMap[0].length; y++) {
           for (int x = 0; x <  starMap.length; x++) {
               if (starMap[x][y].getCh() == '-' && starMap[x][y].getGroup() ==0){
                    currentGroupCount++;
                    setStarGroup(starMap[x][y], currentGroupCount);
                    setAdjacentNodesToGroup(starMap[x][y], currentGroupCount);
               }
           }
        }
    }

    /**
     * sets the stars group number.
     * @param star current star to set group number to.
     * @param currentGroup current group number distributed.
     */
    private void setStarGroup(node star, int currentGroup){
        star.setGroup(currentGroup);
    }

    /**
     * sets the adjacent star nodes and all the other star nodes connected to the current group number.
     * @param star the star we star-t out from.
     * @param group current group number distributed.
     */
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

    /**
     * prints out the map's results in cases with a nice layout
     * @param ucase current map case.
     */
    private void printStarCount(int ucase){
        System.out.println("Case " + ucase+": "+currentGroupCount);
    }

    /**
     * Prints the maps with a group numbers instead of '-' or '#'
     * number 0 is either - not a  star/part of a star or not assigned a group yet.
     */
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

    /**
     * Node: is the Object used in the map to (with ease) connect adjacent nodes.
     * it contains a reference to the adjacent nodes through the up, down, left, right variables.
     * it contains the character that determines if it is a star/part of a star or just empty space.
     * it cintains the star group it is associated to -- 0 = no group and all the numbers above is a star group.
     */
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
