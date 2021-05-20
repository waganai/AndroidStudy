package com.example.jetpackdemeo.test;

import android.util.Log;

public class HexUtils {
    private static final String TAG = HexUtils.class.getSimpleName();

    public static void main(String[] arg) {
        toHexFormat("https://bilibili.com/huiyuangou/goods/detail.html");
    }

    public static String toHexFormat(String str) {
        Log.e(TAG, "toHexFormat() start");

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            //位于运算
            bit = bs[i] & 0x0f;
            //进行字符串的拼接
            sb.append(chars[bit]);
        }

        Log.e(TAG, "toHexFormat() " + sb.toString());

        Log.e(TAG, "toHexFormat() end");

        return sb.toString();
    }
}