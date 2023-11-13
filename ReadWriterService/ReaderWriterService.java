package ReadWriterService;

import java.util.concurrent.Semaphore;

public class ReaderWriterService {
    public static Semaphore readLock = new Semaphore(1);
    public static Semaphore writeLock = new Semaphore(1);
    static int readCount = 0;


}
