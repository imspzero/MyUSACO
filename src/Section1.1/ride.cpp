/*
ID: denglee1
PROG: ride
LANG: C++
*/
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int change(char dex){
    return dex-'A'+1;
}

int main()
{
    ifstream fin("ride.in");
    ofstream fout("ride.out");

    string comet;
    string group;

    fin >> comet >> group;
    int numComet=1;
    int numGroup=1;

    for(int i = 0; i < comet.length(); i++){
        numComet *= change(comet[i]);
    }
    for(int i = 0; i < group.length(); i++){
        numGroup *= change(group[i]);
    }
    int resultC = numComet % 47;
    int resultG = numGroup % 47;
    if(resultC == resultG)
        fout << "GO" << endl;
    else
        fout << "STAY" << endl;
    fout.close();
    fin.close();
    return 0;
}
