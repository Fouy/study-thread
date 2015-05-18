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
//							System.out.println("��ȡ����" + btImg.length + " �ֽ�");
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
		
		System.out.println("��д���");
		
	}
	
	public static synchronized String getPath() {
		return lists.remove(0);
	}
	
	
	/**
	 * ��ͼƬд�뵽����
	 * @param img ͼƬ������
	 * @param fileName �ļ�����ʱ������
	 */
	public static void writeImageToDisk(byte[] img, String fileName){
		try {
			File file = new File("D:/picture/" + fileName);
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
//			System.out.println("ͼƬ�Ѿ�д�뵽C��");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ���ݵ�ַ������ݵ��ֽ���
	 * @param strUrl �������ӵ�ַ
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl){
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(200 * 1000);
			InputStream inStream = conn.getInputStream();//ͨ����������ȡͼƬ����
			byte[] btImg = readInputStream(inStream);//�õ�ͼƬ�Ķ���������
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ���������л�ȡ����
	 * @param inStream ������
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