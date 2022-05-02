package com.example.webproject.Constants;

import java.util.ArrayList;
import java.util.List;

public enum Bank {
    SBERBANK(4563),TINKOFF(3321),VTB(1657),MTS(7031),ALPHA(9122),RAIFFEISEN(5190);
    public final int i;



    Bank(int i) {
        this.i =i;
    }

    @Override
    public String toString() {
        return i+"";
    }
    public static List<String> TakeList()
    {
        List<String> list = new ArrayList<>();
        list.add(SBERBANK.name());
        list.add(TINKOFF.name());
        list.add(VTB.name());
        list.add(MTS.name());
        list.add(ALPHA.name());
        list.add(RAIFFEISEN.name());
        return list;
    }
    public static int GetValue(String str)
    {
        switch (str) {
            case ("SBERBANK"):
                return SBERBANK.i;
            case ("TINKOFF"):
                return TINKOFF.i;
            case ("VTB"):
                return VTB.i;
            case ("MTS"):
                return MTS.i;
            case ("ALPHA"):
                return ALPHA.i;
            case ("RAIFFEISEN"):
                return RAIFFEISEN.i;

            default:
                return 0;

        }
    }
}
