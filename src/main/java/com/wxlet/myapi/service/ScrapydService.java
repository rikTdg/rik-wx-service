package com.wxlet.myapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScrapydService {

    private final RestTemplate restTemplate;

    public ScrapydService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String listProjects() {
        String url = "http://localhost:6800/listprojects.json";  // Scrapyd 的 API URL
        return restTemplate.getForObject(url, String.class);
    }

    public String startSpider(String project, String spider) {
        String url = "http://localhost:6800/schedule.json";
        String fullUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("project", project)
                .queryParam("spider", spider)
                .toUriString();

        String response = restTemplate.postForObject(fullUrl, null, String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> responseMap = mapper.readValue(response, Map.class);
            String jobId = responseMap.get("jobid");

            while (true){
                String statusStr = this.getJobStatus(jobId);
                Map<String, String> statusMap = mapper.readValue(statusStr, Map.class);
                if("finished".equals(statusMap.get("currstate"))){
                    break;
                }
                Thread.sleep(2000);
            }

            // 查询日志
            String logUrl = String.format("http://localhost:6800/logs/%s/%s/%s.log", project, spider, jobId);

            String logResponse = restTemplate.getForObject(logUrl, String.class);
            String itemCntStr = Arrays.stream(logResponse.split("\n"))
                    .filter(line -> line.contains("item_scraped_count")).findFirst().get();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(itemCntStr);
            if(matcher.find()){
                responseMap.put("itemCnt", matcher.group());
                response = mapper.writeValueAsString(responseMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public String getJobStatus(String job) {
        String url = "http://localhost:6800/status.json";
        return restTemplate.getForObject(url + "?job=" + job, String.class);
    }
}

