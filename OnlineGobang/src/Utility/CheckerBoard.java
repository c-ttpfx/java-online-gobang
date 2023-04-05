package Utility;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/04/18:40
 * @Description: 为人所不为, 能人所不能
 */
public interface CheckerBoard {
    /**
     *
     * @param table table表示为一个抽象棋盘,0表示空白,1为白棋,2为黑棋
     * @param color color表示为刚刚走的棋子颜色
     * @return 判断刚刚走的棋子是否与其他棋子连成5个,是就返回true,否则返回false;
     */
    public boolean judge(int[][] table,int color);

    /**
     *
     * @param table table表示为一个抽象棋盘,0表示空白,1为白棋,2为黑棋
     * @param color color表示为刚刚走的棋子颜色
     * @param x 表示刚刚走的棋子的x坐标
     * @param y 表示刚刚走的棋子的y坐标
     * @return 判断刚刚走的棋子是否与其他棋子连成5个,是就返回true,否则返回false;
     */
//    public boolean judge(int[][] table,int color,int x,int y);
}
