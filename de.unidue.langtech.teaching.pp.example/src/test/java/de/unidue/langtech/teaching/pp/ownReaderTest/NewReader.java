package de.unidue.langtech.teaching.pp.ownReaderTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

public class NewReader
    extends JCasCollectionReader_ImplBase
{
    public static final String PARAM_INPUT_FILE = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
    private File inputFile;

    private List<String> lines;
    private int currentLine;

    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);

        try {
            lines = FileUtils.readLines(inputFile);
            currentLine = 0;
        }
        catch (IOException e) {
            throw new ResourceInitializationException(e);
        }
    }

    public boolean hasNext()
        throws IOException, CollectionException
    {
    	int sentenceCounter = 0;
    	for (int i = 0; i < lines.size(); i++){
    		if (lines.get(i).isEmpty() == true){
    			sentenceCounter++;
    		}
    	}
    	
    	int[][] sentenceIndexes = new int[sentenceCounter][2];
    	
    	int sentenceBeginningCounter = 0;
    	int indexCounter = 0;
    	for (int i = 0; i < lines.size(); i++){
    		if (lines.get(i).isEmpty() == false){
    			
    			sentenceIndexes[sentenceBeginningCounter][0] = i;
    		}
    	}
    	
    	String[] sentences = new String[sentenceCounter];
    	
    	int counter = 0;
    	for (int i = 0; i < lines.size(); i++){
    		if (lines.get(i).isEmpty() == false){
    			
    		}
    		
    	}
        return currentLine < lines.size();
    }

    public Progress[] getProgress()
    {
        return null;
    }

    public void getNext(JCas aJCas)
        throws IOException, CollectionException
    {
    	
    	 
    	for (int i = 0; i <lines.size(); i++)
    	{
    		
    	}
        // increment to avoid infinite looping - delete it if you don't need it
        currentLine++;
    }

}
