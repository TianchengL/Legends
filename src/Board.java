import sun.misc.CEFormatException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Board object used to init bunch of cells
 * print cells on command line
 */
public class Board {

    private Cell[][] cells;
    private final int row;
    private final int col;
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    public Board(int row, int col){
        this.row = row;
        this.col = col;
        this.initCells(row, col);
    }

    //random create 20% block cell, 50% common cell, 30% market cell
    public void initCells(int row, int col){
        this.cells = new Cell[row][col];
        int size = (row - 2) * (col - 2);
        int numCave = (int)Math.round(size * 0.2);
        int numKou = (int)Math.round(size * 0.2);
        int numBush = (int)Math.round(size * 0.2);

//        int totalTile = 12;
//        int numBCK = (int)Math.round(totalTile * 0.2);
//
//        this.setLane(getRandomNumber(totalTile, numBCK,numBCK,numBCK),0);
//        this.setLane(getRandomNumber(totalTile, numBCK,numBCK,numBCK),3);
//        this.setLane(getRandomNumber(totalTile, numBCK,numBCK,numBCK),6);
//
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(i == 0 ){
                    this.cells[i][j] = new MonsterNexusCell("N");
                }
                if(i == 7){
                    this.cells[i][j] = new HeroNexusCell("N");
                }
                if(j == 2 || j == 5){
                    this.cells[i][j] = new InaccessibleCell("I");
                }
            }
        }
        while(size > 0){
            int r = (int) (Math.random() * row);
            int c = (int) (Math.random() * col);

            if(numCave > 0 && cells[r][c] == null){
                cells[r][c] = new CaveCell("C");
                numCave--;
                size--;
            }else if(numKou > 0 && cells[r][c] == null){
                cells[r][c] = new KoulouCell("K");
                numKou--;
                size--;
            }else if(numBush > 0 && cells[r][c] == null){
                cells[r][c] = new BushCell("B");
                numBush--;
                size--;
            }else if( cells[r][c] == null){
                cells[r][c] = new CommonCell("P");
               // numPlain--;
                size--;
            }
        }

    }

//    public ArrayList<ArrayList<Integer>> getRandomNumber(int total, int num1, int num2,int num3){
//        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//
//        ArrayList<Integer> list1 = new ArrayList<>();
//        ArrayList<Integer> list2 = new ArrayList<>();
//        ArrayList<Integer> list3 = new ArrayList<>();
//        ArrayList<Integer> list4 = new ArrayList<>();
//
//        int count = 0;
//        while(count < num1){
//            int random = new Random().nextInt(total);
//            if(list1.contains(random)){
//                continue;
//            }
//            list1.add(random);
//            count++;
//        }
//
//        count = 0;
//        while(count < num2){
//            int random = new Random().nextInt(total);
//            if(list1.contains(random) || list2.contains(random)) {
//                continue;
//            }
//            list2.add(random);
//            count++;
//
//        }
//
//        count = 0;
//        while(count < num3) {
//            int random = new Random().nextInt(total);
//            if (list1.contains(random) || list2.contains(random) || list3.contains(random)) {
//                continue;
//            }
//            list3.add(random);
//            count++;
//
//        }
//        for (int i = 0; i < total; i++) {
//            if(!list1.contains(i) && !list2.contains(i) && !list3.contains(i) ){
//                list4.add(i);
//            }
//        }
//
//
//        result.add(list1);
//        result.add(list2);
//        result.add(list3);
////        result.add(list4);
//        return result;
//    }
//    public void setLane(ArrayList<ArrayList<Integer>> randomNumber, int startColumn ) {
//        ArrayList<Integer> numBush = randomNumber.get(0);
//        ArrayList<Integer> numCave = randomNumber.get(1);
//        ArrayList<Integer> numKoulou = randomNumber.get(2);
//
//        for (Integer i : numBush) {
//            int x = i / 2;
//            int y = i % 2;
//            this.cells[x + 1][y + startColumn] = new BushCell(x + 1, y + startColumn, "B");
//
//        }
//        for (Integer i : numCave) {
//            int x = i / 2;
//            int y = i % 2;
//            this.cells[x + 1][y + startColumn] = new CaveCell(x + 1, y + startColumn, "C");
//
//        }
//
//        for (Integer i : numKoulou) {
//            int x = i / 2;
//            int y = i % 2;
//            this.cells[x + 1][y + startColumn] = new KoulouCell(x + 1, y + startColumn, "K");
//
//        }
//    }

    //print current board
    public void printBoard(){
//        for( int r = 0; r < row; r++){
//
//            for( int c = 0; c < col; c++) {
//
////                if(cells[r][c] instanceof InaccessibleCell){
////                    cells[r][c].printCell();
////                }else if(cells[r][c] instanceof MarketCell && !cells[r][c].getValue().equals("P")){
////                    cells[r][c].printCell();
////                }else{
////                    cells[r][c].printCell();
////                }
//
//               cells[r][c].printCell();
//                //System.out.print("  ");
//            }
//            //System.out.println();
//        }
        for(int r = 0; r < row; r++){
            for(int cellR = 0; cellR < 3; cellR++){
                for(int c = 0; c < col; c++){
                    for(int cellC = 0; cellC < 5; cellC++){
                        System.out.print(cells[r][c].getPos()[cellR][cellC]);
                    }
                    System.out.print("  ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    //set each hero initial lane
    public void setHeroLane(){}

    public void setMonsterLane(){};

    public void setCells(String str, int rol, int col){
        cells[rol][col].setCellValue(str);
    }
    public Cell[][] getCells() {
        return cells;
    }

    public static void main(String[] args) {
        Board board = new Board(8, 8);
        board.printBoard();
    }

}
