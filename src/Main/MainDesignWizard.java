package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import org.designwizard.api.DesignWizard;
import org.designwizard.design.MethodNode;

import code_IMP.Constant;
import code_IMP.RecuperatorClasseIMP;
import code_IMP.RecuperatorMethodIMP;
import exceptions.CodeSelectorExceptions;

public class MainDesignWizard {

	static int metodos = 0;
	static int metodosPrivates = 0;
	static RecuperatorMethodIMP recuperatorMethodIMP = new RecuperatorMethodIMP();
	static RecuperatorClasseIMP recuperatorClasseIMP = new RecuperatorClasseIMP();

	public static void main(String basePath2) throws CodeSelectorExceptions {

		String basePath = basePath2.toString();

		try {
			DesignWizard designWizard = new DesignWizard(basePath);
			Set<MethodNode> meth = designWizard.getAllMethods();
			for (MethodNode methodNode : meth) {
				metodos = metodos + 1;

				String visib = RecuperatorMethodIMP.checksVisibilityMethod(methodNode);
				System.out.println("\n" + "==> Nome do Método: " + recuperatorMethodIMP.getNameMethod(methodNode) + "\n"
						+ "==> Caller: " + recuperatorMethodIMP.getCallersMethodoNode(methodNode) + "\n"
						+ "==> Visibilidade" + visib);

				// System.out.println("Nome do Método: " + methodNode.getName()
				// +"\n" + "Visibilidade" + visib);

				verificaSeEhPrivateOuProtected(visib, methodNode);

				System.out.println("--------------------" + "\n");

			}
			System.out.println("Quantidade de metodos: " + metodos);

			System.out.println("Quantidade de metodos privados: " + metodosPrivates);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void verificaSeEhPrivateOuProtected(String visib, MethodNode methodNode) {
		ArrayList<MethodNode> metodosPriEPro = new ArrayList<>();

		if (visib.contains(Constant.PRIVATE) || visib.contains(Constant.PROTECTED)) {
			metodosPrivates = metodosPrivates + 1;

			verificaCallersFather(methodNode);

			// armazena apenas os métodos private e protected em um array
			metodosPriEPro.add(methodNode);

			Set<MethodNode> metodosCaller = recuperatorMethodIMP.getCallersMethodoNode(methodNode);

			System.out.println("\n" + "====== APENAS MÉTODOS PRIVATE E PROTECTED ======");

			System.out.println("==> Nome do Método: " + methodNode.getName() + "\n" + "==> Visibilidade" + visib + "\n"
					+ "==> Caller: " + recuperatorMethodIMP.getCallersMethodoNode(methodNode) + "\n");

		}
	}

	private static MethodNode verificaCallersFather(MethodNode methodNode) {
		Set<MethodNode> metodosCallers = recuperatorMethodIMP.getCallersMethodoNode(methodNode);
		int count = 0;
		for (MethodNode methodNode2 : metodosCallers) {
			if (RecuperatorMethodIMP.checksVisibilityMethod(methodNode2).equals(Constant.PUBLIC)) {
				return methodNode2;
			} else {
				count++;
				metodosCallers = recuperatorMethodIMP.getCallersMethodoNode(methodNode);

			}

		}
		System.out.println("=========" + count);
		return null;

	}

}
