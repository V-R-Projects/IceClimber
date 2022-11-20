#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <json-c/json.h>
#include "requestHandler.h"


#define SIZE 2048
#define PORT 43007
#define IP_ADDR "127.0.0.1"

int main(void)
{

    json_object *jsonObject = json_object_from_file("struct.json");

    int server_socket, client_sock, client_size;
    struct sockaddr_in server_addr, client_addr;
    char server_message[SIZE], client_message[SIZE];

    // Clean buffers:
    memset(server_message, '\0', sizeof(server_message));
    memset(client_message, '\0', sizeof(client_message));

    // Create socket:
    server_socket = socket(AF_INET, SOCK_STREAM, 0);

    if (server_socket < 0)
    {
        printf("Error while creating socket\n");
        return -1;
    }
    printf("Socket created successfully\n");

    // Set port and IP:
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT);
    server_addr.sin_addr.s_addr = inet_addr(IP_ADDR);

    // Bind to the set port and IP:
    if (bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) < 0)
    {
        perror("Couldn't bind to the port\n");
        exit(1);
        return -1;
    }
    printf("Done with binding\n");

    while (1)
    {
        // Listen for clients:
        if (listen(server_socket, 1) < 0)
        {
            printf("Error while listening\n");
            return -1;
        }
        printf("\nListening for incoming connections.....\n");

        // Accept an incoming connection:
        client_size = sizeof(client_addr);
        client_sock = accept(server_socket, (struct sockaddr *)&client_addr, &client_size);

        if (client_sock < 0)
        {
            printf("Can't accept\n");
            return -1;
        }
        
        // Receive client's message:
        if (recv(client_sock, client_message, sizeof(client_message), 0) < 0)
        {
            printf("Couldn't receive\n");
            return -1;
        }
        printf("Msg from client: %s\n", client_message);

        // Sending the string
        strcpy(server_message, json_object_to_json_string(jsonObject));
        printf("The json file: %s\n", server_message);

        if(strcmp(client_message, "quit\n") == 0) strcpy(server_message, "Closing Server...");

        // Respond to client:

        if (send(client_sock, server_message, strlen(server_message), 0) < 0)
        {
            printf("Can't send\n");
            return -1;
        }

        shutdown(client_sock, 2);
        
        if(strcmp(client_message, "quit\n") == 0) break;
        
        // Clean buffers:
        memset(server_message, '\0', sizeof(server_message));
        memset(client_message, '\0', sizeof(client_message));
    }
    // Closing the socket:
    shutdown(server_socket, 2);
    printf("Server closed\n");

    return 0;
}