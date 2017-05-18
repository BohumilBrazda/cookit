package cz.brazda.cookit.common;

/**
 * Created by BOBES on 15.8.2015.
 */

public enum Unit {
    kg(0), dg(1), g(2), l(3), dl(4), cl(5);

    private final int code;

    Unit(int index) {
        this.code = index;
    }

    public static Unit fromCode(int code){
        switch (code) {
            case (0):
                return kg;
            case (1):
                return dg;
            case (2):
                return g;
            case (3):
                return l;
            case (4):
                return dl;
            case (5):
                return cl;
            default:
                return kg;
        }

    }

    public int getCode(){
        return code;
    }

}
