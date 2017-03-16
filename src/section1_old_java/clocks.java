/*
 ID:denglee1
 LANG:JAVA
 PROG: clocks
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class clocks {

	/**
	 * @param args
	 */
	private static BufferedReader f;
	private static PrintWriter out;
	private static int[][] clocks;
	private static Queue queue;
	static class State{
		public int [][]clockState ;
		public List moveList = null; 
	}
	private static State initState;
	private static State state;
	private static List resultList;
	private static int length;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		f = new BufferedReader(new FileReader("clocks.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
		StringTokenizer st = null;
		clocks = new int[3][3];
		initState = new State();
		queue =new ArrayDeque(30000);
		resultList = new ArrayList();
		for(int i=0;i<3;i++){	
			st = new StringTokenizer(f.readLine());
			clocks[i][0]=Integer.parseInt(st.nextToken());
			clocks[i][1]=Integer.parseInt(st.nextToken());
			clocks[i][2]=Integer.parseInt(st.nextToken());
			
		}
		
//		for(int i=0;i<3;i++){
//			System.out.println(clocks[i][0]+" "+clocks[i][1]+" "+clocks[i][2]);
//		}
		initState.clockState = clocks;
		initState.moveList = new ArrayList();
		
		/*State state1 = new State();
		State state2 = null;
		
		state1.clockState = clocks;
		state1.moveList = new ArrayList();
		state2 = move1(state1);
		System.out.println("state1");
		for(int i=0;i<3;i++){			
			System.out.println(state1.clockState[i][0]+" "+state1.clockState[i][1]+" "+state1.clockState[i][2]);
		}
		System.out.println("state2");
		for(int i=0;i<3;i++){
			
			System.out.println(state2.clockState[i][0]+" "+state2.clockState[i][1]+" "+state2.clockState[i][2]);
		}
		state1.moveList.add(new Integer(2));
		
		for(int i=0;i<state1.moveList.size();i++){
			System.out.println(state1.moveList.get(i));
		}
		for(int i=0;i<state2.moveList.size();i++){
			System.out.println(state2.moveList.get(i));
		}*/
		
		search();
//		System.out.println(resultList.size());
//		for(int i=0;i<resultList.size();i++){
//			state = (State)resultList.get(i);
//			for(int k=0;k<state.moveList.size();k++){
//				System.out.print( state.moveList.get(k)+" ");
//			}
//			System.out.println();
//		}
//		for(int i=0;i<length-1;i++){
//			out.print(((State)resultList.get(0)).moveList.get(i)+" ");
//		}
//		out.println(((State)resultList.get(0)).moveList.get(length-1));
		
		for(int i=0;i<length-1;i++){
			out.print(state.moveList.get(i)+" ");
		}
		out.println(state.moveList.get(length-1));
		out.close();
		System.exit(0);
		
	}
	
	public static void generate(State state){
		State temp = move1(state);
		queue.add(temp);
		temp = move2(state);queue.add(temp);
		
		temp = move3(state);queue.add(temp);
		
		temp = move4(state);queue.add(temp);
		
		temp = move5(state);queue.add(temp);
		
		temp = move6(state);queue.add(temp);
		
		temp = move7(state);queue.add(temp);
		
		temp = move8(state);queue.add(temp);
		
		temp = move9(state);queue.add(temp);
		
	}
	
	public static boolean match(State state){
		boolean flag = true;
		for(int i=0;i<3;i++){
			for(int k=0;k<3;k++){
				if(state.clockState[i][k]!=12){
					flag=false;
				}
			}
		}		
		return flag;
	}
	public static void  search(){
		queue.add(initState);
		state = (State)queue.remove();
		
		// first sequence
		while(!match(state)){ 
			generate(state);
			state = (State)queue.remove();
		}
		resultList.add(state);
//		// other sequences with same length
		length=state.moveList.size();
//		while(state.moveList.size() == length){
//			if(match(state)){
//				resultList.add(state);
//			}
////			generate(state);
//			state = (State)queue.remove();
//		}
	}
	
	public static State move1(State state){
		State temp = new State();
		temp.clockState = new int[3][3];
		for(int i=0;i<3;i++){	
			temp.clockState[i][0] = state.clockState[i][0];
			temp.clockState[i][1] = state.clockState[i][1];
			temp.clockState[i][2] = state.clockState[i][2];
		}
		//ABDE
		if(temp.clockState[0][0]==12){
			temp.clockState[0][0]=3;
		}else{
			temp.clockState[0][0] = temp.clockState[0][0]+3;
		}
		
		if(temp.clockState[0][1]==12){
			temp.clockState[0][1]=3;
		}else{
			temp.clockState[0][1] = temp.clockState[0][1]+3;
		}
		if(temp.clockState[1][0]==12){
			temp.clockState[1][0]=3;
		}else{
			temp.clockState[1][0] = temp.clockState[1][0]+3;
		}
		if(temp.clockState[1][1]==12){
			temp.clockState[1][1]=3;
		}else{
			temp.clockState[1][1] = temp.clockState[1][1]+3;
		}
		
		
		temp.moveList = new ArrayList();
		for(int i=0;i<state.moveList.size();i++){
			temp.moveList.add(state.moveList.get(i));
		}		
		temp.moveList.add(new Integer(1));
		return temp;
	}
	public static State move2(State state){
		State temp = new State();
		temp.clockState = new int[3][3];
		for(int i=0;i<3;i++){	
			temp.clockState[i][0] = state.clockState[i][0];
			temp.clockState[i][1] = state.clockState[i][1];
			temp.clockState[i][2] = state.clockState[i][2];
		}
		//ABC
		if(temp.clockState[0][0]==12){
			temp.clockState[0][0]=3;
		}else{
			temp.clockState[0][0] = temp.clockState[0][0]+3;
		}		
		if(temp.clockState[0][1]==12){
			temp.clockState[0][1]=3;
		}else{
			temp.clockState[0][1] = temp.clockState[0][1]+3;
		}
		if(temp.clockState[0][2]==12){
			temp.clockState[0][2]=3;
		}else{
			temp.clockState[0][2] = temp.clockState[0][2]+3;
		}		
		temp.moveList = new ArrayList();
		for(int i=0;i<state.moveList.size();i++){
			temp.moveList.add(state.moveList.get(i));
		}		
		temp.moveList.add(new Integer(2));
		return temp;
	}
	public static State move3(State state){
		State temp = new State();
		temp.clockState = new int[3][3];
		for(int i=0;i<3;i++){	
			temp.clockState[i][0] = state.clockState[i][0];
			temp.clockState[i][1] = state.clockState[i][1];
			temp.clockState[i][2] = state.clockState[i][2];
		}
		//BCEF
		if(temp.clockState[0][1]==12){
			temp.clockState[0][1]=3;
		}else{
			temp.clockState[0][1] = temp.clockState[0][1]+3;
		}		
		if(temp.clockState[0][2]==12){
			temp.clockState[0][2]=3;
		}else{
			temp.clockState[0][2] = temp.clockState[0][2]+3;
		}
		if(temp.clockState[1][1]==12){
			temp.clockState[1][1]=3;
		}else{
			temp.clockState[1][1] = temp.clockState[1][1]+3;
		}
		if(temp.clockState[1][2]==12){
			temp.clockState[1][2]=3;
		}else{
			temp.clockState[1][2] = temp.clockState[1][2]+3;
		}		
		
		temp.moveList = new ArrayList();
		for(int i=0;i<state.moveList.size();i++){
			temp.moveList.add(state.moveList.get(i));
		}		
		temp.moveList.add(new Integer(3));
		return temp;
	}
	public static State move4(State state){
		State temp = new State();
		temp.clockState = new int[3][3];
		for(int i=0;i<3;i++){	
			temp.clockState[i][0] = state.clockState[i][0];
			temp.clockState[i][1] = state.clockState[i][1];
			temp.clockState[i][2] = state.clockState[i][2];
		}
		//ADG
		temp.clockState[0][0] = temp.clockState[0][0]==12?3:(temp.clockState[0][0]+3);
		temp.clockState[1][0] = temp.clockState[1][0]==12?3:(temp.clockState[1][0]+3);
		temp.clockState[2][0] = temp.clockState[2][0]==12?3:(temp.clockState[2][0]+3);
		
		temp.moveList = new ArrayList();
		for(int i=0;i<state.moveList.size();i++){
			temp.moveList.add(state.moveList.get(i));
		}		
		temp.moveList.add(new Integer(4));
		return temp;
	}
	
	public static State move5(State state){
		State temp = new State();
		temp.clockState = new int[3][3];
		for(int i=0;i<3;i++){	
			temp.clockState[i][0] = state.clockState[i][0];
			temp.clockState[i][1] = state.clockState[i][1];
			temp.clockState[i][2] = state.clockState[i][2];
		}
		//BDEFH
		temp.clockState[0][1] = temp.clockState[0][1]==12?3:(temp.clockState[0][1]+3);
		temp.clockState[1][0] = temp.clockState[1][0]==12?3:(temp.clockState[1][0]+3);
		temp.clockState[1][1] = temp.clockState[1][1]==12?3:(temp.clockState[1][1]+3);
		temp.clockState[1][2] = temp.clockState[1][2]==12?3:(temp.clockState[1][2]+3);
		temp.clockState[2][1] = temp.clockState[2][1]==12?3:(temp.clockState[2][1]+3);
		
		temp.moveList = new ArrayList();
		for(int i=0;i<state.moveList.size();i++){
			temp.moveList.add(state.moveList.get(i));
		}		
		temp.moveList.add(new Integer(5));
		return temp;
	}
	public static State move6(State state){
		State temp = new State();
		temp.clockState = new int[3][3];
		for(int i=0;i<3;i++){	
			temp.clockState[i][0] = state.clockState[i][0];
			temp.clockState[i][1] = state.clockState[i][1];
			temp.clockState[i][2] = state.clockState[i][2];
		}
		//CFI
		temp.clockState[0][2] = temp.clockState[0][2]==12?3:(temp.clockState[0][2]+3);
		temp.clockState[1][2] = temp.clockState[1][2]==12?3:(temp.clockState[1][2]+3);
		temp.clockState[2][2] = temp.clockState[2][2]==12?3:(temp.clockState[2][2]+3);
		
		temp.moveList = new ArrayList();
		for(int i=0;i<state.moveList.size();i++){
			temp.moveList.add(state.moveList.get(i));
		}		
		temp.moveList.add(new Integer(6));
		return temp;
	}
	public static State move7(State state){
		State temp = new State();
		temp.clockState = new int[3][3];
		for(int i=0;i<3;i++){	
			temp.clockState[i][0] = state.clockState[i][0];
			temp.clockState[i][1] = state.clockState[i][1];
			temp.clockState[i][2] = state.clockState[i][2];
		}
		//DEGH
		temp.clockState[1][0] = temp.clockState[1][0]==12?3:(temp.clockState[1][0]+3);
		temp.clockState[1][1] = temp.clockState[1][1]==12?3:(temp.clockState[1][1]+3);
		temp.clockState[2][0] = temp.clockState[2][0]==12?3:(temp.clockState[2][0]+3);
		temp.clockState[2][1] = temp.clockState[2][1]==12?3:(temp.clockState[2][1]+3);
		
		temp.moveList = new ArrayList();
		for(int i=0;i<state.moveList.size();i++){
			temp.moveList.add(state.moveList.get(i));
		}		
		temp.moveList.add(new Integer(7));
		return temp;
	}
	public static State move8(State state){
		State temp = new State();
		temp.clockState = new int[3][3];
		for(int i=0;i<3;i++){	
			temp.clockState[i][0] = state.clockState[i][0];
			temp.clockState[i][1] = state.clockState[i][1];
			temp.clockState[i][2] = state.clockState[i][2];
		}
		//GHI
		temp.clockState[2][0] = temp.clockState[2][0]==12?3:(temp.clockState[2][0]+3);
		temp.clockState[2][1] = temp.clockState[2][1]==12?3:(temp.clockState[2][1]+3);
		temp.clockState[2][2] = temp.clockState[2][2]==12?3:(temp.clockState[2][2]+3);
		
		temp.moveList = new ArrayList();
		for(int i=0;i<state.moveList.size();i++){
			temp.moveList.add(state.moveList.get(i));
		}		
		temp.moveList.add(new Integer(8));
		return temp;
	}
	public static State move9(State state){
		State temp = new State();
		temp.clockState = new int[3][3];
		for(int i=0;i<3;i++){	
			temp.clockState[i][0] = state.clockState[i][0];
			temp.clockState[i][1] = state.clockState[i][1];
			temp.clockState[i][2] = state.clockState[i][2];
		}
		//EFHI
		temp.clockState[1][1] = temp.clockState[1][1]==12?3:(temp.clockState[1][1]+3);
		temp.clockState[1][2] = temp.clockState[1][2]==12?3:(temp.clockState[1][2]+3);
		temp.clockState[2][1] = temp.clockState[2][1]==12?3:(temp.clockState[2][1]+3);
		temp.clockState[2][2] = temp.clockState[2][2]==12?3:(temp.clockState[2][2]+3);
		
		temp.moveList = new ArrayList();
		for(int i=0;i<state.moveList.size();i++){
			temp.moveList.add(state.moveList.get(i));
		}		
		temp.moveList.add(new Integer(9));
		return temp;
	}
	
	
	
	
	
	

}
