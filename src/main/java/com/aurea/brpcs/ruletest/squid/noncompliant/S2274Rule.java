package com.aurea.brpcs.ruletest.squid.noncompliant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class S2274Rule {

    public void waitTest() throws InterruptedException {
        Integer obj = new Integer(1);
        synchronized (obj) {
            if (obj != 2) {
                obj.wait(1000); // the thread can wakeup whereas the condition
                // is still false
            }
        }
    }

    public void waitTestFor() throws InterruptedException {
        Integer obj = 1;
        synchronized (new Object()) {
            for (int x = 0; x < 100; x++) {
                obj++;
                obj.wait(1000);
            }
        }
    }

    public void testUtilCondition() throws InterruptedException {
        Condition notFull = new ReentrantLock().newCondition();
        notFull.await(1, TimeUnit.MINUTES);
    }
}
