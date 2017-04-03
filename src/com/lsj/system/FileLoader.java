package com.lsj.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileLoader {
	private final Map<String, String> filemap = new HashMap<String, String>();
	
	public FileLoader(File dir){
		if(!dir.exists()){return ;}
		
		File[] files = dir.listFiles();
		for(File file : files){
			if(file.isDirectory()){continue;}
			try {
				String content = file2string(file);
				System.out.println(content);
				filemap.put(file.getName(), content);
			} catch (IOException e) {}
		}
	}
	
	public String get(String file){
		return filemap.get(file);
	}
	
	private String file2string(File file) throws IOException{
		InputStream is = new FileInputStream(file);
		StringBuilder sb = new StringBuilder();
		byte[] bytes = new byte[1024];
		int length = 0;
		while((length=is.read(bytes)) != -1){
			sb.append(new String(bytes, 0, length, "utf-8"));
		}
		is.close();
		return sb.toString();
	}
}
