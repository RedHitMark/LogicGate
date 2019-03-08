package logic;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Circuit {
    private ArrayList<Device> devices;

    public Circuit() {
        this.devices = new ArrayList();
    }

    public void add(Device pDevice) {
        devices.add(pDevice);
    }

    public Device getDeviceByID(int pID) throws Exception {
        int i = 0;
        while ( i < devices.size() && devices.get(i).getID() != pID) {
            i++;
        }

        if ( i >= devices.size()) {
            throw new Exception("Not Found!");
        } else {
            return devices.get(i);
        }
    }

    public Device getDeviceByPoint(Point pPoint) throws Exception {
        int i = 0;
        while ( i < devices.size() && !devices.get(i).contains(pPoint)) {
            i++;
        }

        if ( i >= devices.size()) {
            throw new Exception("Not Found!");
        } else {
            return devices.get(i);
        }
    }

    public boolean removeDeviceByPoint(Point pPoint) {
        try {
            Device toRemove = getDeviceByPoint(pPoint);

            for(int i = 0; i < toRemove.getNexts().size(); i++) {
                toRemove.getNexts().get(i).getPrevs().remove(toRemove);
                toRemove.getNexts().get(i).updateState();
            }
            toRemove.getNexts().clear();

            for(int i = 0; i < toRemove.getPrevs().size(); i++) {
                toRemove.getPrevs().get(i).getNexts().remove(toRemove);
                toRemove.getPrevs().get(i).updateState();
            }
            toRemove.getPrevs().clear();

            devices.remove(toRemove);
            return true;
        } catch (Exception e) {

            //e.printStackTrace();
            return false;
        }
    }

    public void linkByID(int pFirstID, int pSecondID) throws Exception {
        Device f = getDeviceByID(pFirstID);
        Device s = getDeviceByID(pSecondID);

        if (pFirstID == pSecondID) {
            throw new Exception("Non puoi collegare allo stesso device");
        }

        if (s instanceof Led && f instanceof Led) {
            throw new Exception("Non puoi collegare due led");
        }

        if (s instanceof Led && s.hasAtleastOneNext() ) {
            throw new Exception("Led già in uso");
        }

        if(f instanceof Led && f.hasAtleastOnePrevious()) {
            throw new Exception("Led già in uso");
        }

        if (s.hasAllPrevs()) {
            throw new Exception("Connettori occupati");
        }

        f.setNewNext(s);
        s.setPrevious(f);

        f.updateState();


        s.updateState();
    }

    public void draw(Graphics g, ImageObserver observer) {
        for(int i = 0; i < devices.size(); i++) {
            Device toDraw = devices.get(i);
            toDraw.draw(g, observer);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i<this.devices.size(); i++) {
            s.append(devices.get(i) + "\n");
        }

        return s.toString();
    }

    public void clear() {
        devices.clear();
    }
}
