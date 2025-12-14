#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> 
#include <sys/wait.h>
  
int main() {

    printf("Processus 1: pid=%d\n", getpid());

    if (fork() == 0) {
        printf("Processus 2: pid=%d , ppid=%d\n", getpid(), getppid());

        printf("Je vais dormir pendant 20 secondes...\n");
        sleep(20);   // le processus se met en pause 20 secondes
        printf("Réveil après 20 secondes !\n");
        exit(0);
    }

    if (fork() == 0) {
        printf("Processus 3: pid=%d , ppid=%d\n", getpid(), getppid());

        if (fork() == 0) {
            printf("Processus 4: pid=%d , ppid=%d\n", getpid(), getppid());
            exit(0);
        }

        if (fork() == 0) {
            printf("Processus 5: pid=%d , ppid=%d\n", getpid(), getppid());
            exit(0);
        }

        wait(NULL);
        wait(NULL);
        exit(0);
    }

    wait(NULL);
    wait(NULL);

    return 0;
}
