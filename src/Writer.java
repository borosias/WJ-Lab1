import lombok.Setter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Writer extends Thread{

    private List<String> resWords;
    public void writer() throws IOException {
        int count = 0;
        if (resWords.isEmpty()){
            resWords.add("Words not found!");
        } else {
            count = resWords.size();
        }
        String res = String.join(", ", resWords).replaceAll("", "") + "\n" + count + "\n=====================\n";
        Path filePath = Paths.get("C:\\Users\\Bohdan\\Desktop\\test\\result.txt");
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        } else {
            try {
                Files.write(Paths.get(filePath.toUri()), res.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(e);
            }
            List<String> lines;
            lines = Files.readAllLines(filePath, Charset.forName("windows-1251"));
            for (String s : lines) {
                System.out.println(s);
            }
        }
    }

    public void setResWords(List<String> resWords) {
        this.resWords = resWords;
    }

    @SneakyThrows
    @Override
    public void run() {
        writer();
    }


}
