import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Writer extends Thread{

    private List<String> resWords = new ArrayList<>();
    private String filename;

    public void  writer() throws IOException {
        int count = 0;
        if (resWords.isEmpty()){
            resWords.add("Words not found!");
        } else {
            count = resWords.size();
        }
        String res = String.join(", ", resWords) + "\n" + count+"\nIn file:"+filename+"\n=====================\n";
        Path filePath = Paths.get("C:\\Users\\Bohdan\\Desktop\\test\\result.txt");
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        } else {
            try {
                Files.write(Paths.get(filePath.toUri()), res.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        Thread.currentThread().interrupt();
    }

    public void setResWords(List<String> resWords) {
        this.resWords = resWords;
    }
    @SneakyThrows
    @Override
    public void run() {
        writer();
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
