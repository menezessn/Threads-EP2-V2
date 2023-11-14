package ReadWriterService;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class WritersService implements Runnable{

    private ArrayList<String> pointContentFile;

    public WritersService(ArrayList<String> contentFileBase){
        this.pointContentFile = contentFileBase;
    }

    public void run() {
        try {
            ReaderWriterService.Lock.acquire();
            for (int i = 0; i < 100; i++) {
                Random random = new Random();
                int randomPositionNumber = random.nextInt(100);
                pointContentFile.set(randomPositionNumber, "MODIFICADO");
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ReaderWriterService.Lock.release();
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<WritersService> createWriters(int quantity, ArrayList<String> criticBase){
        ArrayList<WritersService> writersList = new ArrayList<WritersService>();
        for(int i = 0; i < quantity; i++){
            writersList.add(new WritersService(criticBase));
        }
        return writersList;
    }
}
