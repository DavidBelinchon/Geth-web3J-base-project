package com.hellokoding.springboot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Controller
public class WalletController {

    Web3j web3 = Web3j.build(new HttpService()); // defaults to http://localhost:8545/

    @RequestMapping(value="/createWallet", method = RequestMethod.GET)
    @ResponseBody
    public String createWallet() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException {

        String password = "123456";
        String destDir = "../geth/datadir/keystore/";
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

        String walletFileName = WalletUtils.generateNewWalletFile(password, new File(destDir), false);
        WalletFile walletFile = objectMapper.readValue(new File(destDir + File.separator + walletFileName), WalletFile.class);

        System.out.println(walletFile.getAddress());

        return "0x"+walletFile.getAddress();

    }
}
