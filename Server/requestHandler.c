#include <json-c/json.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "requestHandler.h"

int players = 0;

void process_request(char* request){
    write_file(request, "struct.json");
    json_object* obj = json_object_from_file("struct.json");
    json_object* r = json_object_object_get(obj, "request");
    int req = json_object_get_int(r);
    json_object_put(obj);
    handle_type_request(req);
}

void write_file(char* request, char* filename){
    FILE* file = fopen(filename, "w");
    fwrite(request, sizeof(char), SIZE, file);
    fclose(file);
}

void handle_type_request(int req){
    switch (req)
    {
    case -1:
        if (players == 2)break;
        players++;
        char* buff = malloc(12*sizeof(char));
        sprintf(buff, "client%d.json", players);
        json_object* obj = json_object_from_file("struct.json");
        json_object* request = json_object_object_get(obj, "request");
        json_object_set_int(request, players);
        // correr la terminal del client correcto
        write_file(json_object_to_json_string(obj), buff);
        write_file(json_object_to_json_string(obj), "struct.json");
        json_object_put(obj);

        break;
    case 0:
        // Add Observer
        break;
    case 1:
        json_object* obj = json_object_from_file("struct.json");
        json_object* clt1 = json_object_from_file("client1.json");
        json_object* enemiesclt1 = json_object_object_get(clt1, "enemies");
        json_object_object_del(obj, "enemies");
        json_object_object_add(obj, "enemies", enemiesclt1);
        write_file(json_object_to_json_string(obj), "client1.json");
        write_file(json_object_to_json_string(obj), "struct.json");
        json_object_put(clt1);
        json_object_put(obj);
        break;
    case 2:
        //Player 2 consult
        break;
    
    default:
        // Upper two Observer consult
        break;
    }
}