package com.hellokoding.springboot.controllers;

import com.hellokoding.springboot.services.Simplestorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.math.BigInteger;

@Controller
public class SimpleStorageController {

    Web3j web3 = Web3j.build(new HttpService()); // defaults to http://localhost:8545/

    Simplestorage contract = null;

    @RequestMapping("/deploy")
    @ResponseBody
    public String deploy(@RequestParam(value="name", required=false, defaultValue="World") String name) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("password", "../geth/datadir/keystore/UTC--2018-06-27T08-19-13.834302800Z--b78ff8cf45b1a52869d342eaa5fab40350a24824");
        contract = Simplestorage.deploy(web3,credentials,ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT, new BigInteger("12")).send();
        String contractAddress = contract.getContractAddress();
        return contractAddress;
    }

    @RequestMapping("/load")
    @ResponseBody
    public String load(@RequestParam(value="address") String address) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("password", "../geth/datadir/keystore/UTC--2018-06-27T08-19-13.834302800Z--b78ff8cf45b1a52869d342eaa5fab40350a24824");
        contract = Simplestorage.load(address,web3,credentials,ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        return "Contract loaded";
    }

    @RequestMapping("/setNumber")
    @ResponseBody
    public String setNumber(@RequestParam(value="number", required=true) BigInteger number) throws Exception {
        TransactionReceipt transactionReceipt = contract.set(number).send();
        return transactionReceipt.toString();
    }

    @RequestMapping("/getNumber")
    @ResponseBody
    public BigInteger getNumber() throws Exception {
        BigInteger newValue = contract.get().send();
        return newValue;
    }
}
