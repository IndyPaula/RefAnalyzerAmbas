package code_IMP;

import java.lang.reflect.Modifier;

public class Modificadores {

	public String modificadores(int classModifier) {
		if (Modifier.isPublic(classModifier)) {
			return Constant.PUBLIC;

		} else if (Modifier.isPrivate(classModifier)) {
			return Constant.PRIVATE;

		} else if (Modifier.isProtected(classModifier)) {
			return Constant.PROTECTED;

		} else if (Modifier.isStatic(classModifier)) {
			return Constant.STATIC;

		} else if (Modifier.isFinal(classModifier)) {
			return Constant.FINAL;

		} else if (Modifier.isInterface(classModifier)) {
			return Constant.INTERFACE;

		} else if (Modifier.isAbstract(classModifier)) {
			return Constant.ABSTRACT;

		}

		return Constant.OUTROS;
	}

}
