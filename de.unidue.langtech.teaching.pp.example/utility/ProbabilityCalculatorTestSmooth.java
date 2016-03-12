import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ProbabilityCalculatorTestSmooth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 PrintWriter writer = null;
		 String line = null;
		 String fileName = "AdjectivesVerbsList.txt";
		 ArrayList<String> types = new ArrayList<String>();
		 ArrayList<String> words = new ArrayList<String>();
		 ArrayList<String> values = new ArrayList<String>();
		 ArrayList<Object> amount = new ArrayList<Object>();
		 ArrayList<Object> posAmount = new ArrayList<Object>();
		 ArrayList<Object> negAmount = new ArrayList<Object>();
		 ArrayList<Object> neuAmount = new ArrayList<Object>();
		 ArrayList<Object> posProbability = new ArrayList<Object>();
		 ArrayList<Object> negProbability = new ArrayList<Object>();
		 ArrayList<Object> neuProbability = new ArrayList<Object>();
		 ArrayList<Float> minAmountCounter = new ArrayList<Float>();
		 String[] parts; 
		 float counter = 0;
		 float positive = 0;
		 float negative = 0;
		 float neutral = 0;
		 float posProb = 0;
		 float negProb = 0;
		 float neuProb = 0;
		
		 // Erstellen der Haeufigkeitsliste der Woerter und der entsprechenden Values
		        try {
					writer = new PrintWriter("OccurrenceListTestSmooth.txt", "UTF-8");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	       	
				try {
					FileReader fileReader = new FileReader(fileName);
					
					BufferedReader br = new BufferedReader(fileReader);
					
					while ((line = br.readLine()) != null){
						parts = line.split("\t");
						words.add(parts[1]);
						values.add(parts[2]);
						types.add(parts[0]);
						//writer.println(line);
						System.out.println(line);
					}
					
					for (int i = 0; i < words.size(); i++){
						for (int x = 0; x < words.size(); x++){
							if (words.get(i).equals(words.get(x))){
								counter += 1;
								
								if (values.get(x).equals("positive")){
									positive += 1;
								}
								if (values.get(x).equals("negative")){
									negative += 1;
								}
								if (values.get(x).equals("neutral")){
									neutral += 1;
								}
							}
						}
						
						posProb = (positive+1) / (counter+3);
						negProb = (negative+1) / (counter+3);
						neuProb = (neutral+1) / (counter+3);
						posProbability.add(posProb);
						negProbability.add(negProb);
						neuProbability.add(neuProb);
						posAmount.add(positive);
						negAmount.add(negative);
						neuAmount.add(neutral);
						amount.add(counter);
						minAmountCounter.add(counter);
						positive = 0;
						negative = 0;
						neutral = 0;
						counter = 0;	
						posProb = 0;
						negProb = 0;
						neuProb = 0;
						
						
						
					}
					for (int i = 0; i < words.size(); i++){
//						if (minAmountCounter.get(i) > 9.0){
							writer.println(types.get(i) + "\t" + words.get(i) + "\t" + values.get(i) + "\t" + amount.get(i) + "\t" + posAmount.get(i) + "\t" + negAmount.get(i) + "\t" + neuAmount.get(i) + "\t" + posProbability.get(i) + "\t" + negProbability.get(i) + "\t" + neuProbability.get(i));
//						}
							
						
					}
					
					 br.close();
					 writer.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			// Zweiter Teil: Jedes Wort einmalig in der Liste
			
				
		PrintWriter writer2 = null;
		String fileName2 = "OccurrenceListTestSmooth.txt";
		String line2 = null;
		String[] parts2;
		int trueCounter = 0;
		int whileCounter = 0;
		ArrayList<String> words2 = new ArrayList<String>();
		ArrayList<String> lines = new ArrayList<String>();
		Boolean[] isWritten = new Boolean[words.size()];
		int equalCount = 0;
		for (int i = 0; i < isWritten.length; i++){
			isWritten[i] = false;
		}
		
		
		/*for (int i = 0; i < words.size(); i++){
			isWritten[i] = false;
			for (int x = 0; x <= i; x++){
				if (x != i || words.get(i).equals(words.get(x))){
					isWritten[i] = true;
				}
			}
		}*/
		
				try {
					writer2 = new PrintWriter("UniqueProbabilityListTestSmooth.txt", "UTF-8");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				try {
					
					FileReader fileReader2 = new FileReader(fileName2);
					
					BufferedReader br2 = new BufferedReader(fileReader2);
					
					while ((line2 = br2.readLine()) != null){
						parts2 = line2.split("\t");
						lines.add(line2);
						
						for (int i = 0; i < lines.size(); i++){
							if (parts2[1].equals(words.get(i))){
								trueCounter += 1;
							}
						}
						
						if (trueCounter == 1){
							writer2.println(words.get(whileCounter) + "\t" + posProbability.get(whileCounter) + "\t" + negProbability.get(whileCounter) + "\t" + neuProbability.get(whileCounter));
							
						}
						
						trueCounter = 0;
						//if (line2.equals(lines.get(whileCounter)) == false){
						//	writer2.println(line2);
						//}
						//words2.add(parts2[1]);
						/*for (int i = 0; i < words.size(); i++){
							if (parts2[1].equals(words.get(i))){
								trueCounter += 1;
							}
						}
						if (trueCounter == 1){
							writer2.println(line2);
							trueCounter = 0;
						}*/
						whileCounter += 1;
					}
					br2.close();
					writer2.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				;
				
	}

}
