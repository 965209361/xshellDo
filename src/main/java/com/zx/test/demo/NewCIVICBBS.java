package com.zx.test.demo;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;

/**
 * created by zengqintao on 2018-06-19 9:26 .
 **/
public class NewCIVICBBS {
    private static Properties proKit = prop();

    private static final Logger log = LoggerFactory.getLogger(NewCIVICBBS.class);

    public static void main(String[] args) {
        Document doc = null;
        Elements elems = null;
        String contentRule = proKit.getProperty("contentRule");
        WebClient webClient = new WebClient();
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);
        String webUrl = proKit.getProperty("url");
        for (int i = 1; i < 3; i++) {
            String websiteUrl = MessageFormat.format(webUrl, i);
            try {
                webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
                webClient.getOptions().setJavaScriptEnabled(false);
                webClient.getOptions().setCssEnabled(false);
                HtmlPage htmlPage = webClient.getPage(websiteUrl);
                doc = Jsoup.parse(htmlPage.asXml());
                elems = doc.select(proKit.getProperty("listRule"));
            } catch (IOException e) {
                e.printStackTrace();
                log.error("The url {},not connect", websiteUrl);
            }
            for (Element ele : elems) {
                String url = "http://www.xcar.com.cn" + ele.attr("href");
                String title = ele.text();
                try {
                    HtmlPage page = webClient.getPage(url);
                    doc = Jsoup.parse(page.asXml());
                    String content = doc.select(contentRule).text();
                    log.info("标题:{},--内容:{}", title, content);
                } catch (IOException e) {
                    e.printStackTrace();
                    log.warn("title:{}--url:{}", title, url);
                }
            }
        }

    }

    public static Properties prop() {
        InputStream io = NewCIVICBBS.class.getClassLoader().getResourceAsStream("newCivic.properties");
        Properties prop = new Properties();
        try {
            prop.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
