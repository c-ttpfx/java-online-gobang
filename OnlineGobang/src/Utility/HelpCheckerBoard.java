package Utility;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/04/18:55
 * @Description: 为人所不为, 能人所不能
 */
public class HelpCheckerBoard implements CheckerBoard {
    private int rowLen;
    private int colLen;
    {
        rowLen = 15;
        colLen = 15;
    }

    @Override
    public boolean judge(int[][] table, int color) {
        System.out.println("调用方法");
        rowLen = table.length;
        colLen = table[0].length;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (table[i][j] == color) {
                    if (checkEveryOne(table, color, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkEveryOne(int[][] table, int color, int x, int y) {
        if (checkLeft(table, color, x, y) + checkRight(table, color, x, y) + 1 >= 5) {
            return true;
        } else if (checkTop(table, color, x, y) + checkBottom(table, color, x, y) + 1 >= 5) {
            return true;
        } else if (checkLeftTop(table, color, x, y) + checkRightBottom(table, color, x, y) + 1 >= 5) {
            return true;
        } else if (checkRightTop(table, color, x, y) + checkLeftBottom(table, color, x, y) + 1 >= 5) {
            return true;
        } else {
            return false;
        }
    }

    public int checkLeft(int[][] table, int color, int x, int y) {
        int num = -1;
        while (x >= 0 && table[x][y] == color) {
            num++;
            x--;
        }
        return num;
    }

    public int checkRight(int[][] table, int color, int x, int y) {
        int num = -1;
        while (x < rowLen && table[x][y] == color) {
            num++;
            x++;
        }
        return num;
    }

    public int checkTop(int[][] table, int color, int x, int y) {
        int num = -1;
        while (y >= 0 && table[x][y] == color) {
            num++;
            y--;
        }
        return num;
    }

    public int checkBottom(int[][] table, int color, int x, int y) {
        int num = -1;
        while (y < colLen && table[x][y] == color) {
            num++;
            y++;
        }
        return num;
    }

    public int checkLeftTop(int[][] table, int color, int x, int y) {
        int num = -1;
        while (x >= 0 && y >= 0 && table[x][y] == color) {
            num++;
            x--;
            y--;
        }
        return num;
    }

    public int checkLeftBottom(int[][] table, int color, int x, int y) {
        int num = -1;
        while (x >= 0 && y < colLen && table[x][y] == color) {
            num++;
            x--;
            y++;
        }
        return num;
    }

    public int checkRightTop(int[][] table, int color, int x, int y) {
        int num = -1;
        while (x < rowLen && y >= 0 && table[x][y] == color) {
            num++;
            x++;
            y--;
        }
        return num;
    }

    public int checkRightBottom(int[][] table, int color, int x, int y) {
        int num = -1;
        while (x < rowLen && y < colLen && table[x][y] == color) {
            num++;
            x++;
            y++;
        }
        return num;
    }


}
