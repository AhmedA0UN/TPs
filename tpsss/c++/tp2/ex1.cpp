#include <iostream>
using namespace std;

int main() {
    int* ptrInt = new int(42);
    cout << "Entier : " << *ptrInt << ", Adresse : " << ptrInt << endl;

    double* ptrDouble = new double(3.14);
    cout << "Réel : " << *ptrDouble << ", Adresse : " << ptrDouble << endl;

    delete ptrInt;
    delete ptrDouble;
    return 0;
}