package com.company;

import java.util.*;

/**
 * Created by lion on 16/9/28.
 */
public class Test {
    public static void main(String [] args)
    {


//        String stra = new String("abc");
//        String strb = new String("abc");
//        System.out.println(  stra == strb  );

//        StringBuffer s1 = new StringBuffer("a");
//        StringBuffer s2 = new StringBuffer("a");
//        System.out.println(s1.equals(s2));


        //  StringBuffer和StringBuilder类  !!!!

        //这种写法肯定ok,因为判断条件是 list.size,会每次都执行吗???,会的!!不然变量i有何意义
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
//        for (int i = 0; i < list.size(); i++) {
//            if(i==2)
//            {
//                Integer obj = list.get(i);
//                list.remove(obj);
//            }
//            System.out.println(list.get(i));
//        }


        Iterator it = list.iterator();

        while (it.hasNext())
        {
         Integer obj = (Integer)it.next();
            if(obj == 2){

            it.remove();

            }

        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }



    }
}
