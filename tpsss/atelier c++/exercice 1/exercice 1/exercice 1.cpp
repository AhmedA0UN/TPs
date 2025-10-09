#include <iostream>
#include<cstdlib>
using namespace std;

int main()
{
	int a;
	int* pa = &a;
	a = 42;
	cout << "la valeur de a est : " << *pa << endl;
	cout << "l'adresse de a est : "  << pa;

	return 0;
}
