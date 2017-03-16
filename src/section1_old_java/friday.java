/*
 ID:denglee1
 LANG:JAVA
 PROG: friday
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class friday {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader f ;
		PrintWriter out ;
		int [] frequency = new int[7];// sat,sun,mon,tus,wed,thu,fri --- 0 1 2 3 4 5 6 
		int days = 0; //days each month
		int totalDays = 0;
		
		try {
		     f = new BufferedReader(new FileReader("friday.in"));
		     out= new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		     
		     StringTokenizer st = new StringTokenizer(f.readLine());
		     int N = Integer.parseInt(st.nextToken());
		     	     
		     for(int i = 0;i<N;i++){
		    	 int K = 1900+i;
		    	 for(int j =1;j<=12;j++){
			    	 if(j == 2){
			    		 if(K %100 == 0){
				    		 if(K%400 == 0){
				    			 days = 29;
				    			 totalDays +=13;
					    		 frequency[find(totalDays)]++;
					    		 totalDays -=13;
					    		 totalDays+=days;
				    		 }else{
				    			 days = 28;
				    			 totalDays +=13;
					    		 frequency[find(totalDays)]++;
					    		 totalDays -=13;
					    		 totalDays+=days;
				    		 }
				    	 }else{
				    		 if(K%4 == 0){
				    			 days = 29;
				    			 totalDays +=13;
					    		 frequency[find(totalDays)]++;
					    		 totalDays -=13;
					    		 totalDays+=days;
				    		 }else{
				    			 days = 28;
				    			 totalDays +=13;
					    		 frequency[find(totalDays)]++;
					    		 totalDays -=13;
					    		 totalDays+=days;
				    		 }
				    	 }
			    	 }else if(j ==11|| j == 6||j ==9||j==4 ){
			    		 days = 30;
			    		 totalDays +=13;
			    		 frequency[find(totalDays)]++;
			    		 totalDays -=13;
			    		 totalDays+=days;
			    	 }else{
			    		 days = 31;
			    		 totalDays +=13;
			    		 frequency[find(totalDays)]++;
			    		 totalDays -=13;
			    		 totalDays+=days;
			    	 }
			     } 
		     }
		     out.println( frequency[6]+" "+ frequency[0]+" "+ frequency[1]+" "+ frequency[2]+" "+ frequency[3]
		               +" "+ frequency[4]+" "+frequency[5]);
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
	
	
	public static int find(int totalDays){
		int i = totalDays%7;
		return i;
	}
}
