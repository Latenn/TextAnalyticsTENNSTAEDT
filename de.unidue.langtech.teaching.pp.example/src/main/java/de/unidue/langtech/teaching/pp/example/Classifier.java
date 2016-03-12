package de.unidue.langtech.teaching.pp.example;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
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
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;
import de.unidue.langtech.teaching.pp.type.DetectedValue;
import de.unidue.langtech.teaching.pp.type.GoldLanguage;

public class Classifier
    extends JCasAnnotator_ImplBase
{
	
	 /**
     * Input file
     */
    public static final String PARAM_INPUT_FILE = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
    private File inputFile;    
    
    private ArrayList<Float> posProb = new ArrayList<Float>();
    private ArrayList<Float> negProb = new ArrayList<Float>();
    private ArrayList<Float> neuProb = new ArrayList<Float>();
    private ArrayList<String> probWords = new ArrayList<String>();
    
    private float priorPosProbability;
    private float priorNegProbability;
    private float priorNeuProbability;
    
	String line = null;
	String line2 = null;
	String[] parts;
	String[] parts2;
	String probFileName = "PriorProbabilities.txt";

    
   
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context); 
        
        //Einlesen der Wahrscheinlichkeitsliste
		try {
			FileReader fileReader = new FileReader(inputFile);
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
		
		//Einlesen der Prior-Probabilities
		try {
			FileReader fileReader2 = new FileReader(probFileName);
			
			BufferedReader br2 = new BufferedReader(fileReader2);
			
			while ((line2 = br2.readLine()) != null){
				parts2 = line2.split("\t");
				priorPosProbability = (Float.parseFloat(parts2[0]));
				priorNegProbability = (Float.parseFloat(parts2[1]));
				priorNeuProbability = (Float.parseFloat(parts2[2]));
				
			}
			br2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    
   
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
  
       ArrayList<String> words = new ArrayList<String>();
       double posValue = 1;
       double negValue = 1;
       double neuValue = 1;
       int matchCount = 0;
       
       DetectedValue detectedValue = new DetectedValue(jcas);
       
       
       Collection<Token> select = JCasUtil.select(jcas, Token.class);
       for (Token t : select){
    	   words.add(t.getCoveredText());
       }
       System.out.println(words.size());
       
       //Hier erfolgt der erste Teil der Wahrscheinlichkeitsberechnung fuer jede der 3 Klassen
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
       
       //Zweiter Teil: Verrechnen mit der Priori-Wahrscheinlichkeit fuer die entsprechende Klasse (oben eingelesen)
       posValue *= priorPosProbability;
       negValue *= priorNegProbability;
       neuValue *= priorNeuProbability;
       
       System.out.println(posValue + " "  + " "  + negValue + " " + neuValue);
       System.out.println(matchCount);
       if (posValue > negValue && posValue > neuValue){
    	   detectedValue.setValue("positive");
       }else if (negValue > posValue && negValue > neuValue){
    	   detectedValue.setValue("negative");
       }else if (neuValue > posValue && neuValue > negValue){
    	   detectedValue.setValue("neutral");
       }else { detectedValue.setValue("neutral");}
    	

       detectedValue.addToIndexes();
       
    }


   
    @Override
    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        super.collectionProcessComplete();
    }
}