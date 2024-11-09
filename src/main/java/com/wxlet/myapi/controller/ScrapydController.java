package com.wxlet.myapi.controller;

import com.wxlet.myapi.service.ScrapydService;
import com.wxlet.myapi.utils.ScrapyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capture")
//@CrossOrigin(origins = {"http://localhost:24802", "http://42.224.224.78:24802"})
public class ScrapydController {

    @Autowired
    private ScrapydService scrapydService;


    @GetMapping("/list")
    public String getProjects() {
        return scrapydService.listProjects();
    }

    @GetMapping("/item/start/{domain}")
    public String startSpider(@PathVariable("domain") String domain) {
        String spider = "";
        switch (domain){
            case "amz":
                spider = "myspider-amz";
                break;
            case "dd":
                spider = "myspider-dangdang";
                break;
            default:
                return "{'itemCnt': 0}";
        }
        return scrapydService.startSpider("scrapy_wxlet", spider);
    }

    // 获取爬虫状态的 API
    @GetMapping("/status/{jobid}")
    public String getJobStatus(@PathVariable("jobid") String jobid) {
        return scrapydService.getJobStatus(jobid);
    }
}
