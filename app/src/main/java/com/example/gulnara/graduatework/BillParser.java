package com.example.gulnara.graduatework;

import android.widget.Toast;

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
        //String buffer="";
        for (String s:array) {
            //groups:                    //1  //2      //3                 //6
            Pattern p = Pattern.compile("(.+ )(\\d{2,})(((\\.|,|-)\\d{2})?)(( (руб|руб\\.|РУБ|Руб\\.))?)");
            Matcher m = p.matcher(s);
            if (m.matches()) {

                int price = Integer.parseInt(m.group(2));

                //buffer += s + "-"+price + "-" + m.group(3) + "-"+m.group(6)+ "\n";

                //group a - is the name
                //group b - is the price
                Dish dish = new Dish(m.group(1), price, 1);
                bill.add(dish);

            }
        }
        //result = buffer;


        return bill;
    }

}
