package eu.qped.java;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({ "eu.qped.java.syntax", "eu.qped.java.style", "eu.qped.java.semantics", "eu.qped.java.testing",
		"eu.qped.java.design" })
public class JavaCheckerTests {

}
