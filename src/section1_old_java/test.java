/*
 ID:denglee1
 LANG:JAVA
 TASK:test
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;



public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader f;
		try {
			f = new BufferedReader(new FileReader("test.in"));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
			StringTokenizer st = new StringTokenizer(f.readLine());
			int i1 = Integer.parseInt(st.nextToken());
			int i2 = Integer.parseInt(st.nextToken());
			
			out.println(i1+i2);
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
