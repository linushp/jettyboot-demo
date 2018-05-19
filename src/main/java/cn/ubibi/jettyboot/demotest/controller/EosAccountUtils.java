package cn.ubibi.jettyboot.demotest.controller;

public class EosAccountUtils {


    public static String getEosAccountName(long num) {
        String dict = "12345abcdefghijklmnopqrstuvwxyz";
        int dictLength = dict.length();

        char nameChar[] = new char[]{'b', 'e', 'e', 'a', '1', '2', '3', '4', '.', 'c', 't', 'r'};


        String x = "" + num;

        for (int i = 1; i < nameChar.length - 4; i++) {

            int index = i + 4;


            char c0 = x.charAt(index);
            int c0n = Integer.valueOf("" + c0);//6
            c0 = dict.charAt(c0n);
            nameChar[i] = c0;
        }

        return new String(nameChar);
    }


    public static String getEosRandomAccountName() {
        long num = System.currentTimeMillis();
        return getEosAccountName(num);
    }


    public static void main(String[] args) {
        System.out.println(getEosAccountName(System.currentTimeMillis()));
    }

}
