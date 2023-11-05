package ReadWriterService;

import java.util.ArrayList;
import java.util.Random;

public class ReadersService implements Runnable{

    ArrayList<String> pointContentFile;

    public ReadersService(ArrayList<String> contentFileBase){
        this.pointContentFile = contentFileBase;
    }

    public void run(){
        Random random = new Random();
        int randomPositionNumber = random.nextInt(100);
        System.out.println(pointContentFile.get(randomPositionNumber));
    }

    public static ArrayList<ReadersService> createReaders(int quantity, ArrayList<String> criticBase){
        ArrayList<ReadersService> readersList = new ArrayList<ReadersService>();
        for(int i = 0; i < quantity; i++){
            readersList.add(new ReadersService(criticBase));
        }
        return readersList;
    }
}
