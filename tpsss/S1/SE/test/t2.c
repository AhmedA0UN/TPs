#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

char buffer;
int finished = 0;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

void* lecteur(void* arg) {
    while (!finished) {
        char c = getchar();
        pthread_mutex_lock(&mutex);
        buffer = c;
        if (c == 'F') finished = 1;
        pthread_cond_signal(&cond);
        pthread_mutex_unlock(&mutex);
    }
    return NULL;
}

void* afficheur(void* arg) {
    while (!finished) {
        pthread_mutex_lock(&mutex);
        pthread_cond_wait(&cond, &mutex);
        if (!finished) putchar(buffer);
        pthread_mutex_unlock(&mutex);
    }
    return NULL;
}

int main() {
    pthread_t t1, t2;
    pthread_create(&t1, NULL, lecteur, NULL);
    pthread_create(&t2, NULL, afficheur, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    return 0;
}