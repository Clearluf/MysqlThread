package test;

public class GetMemory {
	public static void main(String[] args) {
		Runtime currentRuntime=Runtime.getRuntime();
		int nFreeMemory = ( int ) (currentRuntime.freeMemory() / 1024 / 1024);
		int nTotalMemory = ( int ) (currentRuntime.totalMemory() / 1024 / 1024);
		System.out.println("�����ڴ�:" +nFreeMemory+"M");
		System.out.println("���ڴ�:" +nTotalMemory+"M");
		int ncpus = Runtime.getRuntime().availableProcessors();
		System.out.println("cpu������:"+ncpus);

}

}