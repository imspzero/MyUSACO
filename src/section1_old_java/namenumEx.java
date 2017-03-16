/*
 ID:denglee1
 LANG:JAVA
 PROG:namenum 
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;






public class namenumEx {
		
	private static char [][] keypad =new  char[10][3];
	private static BufferedReader f;
	private static PrintWriter out;
	private static char [] name;
	private static int count =0;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		init();
		char[] num ;//
		StringTokenizer st = new StringTokenizer(f.readLine());
		String str = st.nextToken();
		num = str.toCharArray();
		name = new char[num.length];
		findName(num.length, num);		
		System.out.println(count);
		out.close();
		f.close();
		System.exit(0);
	}
	
	public static void findName(int k,char[] num) throws IOException{
		if(k ==0){
			count++;
			System.out.println(name);
			if(validate(new String(name))){
				out.println(new String(name));
			}
		}else{
			for(int i =0;i<3;i++){
				name[num.length-k] = keypad[num[num.length-k]-48][i];
				findName(k-1,num);
			}
		}
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
	
	public static boolean validate(String name)throws IOException{
		boolean flag = false;
		BufferedReader f = new BufferedReader(new FileReader("dict.txt"));
		int i=1;
		for(int k=0;k<4617;k++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			String temp = st.nextToken();			
			if(temp.equals(name)){
				flag = true;
				break;
			}	
		}
		f.close();
		return flag;
		
	}
	
}
