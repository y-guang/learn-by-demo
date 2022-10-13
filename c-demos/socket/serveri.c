#include "csapp.h"

/*
 * imitate the echo of CSAPP book.
 */
int main(int argc, char const *argv[])
{
    int listenfd;
    char* LISTEN_PORT = "12323";
    printf("initialized.\n");

    // start listen.
    listenfd = Open_listenfd(LISTEN_PORT);
    printf("listen start, fd: %d\n", listenfd);

    // build connect by accept.
    int connfd;
    struct sockaddr_storage clientaddr;
    socklen_t clientlen = sizeof(struct sockaddr_storage);
    connfd = Accept(listenfd, (SA *)&clientaddr, &clientlen);
    /*
    Note: the Accept will wait any connect, otherwise, our process sleep.
    */
    printf("accepted, connfd: %d\n", connfd);

    // then, let's analysis the addr.
    struct sockaddr_in *addr_in = (struct sockaddr_in *)&clientaddr;
    printf("Addr Info\n");
    printf("addr: \t%s, \trow: 0x%X\n", inet_ntoa(addr_in->sin_addr), addr_in->sin_addr);
    printf("type: \t%d\n", addr_in->sin_family);
    printf("port: \t%d\n", addr_in->sin_port);

    // send halo
    printf("sending info\n");
    char* str0 = "[serveri] hi from serveri\n";
    Rio_writen(connfd, str0, strlen(str0));
    printf("sended\n");

    // read halo
    char buf[MAXLINE];
    printf("start reading from client\n");
    ssize_t n = Rio_readn(connfd, buf, 24); 
    /* 
    Note: Here is a magic num, 24. The same with client's receive part.
    Why?
    It's the exact length of the message sent from client.
    If it is diff from the exact length, the socket will be **blocked**.
    Following opts on socket cannot work normally.
    We should notice that.
    */
    printf("end read, readed len: %d\n", n);
    printf("%s", buf);

    // close the connect
    Close(connfd);
    printf("closed\n");
    return 0;
}
