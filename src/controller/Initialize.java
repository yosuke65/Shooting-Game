package controller;

import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Initialize {

    public static Image backgroundImage = null;
    public static Font scoreFont;
    public static Font gameoverFont;
    public static Font levelFont;
    public static Font itemFont;
    public static Font normalFont;
    public static Font continueFont;
    public static Font quitFont;

    Initialize() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/view/purple.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open meteorGrey_big2.png");
            System.exit(-1);
        }

        String fName = "/view/Kenney Future Narrow.ttf";
        try {
            InputStream is = Main.class.getResourceAsStream(fName);
            scoreFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            InputStream is = Main.class.getResourceAsStream(fName);
            gameoverFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(50f);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            InputStream is = Main.class.getResourceAsStream(fName);
            levelFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            InputStream is = Main.class.getResourceAsStream(fName);
            itemFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            InputStream is = Main.class.getResourceAsStream(fName);
            normalFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(25f);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            InputStream is = Main.class.getResourceAsStream(fName);
            continueFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            InputStream is = Main.class.getResourceAsStream(fName);
            quitFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
