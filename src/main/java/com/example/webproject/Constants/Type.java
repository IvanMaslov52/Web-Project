package com.example.webproject.Constants;


import java.util.ArrayList;
import java.util.List;
public enum Type {

    MIR(1038),UNIONPAY(3921),MASTERCARD(2650),VISA(5345),JCB(3541);
    public final int i;

    Type(int i) {
        this.i=i;
    }

    @Override
    public String toString() {
        return i+"";
    }
    public static List<String> TakeTypeList()
    {
        List<String> list = new ArrayList<>();
        list.add(MIR.name());
        list.add(UNIONPAY.name());
        list.add(MASTERCARD.name());
        list.add(VISA.name());
        list.add(JCB.name());
        return list;
    }
    public static int GetValue(String str)
    {
        switch (str) {
            case ("MIR"):
                return MIR.i;
            case ("UNIONPAY"):
                return UNIONPAY.i;
            case ("MASTERCARD"):
                return MASTERCARD.i;
            case ("VISA"):
                return VISA.i;
            case ("JCB"):
                return JCB.i;
            default:
                return 0;

        }
    }
}
