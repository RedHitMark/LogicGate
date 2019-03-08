package GUI;

import util.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {

    private static final Dimension FRAME_DIMENSION = new Dimension(1280, 720);
    private static final String FRAME_TITLE = "LogicGates";
    private static final BufferedImage FRAME_ICON = Resources.getImage("/resources/AND.png");

    private static final BufferedImage DEFAULT_CURSOR_IMG = Resources.getImage("/resources/CURSOR.png");
    private static final BufferedImage LED_CURSOR_IMG = Resources.getImage("/resources/LED.png");
    private static final BufferedImage AND_CURSOR_IMG = Resources.getImage("/resources/AND.png");
    private static final BufferedImage OR_CURSOR_IMG = Resources.getImage("/resources/OR.png");
    private static final BufferedImage XOR_CURSOR_IMG = Resources.getImage("/resources/XOR.png");
    private static final BufferedImage NAND_CURSOR_IMG = Resources.getImage("/resources/NAND.png");
    private static final BufferedImage NOR_CURSOR_IMG = Resources.getImage("/resources/NOR.png");
    private static final BufferedImage NXOR_CURSOR_IMG = Resources.getImage("/resources/NXOR.png");
    private static final BufferedImage NOT_CURSOR_IMG = Resources.getImage("/resources/NOT.png");
    private static final BufferedImage DELETE_CURSOR_IMG = Resources.getImage("/resources/DELETE.png");

    public static final Cursor DEFAULT_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.DEFAULT_CURSOR_IMG, new Point(0,0),"default cursor");
    public static final Cursor LED_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.LED_CURSOR_IMG, new Point(0,0),"led cursor");
    public static final Cursor AND_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.AND_CURSOR_IMG, new Point(0,0),"and cursor");
    public static final Cursor OR_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.OR_CURSOR_IMG, new Point(0,0),"or cursor");
    public static final Cursor XOR_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.XOR_CURSOR_IMG, new Point(0,0),"xor cursor");
    public static final Cursor NAND_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.NAND_CURSOR_IMG, new Point(0,0),"nand cursor");
    public static final Cursor NOR_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.NOR_CURSOR_IMG, new Point(0,0),"nor cursor");
    public static final Cursor NXOR_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.NXOR_CURSOR_IMG, new Point(0,0),"nxor cursor");
    public static final Cursor NOT_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.NOT_CURSOR_IMG, new Point(0,0),"not cursor");
    public static final Cursor DELETE_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(MainFrame.DELETE_CURSOR_IMG, new Point(0,0),"delete cursor");

    private JPanel circuitPanel;

    public MainFrame() {
        this.initFrame();

        this.changeMouseIcon(MainFrame.DEFAULT_CURSOR);

        this.circuitPanel = new CircuitPanel(this);
        this.getContentPane().add(this.circuitPanel);
    }

    public void changeMouseIcon(Cursor pCursor) {
        setCursor(pCursor);
    }

    private void initFrame() {
        this.setSize(MainFrame.FRAME_DIMENSION);
        this.setLocationRelativeTo(null);


        this.setTitle(MainFrame.FRAME_TITLE);
        this.setIconImage(MainFrame.FRAME_ICON);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }
}
