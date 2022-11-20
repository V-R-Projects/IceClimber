#include <json-c/json.h>

#ifndef REQUESTHANDLER_H
#define REQUESTHANDLER_H
#define SIZE 2048
#define PORT 43008
#define IP_ADDR "192.168.0.28"

void process_request(char* request);
void write_file(char* request, char* filename);
void handle_type_request(int req);
#endif