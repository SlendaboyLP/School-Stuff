package SwingFilter;

import java.awt.image.BufferedImage;

 class FilterableImage {
    //Image shown
    BufferedImage image;

    //Width and Height that are shown on screen, not actual width and height of the image
    int currWidth;
    int currHeight;

    //zoom level, 0.10x - 2.0x
    double zoom;

    FilterableImage(BufferedImage image, int currWidth, int currHeight) {
        this.image = image;
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
                 (int) (image.getWidth() * getZoom())
         );
         this.setCurrHeight(
                 (int) (image.getHeight() * zoom)
         );
     }
 }
