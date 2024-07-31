package io.github.asdqwenm123.cenkor;


import java.util.List;
import java.util.Map;

public class CensorResult {
    boolean filtered;
    Map<String, List<CensorInfo>> filters;
    String input;

    public CensorResult(boolean filtered, Map<String, List<CensorInfo>> filters, String input) {
        this.filtered = filtered;
        this.filters = filters;
        this.input = input;
    }

    public boolean isFiltered() {
        return filtered;
    }

    @Override
    public String toString() {
        return "CensorResult{" +
                "filtered=" + filtered +
                ", filters=" + filters +
                ", input='" + input + '\'' +
                '}';
    }
}