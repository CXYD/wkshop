package net.jeeshop.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/28
 * Time: 18:46
 */
public class Test2 {


    public static void main(String[] args) {


//        Matcher matcher=Pattern.compile("data:image/[^>]*").matcher("data:image/png;base64,iVBORw0KG");
//        while(matcher.find()){
//            System.out.println(matcher.group());
//        }
//
//
        String str = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAAkCAYAAABIdFAMAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAHhJREFUeNo8zjsOxCAMBF\n" +
                "B/KEAUFFR0Cbng3nQPw68ArZdAlOZppPFIBhH5EAB8b+Tlt9MYQ6i1BuqFaq1CKSVcxZ2Acs6406KUgpt5/LCKuVgz5BDCSb13ZO99ZOdcZGvt4mJjzMV";

        substringBetween(str,"data:image/", ";");



    }

    public static  String substringBetween(String str,String start, String end){

        String regex = start+"(.*)"+end;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
        return "";
    }
}
