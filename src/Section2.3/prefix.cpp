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
int prefix_matrix[30][30]; //-1:init; 0:not chained;

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
int solve_by_dp(int b_index, int e_index) {

//	cout<<b_index<<e_index<<endl;
//	cout<<prefix_matrix[b_index][e_index];

	if (prefix_matrix[b_index][e_index] != -1) {
		return prefix_matrix[b_index][e_index];
	}

	int length = 0;

	for (int i = 0; i < v_primitives.size(); i++) {

		string primitive = v_primitives[i];

//		cout<<b_index<<e_index<<primitive<<endl;

		if (primitive.size() > (e_index - b_index + 1)) {
			length = 0;
//			return 0;
		} else {
			if (match(primitive, b_index)) {
				prefix_matrix[b_index][b_index + primitive.size() - 1] =
						primitive.size();
				prefix_matrix[b_index + primitive.size()][e_index] =
						solve_by_dp(b_index + primitive.size(), e_index);
				int tmp_len = prefix_matrix[b_index][b_index + primitive.size()
						- 1]
						+ prefix_matrix[b_index + primitive.size()][e_index];
				if (tmp_len >= length) {
					length = tmp_len;
				}
			}
		}
	}
	prefix_matrix[b_index][e_index] = length;
	return length;
}

int main() {

	for (int i = 0; i <= 30; i++) {
		memset(prefix_matrix[i], -1, sizeof(prefix_matrix[i]));
	}

//	for(int i =1;i<=100;i++){
//		for(int j=1;j<=100;j++){
//			cout<<" "<<prefix_matrix[i][j];
//		}
//		cout<<endl;
//	}

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

	int longest_prefix_length = solve_by_dp(0, sequence.size() - 1);
	cout << longest_prefix_length << endl;
	fout << longest_prefix_length;
	fin.close();
	fout.close();
	return 0;
}

