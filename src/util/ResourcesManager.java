package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResourcesManager {
    //    private BufferedImage[] images;
//    private String[] paths;
    private Map<String, BufferedImage> images;
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
//        images = new BufferedImage[2];
//        paths = new String[2];
        images = new HashMap<>();
        count = 0;
        for (int i = 1; i <= 33; i++) {
            getImage("food/farm_product"+ i +".png");
        }
    }

    public BufferedImage getImage(String path){
//        int index = findExist(path);
//        if(index == -1){
//            return addImage(path);
//        }
        if (!findExist(path)){
            return addImage(path);
        }
        return images.get(path);
    }

    private BufferedImage addImage(String path){
        try {
            BufferedImage image = ImageIO.read(new File(PRESET_PATH + path));
//            if(count == images.length){
//                doubleArr();
//            }
//            images[count] = image;
//            paths[count++] = path;
            images.put(path, image);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            System.out.println(path + " not found.");
        }
        return null;
    }

    private boolean findExist(String path){
//        for (int i = 0; i < count; i++) {
//            if (paths[i].equals(path)){
//                return i;
//            }
//        }
//        return true;
        return images.containsKey(path);
    }

//    private void doubleArr(){
//        BufferedImage[] tmp = new BufferedImage[images.length*2];
//        String[] tmp1 = new String[paths.length*2];
//        for (int i = 0; i < count; i++) {
//            tmp[i] = images[i];
//            tmp1[i] = paths[i];
//        }
//        images = tmp;
//        paths = tmp1;
//    }
}
