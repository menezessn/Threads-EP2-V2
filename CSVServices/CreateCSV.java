package CSVServices;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateCSV {
    String fileAdress;
    BufferedWriter writer;
    public CreateCSV(String name) {
        this.fileAdress = "CSV/" + name + ".csv";

        try {
            FileWriter fileWriter = new FileWriter(new File(this.fileAdress), true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            // Escrever cabeçalho
            writer.write("N° Readers, N° Writers, Média de tempo de execução");
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo CSV: " + e.getMessage());
        }

    }

    public void csvPrint(int nReaders, int nWriters, long media){

        try {
            // Escrever dados
            FileWriter fileWriter = new FileWriter(new File(this.fileAdress), true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(nReaders + "," + nWriters + "," + media);
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo CSV: " + e.getMessage());
        }
    }
}
