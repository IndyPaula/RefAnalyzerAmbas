package code_IMP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.designwizard.api.DesignWizard;
import org.designwizard.design.MethodNode;

public class BackTracking {

	static RecuperatorMethodIMP recuperatorMethodIMP = new RecuperatorMethodIMP();
	static RecuperatorClasseIMP recuperatorClasseIMP = new RecuperatorClasseIMP();
	static int niveis = 0;
	static ArrayList<MethodNode> setFinal;
	static int metodos = 0;
	static int metodosPrivates = 0;
	static String nome = "";

	public static void main(String[] args) {
		try {

			String basePath = args.toString();
			DesignWizard designWizard = new DesignWizard(basePath);
			Set<MethodNode> meth = designWizard.getAllMethods();
			for (MethodNode methodNode : meth) {
				metodos = metodos + 1;
				setFinal = new ArrayList<>();
				String visib = recuperatorMethodIMP.checksVisibilityMethod(methodNode);

				verificaNivelCallersMethPrivaEProtec(methodNode, visib);

				// System.out.println("\n" + "==> Nome do Método: " +
				// recuperatorMethodIMP.getNameMethod(methodNode) + "\n"
				// + "==> Caller: " +
				// recuperatorMethodIMP.getCallersMethodoNode(methodNode) + "\n"
				// + "==> Visibilidade" + visib);

			}

			System.out.println("\n" + "Quantidade de metodos: " + metodos);
			System.out.println("Quantidade de metodos privados: " + metodosPrivates);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void verificaNivelCallersMethPrivaEProtec(MethodNode methodNode, String visib) {

		if (condicaoVerificaSeEhPrivOuProtect(methodNode)) {

			metodosPrivates = metodosPrivates + 1;

			Set<MethodNode> callerMethods = recuperatorMethodIMP.getCallersMethodoNode(methodNode);

			ArrayList<MethodNode> callers = backTracking(callerMethods);
			niveis = niveis + 1;
			// Imprime resutado dos metodos privados

			System.out.println("\n" + "Methodos " + methodNode + "Visibilidade: " + visib + "\n" + "Callers: " + callers
					+ "\n" + "Níveis: " + niveis + "\n" + "\n");
			niveis = 0;
		}
	}

	private static ArrayList<MethodNode> backTracking(Set<MethodNode> callerMethods) {
		for (MethodNode methodNode : callerMethods) { // intera no array de
														// callers
			if (condicaoVerificaSeEhPrivOuProtect(methodNode)) {
				niveis++;
				backTracking(recuperatorMethodIMP.getCallersMethodoNode(methodNode));

			}
			setFinal.add(methodNode);
			return setFinal;
		}

		return setFinal;

	}

	private static boolean condicaoVerificaSeEhPrivOuProtect(MethodNode methodNode) {
		return recuperatorMethodIMP.checksVisibilityMethod(methodNode).contains(Constant.PRIVATE)
				|| recuperatorMethodIMP.checksVisibilityMethod(methodNode).contains(Constant.PROTECTED);
	}
}
