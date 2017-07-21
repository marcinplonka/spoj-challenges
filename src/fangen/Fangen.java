package fangen;


import java.lang.*;
import java.util.*;


class Main {

    public static void main (String[] args) throws java.lang.Exception {


        DataCache cache = DataCacheFactory.getDataCache(DataCacheType.Deque);
        cache.collectDataFromUser();

        WingedFanPrinter fanPrinter = WingedFanPrinterFactory.getWingedFanPrinter(FanPrinterType.QuadrupleSymmetry);

        while (cache.isNotEmpty())

            fanPrinter.printFan(cache.getWingSize(), cache.getFanRotation());
    }
}




enum DataCacheType {

    Deque,
}


enum FanPrinterType {

    QuadrupleSymmetry,
}


enum WingType {

    AsteriskTriangle,
}



enum FanRotation {
    Clockwise,
    Anticlockwise,
}


interface WingedFanPainter {

    ArrayList<String> paintWingPattern(int size);
    ArrayList<StringBuilder> paintEntireFan(ArrayList<String> wingPattern);
}

interface WingedFanPrinter {

    void printFan(int size, FanRotation fanRotation);

}


interface DataCache {

    void collectDataFromUser();
    int getWingSize();
    FanRotation getFanRotation();
    boolean isNotEmpty();

}


class WingedFanPrinterFactory {


    static WingedFanPrinter getWingedFanPrinter(FanPrinterType type) {

		WingedFanPainter painter = WingPainterFactory.getWingPainter(WingType.AsteriskTriangle);

		if (type == FanPrinterType.QuadrupleSymmetry) {
			return new WingedFanPrinterQuadrupleSymmetry(painter);
		}
		throw new IllegalArgumentException("Illegal FanPrinterType: " + type);
	}

}

class WingPainterFactory {



    static WingedFanPainter getWingPainter(WingType type) {

        if(type == WingType.AsteriskTriangle) {
            return new WingedFanPainterAsteriskTriangle();
        }

        throw new IllegalArgumentException("Illegal FanWingType: " + type);

    }

}


class DataCacheFactory {
    public static DataCache getDataCache(DataCacheType type) {
        if (type == DataCacheType.Deque) {
            return new DequeDataCache();
        }
        throw new IllegalArgumentException("Illegal DataCacheType: " + type);
    }
}


class WingedFanPainterAsteriskTriangle implements WingedFanPainter {

    @Override
    public ArrayList<String> paintWingPattern(int size) {

        ArrayList<String> wingPattern = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder(size);
            for (int j = 0; j < size; j++) {
                if (i >= j) {
                    sb.append('*');
                } else {
                    sb.append('.');
                }

            }
            wingPattern.add(sb.toString());

        }
        return wingPattern;
    }

    @Override
    public ArrayList<StringBuilder> paintEntireFan(ArrayList<String> wingPattern) {
        ArrayList<StringBuilder> fan = new ArrayList<>();

        for (String string : wingPattern) {
            fan.add(new StringBuilder(string));
        }

        for (String string : wingPattern) {
            fan.add(new StringBuilder(string).reverse());
        }

        Collections.reverse(wingPattern);


        for (int i = 0; i < wingPattern.size(); i++) {
            fan.get(i).append(new StringBuilder(wingPattern.get(i)));
        }

        for (int i = wingPattern.size(), j = 0; i < fan.size(); i++, j++) {
            fan.get(i).append(new StringBuilder(wingPattern.get(j)).reverse());
        }

        return fan;

    }

}


class WingedFanPrinterQuadrupleSymmetry implements WingedFanPrinter {


    private WingedFanPainter painter;

    WingedFanPrinterQuadrupleSymmetry(WingedFanPainter painter) {
        this.painter = painter;
    }
    @Override
    public void printFan(int size, FanRotation fanRotation) {

        ArrayList<StringBuilder> fan;
        fan = painter.paintEntireFan(painter.paintWingPattern(size));


        switch (fanRotation) {
            case Clockwise:
                break;
            case Anticlockwise: Collections.reverse(fan);
                break;
            default: throw new IllegalArgumentException("Illegal FanDirectionType: " + fanRotation);
        }

        fan.forEach(System.out::println);
        System.out.println("");

    }


}


class DequeDataCache implements DataCache {

    private ArrayDeque<Integer> deque = new ArrayDeque<>();

    @Override
    public void collectDataFromUser() {

        Scanner sc = new Scanner(System.in);
        int number;
        while (true){
            number = sc.nextInt();
            if (number != 0) {
                deque.add(number);
            } else

            break;
        }
    }
    @Override
    public int getWingSize() {
        return Math.abs(deque.peek());
    }

    @Override
    public FanRotation getFanRotation() {
        return deque.poll() > 0 ? FanRotation.Clockwise : FanRotation.Anticlockwise;
    }
    @Override
    public boolean isNotEmpty() {
        return !deque.isEmpty();
    }
}


