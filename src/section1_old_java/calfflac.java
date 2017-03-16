/*
 ID:denglee1
 LANG:JAVA
 PROG:calfflac
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class calfflac {
	private static int [] range;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int [] index = new int[20000];
		BufferedReader f = new BufferedReader(new FileReader("calfflac.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
	
		String input = new String();
		String temp = f.readLine();
		while(temp!=null){
			temp = temp.concat("\n");
			input=input.concat(temp);
			temp = f.readLine();
		}
		String text = new String(input);
//		System.out.println((int)'A'+" "+(int)'Z'+" "+(int)'a'+" "+(int)'z');
		
// 		transform
		int count=0;
		for(int i=0;i<text.length();i++){
			index[i]=i+count;
			if(text.charAt(i)<65 ||(text.charAt(i)>90&&text.charAt(i)<97)||text.charAt(i)>122||text.charAt(i)==32||text.charAt(i)==10){
				String font = text.substring(0, i);
				String back = text.substring(i+1, text.length());
				text = font.concat(back);
				count++;
				i--;
			}
		}

/*		System.out.println(text);
		for(int i=0;i<input.length();i++){
			System.out.print(i+" ");
		}
		System.out.println();
		System.out.println(input);
		for(int i=0;i<text.length();i++){
			System.out.print(index[i]+" ");
		}
		System.out.println();
		System.out.println(text);*/

		int length =1;
		int middleFlag =0;
		int flagStart =0;
/*		
 * 		method 1 
 *  	to slow
		for(int i=0;i<text.length();i++){
			for(int k =i+length;k<text.length();k++){
				if(validate(i, k, text)){
					if(length<k-i+1){
						length = k-i+1;
						flagStart =i;
					}
				}
			}
		}
		
		*/
//		System.out.println(index[flagStart]);
//		System.out.println(input.substring(index[flagStart],index[flagStart+length-1]));
//		System.out.println(length);
		
/*
 * 		method 2
 */
		
		for(int i=0;i<text.length();i++){
//			System.out.print(findPalindrome(i, text)+" ");
			int [] result = findPalindrome(i, text);
			if(length<result[0]){
				length = result[0];
				range = result;
				middleFlag = i;
			}
		}
		
		out.println(length);
		input = input.substring(index[range[1]],index[range[2]]+1);
//		System.out.println(input);
//		input = input.substring(index[flagStart],index[flagStart+length-1]+1);
//		System.out.println(input);
		
//		System.out.println(input.substring(0,80));
		
		out.println(input);
/*		if(input.length()<=80){
			out.println(input);
		}else{		
			out.println(input.substring(0,80));
			for(int i=1;i<input.length()/80+1;i++){
				if((i*80+79)<input.length()-1){
					out.println(input.substring(i*80, i*80+80));
				}else{
					out.println(input.substring(i*80,input.length()));
				}
			}
		}*/
//		out.println(input.substring(index[flagStart],index[flagStart+length-1]));
		
		out.close();
		System.exit(0);
		
		
	}
	
	
	public static int [] findPalindrome (int start,String text){
		int [] result = new int[3];
		int count1 =1;
		int count2 =0;
		int i = 1;
		if(start==0){
			if(text.charAt(start)==text.charAt(start+1)){
				result[1]=start;
				result[2]=start+1;
				result[0] = 2;
				return result;
			}
			result[1]=start;
			result[2]=start;
			result[0] = 1;
			return result;
		}
		if(start==(text.length()-1)){
			if(text.charAt(start)==text.charAt(start-1)){
				result[1]=start-1;
				result[2]=start;
				result[0] = 1;
				return result;
			}
			result[1]=start;
			result[2]=start;
			result[0] = 1;
			return result;
		}
		int i1=start,j1=start;
		int i2=start,j2=start;
		while(text.charAt(start-i)==text.charAt(start+i)||(Math.abs(text.charAt(start-i)-text.charAt(start+i))==32)){
			i1 =start-i;
			j1 =start+i;
			i++;
			count1 = count1+2;
			if((start-i)<0||(start+i)>(text.length()-1)){
				break;
			}

		}
		i=1;
		while(text.charAt(start-i+1)==text.charAt(start+i)||(Math.abs(text.charAt(start-i+1)-text.charAt(start+i))==32)){
			i2=start-i+1;
			j2=start+i;
			i++;
			count2 = count2+2;
			if((start-i+1)<0||(start+i)>(text.length()-1)){
				break;
			}

		}
		if(count1>count2){
			result[1]=i1;
			result[2]=j1;
			result[0] = count1;
		}else{
			result[1]=i2;
			result[2]=j2;
			result[0] = count2;
		}
		return result;
	}
	
	
	public static boolean validate(int start,int end,String text){
		boolean flag = true;
		for(int i=0;i<=(end-start)/2;i++){
			if(text.charAt(start+i) !=text.charAt(end-i)&&Math.abs(text.charAt(start+i)-text.charAt(end-i))!=32){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	
}
