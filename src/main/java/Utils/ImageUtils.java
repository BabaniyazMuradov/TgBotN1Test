package Utils;

import Commands.AppBotCommand;
import Commands.BotCommonCommands;
import Functions.FilterOperations;
import Functions.ImageOperation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class ImageUtils {

    public static BufferedImage getImage(String path) throws IOException {
        final File file = new File(path);
        return ImageIO.read(file);
    }

    public static void saveImage(BufferedImage image, String path) throws IOException {
        ImageIO.write(image,"png",new File(path));
    }

    static float[] rgbIntToArray(int pixel){
        Color color = new Color(pixel);
        return color.getRGBColorComponents(null);
    }

    static int arrayToRGBInt(float[] pixel) throws Exception {
        Color color = null;
        if(pixel.length == 3){
            color = new Color(pixel[0],pixel[1],pixel[2]);
        } else if(pixel.length ==4){
            color = new Color(pixel[0],pixel[1],pixel[2],pixel[3]);
        }
        if(color != null){
        return color.getRGB();
        }
        throw new Exception("invaliable color");
    }

    public static ImageOperation getOperation(String operationName){
        FilterOperations filterOperations = new FilterOperations();
        Method[] classMethods =  filterOperations.getClass().getDeclaredMethods(); // что такое инстанс ?
        for (Method method: classMethods) {
            if(method.isAnnotationPresent(AppBotCommand.class)){
                AppBotCommand command  = method.getAnnotation(AppBotCommand.class);
                if(command.name().equals(operationName)){
                    return (f)-> (float[]) method.invoke(filterOperations, f);
                }
            }
        }
        return null;
    }
}
