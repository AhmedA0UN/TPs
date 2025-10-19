#include <iostream>
using namespace std;

void incrementerAdresse(int* a) {
    (*a)++;
}

void permuterAdresse(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void incrementerReference(int& a) {
    a++;
}

void permuterReference(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}

int main() {
    int x = 5, y = 10;
    incrementerAdresse(&x);
    permuterAdresse(&x, &y);
    incrementerReference(x);
    permuterReference(x, y);
    return 0;
}