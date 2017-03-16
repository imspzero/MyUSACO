/*
 ID:denglee1
 LANG:JAVA
 PROG:transform 
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class transform {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		
		int N;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		int [][]start = new int[N][N];
		int [][]end = new int[N][N];
		
		for(int i=0;i<N;i++){
			st = new StringTokenizer(f.readLine());
			String temp = st.nextToken();
			for(int k=0;k<N;k++){
				if(temp.charAt(k)=='@'){
					start[i][k] = 0;
				}else{
					start[i][k] = 1;
				}
			}
		}
		for(int i=0;i<N;i++){
			st = new StringTokenizer(f.readLine());
			String temp = st.nextToken();
			for(int k=0;k<N;k++){
				if(temp.charAt(k)=='@'){
					end[i][k] = 0;
				}else{
					end[i][k] = 1;
				}
			}
		}
		
		int [][]temp;
		int output = 7;
		temp = rotation(N, 90, start);
		if(match(end, temp, N)){
			if(output>1)
				output =1;
		}
		
		temp = rotation(N, 180, start);
		if(match(end, temp, N)){
			if(output>2)
				output =2;
		}
		temp = rotation(N, 270, start);
		if(match(end, temp, N)){
			if(output>3)
				output =3;
		}
		
		temp = reflection(N, start);
		if(match(end, temp, N)){
			if(output>4)
				output =4;
		}
		
		for(int i = 0;i<3;i++){
			temp = reflection(N, start);
			temp = rotation(N, 90+i*90, temp);
			if(match(end, temp, N)){
				if(output>5)
					output =5;
			}
		}
		
		if(match(start, end, N)){
			if(output>6)
				output =6;
		}
		
		
		
//		int [][]s = rotation(N, 90, start);
//	
//		for(int i=0;i<N;i++){
//			for(int k=0;k<N;k++){
//				System.out.print(start[i][k]);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for(int i=0;i<N;i++){
//			for(int k=0;k<N;k++){
//			System.out.print(s[i][k]);
//			}
//			System.out.println();
//		}
//		System.out.println(match(start, s, N));
		out.println(output);
		out.close();
		System.exit(0);
	}
	
	public static boolean match(int [][]start,int [][]end,int N){
		boolean flag = true;
		for(int i = 0;i<N;i++){
			for(int k=0;k<N;k++){
				if(start[i][k] != end[i][k]){
					flag = false;
				}
			}
		}
		return flag;
	}
	public static int[][] rotation(int N,int degree,int [][]start){
		int [][]temp = new int[N][N];
		if(degree == 90){
			for(int i=0;i<N;i++){
				for(int k=0;k<N;k++){
					temp[i][k]=start[N-k-1][i];
				}
			}
		}else if(degree == 180){
			for(int i=0;i<N;i++){
				for(int k=0;k<N;k++){
					temp[i][k]=start[N-i-1][N-k-1];
				}
			}
		}else if(degree == 270){
			for(int i=0;i<N;i++){
				for(int k=0;k<N;k++){
					temp[i][k]=start[k][N-i-1];
				}
			}
		}
		return temp;
	}
	
	public static int[][] reflection(int N,int [][]start){
		int [][]temp = new int[N][N];
		for(int i=0;i<N;i++){
			for(int k =0;k<N;k++){
				temp[i][k]=start[i][N-k-1];
			}
		}
		return temp;
	}

}
