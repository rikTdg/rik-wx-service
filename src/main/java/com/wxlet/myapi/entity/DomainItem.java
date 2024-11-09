package com.wxlet.myapi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "items")
public class DomainItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String price;
    private String imgUrl;
    private String content;
    private String itemTip;
    private String domain;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetimeGen;

    public String getItemTip() {
        return itemTip;
    }

    public void setItemTip(String itemTip) {
        this.itemTip = itemTip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getDatetimeGen() {
        return datetimeGen;
    }

    public void setDatetimeGen(Date datetimeGen) {
        this.datetimeGen = datetimeGen;
    }

    @Override
    public String toString() {
        return "DomainItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", content='" + content + '\'' +
                ", itemTip='" + itemTip + '\'' +
                ", domain='" + domain + '\'' +
                ", datetimeGen=" + datetimeGen +
                '}';
    }
}
