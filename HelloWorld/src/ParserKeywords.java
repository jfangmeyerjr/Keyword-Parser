import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class ParserKeywords {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParserKeywords c = new ParserKeywords();
		try {
			c.parse(args[0], args[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void parse(String input, String output) throws Exception {
		File csvData = new File(input);
		CSVParser parser = CSVParser.parse(csvData, Charset.forName("UTF-8"), CSVFormat.EXCEL);

		File file = new File(output);
	    file.createNewFile();
	    FileWriter writer = new FileWriter(file); 
	    CSVPrinter out = new CSVPrinter(writer, CSVFormat.EXCEL);
		// print headings
	    out.print("EID");
		out.print("Cites");
		out.print("Keyword");
		// go to next line
		out.println();

		
	    boolean header = true;
	    
		for (CSVRecord csvRecord : parser) {
			//skip header in input file
			if (header) {
				header = false;
			} else {
			
				String eid = csvRecord.get(2);
				String cites = csvRecord.get(1);
				String keywords = csvRecord.get(0);
				System.out.println(eid + " , "+cites+" , "+keywords);
			
				if (cites==null || cites.equals(""))
					cites = "0";
				
				StringTokenizer st = new StringTokenizer(keywords,";");
				while(st.hasMoreElements()) {
					String keyword = st.nextElement().toString().trim();
					out.print(eid);
					out.print(cites);
					out.print(keyword);
					out.println();
				}
				
			}
//			Authors a = new Authors(csvRecord.get(0), csvRecord.get(1));
//			System.out.println(a.getLink());
//			List<Person> authors = a.parse();
			
//			for (int i=0; i<authors.size(); i++) {
//				System.out.println(" - "+authors.get(i).getName()+" -- "+authors.get(i).getAffil());
//				
//				// Write CSV
//				out.print(a.getLink());
//				out.print(authors.get(i).getName());
//				out.print(authors.get(i).getAffil());
//				out.println();
//			}

		 }
		 
	    writer.flush();
	    writer.close();
	    
	}

}
