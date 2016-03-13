package de.unidue.langtech.teaching.pp.example.pipeline;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;


import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.example.Classifier;
import de.unidue.langtech.teaching.pp.example.MyReader;
import de.unidue.langtech.teaching.pp.example.Printer;
import de.unidue.langtech.teaching.pp.example.TrainingModule;


public class BasicPipeline
{

    public static void main(String[] args)
        throws Exception
    {
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        MyReader.class,
                        MyReader.PARAM_INPUT_FILE, "src/test/resources/test/Testdata.txt"
                ),
                //Fuer Training: Classifier und Printer aus der Pipeline entfernen und nach StanfordPosTagger lediglich TrainingModule laufen lassen
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(StanfordPosTagger.class, StanfordPosTagger.PARAM_LANGUAGE, "en"),
                AnalysisEngineFactory.createEngineDescription(Classifier.class, Classifier.PARAM_INPUT_FILE, "src/test/resources/test/UniqueProbabilityListSmooth.txt"),
                AnalysisEngineFactory.createEngineDescription(Printer.class)
                //AnalysisEngineFactory.createEngineDescription(TrainingModule.class)
        );
    }
}