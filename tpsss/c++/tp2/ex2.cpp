#include <iostream>
using namespace std;

bool estEgalValeur(int a, int b) {
    return a == b;
}

bool estEgalAdresse(int* a, int* b) {
    return *a == *b;
}

bool estEgalReference(int& a, int& b) {
    return a == b;
}

int main() {
    int x = 10, y = 10, z = 20;
    cout << estEgalValeur(x, y) << endl;
    cout << estEgalAdresse(&x, &z) << endl;
    cout << estEgalReference(y, z) << endl;
    return 0;
}