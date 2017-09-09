# etherflow
Java library for interacting with ethereum nodes using Json via RPC and IPC

## Install parity

https://github.com/paritytech/parity

<code>
bash <(curl https://get.parity.io -Lk)
</code>

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

## Start Parity

The personal API is turned by be default in the config.toml for RPC calls for obvious reasons.

Only enable it for development if you know what your are doing and have firewalls
configured etc.

In this case we are demonstrating the API calls from the jshell to the parity node.

<code>

[rpc]

disable = false

port = 8545

interface = "local"

cors = "null"

apis = ["web3", "eth", "net", "parity", "traces", "rpc", "secretstore", "personal"]
</code>

Start parity

<code>
~/parity$ parity --chain dev
</code>

## Start the shell

Start the shell passing the etherflow jar in the class-path

<code>

~/etherflow$ ~/Downloads/jdk-9/bin/jshell --class-path ~/etherflow/target/etherflow.jar

|  Welcome to JShell -- Version 9
|  For an introduction type: /help intro
jshell>

</code>

## Execute API calls

<code>

~/adbleu0/etherflow$ ~/Downloads/jdk-9/bin/jshell --class-path ~/adbleu0/etherflow/target/etherflow.jar

|  Welcome to JShell -- Version 9
|  For an introduction type: /help intro

jshell> import com.adbleu.jshell.Parity;

jshell> Parity.listAccounts()

2017-09-09 15:51:31 [main] INFO :: {
  "jsonrpc" : "2.0",
  "id" : "15809296692549",
  "method" : "personal_listAccounts",
  "params" : [ ]
}

2017-09-09 15:51:32 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : [ "0x008ae303513c1ed9cb24bcf243b806c828e94b60", "0x009ed82813cacf2577410213802371e7abed7c7d", "0x00a329c0648769a73afac7f9381e08fb43dbea72" ],
  "id" : 15809296692549
}

{
  "jsonrpc" : "2.0",
  "result" : [ "0x008ae303513c1ed9cb24bcf243b806c828e94b60", "0x009ed82813cacf2577410213802371e7abed7c7d", "0x00a329c0648769a73afac7f9381e08fb43dbea72" ],
  "id" : 15809296692549
}

jshell> Parity.newAccount("test")

2017-09-09 16:05:58 [main] INFO :: {
  "jsonrpc" : "2.0",
  "id" : "16676284750693",
  "method" : "personal_newAccount",
  "params" : [ "test" ]
}

2017-09-09 16:05:58 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : "0x00298510ab85f7f2af9cfe76c7369068e2ff2728",
  "id" : 16676284750693
}

{
  "jsonrpc" : "2.0",
  "result" : "0x00298510ab85f7f2af9cfe76c7369068e2ff2728",
  "id" : 16676284750693
}

</code>

## List of calls supported

| Java call     | Example       | RPC API |
| ------------- |:-------------:| -----:|
| Parity.listAccounts()  |               | [list accounts](https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_listaccounts)     |
| Parity.newAccount(<password>)  |  Parity.newAccount("passwordforacc")            | [new account](https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_newaccount)     |
