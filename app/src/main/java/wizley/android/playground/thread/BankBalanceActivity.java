package wizley.android.playground.thread;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BankBalanceActivity extends AppCompatActivity {
    private static final String TAG = "BankBalanceActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ATM atm = new ATM();
        Thread t1 = new Thread(atm, "t1");
        Thread t2 = new Thread(atm, "t2");

        t1.start();
        t2.start();
    }

    class Account {
        int balance = 1000;

        public synchronized void withDraw(int money){
            if(balance >= money){
                try {
                    wait();
                } catch (Exception e){
                    e.printStackTrace();
                }
                balance -= money;
            }
        }

        public synchronized void deposit(int money){
            balance += money;
            notify();
        }
    }

    class ATM implements Runnable{
        Account account = new Account();

        @Override
        public void run() {
            while(account.balance > 0){
                int money = (int) ((Math.random() * 3 + 1) * 100);
                if(Thread.currentThread().getName().equals("t1")) {
                    account.withDraw(money);
                } else {
                    account.deposit(10);
                }
                Log.e(TAG, Thread.currentThread().getName() + " : " + String.valueOf(account.balance));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

