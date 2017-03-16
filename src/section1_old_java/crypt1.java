/*
 ID:denglee1
 LANG:JAVA
 PROG:crypt1
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class crypt1 {
	private static int N;
	private static int count;
	private static int [] set;
	private static int [] product1;
	private static int [] product2;
	private static int [] result;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		
		StringTokenizer st =new StringTokenizer(f.readLine());
		N =Integer.parseInt(st.nextToken());
		set = new int[N];
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<N;i++){
			set[i]= Integer.parseInt(st.nextToken());
		}
//		for(int i=0;i<N;i++){
//			System.out.println(set[i]);
//		}
//		
		int []num = new int [5];
		product1 = new int [3];
		product2 = new int [3];
		result = new int [4];
		count =0;
		
//		int [] test = new int [4];
//		putIntoArray(7136, test);
//		for(int i=0;i<test.length;i++){
//			System.out.print(test[i]);
//		}
//		System.out.println(validate(test));
		
		tryRecur(0, num);
		
//		product1=putIntoArray(1235, result);
//		for(int i=0;i<result.length;i++){
//			System.out.println(result[i]);
//		}
		out.println(count);
		out.close();
		System.exit(0);
	}
	
	public static void tryRecur(int k,int [] num){
		if(k ==5){
			int pro1 =(num[0]*100+num[1]*10+num[2])*num[4];
			int pro2 =(num[0]*100+num[1]*10+num[2])*num[3];
			int resultTemp = pro1+pro2*10;
//			System.out.println(pro1 +" "+pro2+" "+resultTemp+" ");
			if(pro1>=1000||pro2>=1000||resultTemp>=10000){
				return;
			}
			product1=putIntoArray(pro1, product1);
			product2=putIntoArray(pro2, product2);
			result=putIntoArray(resultTemp, result);
//			System.out.println(validate(product1) +" "+validate(product2)+" "+validate(result)+" ");
			if(validate(product1)&&validate(product2)&&validate(result)){
				count++;
				return;
			} 
		}else{
			for(int i=0;i<N;i++){
				num[k]=set[i];
				tryRecur(k+1,num);
			}
		}
	}
	
	public static int []  putIntoArray(int num,int []numbers){
		int k =10;
		for(int i=0;i<numbers.length-2;i++){
			k*=10;
		}
		for(int i=0;i<numbers.length;i++){
			numbers[i]=num/k;
			num=num -numbers[i]*k;
			k/=10;
		}
		return numbers;
	}
	
	public static boolean validate(int [] numbers){
		int M=0;
		for(int i=0;i<numbers.length;i++){
			for(int k =0;k<set.length;k++){
				if(numbers[i]==set[k]){
					M++;
				}
			}
		}
		if(M<numbers.length)
			return false;
		return true;
	}
	
}		
