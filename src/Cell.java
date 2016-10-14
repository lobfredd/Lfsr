/**
 * Created by Fredrik on 11.10.2016.
 */
public class Cell {
    private byte value;
    private boolean gateOpen;

    public Cell() {
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public boolean isGateOpen() {
        return gateOpen;
    }

    public void setGateOpen(boolean gateOpen) {
        this.gateOpen = gateOpen;
    }
}
