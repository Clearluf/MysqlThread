package test;
/**
NCPU = CPU的数量
UCPU = 期望对CPU的使用率 0 ≤ UCPU ≤ 1
W/C = 等待时间与计算时间的比率
如果希望处理器达到理想的使用率，那么线程池的最优大小为：
线程池大小=NCPU *UCPU(1+W/C)
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import util.myCall;


public class TestMyCall {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		int dotime;
		int ThreadNum;
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		System.out.println("input thread number:");
		ThreadNum=sc.nextInt();
		System.out.println("intput how many times:");
		dotime=sc.nextInt();
		
		//创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(ThreadNum);
		//创建多个有返回值的任务
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		for(int i=0;i<ThreadNum;i++) {
			myCall mc=new myCall();
			mc.doTime=dotime;
				@SuppressWarnings("rawtypes")
				Future f=pool.submit(mc);
				list.add(f);
			
		}
		pool.shutdown();
		for (@SuppressWarnings("rawtypes") Future f : list) {
			// 从Future对象上获取任务的返回值
			System.out.println(">>>" + f.get().toString());
		}
	}

}
