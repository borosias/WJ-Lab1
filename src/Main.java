import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<String> resWords = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Writer writer = new Writer();
        Finder f = new Finder(new File("E:\\TestFolder"), "i");
        f.start();
        if (f.isInterrupted()) {
            writer.setResWords(resWords);
            writer.writer();
        }
    }

    public static void adder(List<String> words) {
        resWords.addAll(words);
    }
}

