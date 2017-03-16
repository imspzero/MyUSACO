/*
 ID:denglee1
 LANG:JAVA
 PROG:milk2
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class milk2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int lastTime = 0; 
		int restTime = 0;
		int flag = 0;
		BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int [] start = new int[N];
		int [] end = new int[N];
		for(int i =0;i<N;i++){
			st = new StringTokenizer(f.readLine());
			start[i]= Integer.parseInt(st.nextToken());
			end[i] = Integer.parseInt(st.nextToken());
		}
		int tempStart[] = new int[N];
		int tempEnd[] = new int[N];
//		for(int i =0;i<N;i++){
//			tempStart[i] = start[i];
//			tempEnd[i] = end[i];
//		}
		
		quickSort(start,end,0,N-1);
//		for(int i=0;i<N;i++){
//			for(int k = 0;k<N;k++){
//				if(start[i]==tempStart[k]){
//					end[i] = tempEnd[k];
//				}
//			}
//		}
//		for(int i=0;i<N;i++){
//			System.out.println(start[i] +" "+ end[i] +" " +(end[i]-start[i]));
//		}
		int s = start[0];
		int e = end[0];
		int lastTemp =end[0] - start[0]; 
		lastTime=end[0] - start[0];
		int restTemp =0;
		flag = 0;
		
		for(int i =0;i<N;i++){
			if(start[i]<=(start[flag]+lastTemp)){
				if(end[i]>(start[flag]+lastTemp)){
					lastTemp = end[i] - start[flag];
					if(lastTemp > lastTime){
						lastTime = lastTemp;
					}
				}				
			}else{
					restTemp = start[i] - (start[flag]+lastTemp);
					if(restTemp > restTime){
					     restTime = restTemp;
				    }
					if(lastTemp > lastTime){
						lastTime = lastTemp;
					}
					lastTemp = end[i]-start[i];
					flag = i;
			}
			 s = start[i];
			 e = end[i];
		}
		
		
//		System.out.println(lastTime+" "+restTime);
		out.println(lastTime+" "+restTime);
		out.close();
		System.exit(0);
		
	}
	
	public  static void quickSort(int []a,int []b,int p ,int r){
		if(p<r){
			int q = partition(a, b,p,r);
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
	public static void swap(int[] a,int []b,int i,int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		temp = b[i];
		b[i] = b[j];
		b[j] = temp;
	}

}
