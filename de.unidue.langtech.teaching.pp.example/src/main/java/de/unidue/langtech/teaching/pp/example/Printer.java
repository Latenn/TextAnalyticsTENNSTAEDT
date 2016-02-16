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
    
    private float posAsPos;
    private float posAsNeg;
    private float posAsNeu;
    
    private float negAsNeg;
    private float negAsPos;
    private float negAsNeu;
    
    private float neuAsNeu;
    private float neuAsPos;
    private float neuAsNeg;
    
    private float asPos;
    private float asNeg;
    private float asNeu;
    
    private float posCount;
    private float negCount;
    private float neuCount;
    
    private float posPrecision;
    private float negPrecision;
    private float neuPrecision;
    
    private float posRecall;
    private float negRecall;
    private float neuRecall;
    
    private float posFMeasure;
    private float negFMeasure;
    private float neuFMeasure;
    
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
        
        posAsPos = 0;
        posAsNeg = 0;
        posAsNeu = 0;
        
        negAsNeg = 0;
        negAsPos = 0;
        negAsNeu = 0;
        
        neuAsNeu = 0;
        neuAsPos = 0;
        neuAsNeg = 0;
        
        asPos = 0;
        asNeg = 0;
        asNeu = 0;
        
        posCount = 0;
        negCount = 0;
        neuCount = 0;
        
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
        
        //Fuer positiv:
        if (actual.getLanguage().equals("positive")){
        	posCount += 1;
        	if (detected.getLanguage().equals("positive")){
        		posAsPos += 1;
        		asPos += 1;
        	}
        	if (detected.getLanguage().equals("negative")){
        		posAsNeg += 1;
        		asNeg += 1;
        	}
        	if (detected.getLanguage().equals("neutral")){
        		posAsNeu += 1;
        		asNeu += 1;
        	}
        }
        //Fuer negativ:
        if (actual.getLanguage().equals("negative")){
        	negCount += 1;
        	if(detected.getLanguage().equals("negative")){
        		negAsNeg += 1;
        		asNeg += 1;
        	}
        	if(detected.getLanguage().equals("positive")){
        		negAsPos += 1;
        		asPos += 1;
        	}
        	if(detected.getLanguage().equals("neutral")){
        		negAsNeu += 1;
        		asNeu += 1;
        	}
        }
        //Fuer neutral:
        if (actual.getLanguage().equals("neutral")){
        	neuCount += 1;
        	if(detected.getLanguage().equals("neutral")){
        		neuAsNeu += 1;
        		asNeu += 1;
        	}
        	if(detected.getLanguage().equals("positive")){
        		neuAsPos += 1;
        		asPos += 1;
        	}
        	if(detected.getLanguage().equals("negative")){
        		neuAsNeg += 1;
        		asNeg += 1;
        	}
        }
    }


    /* 
     * This is called AFTER all documents have been processed.
     */
    @Override
    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        super.collectionProcessComplete();
        
        posPrecision = posAsPos / (posAsPos + posAsNeg + posAsNeu);
        negPrecision = negAsNeg / (negAsNeg + negAsPos + negAsNeu);
        neuPrecision = neuAsNeu / (neuAsNeu + neuAsPos + neuAsNeg);
        
        posRecall = posAsPos / (posAsPos + negAsPos + neuAsPos);
        negRecall = negAsNeg / (negAsNeg + posAsNeg + neuAsNeg);
        neuRecall = neuAsNeu / (neuAsNeu + posAsNeu + negAsNeu);
        
        posFMeasure = (2 * posPrecision * posRecall) / (posPrecision + posRecall);
        negFMeasure = (2 * negPrecision * negRecall) / (negPrecision + negRecall);
        neuFMeasure = (2 * neuPrecision * neuRecall) / (neuPrecision + neuRecall);
        
        
        System.out.println(correct + " out of " + nrOfDocuments + " are correct. " + correct/nrOfDocuments*100 + " %");
        try {
			PrintWriter writer = new PrintWriter("Output.txt", "UTF-8");
			
			writer.println("-----------------------");
			writer.println("Overall");
			writer.println("-----------------------");
			writer.println("Total amount of documents tested: " + nrOfDocuments);
			writer.println("Amount of positive documents: " + posCount);
			writer.println("Amount of negative documents: " + negCount);
			writer.println("Amount of neutral documents: " + neuCount);
			writer.println("Accuracy: " + correct/nrOfDocuments*100 + "%");
			writer.println(" ");
			writer.println("-----------------------");
			writer.println("Positive");
			writer.println("-----------------------");
			writer.println("Precision :" + posPrecision*100 + "%");
			writer.println("Recall: " + posRecall*100 + "%");
			writer.println("F-Measure: " + posFMeasure);
			writer.println(" ");
			writer.println("-----------------------");
			writer.println("Negative");
			writer.println("-----------------------");
			writer.println("Precision :" + negPrecision*100 + "%");
			writer.println("Recall: " + negRecall*100 + "%");
			writer.println("F-Measure: " + negFMeasure);
			writer.println(" ");
			writer.println("-----------------------");
			writer.println("Neutral");
			writer.println("-----------------------");
			writer.println("Precision :" + neuPrecision*100 + "%");
			writer.println("Recall: " + neuRecall*100 + "%");
			writer.println("F-Measure: " + neuFMeasure);
			
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