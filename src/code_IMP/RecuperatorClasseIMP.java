package code_IMP;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import exceptions.CodeSelectorExceptions;
import interfaces.RecuperatorClassesIF;
import interfaces.RecuperatorMethodIF;

public class RecuperatorClasseIMP implements RecuperatorClassesIF {

	ArrayList<Class<?>> classes = new ArrayList<>();
	Modificadores mod = new Modificadores();

	@Override
	public ArrayList<Class<?>> takeTheAllClasses(Class<?> class1) throws CodeSelectorExceptions {
		Class<?> class2 = class1;
		classes.add(class2);
		return classes;

	}

	/*
	 * Take the class name
	 */
	@Override
	public String checksVisibilityClass(Class<?> class1) {
		Class reflectClass = class1;

		String className = reflectClass.getName();

		/*
		 * Know if the visibility of class is: public, private or protected.
		 * But, has any others types, for example: isAbstract, isFinal,
		 * isInterface, isStatic, isStrict, isSynchronized, isVolatile
		 */
		int classModifier = reflectClass.getModifiers();

		return mod.modificadores(classModifier);
	}


	/*
	 * Return the Super Class
	 */
	@Override
	public String checksTheSuperClass(Class<?> class1) {
		// Class[] interfaceSuperClasse = class1.getInterfaces();
		if (!class1.isInterface()) {
			Class classSuper = class1.getSuperclass();

			String superClasseName = "";
			superClasseName = classSuper.getName();
			// System.out.println("Super Class: " + classSuper.getName() +
			// "\n");
			return superClasseName;
		}

		return "É " + Constant.INTERFACE;
	}

	@Override
	public String containsMethod(Class<?> clazz, String methodName) {
		try {
			Method a = clazz.getMethod("get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1));
			return a.getName();
		} catch (NoSuchMethodException e) {
			return "Nenhum";
		}
	}

	public String verifyIfTheMethodPrivateIsCalled(Method meth, Method[] methds) {

		return "===> ESTE MÉTODO NÃO FOI IMPLEMENTADO";

	}

	/**
	 * Estudy by here
	 * http://www.java2s.com/Code/Java/Reflection/InvokeamethodusingMethodclass.htm
	 * 
	 * Parece ser muito interessante isso:
	 * http://www.java2s.com/Code/Java/Reflection/Fetchesallmethodsofallaccesstypesfromthesuppliedclassandsuperclasses.htm
	 * 
	 * 
	 * @param method
	 * @param classe
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Method getCallingMethod(Method method, Class<?> classe) throws ClassNotFoundException {

		return null;
	}


	@Override
	public Method[] takeAllMethod(Class<?> class1) throws CodeSelectorExceptions {
		Method[] classMethods = class1.getDeclaredMethods();
		return classMethods;

	}


}
