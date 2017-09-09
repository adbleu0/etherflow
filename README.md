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

## Logging

Change the log4j.properties file in main/resources folder to log to a file

## API calls

### List of calls supported

| API | Java call     | RPC API |
| ---------- | ------------- | -----:|
| Personal | Parity.listAccounts()  | [list accounts](https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_listaccounts)     |
| Personal | Parity.newAccount(password)  |  [new account](https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_newaccount)     |
| Personal | Parity.unlockAccount(address,password,duration)  | [unlock acc](https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_unlockaccount)  |
| Eth | Parity.getBalance(address) | [get balance](https://github.com/paritytech/parity/wiki/JSONRPC-eth-module#eth_getbalance) |
| Custom | Parity.getAllBalances() | Get all the balances on all your accs |
| Custom | Parity.sendEther() | Send ether from one account to another |
| Custom | Parity.deployContract() | Deploy a smart contract |

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

Get the balance for an address

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

0x00298510ab85f7f2af9cfe76c7369068e2ff2728:0x0<br/>
0x00298510ab85f7f2af9cfe76c7369068e2ff2728:0

0x008ae303513c1ed9cb24bcf243b806c828e94b60:0x152d02c7e14ad9b60140<br/>
0x008ae303513c1ed9cb24bcf243b806c828e94b60:99999999999999517000000

0x009ed82813cacf2577410213802371e7abed7c7d:0x0<br/>
0x009ed82813cacf2577410213802371e7abed7c7d:0

0x00a329c0648769a73afac7f9381e08fb43dbea72:0xffffffffffffffffffffffffffffffead2fd381eb509800000<br/>
0x00a329c0648769a73afac7f9381e08fb43dbea72:1606938044258990275541962092341162602422202993782792835301376

0x550a289a06670f1e2b7840b1a859307e95f6b1d2:0x0<br/>
0x550a289a06670f1e2b7840b1a859307e95f6b1d2:0

</code>

### Send Ether

Custom method to unlock an account and send ether from one account to another

<code>


jshell> Parity.sendEther("0x00a329c0648769a73afac7f9381e08fb43dbea72", "", 2, "0x550a289a06670f1e2b7840b1a859307e95f6b1d2", "0x12AF4","0x9184e72a000", "500");

2017-09-09 20:28:09 [main] INFO :: {
  "jsonrpc" : "2.0",
  "id" : "31307568187060",
  "method" : "personal_unlockAccount",
  "params" : [ "0x00a329c0648769a73afac7f9381e08fb43dbea72", "", "0x2" ]
}

2017-09-09 20:28:09 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : true,
  "id" : 31307568187060
}

2017-09-09 20:28:09 [main] INFO :: 0x4563918244f4000000

2017-09-09 20:28:09 [main] INFO :: {
  "jsonrpc" : "2.0",
  "id" : "31307594263602",
  "method" : "eth_sendTransaction",
  "params" : [ {
    "gas" : "0x12AF4",
    "from" : "0x00a329c0648769a73afac7f9381e08fb43dbea72",
    "to" : "0x550a289a06670f1e2b7840b1a859307e95f6b1d2",
    "value" : "0x4563918244f4000000",
    "gasPrice" : "0x9184e72a000"
  } ]
}

2017-09-09 20:28:09 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : "0x158638c678445a2d98305664e8ed47794843faa7f3c6d2d31801eee0c4d0c9e7",
  "id" : 31307594263602
}

2017-09-09 20:28:09 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : "0x158638c678445a2d98305664e8ed47794843faa7f3c6d2d31801eee0c4d0c9e7",
  "id" : 31307594263602
}

0x158638c678445a2d98305664e8ed47794843faa7f3c6d2d31801eee0c4d0c9e7

</code>

### Deploy a smart contract

Write a smart contract and compile it to .bin file, then deploy it using the jshell
and get a contract address.


<code>

jshell> String filePath = "/home/etherflow/src/test/resources/contract/Hello.bin"
filePath ==> "/home/pt/adbleu0/etherflow/src/test/resources/contract/Hello.bin"

jshell> Parity.deployContract("0x550a289a06670f1e2b7840b1a859307e95f6b1d2","test",2,"0x12AF4","0x9184e72a000",filePath, 5);

2017-09-09 20:33:22 [main] INFO :: {
  "jsonrpc" : "2.0",
  "id" : "31620204893744",
  "method" : "personal_unlockAccount",
  "params" : [ "0x550a289a06670f1e2b7840b1a859307e95f6b1d2", "test", "0x2" ]
}

2017-09-09 20:33:22 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : true,
  "id" : 31620204893744
}

2017-09-09 20:33:22 [main] INFO :: 0x6060604052341561000f57600080fd5b5b61016c8061001f6000396000f3006060604052361561003f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806327e235e3146100db575b5b346000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540192505081905550346000803073ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b005b34156100e657600080fd5b610112600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610128565b6040518082815260200191505060405180910390f35b600060205280600052604060002060009150905054815600a165627a7a723058207b58c5e4260d53b7b13e955d951f67be296950d4238582dd7d475e1cbef4dce70029

2017-09-09 20:33:22 [main] INFO :: {
  "jsonrpc" : "2.0",
  "id" : "31620269572509",
  "method" : "eth_sendTransaction",
  "params" : [ {
    "data" : "0x6060604052341561000f57600080fd5b5b61016c8061001f6000396000f3006060604052361561003f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806327e235e3146100db575b5b346000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540192505081905550346000803073ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b005b34156100e657600080fd5b610112600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610128565b6040518082815260200191505060405180910390f35b600060205280600052604060002060009150905054815600a165627a7a723058207b58c5e4260d53b7b13e955d951f67be296950d4238582dd7d475e1cbef4dce70029",
    "gas" : "0x12AF4",
    "from" : "0x550a289a06670f1e2b7840b1a859307e95f6b1d2",
    "gasPrice" : "0x9184e72a000"
  } ]
}

2017-09-09 20:33:22 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : "0x919ef4dcdb80cd4b41a6a5983ef36e9f8371a819f910af63a3c1e225cfbc9da6",
  "id" : 31620269572509
}

2017-09-09 20:33:27 [main] INFO :: {
  "jsonrpc" : "2.0",
  "id" : "31625332753681",
  "method" : "eth_getTransactionReceipt",
  "params" : [ "0x919ef4dcdb80cd4b41a6a5983ef36e9f8371a819f910af63a3c1e225cfbc9da6" ]
}

2017-09-09 20:33:27 [main] INFO :: {
  "jsonrpc" : "2.0",
  "result" : {
    "blockHash" : "0x1a3f198457c12f5030691303bed9a7758af300671ea2524b93c26c2c3ba985f1",
    "blockNumber" : "0x8",
    "contractAddress" : "0x92b3c847c505b1a77ffad79cd855229950be9a5d",
    "cumulativeGasUsed" : "0x12af4",
    "gasUsed" : "0x12af4",
    "logs" : [ ],
    "logsBloom" : "0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
    "root" : null,
    "transactionHash" : "0x919ef4dcdb80cd4b41a6a5983ef36e9f8371a819f910af63a3c1e225cfbc9da6",
    "transactionIndex" : "0x0"
  },
  "id" : 31625332753681
}

0x92b3c847c505b1a77ffad79cd855229950be9a5d

jshell>

</code>
