package com.ops;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class FileCreation extends TimerTask{
	
	//public static Logger log=Logger.getLogger(DemoClass1.class.getName());
	String data="";
	static String fileName="";
	char arr[]=new char[2000];
	boolean append=true;
	
	@Override
	public void run() {
		long currentTime=System.currentTimeMillis();
		long timeMillis=(currentTime-(currentTime%(4*1000)));
		String ConvertedTime=Long.toString(timeMillis);
		fileName="RGF_"+ConvertedTime+".cxmsg";
		System.out.println("currentMillis : "+currentTime);
		System.out.println(timeMillis);
		File f=new File("D:\\files\\"+fileName);
		try {
			if(f.createNewFile()) {
				System.out.println("created file : "+fileName);

			}else {
				System.out.println("File already exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
class SaveLog extends FileCreation{
	//public static Logger log=Logger.getLogger(DemoClass2.class.getName());
	public static Logger log=null;
	static 
	{
		System.setProperty(
			"java.util.logging.SimpleFormatter.format",
			"%5$s %n");
		log=Logger.getLogger(
				SaveLog.class.getName());
	}
	String data="";
	SaveLog(String data){
		this.data=data;
	}
	@Override
	public void run() {
		try {
			FileHandler handler= new FileHandler("D:\\files\\"+fileName,true);
			log.addHandler(handler);
			SimpleFormatter formatter = new SimpleFormatter();
			handler.setFormatter(formatter);	
			log.info(data);
			handler.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
public class POCTask {
	
public static void main(String[] args) throws IOException {
		
		String data="";
		char arr[]=new char[2000];
		//final boolean append=true;
		FileReader fr=new FileReader("D:\\files\\demo.txt");
		BufferedReader input = new BufferedReader(fr);
		input.read(arr);
		data=new String(arr);
		input.close();
		Timer timer=new Timer();
		FileCreation ref1=new FileCreation();
		SaveLog ref2=new SaveLog(data);
		Calendar date=Calendar.getInstance();
		date.set(Calendar.YEAR, 2022);
		date.set(Calendar.MONTH,Calendar.JULY);
		date.set(Calendar.DAY_OF_MONTH,19);
		date.set(Calendar.HOUR_OF_DAY, 14);
		date.set(Calendar.MINUTE,55);
		date.set(Calendar.SECOND,0);
		date.set(Calendar.MILLISECOND, 0);
		//timer.schedule(task1,2000);
		//timer.schedule(task1, date.getTime());
		timer.schedule(ref1,0,10000);
		timer.schedule(ref2,0,2000);
		//timer.schedule(task1, date.getTime(),1000);

	}

}
