package com.zx.test.day01;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * created by zengqintao on 2018-06-29 17:13 .
 **/
public class SolrDemo {
    public static void main(String[] args) {
        String zkHost="192.168.80.130:2181,192.168.80.130:2182,192.168.80.130:2183";
        CloudSolrServer solrServer = new CloudSolrServer(zkHost);
        solrServer.setDefaultCollection("collectionDemo");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","19");
        document.addField("name","ming");
        document.addField("title","辅助UZI成为世界冠军");
        try {
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
