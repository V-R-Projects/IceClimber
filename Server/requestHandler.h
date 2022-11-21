#include <json-c/json.h>

#ifndef REQUESTHANDLER_H
#define REQUESTHANDLER_H
void process_request(const char* request);
void write_file(const char* request, const char* filename);
void handle_type_request(int req);
#endif