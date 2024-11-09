package com.wxlet.myapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxlet.myapi.dao.DomainItemDao;
import com.wxlet.myapi.entity.DomainItem;
import com.wxlet.myapi.service.DomainItemService;
import com.wxlet.myapi.utils.ScrapyHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MyapiApplicationTests {
    @Autowired
    private DomainItemDao domainItemDao;
    @Autowired
    private DomainItemService domainItemService;

    @Test
    void contextLoads() {
    }

    @Test
    void getPage() {
        Page<DomainItem> page = domainItemService.getPage(0, 10);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getContent());
    }

    @Test
    void getSearch() {
        Page<DomainItem> page = domainItemService.getSearch("Pro", 0, 10);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getContent().stream().map(DomainItem::getTitle).collect(Collectors.joining("\n")));
    }

    @Test
    void getByDomain() {
        Page<DomainItem> page = domainItemService.getByDomain("amazon", 0, 10);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

    @Test
    void findLatestByDomain() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(domainItemService.findLatestByDomain());
    }


    @Test
    void testScrapy() {
//        String result = ScrapyHelper.runScrapySpider("myspider-amz");
//        System.out.println(result);
    }

}
