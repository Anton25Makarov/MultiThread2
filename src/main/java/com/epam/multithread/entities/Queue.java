package com.epam.multithread.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    private static final int WAITING_TIME = 5;
    private static Queue instance;
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static Lock initializeLock = new ReentrantLock();
    private Lock pushLock = new ReentrantLock();
    private Lock pullLock = new ReentrantLock();
    private Condition condition = pullLock.newCondition();
    private List<Train> trains = new ArrayList<>();
    private AtomicInteger size = new AtomicInteger(0);

    private Queue() {
    }

    public static Queue getInstance() {
        if (!initialized.get()) {
            try {
                initializeLock.lock();
                if (!initialized.get()) {
                    instance = new Queue();
                    initialized.set(true);
                }
            } finally {
                initializeLock.unlock();
            }
        }
        return instance;
    }

    public void push(Train train) {
        pushLock.lock();
        try {
            trains.add(train);
            size.incrementAndGet();
            signalNotEmpty();
        } finally {
            pushLock.unlock();
        }
    }

    public Train pull() {
        pullLock.lock();
        try {
            while (size.get() == 0) {
                if (!condition.await(WAITING_TIME, TimeUnit.SECONDS)) {
                    return null;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pullLock.unlock();
        }
        Train train = trains.get(0);
        trains.remove(0);
        size.decrementAndGet();
        return train;
    }

    private void signalNotEmpty() {
        final Lock pullLock = this.pullLock;
        pullLock.lock();
        try {
            condition.signalAll();
        } finally {
            pullLock.unlock();
        }
    }
}
