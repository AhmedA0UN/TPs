#include<iostream>
#include<vector>
using namespace std;


bool estpremier (int n) {
    for (int i=2 ;i< n/2 ;i++) {
        if(n % i ==0) return false;
    }
    return true;
}

void remplir (vector<int>& v) {
    int n ;
    cout << "donner la taille max " << endl ;
    cin >> n ;
    for (int i=0; i<n ; i++) {
        v.push_back(i);
    }
}


void afficher (vector<int>& v){
    for (int& el : v) {
        cout << "[" << el << "]" ;
    }
    cout << endl;
}


int main() {
    vector<int> t;
    remplir(t);
    vector<int> vp , vnp;
    for (int& el : t) {
        if (estpremier(el) == true) vp.push_back(el);
        else vnp.push_back(el);
    }
    cout << "les nbres premiers : " << endl ;
    afficher(vp);
    cout << "les nbres non premiers : " << endl ;
    afficher(vnp);    

    return 0;
}