package de.unidue.langtech.teaching.pp.example;

import java.awt.List;
import java.io.FileNotFoundException;
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
import de.unidue.langtech.teaching.pp.type.DetectedValue;
import de.unidue.langtech.teaching.pp.type.GoldValue;

public class TrainingModule
    extends JCasAnnotator_ImplBase
{

    private int tokenCount = 1;
    PrintWriter writer = null;
    private ArrayList<String> words = new ArrayList<String>();
	
	private float posAmount = 0;
	private float negAmount = 0;
	private float neuAmount = 0;

	private float posPriorProb = 0;
	private float negPriorProb = 0;
	private float neuPriorProb = 0;
	
    
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);
        
        
        try {
			writer = new PrintWriter("AdjectivesVerbsList.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
    }
    
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        
    	GoldValue goldValue = JCasUtil.selectSingle(jcas, GoldValue.class);
        
    	
    	//Fuer die Priori-Wahrscheinlichkeitsliste: Merken, wie oft die jeweiligen Labels in den Trainingsdaten vorkommen
    	if (goldValue.getValue().equals("positive")){
    		posAmount += 1;
    	}
    	if (goldValue.getValue().equals("negative")){
    		negAmount += 1;
    	}
    	if (goldValue.getValue().equals("neutral")){
    		neuAmount += 1;
    	}
    	
    	
       Collection<Token> select = JCasUtil.select(jcas, Token.class);
        
        	for (Token t : select){
        		if(t.getPos().getClass().getSimpleName().equals("ADJ") || t.getPos().getClass().getSimpleName().equals("V")){
        			words.add(t.getCoveredText());
        		}
        	}
			
        	//Erstellen der AdjectivesVerbsList
			for (Token t : select){
				if(t.getPos().getClass().getSimpleName().equals("ADJ") || t.getPos().getClass().getSimpleName().equals("V"))
	        	{
						writer.println(t.getPos().getClass().getSimpleName() + "\t" + t.getCoveredText() + "\t" + goldValue.getValue());
	        	}
			}
		 
        for (Token t : select){
        	System.out.println("Token " + tokenCount);
        	System.out.println(t.getPos().getClass().getSimpleName());
        	System.out.println(t.getCoveredText());
        	System.out.println(" ");
        	tokenCount++;
        }
    }

    @Override
    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        super.collectionProcessComplete();
        
        posPriorProb = posAmount/(posAmount+negAmount+neuAmount);
        negPriorProb = negAmount/(posAmount+negAmount+neuAmount);
        neuPriorProb = neuAmount/(posAmount+negAmount+neuAmount);
        
        //Erstellen der Priori-Wahrscheinlichkeitsliste für den Classifier (enthaelt nur diese 3 Werte)
        try {
			PrintWriter writer2 = new PrintWriter("PriorProbabilities.txt", "UTF-8");
			
			writer2.println(posPriorProb + "\t" + negPriorProb + "\t" + neuPriorProb);
			
	        writer2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        writer.close();
        System.out.println("Finished");
    }
}