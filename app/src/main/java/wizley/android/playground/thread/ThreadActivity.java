package wizley.android.playground.thread;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThreadActivity extends AppCompatActivity {
    private static final String TAG = "ThreadActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ControlThread2();
    }

    private void WaitUntilFinished(){
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        thread2.start();
        long startTime = System.currentTimeMillis();

        try {
            thread1.join();
            thread2.join();
            Log.e(TAG, "join");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "Time : " + (System.currentTimeMillis() - startTime));
    }

    private void OrderExecution() throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        Log.e(TAG, "1 Start");

        thread1.join();
        Log.e(TAG, "1 join");

        thread2.start();
        Log.e(TAG, "2 Start");

        thread2.join();
        Log.e(TAG, "2 join");
    }

    private void ControlThread(){
        Runnable1 runnable1 = new Runnable1();
        Runnable1 runnable2 = new Runnable1();
        Runnable1 runnable3 = new Runnable1();

        Thread th1 = new Thread(runnable1, "*");
        Thread th2 = new Thread(runnable2, "**");
        Thread th3 = new Thread(runnable3, "***");

        try {
            th1.start();
            th2.start();
            th3.start();

            Thread.sleep(5000);
            // suspend is deprecated !!
            runnable1.suspend();
            Log.e(TAG, "th1.suspend");

            Thread.sleep(2000);
            runnable2.suspend();
            Log.e(TAG, "th2.suspend");

            Thread.sleep(3000);
            // resume is deprecated !!
            runnable1.resume();
            Log.e(TAG, "th1 resume");

            Thread.sleep(3000);
            // stop is deprecated !!
            runnable1.stop();
            Log.e(TAG, "th1 stop");
            runnable2.stop();
            Log.e(TAG, "th2 stop");

            Thread.sleep(2000);
            runnable3.stop();
            Log.e(TAG, "th3 stop");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void ControlThread2(){
        Thread3 th1 = new Thread3("*");
        Thread3 th2 = new Thread3("**");
        Thread3 th3 = new Thread3("***");
        th1.start();
        th2.start();
        th3.start();

        try{
            Thread.sleep(2000);
            th1.suspend();
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(2000);
            th1.resume();
            Thread.sleep(2000);
            th1.stop();
            th2.stop();
            Thread.sleep(2000);
            th3.stop();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    class Thread1 extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i=0; i<20; i++) {
                Log.e(TAG, "1");
            }
        }
    }

    class Thread2 extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i=0; i<20; i++) {
                Log.e(TAG, "2");
            }
        }
    }

    class Runnable1 implements Runnable{
        boolean suspended = false;
        boolean stopped = false;

        @Override
        public void run() {
            while(!stopped) {
                if(!suspended) {
                    Log.e(TAG, Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void suspend(){
            suspended = true;
        }

        public void resume(){
            suspended = false;
        }

        public void stop(){
            stopped = true;
        }
    }

    class Thread3 implements Runnable{
        private boolean suspended = false;
        private boolean stopped = false;

        private Thread th;

        Thread3(String name){
            th = new Thread(this, name);
        }

        @Override
        public void run() {
            while (!stopped){
                if(!suspended){
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG, Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Thread.yield();
                }
            }
        }

        public void suspend(){
            suspended = true;
            th.interrupt();
        }

        public void resume(){
            suspended = false;
        }

        public void stop(){
            stopped = true;
            th.interrupt();
        }

        public void start(){
            th.start();
        }
    }
}
