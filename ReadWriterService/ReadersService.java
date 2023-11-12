package ReadWriterService;

import java.util.ArrayList;
import java.util.Random;

public class ReadersService implements Runnable{

    ArrayList<String> pointContentFile;

    public ReadersService(ArrayList<String> contentFileBase){
        this.pointContentFile = contentFileBase;
    }

    public void run(){
        String variavel_local;
        for(int i = 0; i < 100; i++) {
            Random random = new Random();
            int randomPositionNumber = random.nextInt(100);
            variavel_local = pointContentFile.get(randomPositionNumber);
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ReadersService> createReaders(int quantity, ArrayList<String> criticBase){
        ArrayList<ReadersService> readersList = new ArrayList<ReadersService>();
        for(int i = 0; i < quantity; i++){
            readersList.add(new ReadersService(criticBase));
        }
        return readersList;
    }
}
