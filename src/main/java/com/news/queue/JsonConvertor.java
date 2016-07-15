package com.news.queue;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.domain.Feed;

public class JsonConvertor {

    public  static String convertToJson(Feed feed) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(feed);
        } catch (Exception e) {
            System.out.println("Error occurred during Json conversion ");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
