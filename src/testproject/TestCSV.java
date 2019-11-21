package testproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCSV {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String txtFileName = "C:\\Users\\veena\\OneDrive\\Documents\\sampletxt1.csv";
		List<String> list = new ArrayList<>();
		double avg=0,sum=0;
		int count=0;
		String date1="20/08/2018 11:50:02", date2="20/08/2018 23:50:02";
		SimpleDateFormat dt1 = new SimpleDateFormat(date1);
		SimpleDateFormat dt2 = new SimpleDateFormat(date2);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); // first example
		//SimpleDateFormat format2 = new SimpleDateFormat("MMMMM dd,yyyy"); // second example
		try {
			Date d1 = format1.parse( date1 );
			Date d2 = format1.parse( date2 );
		
		try (Stream<String> stream = Files.lines(Paths.get(txtFileName))) {
			 
			// 1 filtered flight numbers BA-731 and VA-421
			// 2. then only map filtered result to there airport names only
			// 3. store result into a List
			//list = 
			stream.filter(line -> line.contains("MacLaren") ).forEach(line -> {
				String[] str = line.split(",");
				try {
					//Double sum1 = 0.0;
					Date dt = format1.parse( str[1] );
					if(dt.compareTo(d1) >= 0 && dt.compareTo(d2) <=0) 
						System.out.println( str[1]+" "+str[2]);
					//else
					//	return "-1";	
				}
				catch(ParseException pe) {
					pe.printStackTrace();
				}
				
			});
			//.collect(Collectors.toList());
			
			// 4. print result list
			//list.forEach(System.out::println);
 
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		}
		catch(ParseException pe) {
			
			pe.printStackTrace();
		}

	}

}
