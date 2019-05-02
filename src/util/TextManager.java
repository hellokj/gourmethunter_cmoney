package util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TextManager {
    // 管理遊戲中所有字體
    public static final Font english_font = loadFont("Britannic Bold Regular.ttf");
    public static final Font chinese_font = loadFont("setofont.ttf");

    private static Font loadFont(String fontName){
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/resource/font/" + fontName));
        }catch (Exception e){
            System.out.println("something wrong");
        }
        return font;
    }
}
