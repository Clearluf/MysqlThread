package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;


public class myCall implements Callable<Object> {
	public int doTime;
	ArrayList<Long> timeList=new ArrayList<>();
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		int count=0;
		while(count<doTime) {
			String sql="insert into user(name,age,sex,info) values(?,?,?,?)";
			Conn con=new Conn();
			
			Date d1=new Date();
			Connection mc=con.init();			
			PreparedStatement ps=mc.prepareStatement(sql);
			ps.setString(1, "Joe");
			ps.setInt(2, 18);
			ps.setString(3, "male");
			ps.setString(4, "blabla");			
			ps.execute();
			System.out.println("插入数据："+ps.execute());
			ps.close();
			mc.close();
			Date d2=new Date();
			
			long oneTime=(d2.getTime()-d1.getTime());
			timeList.add(oneTime);
			System.out.println("本次耗时："+oneTime);
			count++;
			Thread.sleep(1000);
			
			
		}
		long totaltime=0;
		for(long l:timeList) {
			totaltime+=l;
		}
		
		
		return totaltime/doTime;
	}
}
