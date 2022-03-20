package fr.univrennes1.istic.wikipediamatrix;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import fr.univrennes1.istic.wikipediamatrix.link.Html;
import fr.univrennes1.istic.wikipediamatrix.visitor.CSVExportVisitor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import org.apache.commons.csv.CSVRecord;
import org.jsoup.HttpStatusException;
import org.junit.Test;

public class BenchTest {
	
	/*
	*  the challenge is to extract as many relevant tables as possible
	*  and save them into CSV files  
	*  from the 300+ Wikipedia URLs given
	*  see below for more details
	**/
	@Test
	public void testBenchExtractors() throws Exception {
		
		String BASE_WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";
		// directory where CSV files are exported (HTML extractor) 
		String outputDirHtml = "output" + File.separator + "html" + File.separator;
		assertTrue(new File(outputDirHtml).isDirectory());
		// directory where CSV files are exported (Wikitext extractor) 
		String outputDirWikitext = "output" + File.separator + "wikitext" + File.separator;
		assertTrue(new File(outputDirWikitext).isDirectory());
		
		File file = new File("inputdata" + File.separator + "wikiurls.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
	    String url;
	    int nurl = 0;
	    while ((url = br.readLine()) != null) {
	       String wurl = BASE_WIKIPEDIA_URL + url; 
	       System.out.println("Wikipedia url: " + wurl);
	       // TODO: do something with the Wikipedia URL 
	       // (ie extract relevant tables for correct URL, with the two extractors)



	       // for exporting to CSV files, we will use mkCSVFileName 
	       // example: for https://en.wikipedia.org/wiki/Comparison_of_operating_system_kernels
	       // the *first* extracted table will be exported to a CSV file called 
	       // "Comparison_of_operating_system_kernels-1.csv"
	       String csvFileName = mkCSVFileName(url, 1);
	       System.out.println("CSV file name: " + csvFileName);
		   Html html = new Html(wurl);
		   try {Demo.main(html);

		   CSVParser parser;
		   // TODO : Change filepath to output here
		   FileReader Data = new FileReader("C:\\Users\\achan\\Documents\\Ensai\\wikipediamatrix-bench\\wikimatrix\\output\\html\\"+csvFileName);
		   parser = CSVParser.parse(Data, CSVFormat.DEFAULT);
		   int lines = parser.getRecords().size();
		   CSVExportVisitor datalines = new CSVExportVisitor();
		   List<String[]> list = datalines.export(html);
		   assertEquals(lines, list.size());

		   }catch (FileNotFoundException nfe){}catch (HttpStatusException hse){}



	       
	       nurl++;	       
	    }
	    
	    br.close();	    
	    assertEquals(nurl, 335);
	    



	}

	private String mkCSVFileName(String url, int n) {
		return url.trim() + "-" + n + ".csv";
	}

}
