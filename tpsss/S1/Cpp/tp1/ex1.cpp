#include<iostream>
using namespace std;

int main(){
    char t;
    float r,d;
    cout << "entrer le type dde transfaire : \n";
    cout << "si de kilometre vers le mile entrer m \n";
    cout << "si de mile vers le kilometre entrer k \n";
    cin >> t;
    cout << "entrer la distanse a convertir ";
    cin >> d;
    
    if(t=='m'){
               r=d/1.609;
               cout <<" la resultat est :" <<r ;
    }
    else if(t=='k'){
               r=d*1.609;
               cout <<" la resultat est :" <<r ;
               }  
    return 0;
    
    }
    
