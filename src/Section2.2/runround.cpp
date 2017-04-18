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

	int count_number[10] = {0};

	unsigned long int a = num;
	int digit_count=0;
	do{
		digit_count++;
		int i = a%10;
		count_number[i]++;
		if(count_number[i]>1){
			return false;
		}
		a = a/10;
	}while(a>=1);

//	cout<<"digit_count: "<<digit_count<<endl;

	// use digit_array as the travel flag for each digit
	bool digit_array[digit_count];
	for(int i = 0;i<digit_count;i++){
		digit_array[i] = false;
	}

	// transfer num into an int array
	int num_array[digit_count];
	unsigned long int tmp = num;
	for(int i = digit_count-1;i>=0;i--){
		num_array[i] = tmp%10;
		tmp = tmp/10;
	}

	cout<<"---"<<endl;
	for(int i = 0;i<digit_count;i++){
		cout<<num_array[i]<<" ";
	}
	cout<<endl;
	cout<<"---"<<endl;

//	sout<<num;
//	string num_str = sout.str();
//	sout.flush();

	// the result should has no zero digit. if has any, return false.
	for(int i = 0;i<digit_count;i++){
		if(num_array[i]==0){
			return false;
		}
	}

//	cout<<num_str<<endl;
//	cout<<"--------------------"<<endl;

	// travel each digit
	int loop_index=0;
	int total_step = 0;
	while(digit_array[loop_index]!=true){

//		cout<<"loop_index:"<<loop_index<<endl;
//		cout<<"digit:"<<num_str[loop_index]<<endl;
//		cout<<"digit to int:"<<num_str[loop_index]-'0'<<endl;

		digit_array[loop_index] = true;
		total_step = total_step + (num_array[loop_index]);

//		cout<<"total_step"<<total_step<<endl;

		loop_index = total_step%digit_count;

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

	while(!find_result){
		inc_num++;
		cout<<inc_num<<endl;
		find_result = check_is_round_num(inc_num);
	}

//	cout<<"1024: "<<check_is_round_num(1024)<<endl;
//	cout<<"100: "<<check_is_round_num(100)<<endl;
//	cout<<"111: "<<check_is_round_num(111)<<endl;

//	find_result = check_is_round_num(inc_num);
//	cout<<find_result<<endl;

	cout<<start_num<<endl;
	cout<<"---------------"<<endl;
	cout<<inc_num<<endl;

	fout<<inc_num<<endl;
	fin.close();
	fout.close();
	return 0;
}



