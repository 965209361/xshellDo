package com.zx.domain;


import org.apache.solr.client.solrj.beans.Field;

/**
 * created by zengqintao on 2018-06-14 14:55 .
 **/
public class Product {

    /**
     * 商品编号
     */
    @Field
    private String id;

    /**
     * 商品名称
     */
    @Field
    private String p_name;

    /**
     * 商品分类名称
     */
    @Field
    private String p_typeName;

    /**
     * 价格
     */
    @Field
    private Float p_price;

    /**
     * 数量
     */
    @Field
    private Long p_number;

    /**
     * 图片名称
     */
    @Field
    private String p_picture;

    /**
     * 商品描述
     */
    @Field
    private String p_description;

    public void setId(String id) {
        this.id = id;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void setP_typeName(String p_typeName) {
        this.p_typeName = p_typeName;
    }

    public void setP_price(Float p_price) {
        this.p_price = p_price;
    }

    public void setP_number(Long p_number) {
        this.p_number = p_number;
    }

    public void setP_picture(String p_picture) {
        this.p_picture = p_picture;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public String getId() {
        return id;
    }

    public String getP_name() {
        return p_name;
    }

    public String getP_typeName() {
        return p_typeName;
    }

    public Float getP_price() {
        return p_price;
    }

    public Long getP_number() {
        return p_number;
    }

    public String getP_picture() {
        return p_picture;
    }

    public String getP_description() {
        return p_description;
    }
    //空参数构造

    public Product() {
    }
}
