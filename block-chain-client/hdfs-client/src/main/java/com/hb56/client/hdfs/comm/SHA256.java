package com.hb56.client.hdfs.comm;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;


public class SHA256 {

	 public static byte [] hashV2(String filePath) throws IOException, NoSuchAlgorithmException {
		 File file = new File(filePath);
		 BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
         
		 MessageDigest digest = MessageDigest.getInstance("SHA-256");
		 int bufferSize = 16384;
		    byte [] buffer = new byte[bufferSize];
		    int sizeRead = -1;
		    while ((sizeRead = in.read(buffer)) != -1) {
		        digest.update(buffer, 0, sizeRead);
		    }
		    in.close();

		    byte [] hash = null;
		    hash = new byte[digest.getDigestLength()];
		    hash = digest.digest();
		    return hash;
		}

	/**
	 * 大文件计算方法
	 * @param filePathorData
	 * @return
	 */
	 public static String hash(String filePathorData) throws NoSuchAlgorithmException, IOException {

	 	 String rhash = null;
		 if(filePathorData.indexOf("/")>=0 || filePathorData.indexOf("\\")>=0 ) {//判断为输入得是文件
			 RandomAccessFile file = new RandomAccessFile(filePathorData, "r");
			 MessageDigest hashSum = MessageDigest.getInstance("SHA-256");
			 int buff = 16384;
			 byte[] buffer = new byte[buff];
			 byte[] partialHash = null;

			 long read = 0;

			 // calculate the hash of the hole file for the test
			 long offset = file.length();
			 int unitsize;
			 while (read < offset) {
				 unitsize = (int) (((offset - read) >= buff) ? buff : (offset - read));
				 file.read(buffer, 0, unitsize);

				 hashSum.update(buffer, 0, unitsize);

				 read += unitsize;
			 }

			 file.close();
			 partialHash = new byte[hashSum.getDigestLength()];
			 partialHash = hashSum.digest();
			 rhash = bytesToHexString(partialHash);
		 }else {
		// 	byte[] data = hexStringToBytes(filePathorData);
			 byte[] data = filePathorData.getBytes();
			 rhash = bytesToHexString(hash(data));
		 }
		 return rhash;
	    }
	
	 /**
	  * 小文件计算方法
	  * @param data
	  * @return
	  */
	 public static byte[] hash(byte[] data) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");

	            md.update(data);
	            return md.digest();
	        } catch (Exception e) {
	            throw new RuntimeException("Unable to compute hash while signing request: " + e.getMessage(), e);
	        }
	    }



	public static String toHex(byte[] data) {
	        StringBuilder sb = new StringBuilder(data.length * 2);
	        for (int i = 0; i < data.length; i++) {
	            String hex = Integer.toHexString(data[i]);
	            if (hex.length() == 1) {
	                // Append leading zero.
	                sb.append("0");
	            } else if (hex.length() == 8) {
	                // Remove ff prefix from negative numbers.
	                hex = hex.substring(6);
	            }
	            sb.append(hex);
	        }
	        return sb.toString().toLowerCase(Locale.getDefault());
	    }


	public static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}


	public static String bytesToHexString(byte[] src){
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	/**
	 * Convert hex string to byte[]
	 * @param hexString the hex string
	 * @return byte[]
	 */
	public static  byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

}
