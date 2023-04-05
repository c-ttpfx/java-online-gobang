package Utility;

import view.StartView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/12:54
 * @Description: 为人所不为, 能人所不能
 */
public class ImageUtility {
    public static Image getImage(String src){
        URL url = StartView.class.getClassLoader().getResource(src);
        Image image = new ImageIcon(url).getImage();
        return image;
    }
    public static ImageIcon getImageIcon(String src){
        URL url = StartView.class.getClassLoader().getResource(src);
        ImageIcon imageIcon = new ImageIcon(url);
        return imageIcon;
    }
}
