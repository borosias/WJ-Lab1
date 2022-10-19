import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Finder extends Thread {

    private File folder;
    private final String initial;

    @SneakyThrows
    public Finder(File folder, String initial) {
        this.folder = folder;
        this.initial = initial;

    }

    public synchronized void finder(File folder) throws InterruptedException {

        File[] folderEntries = folder.listFiles();
        for (File entry : Objects.requireNonNull(folderEntries)) {
            if (entry.isDirectory()) {
                setFolder(entry);
                Thread subDir = new Finder(entry, initial);
                subDir.start();
                subDir.join();

            } else if (entry.getName().endsWith("txt")) {
                Reader r = new Reader();
                r.setInitial(initial);
                r.setFile(entry);
                r.start();
                r.join();

            }

        }
        Thread.currentThread().stop();

    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    @SneakyThrows
    @Override
    public void run() {
        finder(folder);
    }


    static class Reader extends Thread {

        private File file;
        private String initial;

        public synchronized void reader() throws IOException {
            List<String> wordsR = new ArrayList<>();
            List<String> lines;
            lines = Files.readAllLines(getFile().toPath(), Charset.forName("windows-1251"));
            for (String s : lines) {
                String[] words = s.split(" ");
                for (String word : words) {
                    if (word.toLowerCase().startsWith(getInitial().toLowerCase())) {
                        wordsR.add(word);
                    }
                }
            }
            Writer writer = new Writer();
            writer.setResWords(wordsR);
            writer.setFilename(file.getName());
            writer.start();
            Thread.currentThread().stop();
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
}