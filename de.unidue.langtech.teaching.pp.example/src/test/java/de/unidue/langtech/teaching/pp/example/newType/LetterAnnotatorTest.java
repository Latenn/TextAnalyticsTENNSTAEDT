package de.unidue.langtech.teaching.pp.example.newType;

import static org.junit.Assert.*;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.unidue.langtech.teaching.pp.type.LetterACounter;


public class LetterAnnotatorTest {
	

	@Test
	public void testAnnotator() throws UIMAException{
		String text = "Peter went into the office before they arrived there";
		
		JCas jcas = JCasFactory.createJCas();
		jcas.setDocumentText(text);
		
		AnalysisEngineDescription letterAnnotator = AnalysisEngineFactory.createEngineDescription(LetterAnnotator.class);
		AnalysisEngine letterAnnotateEngine = AnalysisEngineFactory.createEngine(letterAnnotator);
		letterAnnotateEngine.process(jcas);
		
		LetterACounter letterCount = JCasUtil.selectSingle(jcas, LetterACounter.class);
		assertEquals(1, letterCount.getCountLetterA());
	}
}
