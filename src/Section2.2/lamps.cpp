/*
 ID: denglee1
 PROG: lamps
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <string>
#include <set>

using namespace std;
int N = 100;
int counter = 0; // the total number of button presses
int lamps_on[101];
int lamps_off[101];

//vector<int> lamps_on(1);
//vector<int> lamps_off(1);
set<string> result_set;
int button_times_is_odd[5];

//void testInput() {
//	cout << N << endl;
//	cout << counter << endl;
//	for (int i = 0; i < lamps_on.size(); i++) {
//		cout << lamps_on[i];
//	}
//	cout << endl;
//	for (int i = 0; i < lamps_off.size(); i++) {
//		cout << lamps_off[i];
//	}
//	cout << endl;
//}

void pressButton1(int lamps[]) {
	// those that are ON are turned OFF and those that are OFF are turned ON.
	for (int i = 1; i <= N; i++) {
		lamps[i] = lamps[i] == 0 ? 1 : 0;
	}
}

void pressButton2(int lamps[]) {
	// Changes the state of all the odd numbered lamps.
	for (int i = 1; i <= N; i++) {
		if (i % 2 != 0) {
			lamps[i] = lamps[i] == 0 ? 1 : 0;
		}
	}
}

void pressButton3(int lamps[]) {
	// Changes the state of all the even numbered lamps.
	for (int i = 1; i <= N; i++) {
		if (i % 2 == 0) {
			lamps[i] = lamps[i] == 0 ? 1 : 0;
		}
	}
}

void pressButton4(int lamps[]) {
	//  Changes the state of the lamps whose number is of the form 3xK+1 (with K>=0)
	for (int i = 0; i <= (N - 1) / 3; i++) {
		if ((3 * i + 1) <= N) {
			lamps[3 * i + 1] = lamps[3 * i + 1] == 0 ? 1 : 0;
		}
	}
}

bool is_final_lamps_valid(int lamps[]) {

	for (int i = 1; i <= N; i++) {
		if (lamps_on[i] == 1 && lamps[i] != 1) {
			return false;
		}
	}

	for (int i = 1; i <= N; i++) {
		if (lamps_off[i] == 1 && lamps[i] != 0) {
			return false;
		}
	}

	return true;
}

// check odd-even is appropriate
bool is_press_times_valid() {

	int odd_count = button_times_is_odd[1] + button_times_is_odd[2]
			+ button_times_is_odd[3] + button_times_is_odd[4];

	// total odd-press-time button should be smaller than the total count
	if (odd_count > counter) {
		return false;
	}

	//
	if ((odd_count % 2) != (counter % 2)) {
		return false;
	}

	return true;
}

void press_and_check() {

	// init lamps
	// When the party starts, all the lamps are ON and the counter C is set to zero
	int lamps[N + 1];
	for (int i = 1; i <= N; i++) {
		lamps[i] = 1;
	}

	// press button, according to odd-even; even: make no change
	if (button_times_is_odd[1] == 1) {
		pressButton1(lamps);
	}
	if (button_times_is_odd[2] == 1) {
		pressButton2(lamps);
	}
	if (button_times_is_odd[3] == 1) {
		pressButton3(lamps);
	}
	if (button_times_is_odd[4] == 1) {
		pressButton4(lamps);
	}

	// check result
	if (is_final_lamps_valid(lamps)) {
		string temp_str = "";

		for (int i = 1; i < N + 1; i++) {
			temp_str = temp_str + (char) (lamps[i] + '0');
		}

		result_set.insert(temp_str);
		return;
	}
}

void back_track_search(int button_num) {
	if (button_num > 4) {
		if (!is_press_times_valid()) {
			return;
		}
		press_and_check();

		for (int i = 1; i <= 4; i++) {
			cout << button_times_is_odd[i];
		}
		cout << endl;
		return;
	}
	button_times_is_odd[button_num] = 1;
	back_track_search(button_num + 1);

	button_times_is_odd[button_num] = 0;
	back_track_search(button_num + 1);
}

int main() {

	fstream fin("lamps.in");
	ofstream fout("lamps.out");

	// process input
	fin >> N;
	fin >> counter;

	int tmp = 0;
	fin >> tmp;
	while (tmp != -1) {
		lamps_on[tmp] = 1;
		fin >> tmp;
	}

	fin >> tmp;
	while (tmp != -1) {
		lamps_off[tmp] = 1;
		fin >> tmp;
	}

//	pressButton4(lamps);
//	for (int i = 1; i <= N; i++) {
//		cout<<lamps[i];
//	}
//	cout<<endl;
//	testInput();

	if (counter == 0) {
		cout << "0-----" << endl;
		string tmp = "";
		for (int i = 1; i <= N; i++) {
			tmp = tmp + '1';
		}
		fout << tmp << endl;
	} else {
		back_track_search(1);
		if (result_set.size() == 0) {
			fout << "IMPOSSIBLE" << endl;
		} else {
			set<string>::iterator it;
			for (it = result_set.begin(); it != result_set.end(); it++) {
				fout << *it << endl;
			}
		}
	}

	fin.close();
	fout.close();
	return 0;
}

