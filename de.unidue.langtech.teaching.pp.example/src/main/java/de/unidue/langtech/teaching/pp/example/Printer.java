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
import de.unidue.langtech.teaching.pp.type.DetectedValue;
import de.unidue.langtech.teaching.pp.type.GoldValue;

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
    
    private float averageFMeasure;
    private float posNegFMeasure;
    private float posNeuFMeasure;
    private float negNeuFMeasure;
    
    

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
        
    }
    
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        
    	DetectedValue detectedValue = JCasUtil.selectSingle(jcas, DetectedValue.class);
    	GoldValue goldValue = JCasUtil.selectSingle(jcas, GoldValue.class);
    	
        System.out.println(goldValue.getValue() + " detected as " + detectedValue.getValue());
		
        
        if (detectedValue.getValue().equals(goldValue.getValue())){
        	correct += 1;
        }
        nrOfDocuments += 1;
        
      //Es folgt die Zaehlung der entsprechenden Klassifizierungen
      //Fuer positiv:
        if (goldValue.getValue().equals("positive")){
        	posCount += 1;
        	if (detectedValue.getValue().equals("positive")){
        		posAsPos += 1;
        		asPos += 1;
        	}
        	if (detectedValue.getValue().equals("negative")){
        		posAsNeg += 1;
        		asNeg += 1;
        	}
        	if (detectedValue.getValue().equals("neutral")){
        		posAsNeu += 1;
        		asNeu += 1;
        	}
        }
        //Fuer negativ:
        if (goldValue.getValue().equals("negative")){
        	negCount += 1;
        	if(detectedValue.getValue().equals("negative")){
        		negAsNeg += 1;
        		asNeg += 1;
        	}
        	if(detectedValue.getValue().equals("positive")){
        		negAsPos += 1;
        		asPos += 1;
        	}
        	if(detectedValue.getValue().equals("neutral")){
        		negAsNeu += 1;
        		asNeu += 1;
        	}
        }
        //Fuer neutral:
        if (goldValue.getValue().equals("neutral")){
        	neuCount += 1;
        	if(detectedValue.getValue().equals("neutral")){
        		neuAsNeu += 1;
        		asNeu += 1;
        	}
        	if(detectedValue.getValue().equals("positive")){
        		neuAsPos += 1;
        		asPos += 1;
        	}
        	if(detectedValue.getValue().equals("negative")){
        		neuAsNeg += 1;
        		asNeg += 1;
        	}
        }
    }



    @Override
    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        super.collectionProcessComplete();
        
        posRecall = posAsPos / (posAsPos + posAsNeg + posAsNeu);
        negRecall = negAsNeg / (negAsNeg + negAsPos + negAsNeu);
        neuRecall = neuAsNeu / (neuAsNeu + neuAsPos + neuAsNeg);
        
        posPrecision = posAsPos / (posAsPos + negAsPos + neuAsPos);
        negPrecision = negAsNeg / (negAsNeg + posAsNeg + neuAsNeg);
        neuPrecision = neuAsNeu / (neuAsNeu + posAsNeu + negAsNeu);
        
        posFMeasure = (2 * posPrecision * posRecall) / (posPrecision + posRecall);
        negFMeasure = (2 * negPrecision * negRecall) / (negPrecision + negRecall);
        neuFMeasure = (2 * neuPrecision * neuRecall) / (neuPrecision + neuRecall);
        
        averageFMeasure = (posFMeasure + negFMeasure + neuFMeasure) / 3;
        
        posNegFMeasure = (posFMeasure + negFMeasure) / 2;
        posNeuFMeasure = (posFMeasure + neuFMeasure) / 2;
        negNeuFMeasure = (negFMeasure + neuFMeasure) / 2;
        
        
        System.out.println(correct + " out of " + nrOfDocuments + " are correct. " + correct/nrOfDocuments*100 + " %");
        try {
        	
        	//Outputfilename wird hier fuer unterschiedliche Outputs (je nach verwendeter Liste) manuell angepasst
			PrintWriter writer = new PrintWriter("TestTestTest.txt", "UTF-8");
			
			writer.println("-----------------------");
			writer.println("Overall");
			writer.println("-----------------------");
			writer.println("Total amount of documents tested: " + nrOfDocuments);
			writer.println("Amount of positive documents: " + posCount);
			writer.println("Amount of negative documents: " + negCount);
			writer.println("Amount of neutral documents: " + neuCount);
			writer.println("Accuracy: " + correct/nrOfDocuments*100 + "%");
			writer.println(" ");
			writer.println("Average F-Measure: " + averageFMeasure);
			writer.println("Positive + Negative F-Measure: " + posNegFMeasure);
			writer.println("Positive + Neutral F-Measure: " + posNeuFMeasure);
			writer.println("Negative + Neutral F-Measure: " + negNeuFMeasure);
			writer.println(" ");
			writer.println("Neutral detected as Neutral: " + neuAsNeu);
			writer.println("Positive detected as Neutral: " + posAsNeu);
			writer.println("Negative detected as Neutral: " + negAsNeu);
			writer.println(" ");
			writer.println("Positive detected as Positive: " + posAsPos);
			writer.println("Negative detected as Positive: " + negAsPos);
			writer.println("Neutral detected as Positive: " + neuAsPos);
			writer.println(" ");
			writer.println("Negative detected as Negative: " + negAsNeg);
			writer.println("Positive detected as Negative: " + posAsNeg);
			writer.println("Neutral detected as Negative: " + neuAsNeg);
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