/*
 * Author: Margaret Linan
 * Organization: SII
 * Purpose: To find telomeric repeat patterns in NGS reads. 
 * Date: 8/30/2016
 * Instructions: Create a text file that contains four columns: read identifier, 
 * chromosome, position, and sequence, all from unmapped reads only.
 */
package patternseq1;
import java.io.*;
import java.util.regex.*;

/**
 *
 * @author margaret
 */
public class PatternSeq1 {
 
    public void searchReplace(String arg_sInputFileName, String arg_sOutputFileName) 
    
        throws Exception {
        BufferedReader dataReader = new BufferedReader(new FileReader(arg_sInputFileName));
        String sCurrentLine;
        try (PrintWriter out = new PrintWriter(new FileWriter(arg_sOutputFileName))) {
            while ((sCurrentLine = dataReader.readLine()) != null) {
                String [] aLines = sCurrentLine.split(" ");
                String [] aInfo= new String[] {aLines[0], aLines[1], aLines[2], aLines[3]};
                String patternONE = "(TTAGGG)";                
                String firstPass = Pattern.compile(patternONE).matcher(aInfo[3]).replaceAll("found1");
                String patternTWO = "(TCAGGG)";
                String secondPass = Pattern.compile(patternTWO).matcher(firstPass).replaceAll("found2");
                String patternTHREE = "(TGAGGG)";
                String thirdPass = Pattern.compile(patternTHREE).matcher(secondPass).replaceAll("found3");
                String patternFOUR = "(TTGGGG)";
                String fourthPass = Pattern.compile(patternFOUR).matcher(thirdPass).replaceAll("found4");
                
                String output = aInfo[0] + "\t" + aInfo[1] + "\t" + aInfo[2] + "\t" + fourthPass;
                out.println(output);   
            }
        }
    }

    public static void main(String[] args) {
            PatternSeq1 analyzer = new PatternSeq1();
                String args_sInputFileName = "C:\\Users\\margaret\\Desktop\\input.txt";
                String arg_sOutputFileName =  "C:\\Users\\margaret\\Desktop\\TelomereResults.txt";
		try { 
			analyzer.searchReplace(args_sInputFileName, arg_sOutputFileName);
		} catch (Exception exp){
		}
        
    }
}
