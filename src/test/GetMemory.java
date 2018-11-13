package test;

public class GetMemory {
	public static void main(String[] args) {
		Runtime currentRuntime=Runtime.getRuntime();
		int nFreeMemory = ( int ) (currentRuntime.freeMemory() / 1024 / 1024);
		int nTotalMemory = ( int ) (currentRuntime.totalMemory() / 1024 / 1024);
		System.out.println("可用内存:" +nFreeMemory+"M");
		System.out.println("总内存:" +nTotalMemory+"M");
		int ncpus = Runtime.getRuntime().availableProcessors();
		System.out.println("cpu的数量:"+ncpus);

}

}