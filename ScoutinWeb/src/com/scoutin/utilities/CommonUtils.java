package com.scoutin.utilities;

import java.lang.reflect.Field;
import java.util.Map;

public class CommonUtils {

	public void describe(Map<String,Object> map, Object bean){
		Field[] fields = bean.getClass().getDeclaredFields();
		for(Field field : fields){
			try {
				map.put(field.getName(), field.get(bean));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
}
