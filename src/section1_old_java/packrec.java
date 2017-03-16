/*
 ID:denglee1
 LANG:JAVA
 PROG:packrec
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class packrec {

	/**
	 * @param args
	 */
	private static BufferedReader f;
	private static PrintWriter out;
	private static boolean[] flag;
	private static Rectangle[] rect;
	private static Rectangle result;
	private static int best;
	
	static class Rectangle{
		public int x =0;
		public int y =0;
		
		public Rectangle(){
			this.x = 0;
			this.y = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		f = new BufferedReader(new FileReader("packrec.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
		StringTokenizer st ;
		flag = new boolean[101];		
		rect = new Rectangle[4];
		result = new Rectangle();
		result.x = Integer.MAX_VALUE;
		result.y = Integer.MAX_VALUE;
		best = Integer.MAX_VALUE;
		for(int i=0;i<flag.length;i++){
			flag[i] =false; 
		}		
		for(int i=0;i<4;i++){
			rect[i] = new Rectangle();
			st = new StringTokenizer(f.readLine());
			rect[i].x = Integer.parseInt(st.nextToken());
			rect[i].y = Integer.parseInt(st.nextToken());
		}
		
		permutate(0);
		out.println(best);
		for(int i=1;i<=100;i++){
			if(flag[i]){
				out.println(i+" "+(best/i));
			}
		}
		out.close();
		System.exit(0);
		
	}
	
	public static void clear(){
		for(int i=1;i<flag.length;i++){
			flag[i] = false;
		}
	}
	
	public static void record(){
		if(result.x*result.y<best){
			best = result.x*result.y;
			clear();
		}
		if(result.x*result.y == best){
			flag[min(result.x,result.y)] = true;
		}
	}
	
	public static int min(int i,int j){
		return i<j?i:j;
	}
	public static int max(int i,int j){
		return i>j?i:j;
	}
	
	public static void cal(){
		//case 1
		result.x =0;result.y=0;
		for(int i =0;i<4;i++){
			result.x+=rect[i].x;
			result.y=max(result.y,rect[i].y);
		}
		record();
		
		//case 2
		result.x=0;result.y=0;
		for(int i=0;i<3;i++){
			result.x+=rect[i].x;
			result.y=max(result.y,rect[i].y);
		}
		result.x = max(result.x,rect[3].x);
		result.y +=rect[3].y;
		record();
		
		//case 3
		result.x=0;result.y=0;
		result.x = max(rect[0].x+rect[1].x,rect[2].x)+rect[3].x;
		result.y = max(rect[3].y,max(rect[0].y,rect[1].y)+rect[2].y);
		record();
		
		//case 4,5
		result.x=0;result.y=0;
		result.x = rect[0].x+max(rect[1].x,rect[2].x)+rect[3].x;
		result.y = max(rect[0].y,max(rect[1].y+rect[2].y,rect[3].y));
		record();
		
		// case 6
		result.x = 0;result.y = 0;
		result.x = rect[0].x+rect[1].x;
		result.y = max(rect[2].y+rect[0].y,rect[1].y+rect[3].y);
		if(rect[0].y<rect[1].y){
			result.x = max(result.x,rect[2].x+rect[1].x);
		}
		if((rect[0].y+rect[2].y)>rect[1].y){
			result.x = max(result.x,rect[2].x+rect[3].x);
		}
		if(rect[0].y>rect[1].y){
			result.x = max(result.x,rect[0].x+rect[3].x);
		}
		result.x = max(result.x,rect[2].x);
		result.x = max(result.x,rect[3].x);
		record();
		
		
	}
	
	public static void rotate(int k){
		if(k==4){
			cal();
		}else{
			rotate(k+1);swap(rect[k]);			
			rotate(k+1);swap(rect[k]);
		}
	}
	
	public static void permutate(int k){
		if(k ==4){
			rotate(0);
		}else{
			for(int i=k;i<4;i++){
				swap(k,i,rect);
				permutate(k+1);
				swap(k,i,rect);
			}
		}
	}
	
	public static void swap(Rectangle rect){
		int temp = rect.x;
		rect.x = rect.y;
		rect.y = temp;
	}
	
	public static void swap(int i,int j,Object [] object){
		Object temp = object[i];
		object[i] = object[j];
		object[j] = temp;		
	}

}
