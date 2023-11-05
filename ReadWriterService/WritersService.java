package ReadWriterService;

import java.util.ArrayList;
import java.util.Random;

public class WritersService implements Runnable{

    private ArrayList<String> pointContentFile;

    public WritersService(ArrayList<String> contentFileBase){
        this.pointContentFile = contentFileBase;
    }

    public void run(){
        Random random = new Random();
        int randomPositionNumber = random.nextInt(100);
        pointContentFile.set(randomPositionNumber, "MODIFICADO");
    }

    public static ArrayList<WritersService> createWriters(int quantity, ArrayList<String> criticBase){
        ArrayList<WritersService> writersList = new ArrayList<WritersService>();
        for(int i = 0; i < quantity; i++){
            writersList.add(new WritersService(criticBase));
        }
        return writersList;
    }
}
