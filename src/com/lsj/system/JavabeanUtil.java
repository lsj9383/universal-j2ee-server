package com.lsj.system;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;

/**
 * javabean对象的赋值
 * @author lsj
 *
 */

public class JavabeanUtil{
	//将Map数据加载到javabean中，javabean支持的类型有String Integer Double Float int double float Date
	static public void loadByMap(Object jb, Map<String, String> propertiesMap) throws IllegalArgumentException, IllegalAccessException, ParseException{
		Class<?> jbClass = jb.getClass();
		Field[] fields = jbClass.getDeclaredFields();
		for(Field field : fields){
			String value = propertiesMap.get(field.getName());
			if(value != null){
				field.setAccessible(true);
				try{
					if(field.getType().equals(String.class)){
						field.set(jb, value);
					}else if(field.getType().equals(int.class)){
						field.set(jb, Integer.parseInt(value));
					}else if(field.getType().equals(double.class)){
						field.set(jb, Double.parseDouble(value));
					}else if(field.getType().equals(float.class)){
						field.set(jb, Float.parseFloat(value));
					}else if(field.getType().equals(Integer.class)){
						field.set(jb, Integer.parseInt(value));
					}else if(field.getType().equals(Double.class)){
						field.set(jb, Double.parseDouble(value));
					}else if(field.getType().equals(Float.class)){
						field.set(jb, Float.parseFloat(value));
					}else if(field.getType().equals(Date.class)){
						SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
						field.set(jb, format.parse(value));
					}
				}catch(Exception e){
					field.set(jb, null);
				}
			}
		}
	}
	
	static public void loadBySqlResultMap(Object jb, Map<String, Object> sqlMap) throws IllegalArgumentException, IllegalAccessException, ParseException{
		Map<String, String> propertiesMap = new HashMap<String, String>();
		for(Entry<String, Object> kv : sqlMap.entrySet()){
			Object value = kv.getValue();
			if(value == null){
				propertiesMap.put(sqlField2JavabeanField(kv.getKey()), null);
			}else{
				if(value.getClass().equals(Timestamp.class)){
					String dateValue = value.toString();
					String[] parts = dateValue.split(" ");
					propertiesMap.put(sqlField2JavabeanField(kv.getKey()), parts[0]);
				}else{
					propertiesMap.put(sqlField2JavabeanField(kv.getKey()), kv.getValue().toString());	
				}					
			}
		}
		loadByMap(jb, propertiesMap);
	}
	
	//从servlet请求中加载其中的参数到javabean中
	static public void loadByRequest(Object jb, ServletRequest request) throws IllegalArgumentException, IllegalAccessException, ParseException{
		Map<String, String> propertiesMap = new HashMap<String, String>();
		Map<String, String[]> paramMap = request.getParameterMap();
		for(Entry<String, String[]> param : paramMap.entrySet()){
			propertiesMap.put(param.getKey(), param.getValue()[0]);
		}
		loadByMap(jb, propertiesMap);
	}
	
	static public String sqlField2JavabeanField(String sqlFieldName){
		String javabeanFieldName = new String();
		String[] parts = sqlFieldName.split("_");
		javabeanFieldName = parts[0];
		for(int i=1; i<parts.length; i++){
			byte[] bytes = parts[i].getBytes();
			bytes[0]-=32;
			javabeanFieldName += new String(bytes);
		}
		return javabeanFieldName;
	}
}
