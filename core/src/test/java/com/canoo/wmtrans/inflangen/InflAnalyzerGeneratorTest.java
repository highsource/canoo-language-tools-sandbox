package com.canoo.wmtrans.inflangen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.canoo.wmtrans.InflAnalyzerGenerator;
import com.canoo.wmtrans.api.IAnswer;
import com.canoo.wmtrans.api.IFeatures;
import com.canoo.wmtrans.api.IForm;
import com.canoo.wmtrans.api.LanguageMode;

public class InflAnalyzerGeneratorTest {

	public static final String TAB_RESOURCE = "/Ger1InflAnGen.tab";
	public static final String TRA_RESOURCE = "/Ger1InflAnGen.tra ";

	@Test
	public void createsInflAnalyzerGenerator() throws Exception {

		try (InputStream tabInputStream = getClass().getResourceAsStream(TAB_RESOURCE);
				InputStream traInputStream = getClass().getResourceAsStream(TRA_RESOURCE);) {

			InflAnalyzerGenerator inflAnalyzerGenerator = InflAnalyzerGenerator.instance(traInputStream, tabInputStream,
					null, LanguageMode.GERMAN);

			IAnswer answer = inflAnalyzerGenerator.generateForms("sonne", "(Cat N)(Num SG)(Case Dat)", false);

			assertThat(answer.hasResult()).isTrue();
			assertThat(answer.getForms()).hasSize(1);
			IForm form = answer.getForms().get(0);

			assertThat(form.getListOfFeatures()).hasSize(1);
			IFeatures features = form.getListOfFeatures().get(0);
			assertThat(features.getFeaturePairs("Gender"))
					.hasOnlyOneElementSatisfying(gender -> assertThat(gender.getValue()).isEqualTo("F"));
			assertThat(features.getFeaturePairs("Num"))
					.hasOnlyOneElementSatisfying(gender -> assertThat(gender.getValue()).isEqualTo("SG"));
			assertThat(features.getFeaturePairs("Case"))
					.hasOnlyOneElementSatisfying(gender -> assertThat(gender.getValue()).isEqualTo("Dat"));

		}
	}
	
	@Test
	public void strasse() throws Exception {

		try (InputStream tabInputStream = getClass().getResourceAsStream(TAB_RESOURCE);
				InputStream traInputStream = getClass().getResourceAsStream(TRA_RESOURCE);) {

			InflAnalyzerGenerator inflAnalyzerGenerator = InflAnalyzerGenerator.instance(traInputStream, tabInputStream,
					null, LanguageMode.GERMAN);

			IAnswer answer = inflAnalyzerGenerator.generateForms("strasse", "(Cat N)(Num SG)", true);

			if (answer.hasResult()) {
				System.out.println("  Query: " + answer.getQuery().getForm() + " " + answer.getQuery().getFilter());
				for (IForm f : answer.getForms()) {
					System.out.println("  Form: " + f.getString());
	                List<IFeatures> listOfFeatures = f.getListOfFeatures();
	                if (listOfFeatures != null) {
	                    for (IFeatures fs : listOfFeatures) {
	                        System.out.println("    " + fs.toString());
	                    }
	                }
				}
			}
		}
	}
}