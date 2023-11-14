package CSVServices;

import java.io.File;

public class DeleteCSV {
    public static void csvDelete(String name){
        String fileAdress = "CSV/" + name + ".csv";
        File file = new File(fileAdress);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Arquivo deletado com sucesso.");
            } else {
                System.err.println("Não foi possível deletar o arquivo.");
            }
        } else {
            System.out.println("O arquivo não existe.");
        }
    }
}
