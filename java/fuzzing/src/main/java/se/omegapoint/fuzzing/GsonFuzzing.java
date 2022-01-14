package se.omegapoint.fuzzing;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonFuzzing {

    static class BagOfPrimitives {
        public int value1 = 1;
        public String value2 = "abc";
        public transient int value3 = 3;
        BagOfPrimitives() {
            // no-args constructor
        }
    }

    public static void fuzzerTestOneInput(com.code_intelligence.jazzer.api.FuzzedDataProvider data) {
        parse(data.consumeRemainingAsString());
    }

    public static void parse(String input) {
        Gson gson = new Gson();
        try {
            BagOfPrimitives obj2 = gson.fromJson(input, BagOfPrimitives.class);
            if (obj2 != null) {
                System.out.println(obj2.value1);
                System.out.println(obj2.value2);
                System.out.println(obj2.value3);
            }

        } catch (JsonSyntaxException ignored) {

        }

    }

    public static void main(String [] args) {
        parse("{\"value1\":1,\"value2\":\"abc\"}");
    }

}
