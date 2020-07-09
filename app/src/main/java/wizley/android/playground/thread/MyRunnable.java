package wizley.android.playground.thread;

import android.os.Process;
import android.util.Log;

public class MyRunnable implements Runnable {
    private static final String TAG = "MyRunnable";

    private static final int RUNNING = 0;
    private static final int SUSPENDED = 1;
    private static final int STOPPED = 2;

    private int state = RUNNING;
    Thread th;

    public MyRunnable(String name){
        th = new Thread(this, name);
    }

    private synchronized void setState(int state){
        this.state = state;

        // if state changes from SUSPENDED to RUNNING
        if(state == RUNNING){
            notify();
        } else {
            th.interrupt();
        }
    }

    public synchronized boolean checkState(){
        while(state == SUSPENDED){
            try {
                wait();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return state == STOPPED;
    }

    public void suspend(){
        setState(SUSPENDED);
    }

    public void resume(){
        setState(RUNNING);
    }

    public void stop(){
        setState(STOPPED);
    }

    public void start(){
        th.start();
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        while(true){
            Log.e(TAG, name);

            try{
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }

            if(checkState()){
                // if STOPPED
                break;
            }
        }
    }
}
