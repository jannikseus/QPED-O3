package eu.qped.java.style;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.qped.framework.CheckLevel;
import eu.qped.framework.qf.QFStyleSettings;
import eu.qped.java.checkers.mass.MainSettings;
import eu.qped.java.checkers.mass.MassExecutor;
import eu.qped.java.checkers.style.StyleChecker;
import eu.qped.java.checkers.style.StyleConfigurator;
import eu.qped.java.checkers.style.StyleFeedback;
import eu.qped.java.checkers.syntax.SyntaxErrorChecker;

class MassStyleTest {

	private MainSettings mainSettingsConfiguratorConf;
	private StyleConfigurator styleConfigurator;
	private StyleChecker styleChecker;

	@BeforeEach
	public void setup() {
		Map<String, String> mainSettings = new HashMap<>();
		mainSettings.put("semanticNeeded", "false");
		mainSettings.put("syntaxLevel", CheckLevel.ADVANCED.name());
		mainSettings.put("preferredLanguage", "en");
		mainSettings.put("styleNeeded", "true");

		mainSettingsConfiguratorConf = new MainSettings(mainSettings);
		
		QFStyleSettings qfStyleSettings = new QFStyleSettings();
		qfStyleSettings.setNamesLevel("adv");
		qfStyleSettings.setMethodName("[a-z][a-zA-Z0-9_]*");

		styleConfigurator = StyleConfigurator.createStyleConfigurator(qfStyleSettings);
		styleChecker = new StyleChecker(styleConfigurator);

	}
	
	@Test
	void testMethodNoError() {

		String code = "void rec(){\n"
				+ "System.out.println(\"pretty\");\n"
				+ "}";

		SyntaxErrorChecker syntaxErrorChecker = SyntaxErrorChecker.createSyntaxErrorChecker(code);

		MassExecutor massE = new MassExecutor(styleChecker, null, syntaxErrorChecker,
				mainSettingsConfiguratorConf);

		massE.execute();

		assertEquals(0, massE.getStyleFeedbacks().size());
	}

}