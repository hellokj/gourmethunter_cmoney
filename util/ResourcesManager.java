package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourcesManager {
    private BufferedImage[] images;
    private String[] paths;
    private int count;

    private static ResourcesManager resourcesManager;
    private static String PRESET_PATH = "src/resource/"; // 預設檔案路徑前綴

    public static ResourcesManager getInstance(){
        if (resourcesManager == null){
            resourcesManager = new ResourcesManager();
        }
        return resourcesManager;
    }

    private ResourcesManager(){
        images = new BufferedImage[2];
        paths = new String[2];
        count = 0;
    }

    public BufferedImage getImage(String path){
        int index = findExist(path);
        if(index == -1){
            return addImage(path);
        }
        return images[index];
    }

    private BufferedImage addImage(String path){
        try {
            BufferedImage image = ImageIO.read(new File(PRESET_PATH + path));
            if(count == images.length){
                doubleArr();
            }
            images[count] = image;
            paths[count++] = path;
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            System.out.println(path + " not found.");
        }
        return null;
    }

    private int findExist(String path){
        for (int i = 0; i < count; i++) {
            if (paths[i].equals(path)){
                return i;
            }
        }
        return -1;
    }

    private void doubleArr(){
        BufferedImage[] tmp = new BufferedImage[images.length*2];
        String[] tmp1 = new String[paths.length*2];
        for (int i = 0; i < count; i++) {
            tmp[i] = images[i];
            tmp1[i] = paths[i];
        }
        images = tmp;
        paths = tmp1;
    }
}
