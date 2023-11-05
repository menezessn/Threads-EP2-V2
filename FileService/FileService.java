package FileService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileService{

    public static ArrayList<String> readContentFile(String path) throws FileNotFoundException {

        ArrayList<String> contentFile = new ArrayList<String>();

        String lineInFile;
        boolean fileFinish = false;
        BufferedReader bf = new BufferedReader(new FileReader(path));

        try {
            while (!fileFinish) {
                lineInFile = bf.readLine();
                if (lineInFile != null) {
                    contentFile.add(lineInFile);
                } else {
                    fileFinish = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contentFile;
    }
}

