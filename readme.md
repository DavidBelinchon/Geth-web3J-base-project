# Geth web3J base project

## Start geth

Go to geth folder and follow the steps, if you are never done this please visit this repo:

https://github.com/DavidBelinchon/Geth-for-beginners


## Dependencies

visit https://github.com/ethereum/solidity/releases downloand and copy solc.exe to solc folder

    ./solc.exe contract/simplestorage.sol --bin --abi --optimize -o contract/

Visit https://github.com/web3j/web3j/releases/tag/v3.4.0 extract folder to root project

    cd geth-web3J\web3j\bin

    ./web3j solidity generate ../../solc/contract/simplestorage.bin ../../solc/contract/simplestorage.abi -o ../../server/src/main/java -p com.hellokoding.springboot.services

Go to server folder an type 

    mvn spring-boot:run


## Let's check

To try this you need to change in all functions your keystorefile, sorry I will change this

### Endpoints 

Visit http://localhost:8080

    /hello?name=david
    /createWallet
    /deploy
    /load
    /setNumber?number=89
    /getNumber
    /getBalance?address=< addres >
    /sendEther?address=< addres >
    

Done! :)