package de.unidue.langtech.teaching.pp.example;

import java.util.Collection;


import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;
import org.apache.uima.fit.descriptor.ConfigurationParameter;

/**
 * The baseline always identifies "EN" as the document language.
 * 
 * @author zesch
 *
 */
public class BaselineExample
    extends JCasAnnotator_ImplBase
{
public static final String PARAM_MESSAGE = "PARAM_MESSAGE";
@ConfigurationParameter(name = PARAM_MESSAGE, mandatory = true, defaultValue = "Hello everyone")
protected String message; 


    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        System.out.println("Document is: " + jcas.getDocumentText());
        
        Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
        System.out.println("CAS contains " + tokens.size() + " tokens.");
        
        DetectedLanguage languageAnno = new DetectedLanguage(jcas);
        
        for (Token t : tokens){
        	if (t.getCoveredText().equals("the")){
        	
        		languageAnno.setLanguage("EN");
        	}
        	else if (t.getCoveredText().equals("ist")){
        		
        		languageAnno.setLanguage("DE");
        	}
        	else if (t.getCoveredText().equals("tu")){
        		
        		languageAnno.setLanguage("FR");
        	}
        	else {
        		languageAnno.setLanguage("Unknown");
        	}
        }
    
        languageAnno.addToIndexes();
        
        System.out.println(message);
    }
}