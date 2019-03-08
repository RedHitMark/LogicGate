package logic;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Device extends Rectangle {

    protected int id;

    protected ArrayList<Device> nexts;

    protected ArrayList<Device> prevs;
    protected final int maxPrevs;

    protected boolean active;


    public Device(int x, int y, int wide, int pID, int pMaxPrevious) {
        super(x, y, wide, wide);

        this.id = pID;

        //default choise
        this.active = false;

        //array inits
        this.nexts = new ArrayList();

        this.prevs = new ArrayList();
        this.maxPrevs = pMaxPrevious;
    }

    public int getID() {
        return this.id;
    }

    public boolean isActive() {
        return this.active;
    }


    public ArrayList<Device> getPrevs() {
        return this.prevs;
    }

    public boolean hasAtleastOnePrevious() {
        return (this.prevs.size() > 0);
    }

    public boolean hasAllPrevs() {
        return (this.prevs.size() == this.maxPrevs);
    }

    public boolean hasAtleastOneNext() {
        return (nexts.size() > 0);
    }

    public void setPrevious(Device device) {
        if (this.prevs.size() < maxPrevs) {
            this.prevs.add(device);
        }
    }

    public ArrayList<Device> getNexts() {
        return this.nexts;
    }


    public void setNewNext(Device device) {
        this.nexts.add(device);
    }

    //implents this
    public abstract void updateState();

    protected void updateAllNexts() {
        for (int i = 0; i < this.nexts.size(); i++) {
            this.nexts.get(i).updateState();
        }
    }

    private Point getNextsAnchoragePoint() {
        return new Point(this.x + this.width, this.y + this.height / 2);
    }

    private Point[] getPrevAnchoragePoint() {
        if (this.maxPrevs == 1) {
            Point[] points = {new Point(this.x, this.y + this.height / 2)};
            return points;
        }

        if (this.maxPrevs == 2) {
            Point[] points = {new Point(this.x, this.y + this.height / 3), new Point(this.x, this.y + 2 * this.height / 3)};
            return points;
        }

        return null;
    }

    public void move() {
        //this.translate();
    }

    //overide this but call super.draw to draw id and lines
    public void draw(Graphics g, ImageObserver observer) {
        //draw id
        g.setColor(Color.BLACK);
        g.drawString("id." + id, this.x, this.y);

        //draw previus "pointers"

        for (int i = 0; i < prevs.size(); i++) {
            Point thisPoint = this.getPrevAnchoragePoint()[i];
            Point prevPoint = this.prevs.get(i).getNextsAnchoragePoint();

            if (this.prevs.get(i).isActive()) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }

            int distance = (int) thisPoint.distance(prevPoint);

            g.drawLine(prevPoint.x, prevPoint.y, prevPoint.x + distance / 2, prevPoint.y);
            g.drawLine(prevPoint.x + distance / 2, prevPoint.y, prevPoint.x + distance / 2, thisPoint.y);
            g.drawLine(prevPoint.x + distance / 2, thisPoint.y, thisPoint.x, thisPoint.y);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Device device = (Device) o;
        return id == device.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("id." + this.id + "(");
        for (int i = 0; i < this.prevs.size(); i++) {
            s.append(this.prevs.get(i).id + ", ");
        }

        s.append(") -> ");

        for (int i = 0; i < this.nexts.size(); i++) {
            s.append(this.nexts.get(i).id + " ");
        }
        return s.toString();
    }
}
