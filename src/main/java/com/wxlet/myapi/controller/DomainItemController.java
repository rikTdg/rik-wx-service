package com.wxlet.myapi.controller;

import com.wxlet.myapi.entity.DomainItem;
import com.wxlet.myapi.entity.LatestItemDTO;
import com.wxlet.myapi.service.DomainItemService;
import com.wxlet.myapi.utils.ScrapyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
//@CrossOrigin(origins = {"http://localhost:24802", "http://42.224.224.78:24802"})
public class DomainItemController {
    @Autowired
    private DomainItemService domainItemService;

    @GetMapping("/getall/{page}")
    public Page<DomainItem> getAll(@PathVariable("page") int page) {
        return domainItemService.getPage(page, 10);
    }

    @GetMapping("/getlatest")
    public List<LatestItemDTO> getlatest() {
        return domainItemService.findLatestByDomain();
    }

    @GetMapping("/getdomain/{domain}/{page}")
    public Page<DomainItem> getByDomain(@PathVariable("domain") String domain, @PathVariable("page") int page) {
        return domainItemService.getByDomain(domain, page, 20);
    }

    @GetMapping("/search/{page}")
    public Page<DomainItem> getSearch(@RequestParam String searchValue, @PathVariable("page") int page) {
        return domainItemService.getSearch(searchValue, page, 20);
    }

    @GetMapping("/loaddata/{domain}")
    public String loadItemData(@PathVariable("domain") String domain) {
        return domainItemService.loadItemData(domain);
    }
}
