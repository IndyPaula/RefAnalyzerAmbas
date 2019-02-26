package code_IMP;

import java.io.FileInputStream;

import javax.print.DocFlavor.STRING;

public class CustomClassLoader extends ClassLoader {
	
	String basePath = "";
	
	RecuperatorMethodIMP recInf = new RecuperatorMethodIMP();
	
	
	public void putTheBasepath(String base) {
		basePath = base;
	}
	
	
	@Override
	public Class<?> findClass(final String name) throws ClassNotFoundException {
		
		String fullName = name.replace('.', '/');
		fullName += ".class";

		String path = basePath + fullName;
		try {
			FileInputStream fis = new FileInputStream(path);
			byte[] data = new byte[fis.available()];
			fis.read(data);

			Class<?> res1 = defineClass(name, data, 0, data.length);
			
			fis.close();
			return res1;
		} catch (Exception e) {
			return super.findClass(name);
		}
	}
}
