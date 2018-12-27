package com.company;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by lion on 16/9/29.
 */
public class MyFile {

    public static void main(String[] args) throws IOException {
        /**
        File directory = new File("");
        //输出当前目录名称
        System.out.println( directory.getAbsoluteFile() );
        File dir = new File(directory.getAbsolutePath());

        //创建新的文件夹 ,路径制定好,然后直接.mkdir就ok
        //跨平台 分隔符 File.separator
        File directory2 = new File(directory.getAbsoluteFile().toString()+File.separator+"testdirectory" );

        //System.out.println(directory2.mkdir());

        //遍历下面的文件, 如果直接使用 new File("")作为目标文件是不可以的
        //但是重新构建一个就ok。很神奇

        File [] files = dir.listFiles();
        for (File file:files)
        {
         System.out.println(file);
        }

        //File
        // java以什么结尾。。
        // dir.getName().endsWith(".java");

        //file1.renameTo(file2) 将文件一移动到文件2的文件地址下
        //这个只有在相同文件系统格式的时候会成功
        //比附file1是NTFS分区,file2也是NTFS则会成功,但是file2是FAT32则会失败

         */

        //将一个文件里的 字符(全部大写的)变为 全部 小写。
        // "/Users/lion/Downloads/[DownSub.com]_Clinton_vs._Trump-_The_first_U.S._presidential_debate_on_CBC_News.srt"
        //1读入,显示   利用FileInputStream 或者 BufferedReader
        //2测试,变大写问题
        //3写入,缓冲什么的 1万5千行呢

        /**
        File file = new File("/Users/lion/Downloads/[DownSub.com]_Clinton_vs._Trump-_The_first_U.S._presidential_debate_on_CBC_News.srt");
        FileInputStream fis = new FileInputStream(file); //!!!! fis.read
        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();

        while ( fis.read(buf) != -1 )
        {
        sb.append(new String(buf));
        buf = new byte[1024]; //这里每次新开辟了一个字节数组
        }


//        System.out.println(sb.toString().toLowerCase());

        //写文件,一定要知道行数吗(因为一般的写法并不是一次性写入的文件)
        File file2 = new File("/Users/lion/Downloads/zimuLowerCase");
        FileOutputStream out = new FileOutputStream(file2,true);  //!!!!  out.write

        out.write(sb.toString().toLowerCase().getBytes("utf-8"));
        out.close();
        */


        //1 文件的复制
        //2 拼写文件名
        // 循环问题

        /**  这样的方法,写入的文件总是空的好奇怪
        String strtemp = "8788";
        File file1 = new File("/Users/lion/Documents/2016十一内蒙自驾游/100CANON/"+"IMG_"+strtemp+".JPG");
        File file2 = new File("/Users/lion/Documents/2016十一内蒙自驾游/精华版本/"+"IMG_"+strtemp+".JPG");
        FileInputStream in = new FileInputStream(file1);
        FileOutputStream out = new FileOutputStream(file2);
        byte[] buffer = new byte[100000000];

            int ins = in.read(buffer);//字节如此大
            System.out.println(ins);

            out.write(buffer);

            in.close();
            out.flush();
            out.close();
        */

        //方法2就搞定了
        String str = "8780 8788 8789 8796 8797 8811 8826 8828 8846 8854 8857 8861 8862 8864 8866 8868 8871 8878 8881 8910 " +
                "8913 8916 8922 8943 8967 8972 8976 8985 8991 8992 8994 8997 9005 9011 9020 9058 9079 9100 9110 9114 9116 " +
                "9123 9134 9149 9150 9152 9157 9169 9175 9194 9199 9236 9263 9264 9266 9268 9269 9289 9293 9295 9296 9334 9337 " +
                "9352 9355 9360 9373 9390 9395 9404 9417 9425 9435 9453 9454 9459 9460 9461 9462 9466 9483 9484 " +
                "9486 9490 9491 9497 9501 9509 9541 9552 9553 9560 9562 9570 9580 9595 9607 9628 9637 9649 9656 9658 9662 9663 9666 9681 9687 " +
                "9698 9700";
        String [] strs = str.split(" ");

        for (int i = 0; i <strs.length; i++) {
            System.out.println(strs[i]+" "+i ) ;
            File f1 = new File("/Users/lion/Documents/2016十一内蒙自驾游/100CANON/"+"IMG_"+strs[i]+".JPG");
            File f2 = new File("/Users/lion/Documents/2016十一内蒙自驾游/精华版本/"+"IMG_"+strs[i]+".JPG");



            copyfile(f1,f2);

        }




//
//        File f1 = new File("/Users/lion/Documents/2016十一内蒙自驾游/100CANON/"+"IMG_"+strtemp+".JPG");
//        File f2 = new File("/Users/lion/Documents/2016十一内蒙自驾游/精华版本/"+"IMG_"+strtemp+".JPG");
//
//
//        copyfile(f1,f2);



//
//        //待完成
//        //BufferedReader
//        //通用的缓冲方式文本读取,提供了很实用的readLine
//        // 这个貌似是再从 FileInputStream中包装
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/lion/Downloads/zimuLowerCase.srt")));
//        String data = null;
//        while( (data = br.readLine()) != null)
//        {
//            System.out.println(data);
//        }
//
//        //RandomAccessFile
//



    }

    //复制文件函数
    public static void copyfile(File f1,File f2) throws IOException {
        int length=2097152;
        FileInputStream in=new FileInputStream(f1);
        FileOutputStream out=new FileOutputStream(f2);
        FileChannel inC=in.getChannel();
        FileChannel outC=out.getChannel();
        int i=0;
        while(true){
            if(inC.position()==inC.size()){
                inC.close();
                outC.close();
                break;
            }
            if((inC.size()-inC.position())<20971520)
                length=(int)(inC.size()-inC.position());
            else
                length=20971520;
            inC.transferTo(inC.position(),length,outC);
            inC.position(inC.position()+length);
            i++;
        }

    }

}
