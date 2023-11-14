import CSVServices.CreateCSV;
import CSVServices.DeleteCSV;
import FileService.FileService;
import ReadWriterService.ReadersService;
import ReadWriterService.WritersService;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Main {

    static ArrayList<String> contentFile = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        DeleteCSV.csvDelete("data");
        CreateCSV csv = new CreateCSV("data");


        //Salva os dados do arquivo txt em uma lista, onde cada indice contém uma palavra (com pontuação satélite, quando houver)
        contentFile = FileService.readContentFile("bd.txt");

        int readersQuantity, writersQuantity, loopController;

        readersQuantity = 100;
        writersQuantity = 0;
        loopController = 1;

        while(loopController <= 101) {

            int executions = 0;
            int sumTimes = 0;

            while(executions<50) {

                //define um contador das threads
                CountDownLatch latch = new CountDownLatch(100);

                //cria leitores e escritores de acordo com a proporção determinada em determinado instante
                ArrayList<ReadersService> readers = ReadersService.createReaders(readersQuantity, contentFile);
                ArrayList<WritersService> writers = WritersService.createWriters(writersQuantity, contentFile);

                //Cria um arranjo de threads
                Thread[] threadsList = new Thread[100];

                //cria um conjunto para rastrear posições que já estejam ocupadas no arranjo
                Set<Integer> busyPositions = new HashSet<>();


                Random random = new Random();

                int randomIndex;

                //Adiciona os readers e writers ao array de threads

                for (ReadersService readerInArray : readers) {

                    //Procura um espaço aleatório do array que esteja desocupado
                    do {
                        randomIndex = random.nextInt(100);
                    } while (busyPositions.contains(randomIndex));
                    //Adiciona o elemento ao array
                    threadsList[randomIndex] = new Thread(readerInArray);
                    busyPositions.add(randomIndex);

                }

                for (WritersService writerInArray : writers) {

                    //Procura um espaço aleatório do array que esteja desocupado
                    do {
                        randomIndex = random.nextInt(100);
                    } while (busyPositions.contains(randomIndex));
                    //Adiciona o elemento ao array
                    threadsList[randomIndex] = new Thread(writerInArray);
                    //adiciona o index ao conjunto de ocupados
                    busyPositions.add(randomIndex);

                }


                long timeStart = System.currentTimeMillis();
                for (Thread threadInArray : threadsList) {
                    threadInArray.start();
                }

                for (Thread threadInArray : threadsList) {
                    threadInArray.join();
                }

                long timeTotal = System.currentTimeMillis() - timeStart;
                sumTimes+= timeTotal;
                executions++;

            }
            long mean = sumTimes/50;


            System.out.printf("TESTE NÚMERO %d: Numero de Readers: %d ; Numero de Writers: %d -> Tempo médio de execução %d\n", loopController, readersQuantity, writersQuantity, mean);
            csv.csvPrint(readersQuantity, writersQuantity, mean);


            loopController++;
            writersQuantity++;
            readersQuantity--;
        }

    }
}
