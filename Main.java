import FileService.FileService;
import ReadWriterService.ReadersService;
import ReadWriterService.WritersService;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    static ArrayList<String> contentFile = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        contentFile = FileService.readContentFile("bd.txt");

        // Teste inicial: 99 Leitores e 1 Escritor
        ArrayList<ReadersService> readers = ReadersService.createReaders(99, contentFile);
        ArrayList<WritersService> writers = WritersService.createWriters(1, contentFile);

        ArrayList<Thread> threadsList = new ArrayList<Thread>();
        for(int i = 0; i < readers.size(); i ++){
            threadsList.add(new Thread(readers.get(i)));
        }

        for(int i = 0; i < writers.size(); i++){
            threadsList.add(new Thread(writers.get(i)));
        }

        for(int i = 0; i < threadsList.size(); i++){
            threadsList.get(i).start();
        }
        int lastThread = threadsList.size() - 1;
        threadsList.get(lastThread).join();

    }
}
