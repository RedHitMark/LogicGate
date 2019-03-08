package GUI;

import logic.*;
import util.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CircuitPanel extends JPanel {

    private static final Dimension CIRCUIT_PANEL_DIMENSION = new Dimension(1280,720);
    private static final Rectangle CIRCUIT_AREA = new Rectangle(0,0,1225,720);
    private static final Rectangle BUTTONS_AREA = new Rectangle(1225,0,40,720);

    private static final BufferedImage CURSOR_BTN_IMG = Resources.getImage("/resources/CURSOR.png");
    private static final BufferedImage LED_BTN_IMG = Resources.getImage("/resources/LED.png");
    private static final BufferedImage AND_BTN_IMG = Resources.getImage("/resources/AND.png");
    private static final BufferedImage OR_BTN_IMG = Resources.getImage("/resources/OR.png");
    private static final BufferedImage XOR_BTN_IMG = Resources.getImage("/resources/XOR.png");
    private static final BufferedImage NAND_BTN_IMG = Resources.getImage("/resources/NAND.png");
    private static final BufferedImage NOR_BTN_IMG = Resources.getImage("/resources/NOR.png");
    private static final BufferedImage NXOR_BTN_IMG = Resources.getImage("/resources/NXOR.png");
    private static final BufferedImage NOT_BTN_IMG = Resources.getImage("/resources/NOT.png");
    private static final BufferedImage LINK_BTN_IMG = Resources.getImage("/resources/LINK.png");
    private static final BufferedImage DELETE_BTN_IMG = Resources.getImage("/resources/DELETE.png");
    private static final BufferedImage CLEAR_BTN_IMG = Resources.getImage("/resources/CLEAR.png");

    private static final int CURSOR_SELECTION = 0;
    private static final int LED_SELECTION = 1;
    private static final int AND_SELECTION = 2;
    private static final int OR_SELECTION = 3;
    private static final int XOR_SELECTION = 4;
    private static final int NAND_SELECTION = 5;
    private static final int NOR_SELECTION = 6;
    private static final int NXOR_SELECTION = 7;
    private static final int NOT_SELECTION = 8;
    private static final int DELETE_SELECTION = 9;

    private MainFrame frameParent;

    private Circuit circuit;
    private int selector;

    private ImagedButton cursorBtn;
    private ImagedButton ledBtn;

    private ImagedButton andBtn;
    private ImagedButton orBtn;
    private ImagedButton xorBtn;
    private ImagedButton nandBtn;
    private ImagedButton norBtn;
    private ImagedButton nxorBtn;
    private ImagedButton notBtn;


    private ImagedButton linkBtn;
    private ImagedButton deleteBtn;
    private ImagedButton clearBtn;



    public CircuitPanel(MainFrame pFrameParent) {
        this.initCircuitPanel();

        this.frameParent = pFrameParent;
        this.circuit = new Circuit();

        this.selector = CircuitPanel.CURSOR_SELECTION;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(CircuitPanel.CIRCUIT_AREA.x, CircuitPanel.CIRCUIT_AREA.y, CircuitPanel.CIRCUIT_AREA.width, CircuitPanel.CIRCUIT_AREA.height);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(CircuitPanel.BUTTONS_AREA.x, CircuitPanel.BUTTONS_AREA.y, CircuitPanel.BUTTONS_AREA.width, CircuitPanel.BUTTONS_AREA.height);

        this.cursorBtn.draw(g, this);
        this.ledBtn.draw(g, this);

        this.andBtn.draw(g, this);
        this.orBtn.draw(g, this);
        this.xorBtn.draw(g, this);
        this.nandBtn.draw(g, this);
        this.norBtn.draw(g, this);
        this.nxorBtn.draw(g, this);
        this.notBtn.draw(g, this);

        this.linkBtn.draw(g, this);

        this.deleteBtn.draw(g, this);
        this.clearBtn.draw(g, this);

        this.circuit.draw(g, this);
    }

    private void initCircuitPanel() {
        this.setSize(CircuitPanel.CIRCUIT_PANEL_DIMENSION);
        this.setLayout(null);

        this.cursorBtn = new ImagedButton(1225,30, 40,40, CircuitPanel.CURSOR_BTN_IMG);

        this.ledBtn = new ImagedButton(1225, 80, 40,40, CircuitPanel.LED_BTN_IMG);

        this.andBtn = new ImagedButton(1225, 150, 40,40, CircuitPanel.AND_BTN_IMG);
        this.orBtn = new ImagedButton(1225, 200, 40,40, CircuitPanel.OR_BTN_IMG);
        this.xorBtn = new ImagedButton(1225, 250, 40,40, CircuitPanel.XOR_BTN_IMG);
        this.nandBtn = new ImagedButton(1225, 300, 40,40, CircuitPanel.NAND_BTN_IMG);
        this.norBtn = new ImagedButton(1225, 350, 40,40, CircuitPanel.NOR_BTN_IMG);
        this.nxorBtn = new ImagedButton(1225, 400, 40,40, CircuitPanel.NXOR_BTN_IMG);
        this.notBtn = new ImagedButton(1225, 450, 40,40, CircuitPanel.NOT_BTN_IMG);

        this.linkBtn = new ImagedButton(1225, 520, 40,40, CircuitPanel.LINK_BTN_IMG);
        this.deleteBtn = new ImagedButton(1225, 570, 40,40, CircuitPanel.DELETE_BTN_IMG);
        this.clearBtn = new ImagedButton(1225, 620, 40, 40, CircuitPanel.CLEAR_BTN_IMG);

        this.addMouseListener(new CircuitListener());
    }


    private class CircuitListener extends MouseAdapter {
        private final Rectangle CIRCUIT_CLICKABLE_AREA = new Rectangle(CircuitPanel.CIRCUIT_AREA.x, CircuitPanel.CIRCUIT_AREA.y, CircuitPanel.CIRCUIT_AREA.width - 60, CircuitPanel.CIRCUIT_AREA.height);

        @Override
        public void mousePressed(MouseEvent e) {
            Point clickedPoint = e.getPoint();

            if(BUTTONS_AREA.contains(clickedPoint)) {
                if(cursorBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.CURSOR_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.DEFAULT_CURSOR);
                }

                if(ledBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.LED_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.LED_CURSOR);
                }

                if (andBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.AND_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.AND_CURSOR);
                }

                if (orBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.OR_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.OR_CURSOR);
                }

                if (xorBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.XOR_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.XOR_CURSOR);
                }

                if (nandBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.NAND_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.NAND_CURSOR);
                }

                if (norBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.NOR_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.NOR_CURSOR);
                }

                if (nxorBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.NXOR_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.NXOR_CURSOR);
                }

                if (notBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.NOT_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.NOT_CURSOR);
                }

                if(linkBtn.contains(clickedPoint)) {
                    try {
                        int firstID = Integer.parseInt(JOptionPane.showInputDialog(null, "Primo ID:"));
                        int secondID = Integer.parseInt(JOptionPane.showInputDialog(null, "Second ID:"));

                        circuit.linkByID(firstID, secondID);
                        repaint();
                        System.out.println(circuit);
                    } catch (Exception ex){
                        ex.printStackTrace();

                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    } finally {
                        selector = CircuitPanel.CURSOR_SELECTION;
                        frameParent.changeMouseIcon(MainFrame.DEFAULT_CURSOR);
                    }
                }

                if (deleteBtn.contains(clickedPoint)) {
                    selector = CircuitPanel.DELETE_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.DELETE_CURSOR);
                }

                if(clearBtn.contains(clickedPoint)) {
                    circuit.clear();
                    DeviceGenerator.resetGenerator();
                    repaint();

                    selector = CircuitPanel.CURSOR_SELECTION;
                    frameParent.changeMouseIcon(MainFrame.DEFAULT_CURSOR);
                }
            }

            if(CIRCUIT_CLICKABLE_AREA.contains(clickedPoint)) {
                switch (selector) {
                    case CURSOR_SELECTION:
                        try {
                            Device d = circuit.getDeviceByPoint(e.getPoint());

                            if (d instanceof Led) {
                                Led led = (Led) d;
                                led.commute();
                                repaint();
                            }
                        } catch (Exception ex) {
                            //
                        }

                        break;

                    case LED_SELECTION:
                        circuit.add(DeviceGenerator.getDevice(DeviceGenerator.LED_TYPE, e.getX(), e.getY()));
                        System.out.print ("Creating a led...\n");
                        break;

                    case AND_SELECTION:
                        circuit.add(DeviceGenerator.getDevice(DeviceGenerator.AND_GATE_TYPE,e.getX(),e.getY()));
                        System.out.print("Creating AND Gate...\n");
                        break;

                    case OR_SELECTION:
                        circuit.add(DeviceGenerator.getDevice(DeviceGenerator.OR_GATE_TYPE,e.getX(),e.getY()));
                        System.out.print("Creating OR Gate...\n");
                        break;

                    case XOR_SELECTION:
                        circuit.add(DeviceGenerator.getDevice(DeviceGenerator.XOR_GATE_TYPE,e.getX(),e.getY()));
                        System.out.print("Creating XOR Gate...\n");
                        break;

                    case NAND_SELECTION:
                        circuit.add(DeviceGenerator.getDevice(DeviceGenerator.NAND_GATE_TYPE,e.getX(),e.getY()));
                        System.out.print("Creating NAND Gate...\n");
                        break;

                    case NOR_SELECTION:
                        circuit.add(DeviceGenerator.getDevice(DeviceGenerator.NOR_GATE_TYPE,e.getX(),e.getY()));
                        System.out.print("Creating NOR Gate...\n");
                        break;

                    case NXOR_SELECTION:
                        circuit.add(DeviceGenerator.getDevice(DeviceGenerator.NXOR_GATE_TYPE,e.getX(),e.getY()));
                        System.out.print("Creating NXOR Gate...\n");
                        break;

                    case NOT_SELECTION:
                        circuit.add(DeviceGenerator.getDevice(DeviceGenerator.NOT_GATE_TYPE,e.getX(),e.getY()));
                        System.out.print("Creating NOT Gate...\n");
                        break;

                    case DELETE_SELECTION:
                        System.out.println(circuit.removeDeviceByPoint(e.getPoint()));
                        break;
                }
                repaint();
                System.out.println(circuit);
            }
        }

    }
}
