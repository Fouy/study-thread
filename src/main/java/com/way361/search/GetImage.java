package com.way361.search;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GetImage {
	
	public static final String host = "http://pic.xrui.net";
	
	public static List<String> lists = DataBaseUtils.getPicPath();

	public static void main(String[] args) {
		
		for(int i = 0; i < 20; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(lists.size() > 0){
						String path = getPath();
						byte[] btImg = getImageFromNetByUrl(host + path);
						if(null != btImg && btImg.length > 0){
//							System.out.println("读取到：" + btImg.length + " 字节");
							String fileName = path.substring(path.lastIndexOf('/') + 1).trim();
							writeImageToDisk(btImg, fileName);
						}
					}
				}
			}).start();;
		}
		
		while(lists.size() > 0){
			try {
				Thread.currentThread().sleep(60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("读写完毕");
		
	}
	
	public static synchronized String getPath() {
		return lists.remove(0);
	}
	
	
	/**
	 * 将图片写入到磁盘
	 * @param img 图片数据流
	 * @param fileName 文件保存时的名称
	 */
	public static void writeImageToDisk(byte[] img, String fileName){
		try {
			File file = new File("D:/picture/" + fileName);
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
//			System.out.println("图片已经写入到C盘");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据地址获得数据的字节流
	 * @param strUrl 网络连接地址
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl){
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(200 * 1000);
			InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 从输入流中获取数据
	 * @param inStream 输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len=inStream.read(buffer)) != -1 ){
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}