package com.company;

import java.io.*;
import java.util.Random;

/**
 * Created by lion on 16/10/8.
 */
public class MySerializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Worm w = new Worm(6,'a');
        System.out.println("序列化之前");
        System.out.println("w= "+w+"hash"+w.hashCode());

        //序列化操作
        ObjectOutputStream oos1 =new ObjectOutputStream(new FileOutputStream("worm.out"));
        oos1.writeObject("Worm storage By FileOutputStream: ");
        oos1.writeObject(w);//这里必须所有的引用对象都实现序列化,这里是Data
        oos1.close();

        //反序列化
        ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("worm.out"));
        String s1 = (String)ois1.readObject(); //因为之前写了2次,因此这里读2次
        Worm w1 = (Worm)ois1.readObject();
        ois1.close();

        System.out.println("反序列化之后");
        System.out.println(s1);
        System.out.println("w1:"+w1+"hash"+w1.hashCode());

        //序列化操作2  使用另一种流来充当过滤流
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        ObjectOutputStream oos2 = new ObjectOutputStream(byteOutStream);
        oos2.writeObject("Worm storage by ByteOutputStream ");
        oos2.writeObject(w);
        oos2.flush();//刷新?清空

        //反序列化2
        ByteArrayInputStream byteInStream = new ByteArrayInputStream(byteOutStream.toByteArray());//通过这里将之前的字节输出流弄过来
        ObjectInputStream ois2 = new ObjectInputStream(byteInStream);
        String s2 = (String) ois2.readObject();
        Worm w2 = (Worm)ois2.readObject();
        ois2.close();
        System.out.println("反序列操作2之后");
        System.out.println(s2);
        System.out.println("w2:" + w2 +"hash"+w2.hashCode());

        Object obj;

    }
}


class Data implements Serializable{
    private static final long serialVersionUID = 7247714666080613254L;
    public int n; //这是数据
    public Data(int n){
        this.n = n;
    }
    public String toString(){
        return Integer.toString(n);
    }

}

class Worm implements Serializable {
    private static final long serialVersionUID = 5468335797443850679L;
    private Data[] d = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };
    private static Random random = new Random(47);//因为这里的random种子一样,因此生成的东西也一样
    private Worm next;
    private char c; //保存一个字符

    public Worm(int i , char x) {
        System.out.println("Worm constructor:" +i);
        c = x;
        if(--i > 0)  //先减少
        {
            next = new Worm(i , (char)(x+1));//下一个字符,这样是递归构造了。
        }
    }
    public Worm() {
        System.out.println("Default constructor!");
    }

    //复写这个仅仅是为了能正确显示,表明保存的之前的东西~
    public String toString() {
        StringBuilder sb = new StringBuilder(":");
        sb.append(c);
        sb.append("(");
        for(Data data : d) {
            sb.append(data);
        }
        sb.append(")");
        if(next!=null)
        {
            sb.append(next);
        }
        return sb.toString();
    }
}
