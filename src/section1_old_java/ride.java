/*
 ID:denglee1
 LANG:JAVA
 PROG: ride 
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class ride {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int cometNum = 1;
		int groupNum = 1;
		BufferedReader f ;
		PrintWriter out;
		try {
			 f = new BufferedReader(new FileReader("ride.in"));
			 out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
			 
			 StringTokenizer st = new StringTokenizer(f.readLine());
			 String a = st.nextToken();

			 for(int i = 0;i<a.length();i++){
				 int b = a.charAt(i)-64;
				 cometNum*=b;
			 }

			 
			 st = new StringTokenizer(f.readLine());
			 a = st.nextToken();
			 for(int i = 0;i<a.length();i++){
				 int b = a.charAt(i)-64;

				 groupNum*=b;
			 }
			 
			 if(cometNum%47 == groupNum%47){
				 out.println("GO");
			 }else{
				 out.println("STAY");
			 }
			
			 out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.exit(0);
		
		
	}

}
