package se.omegapoint.fuzzing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;

public class JacksonJsonFuzzing {

    @Data
    public static class MyValue {
        private String name;
        private int age;
    }

    public static void fuzzerTestOneInput(com.code_intelligence.jazzer.api.FuzzedDataProvider data) {
        parse(data.consumeRemainingAsString());
    }

    public static void parse(String input) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            MyValue value = mapper.readValue(input, MyValue.class);
            System.out.println("name: " + value.name + ", age: " + value.age);
        } catch (JsonProcessingException e) {

        }

    }


    public static void main(String [] args) {
        parse("{\"name\":\"Bob\", \"age\":13}");
    }


}
