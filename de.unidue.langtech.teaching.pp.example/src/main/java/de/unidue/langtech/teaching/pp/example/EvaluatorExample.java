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
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;
import de.unidue.langtech.teaching.pp.type.DetectedValue;
import de.unidue.langtech.teaching.pp.type.GoldLanguage;
import de.unidue.langtech.teaching.pp.type.GoldValue;

public class EvaluatorExample
    extends JCasAnnotator_ImplBase
{

    private int correct;
    private int nrOfDocuments;
    private int tokenCount = 1;
    private int sentenceCount = 1;
    PrintWriter writer = null;
    private ArrayList<String> words = new ArrayList<String>();
	private boolean isInList;
	private int iterationCounter = 0;
	private int amount;
	
    
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
			writer = new PrintWriter("AdjectivesVerbsListTest.txt", "UTF-8");
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
        
        //DetectedLanguage detected = JCasUtil.selectSingle(jcas, DetectedLanguage.class);
        //GoldLanguage actual = JCasUtil.selectSingle(jcas, GoldLanguage.class);
    	
    	//DetectedValue detectedValue = JCasUtil.selectSingle(jcas, DetectedValue.class);
    	GoldValue goldValue = JCasUtil.selectSingle(jcas, GoldValue.class);

        //System.out.println(goldValue.getValue() + " detected as " + detectedValue.getValue());
    	System.out.println(goldValue.getValue() + " detected as " + "Platzhalter");
        
       Collection<Token> select = JCasUtil.select(jcas, Token.class);
        
        	for (Token t : select){
        		if(t.getPos().getClass().getSimpleName().equals("ADJ") || t.getPos().getClass().getSimpleName().equals("V")){
        			words.add(t.getCoveredText());
        		}
        	}
			
			//writer.println("Sentence:" + sentenceCount);
			for (Token t : select){
				/*amount = 0;
				for (int i = 0; i < words.size(); i++){
					if (words.get(i).equals(t.getCoveredText())){
						amount += 1;
					}
				}*/
			
				if(t.getPos().getClass().getSimpleName().equals("ADJ") || t.getPos().getClass().getSimpleName().equals("V"))
	        	{
					/*for (int i = 0; i <= iterationCounter; i++){
						if (t.getCoveredText().equals(words.get(i))){
							isInList = true;
						}
					}*/
					//if (isInList = false){
						writer.println(t.getPos().getClass().getSimpleName() + "\t" + t.getCoveredText() + "\t" + goldValue.getValue());
					//}
	        		//isInList = false;
	        	}
				iterationCounter +=1;
			}
			//writer.println(" ");
			sentenceCount++;
		
	
        
        // Ist oben schon vorhanden: Collection<Token> select = JCasUtil.select(jcas, Token.class);
        // FIXME: Keep track of correctly classified documents! 
        for (Token t : select){
        	System.out.println("Token " + tokenCount);
        	//System.out.println(t.getPos().getPosValue());
        	System.out.println(t.getPos().getClass().getSimpleName());
        	System.out.println(t.getCoveredText());
        	System.out.println(" ");
        	tokenCount++;
        	
        	
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
        writer.close();
        System.out.println(correct + " out of " + nrOfDocuments + " are correct.");
    }
}