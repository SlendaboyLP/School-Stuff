package SwingFilter;

import java.awt.*;
import java.awt.image.BufferedImage;

 class FilterableImage {


    //Image shown
    BufferedImage image;

    //original image, will never change, needed for filters
     final BufferedImage originalImage;

    //Width and Height that are shown on screen, not actual width and height of the image
    int currWidth;
    int currHeight;

    //zoom level, 0.10x - 2.0x
    double zoom;

     Color bitOne, bitTwo;

     public Color getBitOne() {
         return bitOne;
     }

     public Color getBitTwo() {
         return bitTwo;
     }

     public void setBitOne(Color bitOne) {
         this.bitOne = bitOne;
     }

     public void setBitTwo(Color bitTwo) {
         this.bitTwo = bitTwo;
     }

    FilterableImage(BufferedImage image, int currWidth, int currHeight) {
        this.image = image;
        this.originalImage = image;
        this.currWidth = currWidth;
        this.currHeight = currHeight;
        this.zoom = 1.0;
    }

     public BufferedImage getImage() {
         return image;
     }

     public int getCurrWidth() {
         return currWidth;
     }

     public int getCurrHeight() {
         return currHeight;
     }

     public double getZoom() {
         return zoom;
     }

     public void setImage(BufferedImage image) {
         this.image = image;
     }

     public void setCurrWidth(int currWidth) {
         this.currWidth = currWidth;
     }

     public void setCurrHeight(int currHeight) {
         this.currHeight = currHeight;
     }

     public void setZoom(double zoom) {
         this.zoom = zoom;

         this.setCurrWidth(
                 (int) (getImage().getWidth() * getZoom())
         );
         this.setCurrHeight(
                 (int) (getImage().getHeight() * getZoom())
         );
     }

     /**here comes the filters*/

     public void normalFilter(){
         setImage(
                 originalImage
         );
     }

     public void negativeFilter(){
         normalFilter();

         int height, width;
         height = getImage().getHeight();
         width = getImage().getWidth();

         int [] rgbArray = new int[width * height];
         rgbArray = getImage().getRGB(0,0,width,height,rgbArray,0,width);

         for (int i = 0; i < height * width; i++){
             int pixel = rgbArray[i];

             int alpha = (pixel >> 24) & 0x00_00_00_FF;
             int red   = (pixel >> 16) & 0x00_00_00_FF;
             int green = (pixel >> 8)  & 0x00_00_00_FF;
             int blue  = (pixel >> 0)  & 0x00_00_00_FF;



             red   = 0xFF - red;
             green = 0xFF - green;
             blue  = 0xFF - blue;

             pixel = alpha;
             pixel = pixel << 8;
             pixel = pixel | red;
             pixel = pixel << 8;
             pixel = pixel | green;
             pixel = pixel << 8;
             pixel = pixel | blue;

             rgbArray[i] = pixel;

         }

         setImage(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
         getImage().setRGB(0,0,width, height, rgbArray, 0, width);
     }

     public void grayScaleFilter(){
         normalFilter();

         int height, width;
         height = getImage().getHeight();
         width = getImage().getWidth();

         int [] rgbArray = new int[width * height];
         rgbArray = getImage().getRGB(0,0,width,height,rgbArray,0,width);

         for (int i = 0; i < height * width; i++){
             int pixel = rgbArray[i];

             int alpha = (pixel >> 24) & 0x00_00_00_FF;
             int red   = (pixel >> 16) & 0x00_00_00_FF;
             int green = (pixel >> 8)  & 0x00_00_00_FF;
             int blue  = (pixel >> 0)  & 0x00_00_00_FF;

            int average = (red + green + blue) / 3;

             red   = average;
             green = average;
             blue  = average;

             pixel = alpha;
             pixel = pixel << 8;
             pixel = pixel | red;
             pixel = pixel << 8;
             pixel = pixel | green;
             pixel = pixel << 8;
             pixel = pixel | blue;

             rgbArray[i] = pixel;

         }

         setImage(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
         getImage().setRGB(0,0,width, height, rgbArray, 0, width);
     }

     public void twoBit(){
         normalFilter();

         int height, width;
         height = getImage().getHeight();
         width = getImage().getWidth();

         int [] rgbArray = new int[width * height];
         rgbArray = getImage().getRGB(0,0,width,height,rgbArray,0,width);

         for (int i = 0; i < height * width; i++){
             int pixel = rgbArray[i];

             int alpha = (pixel >> 24) & 0x00_00_00_FF;
             int red   = (pixel >> 16) & 0x00_00_00_FF;
             int green = (pixel >> 8)  & 0x00_00_00_FF;
             int blue  = (pixel >> 0)  & 0x00_00_00_FF;

             int average = (red + green + blue) / 3;

             if(average > 128){
                 red   = bitOne.getRed();
                 green = bitOne.getGreen();
                 blue  = bitOne.getBlue();
             }
             else {

                 red   = bitTwo.getRed();
                 green = bitTwo.getGreen();
                 blue  = bitTwo.getBlue();
             }


             pixel = alpha;
             pixel = pixel << 8;
             pixel = pixel | red;
             pixel = pixel << 8;
             pixel = pixel | green;
             pixel = pixel << 8;
             pixel = pixel | blue;

             rgbArray[i] = pixel;

         }

         setImage(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
         getImage().setRGB(0,0,width, height, rgbArray, 0, width);
     }


 }
