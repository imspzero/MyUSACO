/*
 ID: denglee1
 PROG: skidesign
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

int hill_num;

int main() {
	ifstream fin("skidesign.in");
	ofstream fout("skidesign.out");

	fin >> hill_num;
	int hills[hill_num];
	for (int i = 0; i < hill_num; i++) {
		fin >> hills[i];
	}

//	for (int i = 0; i < hill_num; i++) {
//		cout << hills[i]<<" ";
//	}
//	cout << endl;

	sort(hills, hills + hill_num);

//	for (int i = 0; i < hill_num; i++) {
//		cout << hills[i]<<" ";
//	}
//	cout << endl;

	int min_cost = 0;

	int shortest_height = 0;
	int tallest_height = 0;

	for(int i = 0;i<hill_num;i++){
		if(hills[i]>=tallest_height)tallest_height=hills[i];
		if(hills[i]<=shortest_height)shortest_height=hills[i];
	}


	if(tallest_height-shortest_height>17){
		min_cost = -1;
		for(int min_height = 0;min_height<=(100-17);min_height++){
				int max_height = min_height +17;
				int cost = 0;

				for(int i = 0;i<hill_num;i++){
					if(hills[i]<min_height){
						cost = cost + (min_height-hills[i])*(min_height-hills[i]);
					}else if(hills[i]>max_height){
						cost = cost + (hills[i]-max_height)*(hills[i]-max_height);
					}
				}

				if(cost<=min_cost||min_cost ==-1){
					min_cost = cost;
				}
			}
	}

	cout<<min_cost<<endl;

	fout<<min_cost<<endl;

	fin.close();
	fout.close();

	return 0;
}
