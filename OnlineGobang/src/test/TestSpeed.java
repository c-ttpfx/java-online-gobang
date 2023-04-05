package test;


import Utility.HelpCheckerBoard;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/04/23:49
 * @Description: 为人所不为, 能人所不能
 */
public class TestSpeed {
    public static int[][] table = new int[15][15];
    static {
        for (int i = 0; i < 100; i++) {
            int x = (int)(Math.random()*14);
            int y = (int)(Math.random()*14);
            table[x][y] = 1;
        }
        for (int i = 0; i < 100; i++) {
            int x = (int)(Math.random()*14);
            int y = (int)(Math.random()*14);
            table[x][y] = 2;
        }
    }
    public static void main(String[] args) {
        HelpCheckerBoard helpCheckerBoard = new HelpCheckerBoard();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            helpCheckerBoard.judge(table,1);
            helpCheckerBoard.judge(table,2);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end - start));
    }
}
