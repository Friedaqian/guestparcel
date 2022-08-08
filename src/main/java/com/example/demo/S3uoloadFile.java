package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class S3uoloadFile {

	public static void uploadToS3(String fileName, InputStream fileInputStream) {
		String Path = "C:/Users/qianyj/Pictures/Saved Pictures/";
		File uploadfile = new File(Path + "new_" + fileName + ".jpg");
	
		try(OutputStream os = new FileOutputStream(uploadfile)){
			
			byte[] buffer = new byte[fileInputStream.available()];
		    int bytesRead;
		    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
		        os.write(buffer, 0, bytesRead);
		    }
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("successful");
		
	}

}
