package de.unidue.langtech.teaching.pp.example;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;
import de.unidue.langtech.teaching.pp.type.GoldLanguage;

public class Printer
    extends JCasAnnotator_ImplBase
{

    private float correct;
    private float nrOfDocuments;
    private int tokenCount = 1;
    

    /* 
     * This is called BEFORE any documents are processed.
     */
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);
        correct = 0;
        nrOfDocuments = 0; 
        try {
			PrintWriter writer = new PrintWriter("Output.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /* 
     * This is called ONCE for each document
     */
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        
        DetectedLanguage detected = JCasUtil.selectSingle(jcas, DetectedLanguage.class);
        GoldLanguage actual = JCasUtil.selectSingle(jcas, GoldLanguage.class);

        System.out.println(actual.getLanguage() + " detected as " + detected.getLanguage());
		
        if (detected.getLanguage().equals(actual.getLanguage())){
        	correct += 1;
        }
        nrOfDocuments += 1;
       /* 
        // Ist oben schon vorhanden: Collection<Token> select = JCasUtil.select(jcas, Token.class);
        // FIXME: Keep track of correctly classified documents! 
        for (Token t : select){
        	System.out.println("Token " + tokenCount);
        	//System.out.println(t.getPos().getPosValue());
        	System.out.println(t.getPos().getClass().getSimpleName());
        	System.out.println(t.getCoveredText());
        	System.out.println(" ");
        	tokenCount++;
        	
        	
        }*/
    }


    /* 
     * This is called AFTER all documents have been processed.
     */
    @Override
    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        super.collectionProcessComplete();
        System.out.println(correct + " out of " + nrOfDocuments + " are correct. " + correct/nrOfDocuments*100 + " %");
        try {
			PrintWriter writer = new PrintWriter("Output.txt", "UTF-8");
			writer.println(correct + " out of " + nrOfDocuments + " are correct. " + correct/nrOfDocuments*100 + " %");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}