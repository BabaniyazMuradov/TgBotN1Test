package Utils;

import Functions.ImageOperation;

import java.awt.image.BufferedImage;

public class RGBMaster {



    private BufferedImage image;
    private int width;
    private int height;
    private boolean hasAlfaChanel;
    private int[] pixels;

    public RGBMaster(BufferedImage image){
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        hasAlfaChanel = image.getAlphaRaster()!=null;
        pixels = new int[width*height];
        image.getRGB(0,0,width,height,pixels,0,width);
    }

    public BufferedImage getImage() {
        return image;
    }

   public void changeImage(ImageOperation operation) throws Exception {
        for (int i = 0; i < pixels.length; i++) {
            float[] pixel = ImageUtils.rgbIntToArray(pixels[i]);
            float[] newPixel = operation.execute(pixel);
            pixels[i] = ImageUtils.arrayToRGBInt(newPixel);
        }
       image.setRGB(0,0,width,height,pixels,0,width);
    }
}
