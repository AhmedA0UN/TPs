#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

int nbr_parts_gateau = 8;
pthread_mutex_t mutex; // déclaration

void* manger(void* arg) {
    int id = *(int*)arg;
    while (1) {
        pthread_mutex_lock(&mutex);
        if (nbr_parts_gateau > 0) {
            nbr_parts_gateau--;
            printf("Thread %d mange une part. Reste %d parts\n", id, nbr_parts_gateau);
            pthread_mutex_unlock(&mutex);
            sleep(1);
        } else {
            pthread_mutex_unlock(&mutex);
            printf("Thread %d : Gâteau terminé !\n", id);
            break;
        }
    }
    pthread_exit(NULL);
}

int main() {
    pthread_t threads[4]; // déclaration
    int ids[4]; // identifiants

    pthread_mutex_init(&mutex, NULL); // initialisation

    for (int i = 0; i < 4; i++) {
        ids[i] = i + 1;
        pthread_create(&threads[i], NULL, manger, &ids[i]);
    }

    for (int i = 0; i < 4; i++) {
        pthread_join(threads[i], NULL);
    }

    pthread_mutex_destroy(&mutex); // nettoyage
    return 0;
}

