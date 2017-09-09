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

Only enable it for development if you have firewalls configured.

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

## API calls

### List of calls supported

| API | Java call     | RPC API |
| ---------- | ------------- | -----:|
| Personal | Parity.listAccounts()  | [list accounts](https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_listaccounts)     |
| Personal | Parity.newAccount(password)  |  [new account](https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_newaccount)     |
| Personal | Parity.unlockAccount(address,password,duration)  | [unlock acc](https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_unlockaccount)  |
| Eth | Parity.getBalance(address) | [get balance](https://github.com/paritytech/parity/wiki/JSONRPC-eth-module#eth_getbalance) |
| Custom | Parity.getAllBalances() | Get all the balances on all your accs |

### List Accounts

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
</code>

### New account

<code>
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

### Unlock account

<code>

jshell> Parity.unlockAccount("0x550a289a06670f1e2b7840b1a859307e95f6b1d2", "test", 10)

2017-09-09 16:52:04 [main] INFO :: {
  "jsonrpc" : "2.0",
  "id" : "19441721103892",
  "method" : "personal_unlockAccount",
  "params" : [ "0x550a289a06670f1e2b7840b1a859307e95f6b1d2", "test", "0xa" ]
}

2017-09-09 16:52:04 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : true,
  "id" : 19441721103892
}

{
  "jsonrpc" : "2.0",
  "result" : true,
  "id" : 19441721103892
}

</code>

### Get Balance

<code>
jshell> Parity.getBalance("0x550a289a06670f1e2b7840b1a859307e95f6b1d2")

2017-09-09 17:08:11 [main] INFO :: {
  "jsonrpc" : "2.0",
  "id" : "20409114911365",
  "method" : "eth_getBalance",
  "params" : [ "0x550a289a06670f1e2b7840b1a859307e95f6b1d2" ]
}

2017-09-09 17:08:12 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : "0x0",
  "id" : 20409114911365
}

{
  "jsonrpc" : "2.0",
  "result" : "0x0",
  "id" : 20409114911365
}
</code>

### Get All Balances

Custom method to list all the balances on all your accounts

<code>

jshell> Parity.getAllBalances()

0x00298510ab85f7f2af9cfe76c7369068e2ff2728:0x0
0x00298510ab85f7f2af9cfe76c7369068e2ff2728:0

0x008ae303513c1ed9cb24bcf243b806c828e94b60:0x152d02c7e14ad9b60140
0x008ae303513c1ed9cb24bcf243b806c828e94b60:99999999999999517000000

0x009ed82813cacf2577410213802371e7abed7c7d:0x0
0x009ed82813cacf2577410213802371e7abed7c7d:0

0x00a329c0648769a73afac7f9381e08fb43dbea72:0xffffffffffffffffffffffffffffffead2fd381eb509800000
0x00a329c0648769a73afac7f9381e08fb43dbea72:1606938044258990275541962092341162602422202993782792835301376

0x550a289a06670f1e2b7840b1a859307e95f6b1d2:0x0
0x550a289a06670f1e2b7840b1a859307e95f6b1d2:0

</code>
