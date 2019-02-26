package code_IMP;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.designwizard.design.MethodNode;

import exceptions.CodeSelectorExceptions;
import interfaces.RecuperatorMethodIF;

public class RecuperatorMethodIMP implements RecuperatorMethodIF {

	Modificadores mod = new Modificadores();
	static int count = 0;
	static ArrayList<MethodNode> setFinal;
	static int metodos = 0;
	static int metodosPrivates = 0;
	static String nome = "";

	@Override
	public String checksVisibilityMethod(Method meth) {

		Method reflectMethod = meth;

		String methoName = reflectMethod.getName();

		/*
		 * Know if the visibility of class is: public, private or protected.
		 * But, has any others types, for example: isAbstract, isFinal,
		 * isInterface, isStatic, isStrict, isSynchronized, isVolatile
		 */
		int classModifier = reflectMethod.getModifiers();

		return mod.modificadores(classModifier);
	}

	public ArrayList<Parameter> checksAttributesOfMethods(Method meth) {
		// Take the parameters
		Parameter[] methodParameters = meth.getParameters();
		ArrayList<Parameter> att2 = new ArrayList<>();
		String att = "";
		for (Parameter parameter : methodParameters) {
			att = parameter.toString();
			att2.add(parameter);
		}

		return att2;
	}

	@Override
	public String checksTheReturnOfMethods(Method meth) {
		Class<?> returnMethod = meth.getReturnType();
		String att = returnMethod.getName();
		return att;
	}

	@Override
	public String checksIfMethodCallSomeone(Method meth) {
		return null;
	}

	public static String containsMethod(Class<?> clazz, String methodName) {
		try {
			Method a = clazz.getMethod("get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1));
			return a.getName();
		} catch (NoSuchMethodException e) {
			return "Nenhum";
		}
	}

	// public String verifyIfTheMethodPrivateIsCalled(Method meth, Method[]
	// methds) {
	//
	// return "===> ESTE MÉTODO NÃO É NEM PRIVATE, NEM PROTECTED";
	//
	// }

	/**
	 * (byte, short, int, long, float, double, char or boolean.). Besides thats,
	 * String.
	 */
	@Override
	public ArrayList<String> checksAttributePrimitive(Method method) throws CodeSelectorExceptions {
		Parameter[] parametros = method.getParameters();
		ArrayList<String> atributesTypes = new ArrayList<>();

		for (Parameter parameter : parametros) {
			if (!parameter.toString().contains(Constant.bytee) && !parameter.toString().contains(Constant.shortt)
					&& !parameter.toString().contains(Constant.intt) && !parameter.toString().contains(Constant.longg)
					&& !parameter.toString().contains(Constant.floatt) && !parameter.toString().contains(Constant.charr)
					&& !parameter.toString().contains(Constant.booleann)
					&& !parameter.toString().contains(Constant.doublee)
					&& !parameter.toString().contains(Constant.stringg)
					&& !parameter.toString().contains(Constant.voidd)
					&& !parameter.toString().contains(Constant.objct) && !parameter.toString().contains(Constant.VAZIO)) {

				// System.out.println("Os parametros do método: " +
				// method.getName());
				System.out.println("==========> " + " O parametro: " + parameter + ", não é primitivo!" + "\n");
				atributesTypes.add(Constant.PARAM_COMP);
			}
			atributesTypes.add(Constant.PARAM_PRIM);

		}
		atributesTypes.add("Nenhum atributo");
		// }
		return atributesTypes;
	}

	@Override
	public Method[] takeAllMethod(Class<?> class1) {
		return class1.getDeclaredMethods();
	}

	public static Set<MethodNode> getCallersMethodoNode(MethodNode methodNode) {
		return methodNode.getCallerMethods();

	}

	public static String checksVisibilityMethod(MethodNode methodNode) {

		MethodNode reflectMethod = methodNode;

		String methoName = reflectMethod.getName();

		/**
		 * Know if the visibility of class is: public, private or protected.
		 * But, has any others types, for example: isAbstract, isFinal,
		 * isInterface, isStatic, isStrict, isSynchronized, isVolatile
		 */
		Collection<org.designwizard.design.Modifier> classModifier = reflectMethod.getModifiers();
		return classModifier.toString();
	}

	public String getNameMethod(MethodNode methodNode) {
		return methodNode.getName();
	}

	public String checksReturnPrimitive(Method method) {
		String param = checksTheReturnOfMethods(method);
		if (!param.toString().contains(Constant.bytee) && !param.toString().contains(Constant.shortt)
				&& !param.toString().contains(Constant.intt) && !param.toString().contains(Constant.longg)
				&& !param.toString().contains(Constant.floatt) && !param.toString().contains(Constant.charr)
				&& !param.toString().contains(Constant.booleann) && !param.toString().contains(Constant.doublee)
				&& !param.toString().contains(Constant.stringg) && !param.toString().contains(Constant.voidd)
				&& !param.toString().contains(Constant.objct)&& !param.toString().contains(Constant.VAZIO)) {

			System.out.println("==========> " + " O tipo do retorno: " + param + ", não é primitivo!" + "\n");
			return Constant.PARAM_COMP;
		}
		System.out.println("==========> " + " O tipo do retorno: " + param + ", é primitivo!" + "\n");

		return Constant.PARAM_PRIM;

	}

}