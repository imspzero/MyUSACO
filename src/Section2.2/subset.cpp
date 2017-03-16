/*
 ID: denglee1
 PROG: subset
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <string>
#include <map>

using namespace std;
int N = 0;
int sum = 0;
//p[i][j] i is the max number;j is the sum;
//p[i][j] means the partitions count,given the max number and the sum
unsigned long long p[40][(39*40)/2+1];
unsigned long long partitions = 0;

ifstream fin("subset.in");
ofstream fout("subset.out");

/**
 * dynamic programming
 * p[i][j] i is the max number;j is the sum;
 * p[i][j] means the partitions count,given the max number and the sum
 * p[N][sum] is the final answer.
 */
void get_sum_partitions(int max_number,int sum){
	p[1][1]=1;

//	i>j: S(i,j) = S(i-1,j)
//
//	i=j: S(i,j) = S(i-1,j)+1
//
//	i<j:
//		j<i(i+1)/2 : S(i,j) = S(i-1,j)+s(i-1,j-i)
//		j=i(i+1)/2 : 1
//		j>i(i+1)/2 : S(i,j) = 0

	for(int i = 2;i<=N;i++){
		for(int j = 1;j<=(i*(i+1))/2;j++){
			if(i>j){
				p[i][j] = p[i-1][j];
			}else if(i==j){
				p[i][j] = p[i-1][j] +1;
			}else if(i<j){
				int temp_sum = (i*(i+1))/2;
				if(j<temp_sum){
					p[i][j] = p[i-1][j-i]+p[i-1][j];
				}else if(j==temp_sum){
					p[i][j] = 1;
				}else if(j>temp_sum){
					p[i][j] = 0;
				}
			}
//			fout<<"p["<<i<<"]["<<j<<"]="<<p[i][j]<<endl;
		}
	}

	return ;
}

int main() {


	fin>>N;

	//int p[40][(39*40)/4+1];
	for(int i =1;i<=39;i++){
		for(int j = 1;j<=(39*40)/2;j++){
			p[i][j] = 0;
		}
	}

	sum = (N*(N+1))/2;
	cout<<sum<<endl;

	if(sum%2==0){
		get_sum_partitions(N,sum/2);
		partitions = p[N][sum/2];

		cout<<"p["<<N<<"]["<<sum<<"]="<<partitions<<endl;

	}

//	unsigned long long a = 1332638454;
//	unsigned long long b = 828464300;
//	unsigned long long c = a+b;
//	cout<<c<<endl;

	cout<<"-----"<<endl;
	cout<<partitions/2<<endl;
	fout<<partitions/2<<endl;
	fin.close();
	fout.close();
	return 0;
}
