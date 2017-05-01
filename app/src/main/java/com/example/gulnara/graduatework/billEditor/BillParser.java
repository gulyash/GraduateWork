package com.example.gulnara.graduatework.billEditor;

import com.example.gulnara.graduatework.model.Dish;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gulnara on 3/22/17.
 */

public class BillParser {

    public ArrayList<Dish> parse(String string) {

        String result = string.replaceAll("([!\"№;:?*()=_+@#$^&]+)", "");  //weird characters
        result = result.replaceAll("([«»„“…´′″]+)", "");            //weirder characters
        result = result.replaceAll("([\\]\\['<>|~`/{\\}]+)", "");   //more weird characters
        result = result.replaceAll( "([\\.,%\\-—]{2,})" , "");         //multiple characters that might be delimiters for sums
        result = result.replaceAll("([-—]+)", " ");

        //this leaves only strings that end with something that might be a price
        String[] array = result.split("[\n]");

        ArrayList<Dish> bill = new ArrayList<>();
        for (String s:array) {
            //groups:                    //1  //2      //3,4     //5       //6
            Pattern p = Pattern.compile("(.+ |=)(\\d{2,})((\\.|,|-)(\\d{2}))?(( (руб|руб\\.|РУБ|Руб\\.))?)((с|С|в|В)?)");
            Matcher m = p.matcher(s);
            if (m.matches()) {
                int price = parseInt(m.group(5));
                price+=100*parseInt(m.group(2));
                //group a - is the name
                //group b - is the price
                Dish dish = new Dish(m.group(1), price, 1);
                bill.add(dish);
            }
        }

        return bill;
    }

    private int parseInt(String s){
        try{
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

}
