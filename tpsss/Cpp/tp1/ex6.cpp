#include <iostream>
using namespace std;

void swap_ref(int &a, int &b) {
    int temp = a;
    a = b;
    b = temp;
}

void swap_ptr(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void swap_val(int a, int b) {
    int temp = a;
    a = b;
    b = temp;

}

int main() {
    int x = 5, y = 10;

    cout << "Avant swap_ref: x = " << x << ", y = " << y << endl;
    swap_ref(x, y);
    cout << "Apres swap_ref: x = " << x << ", y = " << y << endl;

    swap_ptr(&x, &y);
    cout << "Apres swap_ptr: x = " << x << ", y = " << y << endl;

    swap_val(x, y);
    cout << "Apres swap_val (sans effet): x = " << x << ", y = " << y << endl;

    return 0;
}
