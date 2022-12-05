package SwingFilter;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

     int spinCounter = 0;
     int sepiaCount = 0;

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
         spinCounter = 0;
         sepiaCount = 0;
     }

     public void negativeFilter(){
//         normalFilter();

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
//         normalFilter();

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
//         normalFilter();

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
                 red   = bitTwo.getRed();
                 green = bitTwo.getGreen();
                 blue  = bitTwo.getBlue();

             }
             else {
                 red   = bitOne.getRed();
                 green = bitOne.getGreen();
                 blue  = bitOne.getBlue();
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


    public void changeBrightness(int amount){

        int height, width;
        height = getImage().getHeight();
        width = getImage().getWidth();

        int [] rgbArray = new int[width * height];
        rgbArray = originalImage.getRGB(0,0,width,height,rgbArray,0,width);

        for (int i = 0; i < height * width; i++){
            int pixel = rgbArray[i];


            int alpha = (pixel >> 24) & 0x00_00_00_FF;
            int red   = (pixel >> 16) & 0x00_00_00_FF;
            int green = (pixel >> 8)  & 0x00_00_00_FF;
            int blue  = (pixel >> 0)  & 0x00_00_00_FF;

            alpha = (pixel >> 24) & 0x00_00_00_FF;
            red   *= ((double) amount / 100);
            green *= ((double) amount / 100);
            blue  *= ((double) amount / 100);

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

    public void changeOpacity(double opacity){

         normalFilter();


        int newOpacity = (int) (0xFE * (opacity / 100));

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


            alpha = newOpacity - alpha;

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

    public void sepiaFilter(){
//        normalFilter();


        sepiaCount++;

        if(sepiaCount == 10){

            String soundName = "./src/SwingFilter/scott.wav";
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            try {
                clip.open(audioInputStream);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            clip.start();
        }

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


            int newRed = (int) (0.393*red + 0.769*green + 0.189*blue);
            int newGreen = (int) (0.349*red + 0.686*green + 0.168*blue);
            int newBlue = (int) (0.272*red + 0.534*green + 0.131*blue);

            red = Math.min(newRed, 255);
            green = Math.min(newGreen, 255);
            blue = Math.min(newBlue, 255);


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


     public void flipHeadover() {

         spinCounter++;

         if(spinCounter == 10){

             String soundName = "./src/SwingFilter/right_round.wav";
             AudioInputStream audioInputStream = null;
             try {
                 audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
             } catch (UnsupportedAudioFileException e) {
                 throw new RuntimeException(e);
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
             Clip clip = null;
             try {
                 clip = AudioSystem.getClip();
             } catch (LineUnavailableException e) {
                 throw new RuntimeException(e);
             }
             try {
                 clip.open(audioInputStream);
             } catch (LineUnavailableException e) {
                 throw new RuntimeException(e);
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
             clip.start();
         }

//         normalFilter();

         int height, width;
         height = getImage().getHeight();
         width = getImage().getWidth();

         int [] rgbArray = new int[width * height];
         rgbArray = getImage().getRGB(0,0,width,height,rgbArray,0,width);

         for (int i = 0; i < (height * width) / 2; i++){
             int pixel = rgbArray[i];
             int secondPixel = rgbArray[(rgbArray.length - 1) - i];

             int alpha = (pixel >> 24) & 0x00_00_00_FF;
             int red   = (pixel >> 16) & 0x00_00_00_FF;
             int green = (pixel >> 8)  & 0x00_00_00_FF;
             int blue  = (pixel >> 0)  & 0x00_00_00_FF;
//
//             System.out.println("red " + red);
//             System.out.println("green " + green);
//             System.out.println("blue " + blue);

             rgbArray[i] = secondPixel;
             rgbArray[(rgbArray.length - 1) - i] = pixel;

         }

         setImage(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
         getImage().setRGB(0,0,width, height, rgbArray, 0, width);



     }

 }
