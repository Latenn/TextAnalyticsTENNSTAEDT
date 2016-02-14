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

public class Classifier
    extends JCasAnnotator_ImplBase
{

    private int correct;
    private int nrOfDocuments;
    private int tokenCount = 1;
    
    private ArrayList<Float> posProb = new ArrayList<Float>();
    private ArrayList<Float> negProb = new ArrayList<Float>();
    private ArrayList<Float> neuProb = new ArrayList<Float>();
    private ArrayList<String> probWords = new ArrayList<String>();
    
    private float posCount;
    private float negCount;
    private float neuCount;
    private float generalCount;
    
	private int iterationCounter = 0;
	String line = null;
	String fileName = "src/test/resources/test/UniqueProbabilityListLong.txt";
	String[] parts;

    
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
        
        posCount = 0;
        negCount = 0;
        neuCount = 0;
        generalCount = 0;
        
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fileReader);
			while ((line = br.readLine()) != null){
				parts = line.split("\t");
				probWords.add(parts[0]);
				posProb.add(Float.parseFloat(parts[1]));
				negProb.add(Float.parseFloat(parts[2]));
				neuProb.add(Float.parseFloat(parts[3]));
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
        
        //DetectedLanguage detected = JCasUtil.selectSingle(jcas, DetectedLanguage.class);
        /*GoldLanguage actual = JCasUtil.selectSingle(jcas, GoldLanguage.class);
        if (actual.getLanguage().equals("positive")){
        	posCount += 1;
        	generalCount += 1;
        }
        if (actual.getLanguage().equals("negative")){
        	negCount += 1;
        	generalCount += 1;
        }
        if (actual.getLanguage().equals("neutral")){
        	neuCount += 1;
        	generalCount += 1;
        }
        */
        //System.out.println(actual.getLanguage() + " detected as " + detected.getLanguage());

        
        
        
        
        
        
       ArrayList<String> words = new ArrayList<String>();
       float posValue = 1;
       float negValue = 1;
       float neuValue = 1;
       int matchCount = 0;
       DetectedLanguage languageAnno = new DetectedLanguage(jcas);
       
       Collection<Token> select = JCasUtil.select(jcas, Token.class);
       for (Token t : select){
    	   words.add(t.getCoveredText());
       }
       System.out.println(words.size());
       
       for (int i = 0; i < words.size(); i++){
    	   for (int x = 0; x < probWords.size(); x++){
    		   if (words.get(i).equals(probWords.get(x))){ 
    			   
    			   posValue *= posProb.get(x);			 
    			   negValue *= negProb.get(x);   
    			   neuValue *= neuProb.get(x);	   
    			   matchCount += 1;
    			   System.out.println(words.get(i) + " " + posProb.get(x) + " " + negProb.get(x) + " " + neuProb.get(x));
    		   }
    	   }
       }
       posValue *= 0.35674438;
       negValue *= 0.14819053;
       neuValue *= 0.4950651;
       System.out.println(posValue + " "  + " "  + negValue + " " + neuValue);
       System.out.println(matchCount);
       if (posValue > negValue || posValue > neuValue){
    	   languageAnno.setLanguage("positive");
       }
       if (negValue > posValue || negValue > neuValue){
    	   languageAnno.setLanguage("negative");
       }
       if (neuValue > posValue || neuValue > negValue){
    	   languageAnno.setLanguage("neutral");
       }
       if (neuValue == posValue || posValue == negValue){
    	   languageAnno.setLanguage("positive");
       }
       languageAnno.addToIndexes();
       
        /*
        	for (Token t : select){
        		if(t.getPos().getClass().getSimpleName().equals("ADJ") || t.getPos().getClass().getSimpleName().equals("V")){
        			words.add(t.getCoveredText());
        		}
        	}
			
			for (Token t : select){
			
				if(t.getPos().getClass().getSimpleName().equals("ADJ") || t.getPos().getClass().getSimpleName().equals("V"))
	        	{
						writer.println(t.getPos().getClass().getSimpleName() + "\t" + t.getCoveredText() + "\t" + actual.getLanguage());
				
	        	}
				iterationCounter +=1;
			}
		*/
	
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
       // System.out.println(correct + " out of " + nrOfDocuments + " are correct.");
        //System.out.println("Pos = " + posCount/generalCount + "(" + posCount + ")" + " Neg = " + negCount/generalCount + "(" + negCount + ")" + " Neu = " + neuCount/generalCount + "(" + neuCount + ")" );
    }
}