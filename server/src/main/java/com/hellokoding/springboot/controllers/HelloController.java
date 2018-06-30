package com.hellokoding.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

@Controller
public class HelloController {
    
    Web3j web3 = Web3j.build(new HttpService()); // defaults to http://localhost:8545/
    
    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) throws IOException {
       //Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        model.addAttribute("clientVersion", clientVersion);
        model.addAttribute("name", name);
        return "hello";
    }
    
    @RequestMapping(value="/name", method = RequestMethod.GET)
    @ResponseBody
	public String name() throws IOException {
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        return clientVersion;
	}
    
    
}
