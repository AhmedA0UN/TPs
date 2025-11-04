#include <iostream>
#include <cmath>
using namespace std;

float distance(float xa, float ya, float xb, float yb);

int main() {
    float d, xa, ya, xb, yb;
    cout << "Entrer les coordonnées des points A et B : ";
    cin >> xa >> ya >> xb >> yb;

    d = distance(xa, ya, xb, yb);
    cout << "La distance entre A et B est : " << d << endl;

    return 0;
}


float distance(float xa, float ya, float xb, float yb) {
    return sqrt(pow(xa - xb, 2) + pow(ya - yb, 2));
}