package cn.ubibi.jettyboot.demotest.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class MethodInvokeCallable implements Callable {
	private static Logger logger = LoggerFactory.getLogger(MethodInvokeCallable.class);

	private Method method;
	private Object[] objects;
	private Object target;
	public MethodInvokeCallable(Object target, String methodName, Object ...params) {
		Method method = ClassMethodFindUtils.findMethod(target, methodName, params);
		if(method == null) {
			logger.error("can not find " + this.toString());
//			throw new Exception("can not find" + target.getClass().getSimpleName()+ "." + methodName);
		}
		this.target = target;
		this.method = method;
		this.objects = params;
	}


	public MethodInvokeCallable(Object target, Method method, Object ...params) {
		this.target = target;
		this.method = method;
		this.objects = params;
	}


	@Override
	public Object call() {
		Object resultObject = null;
		try {
			long start = System.currentTimeMillis();

			if (objects!=null && objects.length > 0){
				resultObject = method.invoke(target, objects);
			}else {
				resultObject = method.invoke(target);
			}


			long end = System.currentTimeMillis();
			String key =  "";
			if(end - start > 10000) {
//            	logger.error(this.toString() + " cost:" + (end-start) + "ms");
            } else if(end - start > 5000) {
//            	logger.info(this.toString() + " cost:" + (end-start) + "ms");
            }
			
		} catch (Exception e) {
			logger.error(this.toString(), e);
		}
		return resultObject;
	}
	public String toString() {
		
		StringBuilder builder = new StringBuilder("class:").append(target.getClass().getSimpleName());
		builder.append(",method:").append(this.method.getName());
		builder.append(",params:");
		if(objects == null || objects.length == 0) {
			builder.append("null");
		} else {
			for(Object object : objects) {
				builder.append(String.valueOf(object)).append(",");
			}
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}
}
