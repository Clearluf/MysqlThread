package test;
/**
NCPU = CPU������
UCPU = ������CPU��ʹ���� 0 �� UCPU �� 1
W/C = �ȴ�ʱ�������ʱ��ı���
���ϣ���������ﵽ�����ʹ���ʣ���ô�̳߳ص����Ŵ�СΪ��
�̳߳ش�С=NCPU *UCPU(1+W/C)
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
		
		//�����̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(ThreadNum);
		//��������з���ֵ������
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
			// ��Future�����ϻ�ȡ����ķ���ֵ
			System.out.println(">>>" + f.get().toString());
		}
	}

}
