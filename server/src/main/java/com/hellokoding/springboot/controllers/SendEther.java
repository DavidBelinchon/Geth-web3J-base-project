package com.hellokoding.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@Controller
public class SendEther {

    Web3j web3 = Web3j.build(new HttpService()); // defaults to http://localhost:8545/

    @RequestMapping("/getBalance")
    @ResponseBody
    public BigInteger getBalance(@RequestParam(value="address") String address) throws Exception {
        EthGetBalance ethGetBalance = web3
                .ethGetBalance(address, DefaultBlockParameterName.LATEST)
                .sendAsync()
                .get();

        BigInteger wei = ethGetBalance.getBalance();
        return wei;
    }

    @RequestMapping("/sendEther")
    @ResponseBody
    public String sendEther(@RequestParam(value="address") String address,@RequestParam(value="value") int value) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("password", "../geth/datadir/keystore/UTC--2018-06-27T08-19-13.834302800Z--b78ff8cf45b1a52869d342eaa5fab40350a24824");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3, credentials,
                address,
                BigDecimal.valueOf(value), Convert.Unit.ETHER)
                .send();

        return transferReceipt.toString();
    }

}
