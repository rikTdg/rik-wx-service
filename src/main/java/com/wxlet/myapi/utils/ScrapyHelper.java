package com.wxlet.myapi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScrapyHelper {

    public static String runScrapySpider(String spiderName) {
        // 定义 Scrapy 项目的目录
//        String projectPath = "D:\\XJA\\source\\scrapy_wxlet\\scrapy_wxlet";
        String projectPath = "/home/ubuntu/source/scrapy_wxlet/scrapy_wxlet";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("scrapy", "crawl", spiderName);
            System.out.println(projectPath);
            processBuilder.directory(new java.io.File(projectPath));

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("itemCnt")){
                    result.append(line).append("\n");
                    break;
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return result.toString();
            } else {
                return "Scrapy failed with exit code " + exitCode;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error occurred while running Scrapy";
        }
    }
}
