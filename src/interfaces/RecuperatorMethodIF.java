package interfaces;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Set;

import org.designwizard.design.MethodNode;

import exceptions.CodeSelectorExceptions;

public interface RecuperatorMethodIF {

	
	
	/**
	 * Read the Method and checks the visibilite of the method 
	 * 
	 * @param Class<?> class1
	 * @return String
	 */
	public String checksVisibilityMethod(Method meth);
	
	
	
	/**
	 * Read the Method and checks the attributes of the method 
	 * 
	 * @param Method
	 * @return boolean
	 */
	public ArrayList<Parameter> checksAttributesOfMethods(Method meth);
	
	

	/**
	 * Read the Method and checks the attributes of the method 
	 * 
	 * @param Method
	 * @return boolean
	 */
	public String checksTheReturnOfMethods(Method meth);
	
	
	/**
	 * Read the Method and checks the attributes of the method 
	 * 
	 * @param Method
	 * @return boolean
	 */
	public String checksIfMethodCallSomeone(Method meth);
	
	/**
	 * Checks if all attributes are of primitive type (byte, short, int, long,
	 * float, double, char or boolean.). Besides thats, String.
	 * 
	 * OBS:. In the return, the boolean represented if all attributes are of
	 * primitives types. And the String will have the names of attributes.
	 * 
	 * @param Class<?> class1
	 * @return ArrayList<String> 
	 * @throws CodeSelectorExceptions
	 */
	public ArrayList<String> checksAttributePrimitive(Method method) throws CodeSelectorExceptions;

	public Method[] takeAllMethod(Class<?> class1);
}
