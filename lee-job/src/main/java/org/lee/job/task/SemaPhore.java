package org.lee.job.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class SemaPhore {
    public static void main(String[] args) {
	//线程池
	ExecutorService exec=Executors.newCachedThreadPool();
	//只能5个线程同时访问
	final Semaphore semp=new Semaphore(5);
	//模拟20个客户端访问
	for(int i=0;i<20;i++){
	    final int NO= i;
	    Runnable run=new Runnable() {
	        
	        @Override
	        public void run() {
	            try {
			//获取许可
			semp.acquire();
			System.out.println("Accessing :"+NO);
			Thread.sleep((long)(Math.random()*6000));
			//访问完后释放
			semp.release();
			//availablePermits()指的是当前信号灯库中有多少个可以被使用
			System.out.println("-------------"+semp.availablePermits());
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
	        }
	    };
	    exec.execute(run);
	}
	exec.shutdown();
	
    }
}
