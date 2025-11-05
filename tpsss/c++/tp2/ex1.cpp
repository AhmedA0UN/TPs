#include <iostream>
using namespace std;

int main() {

    int a = 42;           
    float b = 3.14f;      

    cout << "Entier statique: " << a << ", adresse: " << &a << endl;
    cout << "Réel statique: " << b << ", adresse: " << &b << endl;



    int* ptrInt = new int(42);
    cout << "Entier : " << *ptrInt << ", Adresse : " << ptrInt << endl;

    double* ptrDouble = new double(3.14);
    cout << "Réel : " << *ptrDouble << ", Adresse : " << ptrDouble << endl;

    delete ptrInt;
    delete ptrDouble;
    return 0;
}