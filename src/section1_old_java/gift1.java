/*
 ID:denglee1
 LANG:JAVA
 PROG: gift1 
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class gift1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int NP = 0;
		int m = 0;
		int average =0;
		String [] userNames = null;
		BufferedReader f;
		PrintWriter out;
		int [] gives;
		int [] receives;
		int [] count;
		
		try {
			f = new BufferedReader(new FileReader("gift1.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
			
			StringTokenizer st = new StringTokenizer(f.readLine());
			NP = Integer.parseInt(st.nextToken());
			userNames = new String[NP];
			gives = new int [NP];
			receives = new int [NP];
			count = new int[NP];
			
			for(int i = 0;i<NP;i++){
				st = new StringTokenizer(f.readLine());
				userNames[i] = st.nextToken();
			}
			for(int i = 0;i<NP;i++){
				st = new StringTokenizer(f.readLine());
				String name = st.nextToken();
				st = new StringTokenizer(f.readLine());
				int a  = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(b!=0){
				average = a/b;
				gives[find(userNames,name)]=(a/b)*b;
				}
				for(int k =0;k<b;k++){
					st = new StringTokenizer(f.readLine());
					name = st.nextToken();
					m = find(userNames,name);
					receives[m] += average;
				}
			}
			for(int i=0;i<NP;i++){
				out.println(userNames[i]+" "+(receives[i] - gives[i]));
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
	
	public static int find(String []userNames,String name){
		int k = 0;
		for(int i =0;i<userNames.length;i++){
			if(name.equals(userNames[i])){
				k = i; 
			}
		}
		return k;
	}

}
