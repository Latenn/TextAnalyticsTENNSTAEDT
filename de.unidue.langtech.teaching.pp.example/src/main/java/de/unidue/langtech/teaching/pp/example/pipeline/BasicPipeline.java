package de.unidue.langtech.teaching.pp.example.pipeline;

import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;


import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.snowball.SnowballStemmer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.example.BaselineExample;
import de.unidue.langtech.teaching.pp.example.Classifier;
import de.unidue.langtech.teaching.pp.example.EvaluatorExample;
import de.unidue.langtech.teaching.pp.example.MyReader;
import de.unidue.langtech.teaching.pp.example.Printer;
import de.unidue.langtech.teaching.pp.example.ReaderExample;
import de.unidue.langtech.teaching.pp.example.TrainingModule;
import de.unidue.langtech.teaching.pp.example.evaluatorSol.ProbabilityCalculator;
import de.unidue.langtech.teaching.pp.example.newType.LetterAnnotator;
import de.unidue.langtech.teaching.pp.ownReaderTest.NewReader;
import de.unidue.langtech.teaching.pp.ownReaderTest.NewReaderSolution;
import de.unidue.langtech.teaching.pp.ownReaderTest.NewReaderTest;

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
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(BaselineExample.class),
                AnalysisEngineFactory.createEngineDescription(StanfordPosTagger.class, StanfordPosTagger.PARAM_LANGUAGE, "en"),
                AnalysisEngineFactory.createEngineDescription(Classifier.class, Classifier.PARAM_INPUT_FILE, "src/test/resources/test/UniqueProbabilityListTest.txt"),
                AnalysisEngineFactory.createEngineDescription(Printer.class)
                //AnalysisEngineFactory.createEngineDescription(TrainingModule.class)
                //AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class)
//                AnalysisEngineFactory.createEngineDescription(ProbabilityCalculator.class)
//                AnalysisEngineFactory.createEngineDescription(SnowballStemmer.class, SnowballStemmer.PARAM_LANGUAGE, "en"),
//                AnalysisEngineFactory.createEngineDescription(LetterAnnotator.class),
//                AnalysisEngineFactory.createEngineDescription(CasDumpWriter.class)
        );
    }
}