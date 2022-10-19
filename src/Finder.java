import lombok.SneakyThrows;

import java.io.File;
import java.util.Objects;


public class Finder extends Thread {

    private File folder;
    private final String initial;
    Reader r = new Reader();

    Finder(File folder, String initial) {
        this.folder = folder;
        this.initial = initial;
        r.setInitial(initial);
    }


    public synchronized void finder(File folder) throws InterruptedException {
        File[] folderEntries = folder.listFiles();
        for (File entry : Objects.requireNonNull(folderEntries)) {
            if (entry.isDirectory()) {
                setFolder(entry);
                Thread subDir =  new Finder(entry,initial);
                subDir.start();
                subDir.join();

            } else if (entry.getName().endsWith("txt")) {
                r.setFile(entry);
                r.start();
            }
        }
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }


    @SneakyThrows
    @Override
    public void run() {
        finder(folder);
    }


}



