package com.zx.test.day02;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * created by zengqintao on 2018-07-02 14:33 .
 **/
public class BBS_SolrConnection {
    private static Logger log = LoggerFactory.getLogger(BBS_SolrConnection.class);
    private static Properties proKit = prop("boolean.properties");

    public static void main(String[] args) {
        String Weburl = "http://bbs.hefei.cc/forum.php?mod=forumdisplay&fid=196&filter=author&orderby=dateline";
        Document doc = null;
        Elements elemts = null;
        Collection<SolrInputDocument> coll = new ArrayList<>();
        try {
            doc = Jsoup.connect(Weburl).get();
            elemts = doc.select("#moderate table .xst");
            for (int i = 5; i < elemts.size(); i++) {
                String url = elemts.get(i).attr("href");
                doc = Jsoup.connect(url).get();
                SolrInputDocument document = new SolrInputDocument();
                String id = getMD5(url);
                String title = elemts.get(i).text();
                String content = doc.select("#postlist .t_fsz").text();
                String cat = doc.select("#ct .hm span:first-child").text();  //发布时间
                SolrInputDocument inputDocument = new SolrInputDocument();
                inputDocument.addField("id", id);
                inputDocument.addField("title", title);
                inputDocument.addField("content", content);
                inputDocument.addField("cat", cat);
                coll.add(inputDocument);
                log.info("{}已被存入", title);
            }
            saveSolr(coll);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("出现错误！==============");
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("空指针异常---------------");
        }
    }

    @Test
    public static void saveSolr(Collection<SolrInputDocument> collection) {
        String isTrue = proKit.getProperty("isTrue");
        if (isTrue.equals("true")) {
            String zkHost = "192.168.80.130:2181,192.168.80.130:2182,192.168.80.130:2183";
            CloudSolrServer solrServer = new CloudSolrServer(zkHost);
            solrServer.setDefaultCollection("JunitConn");
            try {
                solrServer.add(collection);
                solrServer.commit();
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未存入！-----------------------------------");
        }
    }

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Properties prop(String filename) {
        InputStream is = BBS_SolrConnection.class.getClassLoader().getResourceAsStream(filename);
        Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
