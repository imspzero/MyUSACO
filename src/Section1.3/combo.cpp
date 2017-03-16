/*
 ID: denglee1
 PROG: combo
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <string>
#include <set>

using namespace std;

#define max 100

string to_String(int n) {
	int m = n;
	int i = 0, j = 0;
	char s[max];
	char ss[max];
	while (m > 0) {
		s[i++] = m % 10 + '0';
		m /= 10;
	}
	s[i] = '\0';

	i = i - 1;
	while (i >= 0) {
		ss[j++] = s[i--];
	}
	ss[j] = '\0';

	return ss;
}

/**
 *
 */
string get_right_number_str(int N, int valid_num, int variance) {

	if (N == 1) {
		return to_String(1);
	}

	if ((valid_num + variance) <= 0) {
		return to_String(N + (valid_num + variance));
	} else if (valid_num + variance > N) {
		return to_String((valid_num + variance - N));
	} else if (valid_num + variance <= N) {
		return to_String((valid_num + variance));
	} else {
		return "";
	}
}

int main() {

	ofstream fout("combo.out");

	int N;
	int john_combination[3], master_combination[3];
	ifstream fin("combo.in");
	fin >> N;
	fin >> john_combination[0] >> john_combination[1] >> john_combination[2];
	fin >> master_combination[0] >> master_combination[1]
			>> master_combination[2];
	fin.close();

	cout << "step 1" << endl;
	cout << N << endl;
	cout << john_combination[0] << john_combination[1] << john_combination[2]
			<< endl;
	cout << master_combination[0] << master_combination[1]
			<< master_combination[2] << endl;
	cout << "----" << endl;
	set<string> result_set;
	string temp;

	for (int i = -2; i <= 2; i++) {
		for (int j = -2; j <= 2; j++) {
			for (int k = -2; k <= 2; k++) {
				temp = get_right_number_str(N, john_combination[0], i) + ","
						+ get_right_number_str(N, john_combination[1], j) + ","
						+ get_right_number_str(N, john_combination[2], k);
//				cout << temp << endl;
				result_set.insert(temp);

				temp = get_right_number_str(N, master_combination[0], i) + ","
						+ get_right_number_str(N, master_combination[1], j) + ","
						+ get_right_number_str(N, master_combination[2], k);
//				cout << temp << endl;
				result_set.insert(temp);

			}
		}
	}

	cout << "step 3" << endl;

//	set<string>::iterator it;
//	for(it = result_set.begin();it!=result_set.end();it++){
//		cout<<*it<<endl;
//	}

	int result_size = result_set.size();

	fout << result_size << endl;

	cout << result_size << endl;
	cout << "step 4" << endl;

	fout.close();
	return 0;

}

