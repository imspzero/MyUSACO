/*
 ID:denglee1
 LANG:JAVA
 PROG:namenum 
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class namenum {
	private static char [][] keypad =new  char[10][3];
	private static BufferedReader f;
	private static PrintWriter out;
	private static int count = 0;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		init();
		char [] name;
		StringTokenizer st = new StringTokenizer(f.readLine());
		String num = st.nextToken();
		f = new BufferedReader(new FileReader("dict.txt"));
		for(int i =1;i<=4617;i++){
			st = new StringTokenizer(f.readLine());
			name =st.nextToken().toCharArray(); 
			if(convert(name).equals(num)){
				count++;
				out.println(new String(name));
			}
		}
		if(count ==0){
			out.println("NONE");
		}
		out.close();
		System.exit(0);
	}
	
	
	public static void  init() throws IOException{

		f = new BufferedReader(new FileReader("namenum.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		keypad = new char[10][3];
		keypad[2][0]='A';keypad[2][1]='B';keypad[2][2]='C';
		keypad[3][0]='D';keypad[3][1]='E';keypad[3][2]='F';
		keypad[4][0]='G';keypad[4][1]='H';keypad[4][2]='I';
		keypad[5][0]='J';keypad[5][1]='K';keypad[5][2]='L';
		keypad[6][0]='M';keypad[6][1]='N';keypad[6][2]='O';
		keypad[7][0]='P';keypad[7][1]='R';keypad[7][2]='S';
		keypad[8][0]='T';keypad[8][1]='U';keypad[8][2]='V';
		keypad[9][0]='W';keypad[9][1]='X';keypad[9][2]='Y';
	}
	
	public static String convert(char []name){
		String number = null;
		char []num = new char[name.length];
		for(int i=0;i<name.length;i++){
			for(int m=2;m<10;m++){
				for(int n=0;n<3;n++){
					if(name[i]== keypad[m][n]){
						int bit = m+48;
						num[i] =(char) bit;
					}
				}
			}
		}
		number = new String(num);
		return number;
	}
	
}
