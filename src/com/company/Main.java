package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //数组to String打印而已
        //String[] intArray={"asdf","bbbb"};
        //String intstring= Arrays.toString(intArray);

        //数组to ArrayList 集合  (ArrayList实现了List接口)
//        String[] stringArray= {"1","2","3","4","5"};
//        ArrayList<String> arrayList=new ArrayList<String>(Arrays.asList(stringArray));

        //集合的遍历 ArrayList实现了List  集合的遍历,以及基本方法
//        for(String temp:arrayList){
//            System.out.println(temp);
//        }
//        add(e)  add(index,e)  clear() remove(index)  size()   contains(e)   equals()  get(index)   返回数组 Object[] toArray()

        //迭代器
//        Iterator iter = arrayList.iterator();
//        while(iter.hasNext())
//        {
//            Object object = iter.next();
//            System.out.println(object);
//        }

//
//        Integer integer = new Integer(1);
//        integer.intValue();
//        int compareResult = integer.compareTo(new Integer(1));//返回值是 -1 0 1
//
//        //string to
//        int parseint = Integer.parseInt("123");
//        int parseintRadix2 = Integer.parseInt("1010",2); //二进制的
//
//        //int to string
//        String str = Integer.toString(17,2);  //toString很强大



//        //set 集合一样的用法
//        TreeSet ts=new TreeSet();
//        ts.add("orange");
//        ts.add("apple");
//        ts.add("banana");
//        ts.add("grape");
//
//        Iterator<String> iterator = ts.iterator();
//        while (iterator.hasNext()){
//            String string = iterator.next();
//            System.out.println(string );
//        }



        //结构体排序,需要指定排序函数,
//        Scanner in =new Scanner(System.in);
//        int n;
//        n=in.nextInt();
//        node array[] = new node[10];
//        for (int j = 0; j < n; j++) {
//            array[j] = new node();
//            array[j].x = in.nextInt();
//            array[j].y = in.nextInt();
//        }
        //Arrays.sort(array,0,n,new mycmp());  //对数组进行排序 结果直接在数组中就体现了

//        Scanner in = new Scanner(System.in);
//        int n;
//        n=in.nextInt();
//        List<node> arraylist= new ArrayList<node>();
//        for (int i = 0; i < n; i++) {
//            arraylist.add(new node(in.nextInt(),in.nextInt()));
//        }
//
//        Collections.sort( arraylist, new mycmp()); //对集合的排序 结果直接在集合中就体现了
//
//        for (int i =0;i<n;i++){
//            System.out.println(arraylist.get(i).x +","+ arraylist.get(i).y);
//        }

        //最基本的排序
//        List<Integer> arraylist= new ArrayList<Integer>();
//        arraylist.add(1);
//        arraylist.add(6);
//        arraylist.add(10);
//        arraylist.add(7);
//        Collections.sort(arraylist);
//
//        System.out.println(arraylist.get(3));


//
//        float a= (float)201/7;
//        System.out.println( a);
//        System.out.printf("%.2f\n",a);
//        System.out.println("hello");

        //精确度问题 前m项 每项是前一项平方根 pow()  精度保留 输出和c一样





//        //位运算
//        int a= 5;
//        int aleft2= a<<2;   //左移,低位补0
//        int aright2= a>>3;  //右移,高位补符号位,因此保证了符号相同
//        System.out.println(aright2);
//                            //而无符号右移 是在最左边填上0
//                            //& 与     |或    ^异或   ~位非






        //二维数组  因为java的数组里装的是对象,因此高纬度必须指定
//        int n=10;
//        int m=19;
//        int a[][]=new int[n][m];
//        //初始化二维数组
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                a[i][j]=0;
//            }
//        }



        //出栈入栈  不是啥问题



        //String处理
//        String str= "abcdefhijabcnopq";
//        String newstr= str.replace("abc","xxx");  //这个是返回新的str
//        String newupstr = str.toUpperCase();  //返回新的str
//        String newlowstr = str.toLowerCase(); //返回新的str
//
//
//        System.out.println(str);
//        System.out.println(newstr);
//        System.out.println(newupstr);



        //Map  键值对的使用,类似索引
        //containsKey(key)
        //isEmpty()



/*
        Map<Integer ,Integer> map = new HashMap<Integer,Integer>();
        map.put(1,2);
        map.put(2,2);
        map.put(3,2);
        map.put(4,2);
//        System.out.println(map.get(1));
//        遍历
        for(Object obj:map.keySet()){
            Integer value = map.get(obj);
            System.out.println(value);
        }

        //遍历 map->list
*/

        System.out.println(9/10);

        int [] array = new int[10];
        System.out.println(array);





    }//main函数

} //类



//以下是结构体与排序

//比如自定义的东西,手动改写hashcode

class node{
    int x;
    int y;
    node(int x,int y){
        this.x=x;
        this.y=y;
    }
}


class mycmp implements Comparator<node>   //直接做差 前面-后面的 最后返回为从小到大的自然顺序
{
    @Override
    public int compare(node o1, node o2) {
        if(o1.x != o2.x){
            return o1.x-o2.x;
        }else{
            return o1.y-o2.y;
        }

    }
}




