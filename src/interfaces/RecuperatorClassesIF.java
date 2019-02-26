package interfaces;

import java.lang.reflect.Method;
import java.util.ArrayList;

import exceptions.CodeSelectorExceptions;

public interface RecuperatorClassesIF {

	
	/**
	 * Take the all class from a File
	 * 
	 * @param Class<?> class1
	 * @return boolean
	 */
	public ArrayList<Class<?>> takeTheAllClasses(Class<?> class1) throws CodeSelectorExceptions;
	
	

	/**
	 * Take the all methods
	 * 
	 * @param Class<?> class1
	 * @return boolean
	 */
	public  Method[] takeAllMethod(Class<?> class1) throws CodeSelectorExceptions;
	
	/**
	 * Read the Class and checks the visibilite of the class 
	 * 
	 * @param Class<?> class1
	 * @return boolean
	 */
	public String checksVisibilityClass(Class<?> class1);
	
	/**
	 * Read the class and return the parent 
	 * 
	 * @param Method
	 * @return boolean
	 */
	public String checksTheSuperClass(Class<?> class1);

	public String containsMethod(Class<?> clazz, String methodName);


}
