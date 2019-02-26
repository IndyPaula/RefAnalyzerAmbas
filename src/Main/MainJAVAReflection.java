package Main;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import code_IMP.Constant;
import code_IMP.FileUtil;
import code_IMP.RecuperatorClasseIMP;
import code_IMP.RecuperatorMethodIMP;
import code_IMP.ValidadorIMP;
import exceptions.CodeSelectorExceptions;
import interfaces.RecuperatorClassesIF;
import interfaces.RecuperatorMethodIF;

public class MainJAVAReflection {

	static FileUtil fileUtil = new FileUtil();
	static RecuperatorMethodIMP recuperatorMethodIMP = new RecuperatorMethodIMP();
	static RecuperatorClasseIMP recuperatorClasseIMP = new RecuperatorClasseIMP();
	static ValidadorIMP validadorIMP = new ValidadorIMP();
	ArrayList<Class<?>> classes = new ArrayList<>();
	static ArrayList<Method> metodosProblematicos = new ArrayList<>();

	static Class<?> classTeste;

	public static void main(String basePath2) throws CodeSelectorExceptions {
		
		String basePath = basePath2.toString();
		ArrayList<Class<?>> recInfClasses = FileUtil.getClassListFile(basePath);
		Method[] methods = null;
		for (Class<?> class1 : recInfClasses) {

			classTeste = class1;

// ================== SOBRE CLASSE ===================
			
			// Pega a Classe
			System.out.println("==> Classe: " + class1.getName());

			// Pega a visibilidade da classe
			String visibilidade = recuperatorClasseIMP.checksVisibilityClass(class1);
			System.out.println("==> Visibilidade da classe: " + visibilidade);

			// Pega a super classe
			String superClasse = recuperatorClasseIMP.checksTheSuperClass(class1);
			System.out.println("==> Super Classe: " + superClasse + "\n");

			
// ================== SOBRE MÉTODO ===================

			// Pega os metodos
			methods = recuperatorMethodIMP.takeAllMethod(class1);
			getMethods(methods);

			System.out.println("------------------------");


		
		}
//================ Sobre a Análise final ===================
	 	validadorIMP.showAllProblematicMethods(metodosProblematicos);

		
	}

	private static String getMethods(Method[] methods) throws CodeSelectorExceptions {
		String met = "";
		
		
		for (Method method : methods) {
			met = method.getName();
			// Pega a visibilidade do metodo
			String vis = recuperatorMethodIMP.checksVisibilityMethod(method);
			System.out.println("\n" + "==> Nome do Metodo: " + met + "\n" + "==> Visibilidade: " + vis);

			/// Pega os parametros do metodo
			ArrayList<Parameter> att = recuperatorMethodIMP.checksAttributesOfMethods(method);
			System.out.println("==> Parametros: ");
			for (Parameter parameter : att) {
				System.out.println(att.toString());
			}
			System.out.println("\n");

			// Pega o retorno do metodo
			String ret = recuperatorMethodIMP.checksTheReturnOfMethods(method);
			System.out.println("==> Returno do Metodo: " + ret);

			
			//======================================================================================================
			//======================== Cálcula a probabilidade de ser não ser detectado ============================
			//======================================================================================================
			int[] resulAnali = validadorIMP.vericaParamMetodoByModelo(method);

			int visiPub = resulAnali[0];
			int paramCom = resulAnali[1];
			int retCom = resulAnali[2];
			
			String resulAnaliModel = validadorIMP.vericaMetodoByModelo(visiPub, paramCom, retCom);
			
			if (resulAnaliModel.equals(Constant.NAODECTECTA)) {
				metodosProblematicos.add(method);
			}
		}
		return met;
	}

}
