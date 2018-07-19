package cn.ubibi.jettyboot.demotest.core;

import java.lang.reflect.Method;

public class ClassMethodFindUtils {
	
	public static Method findMethod(Object target, String methodName, Object ...params) {
		Class clazz = target.getClass();
		Method[] methods = clazz.getMethods();
		//Method methodInvoke = clazz.getMethod(method, paramsType); -- 不能用这个方法的原因是 int 和 Integer不是一个Class
		Method methodInvoke = null;
		for(Method method : methods) {
			if(method.getName().equals(methodName) && method.getParameterTypes().length == params.length) {
			    Class<?>[] paramterTypes = method.getParameterTypes();
			    boolean equal = true;
			    for(int i = 0; i < paramterTypes.length; i ++) {
			    	boolean equalInner = typeEquals(paramterTypes[i], params[i]);
			    	if(!equalInner) {
			    		equal = false;
			    		break;
			    	}
			    }
			    if(equal) {
			    	methodInvoke = method;
			    	break;
			    }
			}
		}
		return methodInvoke;
	}

	public static boolean typeEquals(Class paramterType, Object object) {
		Class clazz = object.getClass();
		if(paramterType.equals(clazz)) {
			return true;
		}
		if(paramterType.isAssignableFrom(object.getClass())) {
			return true;
		}
		if(clazz.equals(Integer.class) && paramterType.equals(int.class)) {
			return true;
		}
		if(clazz.equals(Long.class) && paramterType.equals(long.class)) {
			return true;
		}
		if(clazz.equals(Byte.class) && paramterType.equals(byte.class)) {
			return true;
		}
		if(clazz.equals(Short.class) && paramterType.equals(short.class)) {
			return true;
		}
		if(clazz.equals(Boolean.class) && paramterType.equals(boolean.class)) {
			return true;
		}
		if(clazz.equals(Character.class) && paramterType.equals(char.class)) {
			return true;
		}
		if(clazz.equals(Float.class) && paramterType.equals(float.class)) {
			return true;
		}
		if(clazz.equals(Double.class) && paramterType.equals(double.class)) {
			return true;
		}
		return false;
	}
}
