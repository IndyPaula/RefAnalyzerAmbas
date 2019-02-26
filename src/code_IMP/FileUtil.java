package code_IMP;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * Method to list all names into the project.
	 * 
	 * @param path
	 *            - base directory of the project.
	 * @param base
	 *            - used to indicate the base name of directory, utilized for
	 *            purposes of recursion.
	 * @return - The names of all files presents into the current project.
	 */
	public static List<String> listNames(String path, String base, String fileExtension) {
		List<String> result = new ArrayList<String>();
		try {
			File dir = new File(path);

			if (!dir.exists()) {
				throw new RuntimeException("Directory " + dir.getAbsolutePath() + " does not exist.");
			}
			File[] arquivos = dir.listFiles();
			int tam = arquivos.length;
			for (int i = 0; i < tam; i++) {
				if (arquivos[i].isDirectory()) {
					String baseTemp = base + arquivos[i].getName() + ".";
					result.addAll(listNames(arquivos[i].getAbsolutePath(), baseTemp, fileExtension));
				} else {
					if (arquivos[i].getName().endsWith(fileExtension)) {
						String temp = base + arquivos[i].getName();
						temp = removeExtension(temp, fileExtension);
						if (!result.contains(temp))
							result.add(temp);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error in FileUtil.listNames()");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method used to list all classes present into the directory received as
	 * parameter.
	 * 
	 * @param sourceFolder
	 *            = the directory source of the files.
	 * @return
	 */
	public static ArrayList<Class<?>> getClassListFile(String sourceFolder) {
		List<String> listClassNames = listNames(sourceFolder, "", ".class");

		// Coloquei isso
		ArrayList<Class<?>> classes = new ArrayList<>();
		Class<?> res2 = null;

		for (String className : listClassNames) {
			try {
				CustomClassLoader classLoader = new CustomClassLoader();

				classLoader.putTheBasepath(sourceFolder);
				res2 = classLoader.findClass(className);

				// Coloquei isso
				classes.add(res2);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		// return DetectUtil.makeFile(Constants.CLASSES, lines.toString());
		return classes;
	}

	/**
	 * Method to remove the extension of the files.
	 * 
	 * @param arquivo
	 *            - the file that extension will be removed.
	 * @param extension
	 *            - the extension to be removed.
	 * @return - the filename without the extension.
	 */
	private static String removeExtension(String arquivo, String extension) {
		arquivo = arquivo.replaceAll(extension + "\\b", "");
		return arquivo;
	}

	/**
	 * Method to remove the extension of the files.
	 * 
	 * @param arquivo
	 *            - the file that extension will be removed.
	 * @param extension
	 *            - the extension to be removed.
	 * @return - the filename without the extension.
	 */
	protected static String removeExtension1(String arquivo, String extension) {
		arquivo = arquivo.replaceAll(extension + "\\b", "");
		return arquivo;
	}
}