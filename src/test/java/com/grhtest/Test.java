package com.grhtest;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author grh
 */
public class Test {
    public static int myAtoi(String str) {
        // 去除头尾的空格
        str = str.trim();
        if (str.length() == 0) return 0;
        if (str.charAt(0) != '+' && str.charAt(0) != '-' && !Character.isDigit(str.charAt(0))){
            return 0;
        }
        long ans = 0L;
        boolean flag = str.charAt(0) == '-';
        int i = Character.isDigit(str.charAt(0)) ? 0 : 1;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            ans = ans * 10 + (str.charAt(i++) - '0');
            if (!flag && ans > Integer.MAX_VALUE) {
                return 0;
            }
            if (flag && ans > 1L + Integer.MAX_VALUE) {
                return 0;
            }
        }
        return flag ? (int)(-ans) : (int)ans;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        //System.out.println(Test.myAtoi("  -42"));
        String filename = java.net.URLEncoder.encode("compareReport.docx","utf-8");
        System.out.println(filename);
    }
}
