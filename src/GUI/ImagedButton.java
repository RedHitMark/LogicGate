package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class ImagedButton extends Rectangle {

    private BufferedImage img;

    public ImagedButton(int pX, int pY, int pWidth, int pHeight) {
        super(pX, pY, pWidth, pHeight);
    }

    public ImagedButton(int pX, int pY, int pWidth, int pHeight, BufferedImage pImg) {
        super(pX, pY, pWidth, pHeight);

        this.img = pImg;
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(this.x, this.y, this.width,this.height, 5, 5);
        g.setColor(Color.BLACK);
        g.drawRoundRect(this.x, this.y, this.width,this.height, 5, 5);

        if(this.img != null) {
            g.drawImage(this.img, this.x, this.y, this.width,this.height,observer);
        }
    }

}
