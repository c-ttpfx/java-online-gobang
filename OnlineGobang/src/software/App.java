package software;

import view.StartView;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/09/30/15:13
 * @Description: 为人所不为, 能人所不能
 */
public class App {
    public static void main(String[] args) {
        StartView view = new StartView();
        view.setStartView(view);
    }
}
