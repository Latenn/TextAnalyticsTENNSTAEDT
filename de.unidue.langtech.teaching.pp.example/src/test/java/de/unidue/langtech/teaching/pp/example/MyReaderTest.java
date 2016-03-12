package de.unidue.langtech.teaching.pp.example;

import static org.junit.Assert.assertEquals;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.unidue.langtech.teaching.pp.example.MyReader;
import de.unidue.langtech.teaching.pp.type.GoldLanguage;
import de.unidue.langtech.teaching.pp.type.GoldValue;

public class MyReaderTest {

	@Test
	public void testMyReader() throws Exception {
		CollectionReaderDescription reader = CollectionReaderFactory
				.createReaderDescription(MyReader.class,
						MyReader.PARAM_INPUT_FILE,
						"src/test/resources/test/ReaderTestInput.txt");
		
		int i = 0;
		for (JCas jcas : new JCasIterable(reader)) {
			GoldValue goldValue = JCasUtil.selectSingle(jcas, GoldValue.class);
			
			if (i == 0) {
				assertEquals("I also had a dream last night that football season started and the Ravens were playing the steelers and Ray Rice was running all over them", jcas.getDocumentText());
				assertEquals("neutral", goldValue.getValue());
			} else if (i == 1) {
				assertEquals("Steve  Just heard Green Day's 'Time of our life' for the 1st time since leaving florida and i burst into tears. I miss everyone...  Seymour", jcas.getDocumentText());
				assertEquals("negative", goldValue.getValue());
			} else if (i == 2) {
				assertEquals("@swp29 Just seen your goals in the 5-2 City win against Bolton in 2003. Need to get them days back man, hope you play tomorrow. Top player.", jcas.getDocumentText());
				assertEquals("positive", goldValue.getValue());
			}

			i++;
		}

		
	}
}