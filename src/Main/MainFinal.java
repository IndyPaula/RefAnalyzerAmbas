package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import code_IMP.Constant;
import code_IMP.FileUtil;
import code_IMP.MainPrincipal;
import exceptions.CodeSelectorExceptions;

public class MainFinal {

	public static void main(String[] args) {
		MainDesignWizard mainDesignWizard = new MainDesignWizard();
		MainBackTracking mainBackTracking = new MainBackTracking();
		MainPrincipal mainJAVAReflectionANALISE = new MainPrincipal();


		System.out.print("Digite o diretório: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String basePath = "";
		try {
			basePath = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO erro tentando ler o nome");
			System.exit(1);
		}

		chamandoOMain(mainJAVAReflectionANALISE, basePath);

	}

	private static void chamandoOMain(MainPrincipal mainJAVAReflectionANALISE, String basePath) {
		try {
			mainJAVAReflectionANALISE.main(basePath);
		} catch (CodeSelectorExceptions e) {
			e.printStackTrace();
		}
	}
}
