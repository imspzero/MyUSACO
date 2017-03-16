/*
 ID:denglee1
 LANG:JAVA
 PROG:milk
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class milk {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [] prices = new int[M];
		int [] amounts = new int[M];
		
		for(int i=0;i<M;i++){
			st = new StringTokenizer(f.readLine());
			prices[i]= Integer.parseInt(st.nextToken());
			amounts[i]= Integer.parseInt(st.nextToken());
		}
		quickSort(prices, amounts, 0, M-1);
		
//		for(int i=0;i<M;i++){
//			System.out.println(prices[i]+" "+amounts[i]);
//		}
//		
		int need = N;
		int cost = 0;
		for(int i=0;i<M;i++){
			if(need >= amounts[i]){
				cost += amounts[i]*prices[i];
				need -=amounts[i];	
			}else{
				cost += need*prices[i];
				need = 0;
				break;
			}
		}
		out.println(cost);
		out.close();
		System.exit(0);
		
	}
	
	public static void quickSort(int []a,int []b,int p,int r){
		if(p<r){
			int q = partition(a,b,p,r);
			quickSort(a,b,p,q-1);
			quickSort(a,b,q+1,r);
		}
	}
	
	public  static int partition(int [] a,int []b,int p,int r){
		int i = p;
		int j = r+1;
		int x = a[p];
		int y = b[p];
		while(true){
			while(a[++i]<x&&i<r);
			while(a[--j]>x);
			if(i>=j)break;
			swap(a,b, i, j);
		}
		a[p] = a[j];
		b[p] = b[j];
		b[j] = y;
		a[j] = x;
		return j;
	}
	
	public static void swap(int []a,int[]b,int i,int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
		temp = b[i];
		b[i] = b[j];
		b[j] = temp;
		
	}

}
