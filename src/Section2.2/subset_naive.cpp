/*
 ID: denglee1
 PROG: subset
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <string>
#include <map>
#include <math.h>

using namespace std;
int N = 0;
int partitions = 0;


bool is_subset_sum_equal(bool num_array[]){

	int sum_A = 0;
	int sum_B = 0;

	for(int i = 1;i<=N;i++){
		if(num_array[i]==true){
			sum_A+=i;
		}
	}

	if(sum_A==(N*(N+1))/4)
		return true;
	return false;
}

void backtrack_search(bool num_array[],int num){

	int temp_sum = 0;

	for(int i = 1;i<=N;i++){
			if(num_array[i]==true){
				temp_sum += i;
			}
	}

	if(num>N){
		if(is_subset_sum_equal(num_array)){
			partitions++;
		}
		return;
	}else if(temp_sum==(N*(N+1))/4){
		partitions++;
		return;
	}else{
		temp_sum = temp_sum + num;
		if(!(temp_sum>(N*(N+1))/4)){
			num_array[num]=true; //pick num into subset A
			backtrack_search(num_array,num+1);
		}
		num_array[num]=false;//pick num into subset B
		backtrack_search(num_array,num+1);
	}
}

void get_sam_sum_partitions(int N){

	bool num_array[N+1];

	for(int i = 1;i<=N;i++){
		num_array[i] = false;
//		cout<<num_array[i]<<endl;
	}

	backtrack_search(num_array,1);
}

int main() {
	ifstream fin("subset.in");
	ofstream fout("subset.out");


	fin>>N;

	int sum = (N*(N+1))/2;
	cout<<sum<<endl;
	if(sum%2==0){
		get_sam_sum_partitions(N);
	}
	cout<<"-----"<<endl;
	cout<<partitions<<endl;
	fout<<partitions/2<<endl;
	fin.close();
	fout.close();
	return 0;
}
