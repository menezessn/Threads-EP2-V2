import FileService.FileService;
import ReadWriterService.ReadersService;
import ReadWriterService.WritersService;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Main {

    static ArrayList<String> contentFile = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        contentFile = FileService.readContentFile("bd.txt");

        int readersQuantity, writersQuantity, loopController;

        readersQuantity = 99;
        writersQuantity = 1;
        loopController = 0;

        while(loopController < 99) {

            CountDownLatch latch = new CountDownLatch(100);

            ArrayList<ReadersService> readers = ReadersService.createReaders(readersQuantity, contentFile);
            ArrayList<WritersService> writers = WritersService.createWriters(writersQuantity, contentFile);

            ArrayList<Thread> threadsList = new ArrayList<Thread>();

            for(ReadersService readerInArray: readers){
                threadsList.add(new Thread(readerInArray));
            }
            for(WritersService writerInArray: writers){
                threadsList.add(new Thread(writerInArray));
            }


            long timeStart = System.currentTimeMillis();
            for(Thread threadInArray: threadsList){
                threadInArray.start();
            }

            for(Thread threadInArray: threadsList){
                threadInArray.join();
            }

            long timeTotal = System.currentTimeMillis() - timeStart;

            System.out.printf("TESTE NÚMERO %d: Numero de Readers: %d ; Numero de Writers: %d -> Tempo total de execução %d\n", loopController, readersQuantity, writersQuantity, timeTotal);

            loopController++;
            writersQuantity++;
            readersQuantity--;
        }

    }
}
