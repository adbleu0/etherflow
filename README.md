# etherflow
Java library for interacting with ethereum nodes using Json via RPC and IPC

## Install jdk 9

http://jdk.java.net/9/

## Test jshell

<code>
~/Downloads/jdk-9/bin$ ./jshell
|  Welcome to JShell -- Version 9
|  For an introduction type: /help intro

jshell> System.out.println("hello world")
hello world
jshell> /exit
</code>

## Install maven

https://maven.apache.org/install.html

## Clone etherflow project

<code>
git clone https://github.com/adbleu0/etherflow.git
</code>


## Build the project

<code>
~/etherflow$ mvn clean package
</code>

## Start the shell

Start the shell passing the etherflow jar in the class-path

<code>

~/etherflow$ ~/Downloads/jdk-9/bin/jshell -class-path ~/etherflow/target/etherflow-1.0-SNAPSHOT.jar
|  Welcome to JShell -- Version 9
|  For an introduction type: /help intro
jshell>

</code>
