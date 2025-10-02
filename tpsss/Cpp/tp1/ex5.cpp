#include <iostream>
#include <cmath>
using namespace std;

double f(double x, bool &OK) {
    double expression = (x - 1) * (2 - x);
    if (expression >= 0) {
        OK = true;
        return sqrt(expression);
    } else {
        OK = false;
        return 0.0; // Valeur par défaut si non définie
    }
}

int main() {
    bool ok;
    double result = f(1.5, ok);
    if (ok)
        cout << "f(1.5) = " << result << endl;
    else
        cout << "f(1.5) n'est pas définie." << endl;

    result = f(3.0, ok);
    if (ok)
        cout << "f(3.0) = " << result << endl;
    else
        cout << "f(3.0) n'est pas définie." << endl;

    return 0;
}
