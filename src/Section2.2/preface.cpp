/*
 ID: denglee1
 PROG: preface
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <string>
#include <map>
#include <math.h>

using namespace std;

string roman_array[3500 + 1];
map<int, string> number_map;
map<string, int> result_map;

string get_roman_by_rule(int digit, int digit_length);

/**
 * recursion,dp,and memorize subproblems
 */
string transfer_to_roman(string roman_array[], map<int, string> number_map,
		int num) {

	if (roman_array[num] != "") {
		return roman_array[num];
	} else {
		int input_num = num;
		int digit_length = 0; // how long is the num
		string roman_str = "";

		while (input_num / 10 >= 1) {
			input_num = input_num / 10;
			digit_length++;
		}

		input_num = num;

//		cout << "------" << endl;
		while (input_num != 0) {

			if (roman_array[input_num] != "") {//subproblems
				roman_str = roman_str + roman_array[input_num];
				break;
			}

			int digit = input_num / pow(10, digit_length);

			int current_num = digit * pow(10, digit_length);
//			cout << current_num << endl;
			if (roman_array[current_num] != "") {
				roman_str = roman_str + roman_array[current_num];
			} else {
				roman_str = roman_str + get_roman_by_rule(digit, digit_length);
			}
			input_num = input_num - current_num;

			digit_length--;
		}

		return roman_str;
	}
}

/**
 * convert to roman number by rules
 */
string get_roman_by_rule(int digit, int digit_length) {

	string roman_str = "";

	if (digit <= 3) {
		for (int i = digit; i > 0; i--) {
			roman_str = roman_str + number_map[1 * pow(10, digit_length)];
		}
	} else if (digit == 4) {
		roman_str = number_map[1 * pow(10, digit_length)]
				+ number_map[5 * pow(10, digit_length)];
	} else if (digit == 5) {
		roman_str = roman_str + number_map[5 * pow(10, digit_length)];
	} else if (digit < 9) {
		roman_str = roman_str + number_map[5 * pow(10, digit_length)];
		for (int i = digit - 5; i > 0; i--) {
			roman_str = roman_str + number_map[1 * pow(10, digit_length)];
		}
	} else if (digit == 9) {
		roman_str = number_map[1 * pow(10, digit_length)]
				+ number_map[10 * pow(10, digit_length)];
	}

	return roman_str;
}

int main() {
	ifstream fin("preface.in");
	ofstream fout("preface.out");

	int number = 0;
	fin>>number;

	number_map[1] = "I";
	number_map[5] = "V";
	number_map[10] = "X";
	number_map[50] = "L";
	number_map[100] = "C";
	number_map[500] = "D";
	number_map[1000] = "M";

	for(int i = 1;i<=number;i++){
		roman_array[i] = transfer_to_roman(roman_array,number_map,i);
//		cout <<i<<"\t"<<roman_array[i]<< endl;
	}

//	cout << transfer_to_roman(roman_array, number_map, 351) << endl;
//	cout << "------------" << endl;
//	for (int i = 1; i <= 9; i++) {
//		cout << get_roman_by_rule(i, 0) << endl;
//	}

	// sum
	for(int i = 1;i<=number;i++){
		string temp = roman_array[i];
		for(int j = 0;j<temp.length();j++){
			char chr[1];
			chr[0] = temp[j];
			string current_char(chr,1);
			int count = result_map[current_char];
			if(count==0){
				count = 1;
			}else
				count++;
			result_map[current_char]=count;
//			cout<<"count "<<count<<endl;
//			cout<<"current_char "<<result_map[current_char]<<endl;
//			cout<<"|"<<current_char<<"|"<<endl;
		}
	}

	for(int i = 1;i<=1000;i++){
		if(number_map[i]!=""&&result_map[number_map[i]]>0){
			cout<<number_map[i]<<" "<<result_map[number_map[i]]<<endl;
			fout<<number_map[i]<<" "<<result_map[number_map[i]]<<endl;
		}
	}

	fin.close();
	fout.close();
	return 0;
}
