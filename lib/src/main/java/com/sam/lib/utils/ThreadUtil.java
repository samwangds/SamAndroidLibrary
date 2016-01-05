package com.sam.lib.utils;

import android.os.AsyncTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author sam
 * @date 2015.09.21
 * @time 11:39
 * @description
 */
public class ThreadUtil {
	
	private ThreadUtil() {}
	
	private static ExecutorService mMoreExecutor = Executors.newCachedThreadPool();

	/**
	 * 单线程排队执行
	 */
	private static ExecutorService mFixedService = Executors.newFixedThreadPool(1);
	
	public static void executeMore(Runnable runnable) {
		mMoreExecutor.execute( runnable );
	}
	
	public static void executeQueue(Runnable runnable) {
		mFixedService.execute( runnable );
	}

	public static void recyleTask(AsyncTask task){
		if(task != null && !task.isCancelled()){
			task.cancel(true);
			task = null;
		}

	}
}
