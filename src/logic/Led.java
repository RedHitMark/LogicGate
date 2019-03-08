package logic;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Led extends Device {


    public Led(int x, int y, int wide, int pID, int pMaxPrevious) {
        super(x, y, wide, pID, pMaxPrevious);
    }

    @Override
    public void updateState() {
        if(this.hasAtleastOnePrevious()) {
            this.active = this.prevs.get(0).isActive();
        }
    }

    public void commute() {
        if (this.prevs.size() == 0) {
            this.active = !this.active;
            this.updateAllNexts();
        }
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        super.draw(g, observer);

        g.setColor(Color.BLACK);
        g.fillOval(this.x, this.y, this.width, this.height);

        if (this.active) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GRAY);
        }
        g.fillOval(this.x +2, this.y +2, this.width -4, this.height -4);
    }
}
