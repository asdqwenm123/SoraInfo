package io.github.asdqwenm123.cenkor;

public class CensorInfo {
    String word;
    int from;
    int to;

    public CensorInfo(String word, int from, int to) {
        this.word = word;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "CensorInfo{" +
                "word='" + word + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}