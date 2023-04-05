package Utility;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ImagePane extends JPanel {

    private Image image;

    public ImagePane() {
        try {
            image = ImageIO.read(new File("src/images/棋盘.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ImagePane(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.drawImage(image, 0, 0,this);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new ImagePane());
        frame.setSize(900, 900);
        frame.setVisible(true);
    }
}