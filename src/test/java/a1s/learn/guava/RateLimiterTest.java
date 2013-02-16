/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a1s.learn.guava;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;


public class RateLimiterTest extends TestCase {
	public volatile Integer count = 0;
	public RateLimiter rateLimiter;
	
	public void testRateLimiter() {
		count = 0;
		
		rateLimiter = RateLimiter.create(2);
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		SyncThread sync = new SyncThread(this);
		AsyncThread async = new AsyncThread(this);
		
		pool.submit(sync);
		pool.submit(async);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
			Logger.getLogger(RateLimiterTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		sync.stop=true;
		async.stop = true;
		
		pool.shutdown();
		
		assertEquals(4, count.intValue());
	}
	
	private static class SyncThread implements Runnable{
		public volatile boolean stop = false;
		protected RateLimiterTest test;
		
		public SyncThread(RateLimiterTest test) {
			this.test = test;
		}
		
		@Override
		public void run() {
			while (!stop) {
				test.rateLimiter.acquire();
				
				synchronized(test.count) {
					test.count++;
				}
			}
		}
	}
	
	private static class AsyncThread implements Runnable{
		public volatile boolean stop = false;
		protected RateLimiterTest test;
		
		public AsyncThread(RateLimiterTest test) {
			this.test = test;
		}
		
		@Override
		public void run() {
			while (!stop) {
				if (test.rateLimiter.tryAcquire()) {
					synchronized(test.count) {
						test.count++;
					}
				}
			}
		}
	}
}
