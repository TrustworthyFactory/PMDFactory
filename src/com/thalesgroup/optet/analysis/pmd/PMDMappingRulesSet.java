package com.thalesgroup.optet.analysis.pmd;

import java.util.HashMap;
import java.util.Map;

/**
 * PMDMappingRulesSet realize the ruleset mapping for PMD
 * @author F. Motte
 *
 */
public class PMDMappingRulesSet {

	public static Map<String, String> NameToCode = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1641554563315294934L;

		{
			put("Basic Ecmascript", "ecmascript-basic");
			put("Ecmascript Braces", "ecmascript-braces");
			put("Ecmascript Unnecessary", "ecmascript-unnecessary");
			put("Java Android", "java-android");
			put("Java Basic", "java-basic");
			put("Java Braces", "java-braces");
			put("Java Clone Implementation", "java-clone");
			put("Java Code Size", "java-codesize");
			put("Java Comments", "java-comments");
			put("Java Controversial", "java-controversial");
			put("Java Coupling", "java-coupling");
			put("Java Design", "java-design");
			put("Java Empty Code", "java-empty");
			put("Java Finalizer", "java-finalizers");
			put("Java Import Statements", "java-imports");
			put("Java J2EE", "java-j2ee");
			put("Java JavaBeans", "java-javabeans");
			put("Java JUnit", "java-junit");
			put("Java Jakarta Commons Logging", "java-logging-jakarta-commons");
			put("Java Logging", "java-logging-java");
			put("Java Migration", "java-migrating");
			put("Java Naming", "java-naming");
			put("Java Optimization", "java-optimizations");
			put("Java Strict Exceptions", "java-strictexception");
			put("Java String and StringBuffer", "java-strings");
			put("Java Security Code Guidelines", "java-sunsecure");
			put("Java Unnecessary", "java-unnecessary");
			put("Java Unused Code", "java-unusedcode");
			put("Basic JSP", "jsp-basic");
			put("Basic JSF", "jsp-basic-jsf");
			put("XPath in XSL", "xml-basic");
			put("Basic XML", "xsl-xpath");
		}
	};
	public static Map<String, String> CodeToName = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1641554563315294935L;

		{
			put("ecmascript-basic", "Basic Ecmascript");
			put("ecmascript-braces", "Ecmascript Braces");
			put("ecmascript-unnecessary", "Ecmascript Unnecessary");
			put("java-android", "Java Android");
			put("java-basic", "Java Basic");
			put("java-braces", "Java Braces");
			put("java-clone", "Java Clone Implementation");
			put("java-codesize", "Java Code Size");
			put("java-comments", "Java Comments");
			put("java-controversial", "Java Controversial");
			put("java-coupling", "Java Coupling");
			put("java-design", "Java Design");
			put("java-empty", "Java Empty Code");
			put("java-finalizers", "Java Finalizer");
			put("java-imports", "Java Import Statements");
			put("java-j2ee", "Java J2EE");
			put("java-javabeans","Java JavaBeans");
			put("java-junit","Java JUnit");
			put("java-logging-jakarta-commons", "Java Jakarta Commons Logging");
			put("java-logging-java", "Java Logging");
			put("java-migrating", "Java Migration");
			put("java-naming","Java Naming");
			put("java-optimizations", "Java Optimization");
			put("java-strictexception", "Java Strict Exceptions");
			put("java-strings", "Java Strict Exceptions");
			put("java-sunsecure", "Java Security Code Guidelines");
			put("java-unnecessary", "Java Unnecessary");
			put("java-unusedcode", "Java Unused Code");
			put("jsp-basic", "Basic JSP");
			put("jsp-basic-jsf", "Basic JSF");
			put("xml-basic", "XPath in XSL");
			put("xsl-xpath","Basic XML");
		}
	};
}
