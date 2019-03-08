package logic;

import util.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Gate extends Device {
    private static final BufferedImage AND_IMG = Resources.getImage("/resources/AND.png");
    private static final BufferedImage OR_IMG = Resources.getImage("/resources/OR.png");
    private static final BufferedImage XOR_IMG = Resources.getImage("/resources/XOR.png");
    private static final BufferedImage NAND_IMG = Resources.getImage("/resources/NAND.png");
    private static final BufferedImage NOR_IMG = Resources.getImage("/resources/NOR.png");
    private static final BufferedImage NXOR_IMG = Resources.getImage("/resources/NXOR.png");
    private static final BufferedImage NOT_IMG = Resources.getImage("/resources/NOT.png");

    public static final int AND = 0;
    public static final int OR = 1;
    public static final int XOR = 2;
    public static final int NAND = 3;
    public static final int NOR = 4;
    public static final int NXOR = 5;
    public static final int NOT = 6;

    private int type;
    private String name;
    private BufferedImage img;

    public Gate(int x, int y, int wide, int pID, int pMaxPrevious, int pType) {
        super(x, y, wide, pID, pMaxPrevious);

        this.type = pType;

        switch (this.type) {
            case Gate.AND:
                this.name = "AND";
                this.img = AND_IMG;
                break;

            case Gate.OR:
                this.name = "OR";
                this.img = OR_IMG;
                break;

            case Gate.XOR:
                this.name = "XOR";
                this.img = XOR_IMG;
                break;

            case Gate.NAND:
                this.name = "NAND";
                this.img = NAND_IMG;
                break;

            case Gate.NOR:
                this.name = "NOR";
                this.img = NOR_IMG;
                break;

            case Gate.NXOR:
                this.name = "NXOR";
                this.img = NXOR_IMG;
                break;

            case Gate.NOT:
                this.name = "NOT";
                this.img = NOT_IMG;
                break;
        }
    }

    @Override
    public void updateState() {

        if(this.hasAllPrevs() && this.hasAtleastOneNext()) {
            switch(this.type) {
                case Gate.AND:
                    this.active = this.prevs.get(0).isActive() && this.prevs.get(1).isActive();
                    break;

                case Gate.OR:
                    this.active = this.prevs.get(0).isActive() || this.prevs.get(1).isActive();
                    break;

                case Gate.XOR:
                    this.active = this.prevs.get(0).isActive() != this.prevs.get(1).isActive();
                    break;

                case Gate.NAND:
                    this.active = !( this.prevs.get(0).isActive() && this.prevs.get(1).isActive() );
                    break;

                case Gate.NOR:
                    this.active = !( this.prevs.get(0).isActive() || this.prevs.get(1).isActive() );
                    break;

                case Gate.NXOR:
                    this.active = this.prevs.get(0).isActive() == this.prevs.get(1).isActive();
                    break;

                case Gate.NOT:
                    this.active = !this.prevs.get(0).isActive();
                    break;
            }
        } else {
            this.active = false;
        }

        this.updateAllNexts();
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        super.draw(g, observer);

        g.drawImage(this.img, this.x,this.y,this.width,this.height, observer);

        g.drawString(this.name, this.x, this.y+this.height);
    }
}
