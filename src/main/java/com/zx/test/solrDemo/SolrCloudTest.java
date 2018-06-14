package com.zx.test.solrDemo;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * created by zengqintao on 2018-06-14 9:40 .
 **/
public class SolrCloudTest {
    @Test
    public void tesAddDocument() throws Exception {
        //创建一个和solr集群的连接
        //参数就是zookeeper的地址列表,使用逗号分隔
        String zkHost = "192.168.80.128:2181,192.168.80.128:2182,192.168.80.128:2183";
        CloudSolrServer solrServer = new CloudSolrServer(zkHost);
        //设置默认的colletion
        solrServer.setDefaultCollection("mycollection1");
        //创建一个文档对象
        SolrInputDocument doc1 = new SolrInputDocument();
        //向文档中添加域
        doc1.addField("id", "1");
        doc1.addField("name", "张民");

        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.addField("id", "2");
        doc2.addField("name", "刘俊");

        SolrInputDocument doc3 = new SolrInputDocument();
        doc3.addField("id", "3");
        doc3.addField("name", "刘俊2");

        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
        SolrInputDocument do4 = new SolrInputDocument();
        do4.addField("id", "4");
        do4.addField("name", "王刚");
        //把文档添加到索引库
        solrServer.add(do4);
        //提交
        solrServer.commit();
    }

    @Test
    public void deleteDocument() throws SolrServerException, IOException {
        //创建一个和solr集群的额连接
        //参数就是zookeeper的地址列表,使用逗号分隔
        String zkHost = "192.168.80.128:2181,192.168.80.128:2182,192.168.80.128:2183";
        CloudSolrServer solrServer = new CloudSolrServer(zkHost);
        //设置默认的collection
        solrServer.setDefaultCollection("mycollection1");
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }

    @Test
    public void search() {
        System.out.println("测试查询query！！！！");
        String zkHost = "192.168.80.128:2181,192.168.80.128:2182,192.168.80.128:2183";
        CloudSolrServer solrServer = new CloudSolrServer(zkHost);
        solrServer.setDefaultCollection("mycollection1");
        String queryValue = "id:*";
        SolrQuery query = new SolrQuery();
        query.setQuery(queryValue);

        try {
            QueryResponse response = solrServer.query(query);
            SolrDocumentList docs = response.getResults();

            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());

            for (SolrDocument doc : docs) {
                String name = (String) doc.getFieldValue("name");
                String id = (String) doc.getFieldValue("id");
                System.out.println("id: " + id);
                System.out.println("name: " + name);
                System.out.println();
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknowned Exception!!!!");
            e.printStackTrace();
        }
    }
}
