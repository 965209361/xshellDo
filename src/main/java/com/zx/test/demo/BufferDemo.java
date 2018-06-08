package com.zx.test.demo;

/**
 * created by zengqintao on 2018-06-07 15:25 .
 **/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

class BufferDemo {
    static final String url = "https://home.firefoxchina.cn/";
    static final String urlDom = "#nd-gossip .hot-right a";

    /*public static void main(String[] args)throws IOException {
        BufferedReader bufferedReader =new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("请输入一系列文字，可包括空格：");
        String text =bufferedReader.readLine();
        System.out.println("请输入文字："+text);
        try {
            String reader = null;
            InputStream io = new FileInputStream("D:\\关于服务器更新及相关服务启动.txt");
            InputStreamReader isR = new InputStreamReader(io);
            BufferedReader br = new BufferedReader(isR);
            while ((reader = br.readLine()) != null) {
                System.out.println(reader);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public static void main(String[] args) {
        Elements elements;
        String content;
        String title;
        File file = new File("D:\\demo.txt");
        BufferedWriter writer;

        try {
            Document doc = Jsoup.connect(url).get();
            elements = doc.select(urlDom);
            FileWriter fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            for (Element ele : elements) {
                title = ele.text();
                doc = Jsoup.connect(ele.attr("href")).get();
                content = doc.select("#main_content").text();
                writer.write(title + "\t-----" + content);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
