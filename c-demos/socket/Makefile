CC=gcc
CFLAGS=-g -O1 -Wall

COMMON := csapp.o 
CLIENT  := $(COMMON) client.o
SERVERI  := $(COMMON) serveri.o	

client   	:= $(COMMON) client.o 
serveri   	:= $(COMMON) serveri.o 


all: client serveri 

csapp.o: csapp.c
	$(CC) -c -o $@ $(CFLAGS) $^ 	 

client.o: client.c
	$(CC) -c -o $@ $(CFLAGS) $^ 	 

serveri.o: serveri.c
	$(CC) -c -o $@ $(CFLAGS) $^ 	

client: $(CLIENT)
	$(CC) -o $@ $^ -lpthread 

serveri: $(SERVERI)
	$(CC) -o $@ $^ -lpthread 

clean:
	rm -f *.o *~ *.exe client serveri csapp.o

