/*
 ID: denglee1
 PROG: prefix
 LANG: C++
 */

#include <iostream>
#include <memory.h>
#include <string>
#include <fstream>
#include <vector>

using namespace std;

ifstream fin("prefix.in");
ofstream fout("prefix.out");
vector<string> v_primitives;
string sequence = "";
//int prefix_matrix[30][30]; //-1:init; 0:not chained;
int prefix_length[200000];

/**
 * given primitive and an index of sequence
 * check if sequence contains primitive in head.
 */
bool match(string primitive, int seq_index) {

	for (int i = 0; i < primitive.size(); i++) {
		if (primitive[i] != sequence[seq_index + i]) {
			return false;
		}
	}
	return true;
}

void test_input() {
	for (int i = 0; i < v_primitives.size(); i++) {
		cout << " " << v_primitives[i];
	}
	cout << endl;
	cout << sequence << endl;
}

/**
 * dynamic programming
 * try to solve the of sequence[s_index,e_index]
 *
 */
//int solve_by_dp(int b_index, int e_index) {
//
////	cout<<b_index<<e_index<<endl;
////	cout<<prefix_matrix[b_index][e_index];
//
//	if (prefix_matrix[b_index][e_index] != -1) {
//		return prefix_matrix[b_index][e_index];
//	}
//
//	int length = 0;
//
//	for (int i = 0; i < v_primitives.size(); i++) {
//
//		string primitive = v_primitives[i];
//
////		cout<<b_index<<e_index<<primitive<<endl;
//
//		if (primitive.size() > (e_index - b_index + 1)) {
//			length = 0;
////			return 0;
//		} else {
//			if (match(primitive, b_index)) {
//				prefix_matrix[b_index][b_index + primitive.size() - 1] =
//						primitive.size();
//				prefix_matrix[b_index + primitive.size()][e_index] =
//						solve_by_dp(b_index + primitive.size(), e_index);
//				int tmp_len = prefix_matrix[b_index][b_index + primitive.size()
//						- 1]
//						+ prefix_matrix[b_index + primitive.size()][e_index];
//				if (tmp_len >= length) {
//					length = tmp_len;
//				}
//			}
//		}
//	}
//	prefix_matrix[b_index][e_index] = length;
//	return length;
//}

/**
 * return the longest prefix of sequence[start_index,end_index]
 *
 * j<i<N
 * prefix_length[i] = max{ primitive_length[i,j] + prefix_length[j] }
 */
int solve_by_dp(int s_index){

	if(prefix_length[s_index]!=-1){
		return prefix_length[s_index];
	}

//	cout<<"step 1"<<endl;

	int max_length = 0;
	for(int i = 0;i<v_primitives.size();i++){
		string primitive = v_primitives[i];
//		cout<<"primitive: "<<primitive<<endl;
		int current_len = 0 ;

		if(primitive.size()>(sequence.size()-s_index)){
			current_len = 0;
		}else{
			if(match(primitive,s_index)){
				if(primitive.size()==(sequence.size()-s_index)){
					current_len = primitive.size();
					max_length = primitive.size();
					break;
				}else{
					current_len = primitive.size() + solve_by_dp(s_index+primitive.size());
				}
			}else{
				current_len = 0;
			}
		}

		if(current_len>=max_length){
			max_length = current_len;
		}
	}

	prefix_length[s_index] = max_length;

	return prefix_length[s_index];
}

int main() {

	memset(prefix_length, -1, sizeof(prefix_length));

//	for(int i =0;i<=30;i++){
//		cout<<prefix_length[i]<<endl;
//	}

	// get input data
	string tmp_str;
	while (fin >> tmp_str) {
		if (tmp_str == ".")
			break;
		v_primitives.push_back(tmp_str);
	}

	while (fin >> tmp_str) {
		sequence.append(tmp_str);
	}

	test_input();

	int longest_prefix_length = 0;

//	cout<<sequence.size()<<endl;

	for(int i = sequence.size()-1;i>=0;i--){
		solve_by_dp(i);
	}

	longest_prefix_length = prefix_length[0];

//	longest_prefix_length = solve_by_dp(0);
//	solve_by_dp(0, sequence.size() - 1);

//	cout<<"---------------"<<endl;
//	for(int i =0;i<=sequence.size();i++){
//		cout<<"p["<<i<<"]"<<prefix_length[i]<<endl;
//	}

	cout << longest_prefix_length << endl;
	fout << longest_prefix_length << endl;

	fin.close();
	fout.close();
	return 0;
}

