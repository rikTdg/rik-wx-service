package com.wxlet.myapi.service;

import com.wxlet.myapi.dao.DomainItemDao;
import com.wxlet.myapi.entity.DomainItem;
import com.wxlet.myapi.entity.LatestItemDTO;
import com.wxlet.myapi.utils.ScrapyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class DomainItemService {
    @Autowired
    private DomainItemDao domainItemDao;

//    public List<DomainItem> getAll(){
//        return domainItemDao.findAll(Sort.by(Sort.Order.desc("id")));
//    }
    public List<LatestItemDTO> findLatestByDomain(){
        return domainItemDao.findLatestByDomain();
    }

    public Page<DomainItem> getPage(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        return domainItemDao.findAll(pageRequest);
    }

    public Page<DomainItem> getByDomain(String domain, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        return domainItemDao.findByDomainContaining(domain, pageRequest);
    }

    public Page<DomainItem> getSearch(String searchValue, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        return domainItemDao.findByTitleContaining(pageRequest, searchValue);
    }

    public String loadItemData(String domain){
        String resultMsg = null;
        switch (domain){
            case "amz":
                resultMsg = ScrapyHelper.runScrapySpider("myspider-amz");
                break;
            case "dd":
                resultMsg = ScrapyHelper.runScrapySpider("myspider-dangdang");
                break;
            default:
                resultMsg = "{'itemCnt': 0}";
        }
        return resultMsg;
    }
}
