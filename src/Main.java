import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Finder f = new Finder(new File("E:\\TestFolder"), "i");
        f.start();
        boolean tmp;
        while(f.isAlive()){
            continue;
        }
        Path filePath = Paths.get("C:\\Users\\Bohdan\\Desktop\\test\\result.txt");
        List<String> lines;
        lines = Files.readAllLines(filePath, Charset.forName("windows-1251"));
        for (String s : lines) {
            System.out.println(s);
        }
    }
}

