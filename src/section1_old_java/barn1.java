/*
 ID:denglee1
 LANG:JAVA
 PROG:barn1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class barn1 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int M =Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int [] cows = new int[C];
		for(int i =0;i<C;i++){
			st = new StringTokenizer(f.readLine());
			cows[i] = Integer.parseInt(st.nextToken());
//			System.out.println(cows[i]);
		}
		quickSort(cows, 0, cows.length-1);
		int result =0;
		int []gaps = new int[C-1];
		for(int i=1;i<C;i++){
			gaps[i-1] = cows[i]-cows[i-1]-1;
		}
		quickSort(gaps, 0, gaps.length-1);
//		for(int i=0;i<gaps.length;i++){
//			System.out.println(gaps[i]);
//		}
		int gapSum=0;
		for(int i=0;i<M-1;i++){
			if((gaps.length-i-1)<0){
				break;
			}
			gapSum +=gaps[gaps.length-1-i];
			
		}
//		System.out.println(gapSum);
		result = cows[C-1]-cows[0]+1-gapSum;
/*
  // method 1
		int [] gaps = new int[M-1];
		for(int i=0;i<M-1;i++){
			gaps[i] = 0;
		}
		for(int i=1;i<C;i++){
			int temp = cows[i]-cows[i-1]-1;
			chooseGap(temp, gaps);
		}
		result = cows[C-1]-cows[0]+1-sum(gaps);
*/
//		System.out.println(result);
		
		out.println(result);
		out.close();
		System.exit(0);
	}
	
	public static void quickSort(int []a,int p,int r){
		if(p<r){
			int q = partition(a,p,r);
			quickSort(a,p,q-1);
			quickSort(a,q+1,r);
		}
	}
	
	public  static int partition(int [] a,int p,int r){
		int i = p;
		int j = r+1;
		int x = a[p];
		while(true){
			while(a[++i]<x&&i<r);
			while(a[--j]>x);
			if(i>=j)break;
			swap(a, i, j);
		}
		a[p] = a[j];
		a[j] = x;
		return j;
	}
	
	public static void swap(int []a,int i,int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
	}
	
	
/* 
 //method 1
	public static void chooseGap(int gap,int []gaps){
		boolean flag = false;
		for(int i=0;i<gaps.length;i++){
			if(gaps[i]==0){
				gaps[i]=gap;
				flag =true;
				return;
			}
		}
		if(!flag){                // no 0;
			for(int i=0;i<gaps.length;i++){
				if(gap>gaps[i]){
					gaps[i] = gap;
					break;
				}
			}
		}
		return;
	}
	
	public static int sum(int []gaps){
		int m=0;
		for(int i=0;i<gaps.length;i++){
			m+=gaps[i];
		}
		return m;
	}
*/
}
