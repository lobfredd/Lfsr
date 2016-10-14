import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Fredrik on 11.10.2016.
 */
public class Lfsr {
    private List<Cell> cells;

    public Lfsr(long initValue, int degree, int... taps) {
        cells = new ArrayList<>();

        for (int i = 0; i < degree; i++) {
            Cell b = new Cell();
            b.value = getBitAtPosition(initValue, (degree-1) - i);
            int finalI = i;
            b.gateOpen = IntStream.of(taps).anyMatch(x -> x == finalI);
            cells.add(b);
        }
    }

    public byte performClock(){
        byte outValue = cells.get(0).value;
        byte inValue = outValue;

        for (int i = 1; i < cells.size(); i++) {
            if(cells.get(i).gateOpen) inValue ^= cells.get(i).value;
            cells.get(i - 1).value = cells.get(i).value;
        }
        cells.get(cells.size() - 1).value = inValue;

        return outValue;
    }

    private class Cell {
        private byte value;
        private boolean gateOpen;
    }

    public static byte getBitAtPosition(long b, int pos) {
        return (byte) ((b >> pos) & 1);
    }
}
