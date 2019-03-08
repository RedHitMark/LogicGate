package logic;

public class DeviceGenerator {

    public static final int LED_TYPE = 0;
    public static final int AND_GATE_TYPE = 1;
    public static final int OR_GATE_TYPE = 2;
    public static final int XOR_GATE_TYPE = 3;
    public static final int NAND_GATE_TYPE = 4;
    public static final int NOR_GATE_TYPE = 5;
    public static final int NXOR_GATE_TYPE = 6;
    public static final int NOT_GATE_TYPE = 7;

    private static int nextID = 1;

    public static Device getDevice(int pDeviceType, int pX, int pY) {
        Device device = null;

            switch(pDeviceType) {
                case DeviceGenerator.LED_TYPE:
                    device = new Led(pX, pY, 40, nextID, 1);
                    nextID++;
                    break;

                case DeviceGenerator.AND_GATE_TYPE:
                    device = new Gate(pX, pY, 60, nextID, 2, Gate.AND);
                    nextID++;
                    break;

                case DeviceGenerator.OR_GATE_TYPE:
                    device = new Gate(pX, pY, 60, nextID, 2, Gate.OR);
                    nextID++;
                    break;

                case DeviceGenerator.XOR_GATE_TYPE:
                    device = new Gate(pX, pY, 60, nextID, 2, Gate.XOR);
                    nextID++;
                    break;

                case DeviceGenerator.NAND_GATE_TYPE:
                    device = new Gate(pX, pY, 60, nextID, 2, Gate.NAND);
                    nextID++;
                    break;

                case DeviceGenerator.NOR_GATE_TYPE:
                    device = new Gate(pX, pY, 60, nextID, 2, Gate.NOR);
                    nextID++;
                    break;

                case DeviceGenerator.NXOR_GATE_TYPE:
                    device = new Gate(pX, pY, 60, nextID, 2, Gate.NXOR);
                    nextID++;
                    break;

                case DeviceGenerator.NOT_GATE_TYPE:
                    device = new Gate(pX, pY, 60, nextID, 1, Gate.NOT);
                    nextID++;
                    break;
            }

        return device;
    }

    public static void resetGenerator() {
        nextID = 1;
    }
}
