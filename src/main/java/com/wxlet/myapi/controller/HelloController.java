package com.wxlet.myapi.controller;

import com.wxlet.myapi.entity.DomainItem;
import com.wxlet.myapi.service.DomainItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private DomainItemService domainItemService;

    @GetMapping
    public String getAll() {
        return "Hello api...";
    }


}
