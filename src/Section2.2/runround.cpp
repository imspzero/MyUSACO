/*
 ID: denglee1
 PROG: runround
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

using namespace std;
stringstream sout;
unsigned long int start_num;
unsigned long int next_round_num;

bool check_is_round_num(unsigned long int num){

	unsigned long int a = num;
	int digit_count=0;
	do{
		digit_count++;
		a = a/10;
	}while(a>=1);

	cout<<digit_count<<endl;

	bool digit_array[digit_count];
	for(int i = 0;i<digit_count;i++){
		digit_array[i] = false;
	}
	sout<<num;
	string num_str = sout.str();
	cout<<num_str<<endl;
	cout<<"--------------------"<<endl;

	int loop_index=0;
	int total_step = 0;
	while(digit_array[loop_index]!=true){

//		cout<<"loop_index:"<<loop_index<<endl;
//		cout<<"digit:"<<num_str[loop_index]<<endl;
//		cout<<"digit to int:"<<num_str[loop_index]-'0'<<endl;

		digit_array[loop_index] = true;
		total_step = total_step + (num_str[loop_index]-'0');
//		cout<<"total_step"<<total_step<<endl;

		loop_index = total_step%num_str.length();
//		cout<<"next index:"<<loop_index<<endl;

	}

	if(loop_index!=0){
		//not ended up back where you started
		return false;
	}

	for(int i = 0;i<digit_count;i++){
		if(digit_array[i]==false){
			// not travel all digit
			return false;
		}
	}
	return true;
}

int main() {
	ifstream fin("runround.in");
	ofstream fout("runround.out");

	fin>>start_num;

	unsigned long int inc_num = start_num;
	bool find_result = false;

//	while(!find_result){
//		inc_num++;
//
//		find_result = check_is_round_num(inc_num);
//	}

	find_result = check_is_round_num(inc_num);

	cout<<find_result<<endl;
	cout<<"---------------"<<endl;
	cout<<inc_num<<endl;
	fout<<inc_num<<endl;
	fin.close();
	fout.close();
	return 0;
}



