package de.unidue.langtech.teaching.pp.example;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.junit.Assert.*;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.type.DetectedValue;

public class ClassifierTest {

	@Test
	public void testClassifierAnnotation() throws Exception {
		String text = "This is a great simple textline for my classifier";
		
		JCas jcas = JCasFactory.createJCas();
		jcas.setDocumentText(text);
		
		AnalysisEngineDescription segmenter = createEngineDescription(BreakIteratorSegmenter.class);
		AnalysisEngine segEngine = createEngine(segmenter);
		segEngine.process(jcas);
		
		AnalysisEngineDescription classifier = createEngineDescription(Classifier.class, Classifier.PARAM_INPUT_FILE, "src/test/resources/test/UniqueProbabilityList.txt");
		AnalysisEngine classEngine = createEngine(classifier);
		classEngine.process(jcas);
		
		DetectedValue detectedValue = JCasUtil.selectSingle(jcas, DetectedValue.class);
		
		assertEquals("positive", detectedValue.getValue());
	}
}
