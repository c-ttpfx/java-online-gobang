package data;

import Utility.CheckerBoard;

import Utility.HelpCheckerBoard;
import message.Color;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/02/16:47
 * @Description: 为人所不为, 能人所不能
 */
public class ChessDate {
    public static Color color;
    private static int[][] table = new int[15][15];
    private static final int WHITE = 1;
    private static final int BLACK = 2;
    public static CheckerBoard checkerBoard = new HelpCheckerBoard();
    public static int[][] getTable(){
        return table;
    }
    public static void updateTable(int row,int col,Color color){
        if (color.compareTo(Color.BLACK) == 0){
            table[row][col] = BLACK;
        }else {
            table[row][col] = WHITE;
        }
    }
    public static void reset(){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = 0;
            }
        }
    }

}
