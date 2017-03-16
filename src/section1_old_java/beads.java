/*
 ID:denglee1
 LANG:JAVA
 PROG:beads
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class beads {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int count = 0; // try for each 
		
		BufferedReader f = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int num = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(f.readLine());
		String beads = st.nextToken();
		
		for(int i=0;i<beads.length();i++){
			int a1 = tryLeft(i, beads)+ tryRight(i+1, beads);
			int a2 = tryLeft(i-1, beads)+tryRight(i, beads);
			int temp = max(a1,a2);
			if(temp>count){
				count = temp;
			} 
			System.out.println();
		}
		if(count>beads.length()){
			count = beads.length();
		}
		
		out.println(count);
		out.close();
		System.exit(0);
	}
	
	public static int max(int a1,int a2){
		int temp =a1;
		if(a2>temp)temp =a2;
		return temp;
	}
	
	public static int tryLeft(int i,String beads){
		int count=0;
		if(i< 0 ){
			i = beads.length()-1;
		}
		int flag = i;
		char a = beads.charAt(i);
		if(a=='w'){
			int k =i;
			while(beads.charAt(k)=='w'){
				k--;
				if(k< 0 ){
					k = beads.length()-1;
				}
				if(flag == k){
					break;
				}
			}
			a = beads.charAt(k);
		}
		while(beads.charAt(i)==a ||beads.charAt(i)=='w'){
			count++;
			i --;
			if(i< 0 ){
				i = beads.length()-1;
			}
			if(flag == i){
				break;
			}
		}
		
		return count;
	}
	public static int tryRight(int i,String beads){
		int count = 0;
		if(i> beads.length()-1){
			i = 0;
		}
		int flag = i;
		char a = beads.charAt(i);
		if(a =='w'){
			int k =i;
			while(beads.charAt(k)=='w'){
				k++;
				if(k> beads.length()-1){
					k = 0;
				}
				if(flag ==k){
					break;
				}
			}
			a = beads.charAt(k);
		}
		while(beads.charAt(i) == a || beads.charAt(i)=='w'){
			count++;
			i++;
			if(i> beads.length()-1){
				i = 0;
			}
			if(flag == i){
				break;
			}
		}
		
		return count;
	}

}
