import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Reader extends Thread {

    private File file;
    private String initial;

    Writer w = new Writer();
    List<String> resWords = new ArrayList<>();

    public synchronized void reader() throws IOException {
        List<String> lines;
        lines = Files.readAllLines(getFile().toPath(), Charset.forName("windows-1251"));
        for (String s : lines) {
            String[] words = s.split(" ");
            for (String word : words) {
                if (word.toLowerCase().startsWith(getInitial())) {
                    resWords.add(word);
                }
            }
        }
        w.setResWords(resWords);
        w.start();
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @SneakyThrows
    @Override
    public void run() {
        reader();
    }
}
