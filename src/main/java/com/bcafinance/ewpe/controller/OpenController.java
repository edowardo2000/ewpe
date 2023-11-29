package com.bcafinance.ewpe.controller;


import com.bcafinance.ewpe.dto.LoginDTO;
import com.bcafinance.ewpe.service.KaryawanService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/op")
public class OpenController {
    private KaryawanService usrService;
    private ModelMapper modelMapper;
    /*
        Wajib di class controller agar tidak terjadi cycle
     */
//    @Autowired
//    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public OpenController(KaryawanService usrService, ModelMapper modelMapper, AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usrService = usrService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostMapping("/v1/gt")
        public ResponseEntity<Object> getToken(@RequestBody LoginDTO loginDTO, HttpServletRequest request) throws Exception{

        return usrService.authManager(loginDTO,request);
    }


//    @GetMapping("/v1/checkToken")
//    public void testDoank(@RequestParam(value = "token") String strToken,@RequestParam(value = "tokenHash") String strHashToken)
//    {
//        System.out.println("Token is "+bCryptPasswordEncoder.matches(strToken,strHashToken));
//    }
}
