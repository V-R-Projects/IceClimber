#include <json-c/json.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "requestHandler.h"
#include "constants.h"

int players = 0;
/*
function hat gets the request from json and calls
handle_type_request
*/
void process_request(const char* request){
    write_file(request, "struct.json");
    json_object* obj = json_object_from_file("struct.json");
    json_object* r = json_object_object_get(obj, "request");
    int req = json_object_get_int(r);
    json_object_put(obj);
    handle_type_request(req);
    
}

/*
Function that writes request to filename
*/
void write_file(const char* request, const char* filename){
    FILE* file = fopen(filename, "w");
    fwrite(request, sizeof(char), strlen(request), file);
    fclose(file);
}

/*
Function that handles the jsons dependig the request
of the client.
*/
void handle_type_request(int req){
    if (req == -1){
        if (players == 2)return;
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

    }
    else if (req == 0){
        // Add Observer
    }
    else if (req == 1){
        json_object* obj = json_object_from_file("struct.json");
        write_file(json_object_to_json_string(obj), "client1.json");
        json_object_put(obj);


        json_object* clt1 = json_object_from_file("enemies1.json");
        write_file(json_object_to_json_string(clt1), "struct.json");
        json_object_put(clt1);
    }
    else if (req == 2){
        json_object* obj = json_object_from_file("struct.json");
        write_file(json_object_to_json_string(obj), "client2.json");
        json_object_put(obj);


        json_object* clt1 = json_object_from_file("enemies2.json");
        write_file(json_object_to_json_string(clt1), "struct.json");
        json_object_put(clt1);
    }
    else{
        // Upper two Observer consult
    }
}