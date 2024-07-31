package io.github.asdqwenm123.cenkor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.asdqwenm123.SoraInfo;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCensor {
    private Map<String, Category> censorData;
    private SoraInfo plugin;

    public TextCensor(SoraInfo plugin) {
        this.plugin = plugin;
        try (InputStreamReader reader = new InputStreamReader(plugin.getResource("censor.json"))) {
            Type type = new TypeToken<Map<String, Category>>(){}.getType();
            censorData = new Gson().fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CensorResult censorText(String text) {
        boolean filtered = false;
        Map<String, List<CensorInfo>> filters = new HashMap<>();
        String input = text.toLowerCase();

        for (String categoryName : censorData.keySet()) {
            Category category = censorData.get(categoryName);

            if (category.excludes != null) {
                for (String exclude : category.excludes) {
                    input = input.replace(exclude, "");
                }
            }

            for (String word : category.words) {
                Pattern pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(input);
                List<CensorInfo> censorInfos = new ArrayList<>();

                while (matcher.find()) {
                    filtered = true;
                    String foundWord = text.substring(matcher.start(), matcher.end());
                    censorInfos.add(new CensorInfo(foundWord, matcher.start(), matcher.end() - 1));
                }

                if (!censorInfos.isEmpty()) {
                    filters.put(categoryName, censorInfos);
                }

                input = input.replaceAll("(?i)" + word, "");
            }
        }

        return new CensorResult(filtered, filters, input);
    }

}
