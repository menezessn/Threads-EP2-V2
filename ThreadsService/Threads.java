package ThreadsService;

import java.util.ArrayList;

public class Threads extends Thread {

    ArrayList<Thread> threadsColletion = new ArrayList<>();

    public Threads(){
        for(int i = 0; i < 100; i++){
            threadsColletion.add(new Thread());
        }
    }
}
