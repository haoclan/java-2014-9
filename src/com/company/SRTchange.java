package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lion on 2018/6/10.
 */
//针对srt字幕延迟的问题,进行调整 第一版针对特定文件路径进行特定时间延长的修改,针对utf-bom格式的text文件
//第二版本,针对特定的文件目录下的所有的文件(utf-bom格式的text文件)进行 所有文件的时延处理

public class SRTchange {
    //需要调整的延时 单位秒
    static int delaysecond = 180 ;
    static String filename ="/Users/lion/Downloads/a";
    static String filetype=".srt";
    static String filedir="/Users/lion/Downloads/subtitles/";


    public static void main(String[] args) throws IOException, ParseException {

//        File file = new File("/Users/lion/Downloads/a.srt");
//        FileInputStream fis = new FileInputStream(file); //!!!! fis.read
//        byte[] buf = new byte[1024];
//        StringBuffer sb = new StringBuffer();
//
//        while (fis.read(buf) != -1) {
//            sb.append(new String(buf));
//            buf = new byte[1024]; //这里每次新开辟了一个字节数组
//        }
//
//
//        System.out.println(sb.toString().toLowerCase());
//
//        //写文件,一定要知道行数吗(因为一般的写法并不是一次性写入的文件)
//        File file2 = new File("/Users/lion/Downloads/zimuLowerCase");
//        FileOutputStream out = new FileOutputStream(file2, true);  //!!!!  out.write
//
//        out.write(sb.toString().toLowerCase().getBytes("utf-8"));
//        out.close();


       ////////


    //onefile(filename+".srt",filename+"a.srt"); //单个文件测试
        startdir(filedir);

    }// main

    public static void startdir(String dirname) throws ParseException {
        String path =dirname;
        File f = new File(path);
        if (!f.exists()) {
            System.out.println("文件夹名称有误");
        }
        File fa[] = f.listFiles();//此函数为当前文件夹下文件目录
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                //子文件为一个文件夹则跳过
            } else {

                System.out.println(path+fs.getName());
                onefile(path+fs.getName(),path+"A"+fs.getName());
            }
        }
    }

    public static void onefile(String filename,String filenamechanged) throws ParseException {
        File file = new File(filename);
        File newfile = new File(filenamechanged);

        StringBuffer sb= new StringBuffer("");

        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {

                if(tempString.indexOf("-->")!=-1)
                {
                    System.out.println(tempString);
                    //对tempString 修改输出
                    String [] times= tempString.split(" ");
                    //System.out.println(times[0]+" /// "+times[2]);
                    String outline = changeplus(delaysecond,times[0])+" --> "+changeplus(delaysecond,times[2]);

                    sb.append(outline+"\n");
                }
                else
                {//直接输出
                    System.out.println(tempString);
                    sb.append(tempString+"\n");
                }

                line++;

            }

            reader.close();


            //重新复制一个文件
            FileWriter writer = new FileWriter(newfile);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sb.toString());
            bw.close();
            writer.close();

        } catch (IOException e) {

            e.printStackTrace();


        } finally {

            if (reader != null) {

                try {

                    reader.close();


                } catch (IOException e1) {


                }

            }

        }
    }

    static String changeplus(int seconds, String str) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss,SSS");//原来这些数字都保留了含义
        Date date = format.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);

        calendar.add(Calendar.SECOND,seconds);

        return format.format(calendar.getTime()).toString();
    }




}

