/*
 ID:denglee1
 LANG:JAVA
 PROG:dualpal 
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class dualpal {
	
	private static char[] digits;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		init();
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		int S;
		int N;
		int count =0;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		int i =S+1;
		while(count<N){
			int num=0;
			for(int k=2;k<=10;k++){
				if(validate(convert(k, i))){
					num++;
				}
			}
			if(num>=2){
				out.println(i);
				count++;
			}
			i++;
		}
		
		
		out.close();
		System.exit(0);
	}
	
	public static void init(){
		digits = new char[10];
		digits[0]='0';digits[1]='1';
		digits[2]='2';digits[3]='3';
		digits[4]='4';digits[5]='5';
		digits[6]='6';digits[7]='7';
		digits[8]='8';digits[9]='9';
//		digits[10]='A';digits[11]='B';
//		digits[12]='C';digits[13]='D';
//		digits[14]='E';digits[15]='F';
//		digits[16]='G';digits[17]='H';
//		digits[18]='I';digits[19]='J';
	}
	
	
	
	public static boolean validate(String square){
		boolean flag = true;
		int length = square.length();
		for(int i=0;i<square.length();i++){
			if(square.charAt(i)!=square.charAt(length-i-1)){
				flag =false;
			}
		}
		return flag;
	}
	
	public static String convert(int base,int squareNum){
		List num  = new ArrayList();
		while(squareNum!= 0){
			int k = squareNum%base;
			Character temp=new Character(digits[k]);
			num.add(temp);
			squareNum = squareNum/base;
		}
		char [] result = new char[num.size()];
		for(int i=0;i<num.size();i++){
			result[i]=((Character)(num.get(num.size()-1-i))).charValue();
		}
		return new String(result);
	}
}
