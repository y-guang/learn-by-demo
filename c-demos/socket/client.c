#include "csapp.h"

int main(int argc, char const *argv[])
{
    char* PORT = "12323";
    char* HOST = "0.0.0.0";

    // connect
    printf("start connect\n");
    int clientfd = Open_clientfd(HOST, PORT);
    printf("connected, clientfd: %d\n", clientfd);
    
    // read from serveri
    char buf[MAXLINE];
    printf("start reading from clientfd\n");
    ssize_t n = Rio_readn(clientfd, buf, 26);
    printf("end read, readed len: %d\n", n);
    printf("%s", buf);

    // send to serveri
    char* str0 = "[client] hi from client\n";
    printf("send message to serveri\n");
    Rio_writen(clientfd, str0, strlen(str0));
    printf("send end\n");

    Close(clientfd);
    printf("closed\n");
    return 0;
}
