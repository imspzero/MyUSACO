/*
 ID: denglee1
 PROG: wormhole
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <string>
#include <set>

using namespace std;

int hole_num;

int loop_pairing_count = 0;

struct Point {
	int x;
	int y;
};

Point points[13];

void swap(int perm_array[], int index_a, int index_b) {
	int temp = perm_array[index_a];
	perm_array[index_a] = perm_array[index_b];
	perm_array[index_b] = temp;
}

int getMaxPointX(Point points[]){

	int max_x = 0;
	for(int i = 1;i<=hole_num;i++){
		if(points[i].x >=max_x){
			max_x = points[i].x;
		}
	}
	return max_x;
}

bool move_and_check(Point points[],int perm_array[], int begin_x, int begin_y){

	bool can_travel_to_origin = false;
	int x = begin_x;
	int y = begin_y;
	int max_x = getMaxPointX(points);
//	cout<<x<<y<<endl;
	do{
		// move towards +x by 1 step or directly to next point x
		int next_x = max_x;
		bool has_more_points_in_this_line  = false;
		for(int i = 1;i<=hole_num;i++){
			if(points[i].y == y && points[i].x>x && points[i].x<=next_x){
				has_more_points_in_this_line = true;
				next_x = points[i].x;
			}
		}
		if(has_more_points_in_this_line){
			x = next_x;
		}else{
			x = max_x+1;
		}

//		cout<<x<<y<<endl;
		//check whether current position is a hole
		for(int point_num = 1;point_num<=hole_num;point_num++){
			Point point = points[point_num];

			if(x == point.x&&y == point.y){
				//step in a hole,switch position to the pair point

				int pair_point_num = 0;
				for(int perm_index = 1; perm_index<= hole_num ;perm_index++){
					if(perm_array[perm_index]==point_num){
//						cout<<"in point "<<point_num<<endl;
						if(perm_index%2==1){
							pair_point_num = perm_array[perm_index+1];
						}else{
							pair_point_num = perm_array[perm_index-1];
						}
						break;
					}
				}
				//change x and y
//				cout<<"switch to point "<<pair_point_num<<endl;
				x = points[pair_point_num].x;
				y = points[pair_point_num].y;
//				cout<<x<<y<<endl;

				break;
			}
		}

		//go back to the origin ,return true
		if(x ==begin_x && y==begin_y){
			return true;
		}

	}while(x<=max_x);

	return can_travel_to_origin;

}
//
//void test_move_and_check() {
//
//	int p_array[] = { 0, 1, 3, 2, 4 };
//
//	cout << "----------------test--------------" << endl;
//
//	for (int i = 1; i <= hole_num; i++) {
//		cout << p_array[i] << " ";
//	}
//	cout << endl;
//
//	bool result = move_and_check(points, p_array, 0, 0);
//
//	cout<<result<<endl;
//}


bool check_pairing_is_cycle(Point points[],int perm_array[]){

	bool is_cycle = false;

	for(int pair_num = 1;pair_num<=hole_num/2;pair_num++){

		Point pair_point_1 = points[perm_array[pair_num*2-1]];
		is_cycle = move_and_check(points,perm_array,pair_point_1.x,pair_point_1.y);
		if(is_cycle)return true;

		Point pair_point_2 = points[perm_array[pair_num*2]];
		is_cycle = move_and_check(points,perm_array,pair_point_2.x,pair_point_2.y);
		if(is_cycle)return true;
	}
	return false;
}

int find_perm_index_by_value(int perm_end_index, int perm_array[],
		int min_usable_num) {
	int k = 0;
	for (int j = 1; j <= perm_end_index; j++) {
		if (perm_array[j] == min_usable_num) {
			k = j;
			break;
		}
	}
	return k;
}

void find_pairs2(int perm_array[], bool usable_array[],
		int perm_start_index, int perm_end_index) {

	if (perm_start_index == perm_end_index) {
		//no useful point anymore, there is a pair.

		if (check_pairing_is_cycle(points, perm_array)) {
			loop_pairing_count++;
			for (int i = 1; i <= hole_num; i++) {
				cout << perm_array[i] << " ";
			}
			cout << endl;
		}
	}

	if (perm_start_index % 2 == 1) {
		//is the first point of a pair, always choose the smallest one
		int min_usable_num = 0;

		for (int i = 1; i <= hole_num; i++) {
			if (usable_array[i] == false) {
				min_usable_num = i;
				break;
			}
		}

		int perm_index = find_perm_index_by_value(perm_end_index, perm_array,
				min_usable_num);
		usable_array[min_usable_num] = true;
		swap(perm_array, perm_start_index, perm_index);
		find_pairs2(perm_array, usable_array, perm_start_index + 1,
				perm_end_index);
		usable_array[min_usable_num] = false;
		swap(perm_array, perm_start_index, perm_index);

	} else {
		//is the second point of a pair, can be any available number
		for (int point_num = 1; point_num <= hole_num; point_num++) {
			if (usable_array[point_num] == false) {

				int perm_index = find_perm_index_by_value(perm_end_index, perm_array,
								point_num);

				swap(perm_array, perm_start_index, perm_index);
				usable_array[point_num] = true;
				find_pairs2(perm_array, usable_array, perm_start_index + 1, perm_end_index);
				swap(perm_array, perm_start_index, perm_index);
				usable_array[point_num] = false;
			}
		}

	}
}

int main() {
	ifstream fin("wormhole.in");
	ofstream fout("wormhole.out");

	fin >> hole_num;

//	Point points[hole_num + 1];

	for (int i = 1; i <= hole_num; i++) {
		fin >> points[i].x >> points[i].y;
//		cout << points[i].x << points[i].y << endl;
	}

	int perm_array[hole_num + 1];

	bool usable_array[hole_num + 1];

	for (int i = 0; i <= hole_num; i++) {
		perm_array[i] = i;
	}

	for (int i = 0; i <= hole_num; i++) {
		usable_array[i] = false;
	}

//	cout << "--------------------------" << endl;
//	for (int i = 0; i <= hole_num; i++) {
//		cout << perm_array[i] << " ";
//	}
//	cout << endl;
	cout << "--------------------------" << endl;

	find_pairs2(perm_array, usable_array, 1, hole_num);
	cout << "--------------------------" << endl;
//	cout<<loop_pairing_count<<endl;
	fout<<loop_pairing_count<<endl;
	fin.close();
	fout.close();

//	test_move_and_check();

	return 0;
}

