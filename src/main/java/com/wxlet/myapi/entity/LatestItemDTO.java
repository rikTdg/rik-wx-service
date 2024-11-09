package com.wxlet.myapi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

public class LatestItemDTO {
    private String domain;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetimeGen;

    public LatestItemDTO(String domain, Date datetimeGen) {
        this.domain = domain;
        this.datetimeGen = datetimeGen;
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
                "domain='" + domain + '\'' +
                ", datetimeGen=" + datetimeGen +
                '}';
    }
}
