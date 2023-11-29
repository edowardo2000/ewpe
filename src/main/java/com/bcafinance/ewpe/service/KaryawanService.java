package com.bcafinance.ewpe.service;

import com.bcafinance.ewpe.configuration.OtherConfiguration;
import com.bcafinance.ewpe.core.IService;
import com.bcafinance.ewpe.core.security.JwtUtility;
import com.bcafinance.ewpe.dto.KaryawanDTO;
import com.bcafinance.ewpe.dto.KelasCabangDTO;
import com.bcafinance.ewpe.dto.LoginDTO;
import com.bcafinance.ewpe.handler.RequestCapture;
import com.bcafinance.ewpe.handler.ResponseHandler;
import com.bcafinance.ewpe.model.*;
import com.bcafinance.ewpe.repo.KaryawanRepo;
import com.bcafinance.ewpe.repo.LogRequestRepo;
import com.bcafinance.ewpe.util.LogTable;
import com.bcafinance.ewpe.util.LoggingFile;
import com.bcafinance.ewpe.util.TransformDataPaging;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class KaryawanService implements IService<Karyawan> {


    private KaryawanRepo karyawanRepo;
    private String [] strExceptionArr = new String[2];
    private TransformDataPaging transformDataPaging = new TransformDataPaging();
    private Map<String,Object> mapz = new HashMap<>();

    private JwtUtility jwtUtility;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private LogRequestRepo logRequestRepo;

    @Autowired
    private LogService logService;
    private ModelMapper modelMapper;

    public KaryawanService(KaryawanRepo karyawanRepo){
        strExceptionArr[0] = "KaryawanService";
        this.karyawanRepo = karyawanRepo;
    }

    @Autowired
    public KaryawanService(KaryawanRepo karyawanRepo, ModelMapper modelMapper){
        strExceptionArr[0] = "KaryawanService";
        this.karyawanRepo = karyawanRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<Object> save(Karyawan karyawan, HttpServletRequest request) {
        if(karyawan==null)
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002001",//errorCode Fail Validation modul-code 001 sequence 001 range 001 - 010
                    request
            );
        }

        try{
            karyawanRepo.save(karyawan);

        }catch (Exception e)
        {
            strExceptionArr[1] = "save(Karyawan karyawan, HttpServletRequest request) --- LINE 59 \n"+ RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Disimpan",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002001",//errorCode Fail Error modul-code 001 sequence 001 range 001 - 010
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Berhasil Disimpan",//message
                HttpStatus.CREATED,//httpstatus created
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> update(Long id, Karyawan karyawan, HttpServletRequest request) throws Exception {
        Optional<Karyawan> opKaryawan;
        Karyawan karyawanTrans;
        Boolean isValid = true;

        try {
            opKaryawan = karyawanRepo.findById(id);

            if (opKaryawan.isEmpty()) {
                return new ResponseHandler().generateResponse(
                        "Data tidak Valid",//message
                        HttpStatus.BAD_REQUEST,//httpstatus
                        null,//object
                        "FV002011",//errorCode Fail Validation modul-code 001 sequence 001 range 011 - 020
                        request
                );
            }

            karyawanTrans = opKaryawan.get();

            karyawanTrans.setNamaKaryawan(karyawan.getNamaKaryawan());
            karyawanTrans.setJabatan(karyawan.getJabatan());
            karyawanTrans.setCabang(karyawan.getCabang());
            karyawanTrans.setTglMasuk(karyawan.getTglMasuk());
            karyawanTrans.setSales(karyawan.getSales());
            karyawanTrans.setTarget(karyawan.getTarget());
            karyawanRepo.save(karyawanTrans);
        } catch (Exception e) {
            isValid = false;
            strExceptionArr[1] = "update(Long id, Karyawan karyawan, HttpServletRequest request) --- LINE 119 \n" + RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo, strExceptionArr, e, OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002011",//errorCode Fail Validation modul-code 001 sequence 001 range 011 - 020
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Berhasil Diubah",//message
                HttpStatus.CREATED,//httpstatus seharusnya no content 204 (permintaan berhasil tapi tidak ada content untuk dikirim dalam response)
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }


    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        Optional<Karyawan> karyawanTrans =  karyawanRepo.findById(id);

        if(karyawanTrans.isEmpty())
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002021",//errorCode Fail Validation modul-code 001 sequence 001 range 021 - 030
                    request
            );
        }

        try{
            karyawanRepo.deleteById(id);
        }catch (Exception e)
        {
            strExceptionArr[1] = "delete(Long id, HttpServletRequest request) --- LINE 164 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Dihapus",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002021",//errorCode Fail Error modul-code 001 sequence 001 range 021 - 030
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Berhasil Dihapus",//message
                HttpStatus.CREATED,//httpstatus seharusnya no content 204 (permintaan berhasil tapi tidak ada content untuk dikirim dalam response)
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> saveBatch(List<Karyawan> lt, HttpServletRequest request) {
        if(lt.isEmpty())
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002031",//errorCode Fail Validation modul-code 001 sequence 001 range 031 - 040
                    request
            );
        }

        try{
            karyawanRepo.saveAll(lt);
        }catch (Exception e)
        {
            strExceptionArr[1] = "saveBatch(List<Karyawan> lt, HttpServletRequest request) --- LINE 207 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Disimpan",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002031",//errorCode Fail Error modul-code 001 sequence 001 range 031 - 040
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Berhasil Disimpan",//message
                HttpStatus.CREATED,//httpstatus created
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Karyawan> karyawanTrans ;
        KaryawanDTO karyawanDTO;
        try{
            karyawanTrans  = karyawanRepo.findById(id);
            if(karyawanTrans==null)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002041",//errorCode Fail Validation modul-code 001 sequence 001 range 041 - 050
                        request
                );
            }

            karyawanDTO = modelMapper.map(karyawanTrans, new TypeToken<KaryawanDTO>() {}.getType());

        }catch (Exception e)
        {
            strExceptionArr[1] = "findById(Long id, HttpServletRequest request) --- LINE 241 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002041",//errorCode Fail Validation modul-code 001 sequence 001 range 041 - 050
                    request
            );
        }


        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                karyawanDTO,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findByPage(Integer page, Integer size, String columFirst, String valueFirst, HttpServletRequest request) {
        Page<Karyawan> pageKaryawan;
        List<KaryawanDTO> listKaryawanDTO;
        try
        {
            Pageable pageable = PageRequest.of(page,size, Sort.by("idKaryawan"));
            if(columFirst.equals("nama"))
            {
                pageKaryawan = karyawanRepo.findByNamaKaryawanContains(pageable,valueFirst);
            }else {
                pageKaryawan = karyawanRepo.findAll(pageable);
            }
            listKaryawanDTO = modelMapper.map(pageKaryawan.getContent(), new TypeToken<List<KaryawanDTO>>() {}.getType());
            int dataSize = pageKaryawan.getContent().size();

            if(dataSize==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002051",//errorCode Fail Validation modul-code 001 sequence 001 range 051 - 060
                        request
                );
            }

        }catch (Exception e)
        {
            strExceptionArr[1] = "findByPage(Integer page, Integer size, String columFirst, String valueFirst, HttpServletRequest request) --- LINE 304 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002051",//errorCode Fail Validation modul-code 001 sequence 001 range 051 - 060
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                transformDataPaging.mapDataPaging(mapz,pageKaryawan,listKaryawanDTO),//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findAllByPage(Integer page, Integer size, HttpServletRequest request) {
        Page<Karyawan> pageKaryawan;
        List<KaryawanDTO> listKaryawanDTO;
        try
        {
            Pageable pageable = PageRequest.of(page,size, Sort.by("idKaryawan"));
            pageKaryawan = karyawanRepo.findAll(pageable);
            int dataSize = pageKaryawan.getContent().size();
            if(dataSize==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002061",//errorCode Fail Validation modul-code 001 sequence 001 range 061 - 070
                        request
                );
            }
            listKaryawanDTO = modelMapper.map(pageKaryawan.getContent(), new TypeToken<List<KelasCabangDTO>>() {}.getType());


        }catch (Exception e)
        {
            strExceptionArr[1] = "findAllByPage(Integer page, Integer size, HttpServletRequest request) --- LINE 346 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002061",//errorCode Fail Validation modul-code 001 sequence 001 range 061 - 070
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                transformDataPaging.mapDataPaging(mapz,pageKaryawan,listKaryawanDTO),//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        List<Karyawan> listKaryawan;
        List<KaryawanDTO> karyawanDTOList;
        try{
            listKaryawan = karyawanRepo.findAll();
            if(listKaryawan.size()==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002071",//errorCode Fail Validation modul-code 001 sequence 001 range 071 - 080
                        request
                );
            }
            karyawanDTOList = modelMapper.map(listKaryawan, new TypeToken<List<KaryawanDTO>>() {}.getType());
        }catch (Exception e)
        {
            strExceptionArr[1] = " findAll(HttpServletRequest request) --- LINE 382 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002071",//errorCode Fail Validation modul-code 001 sequence 001 range 071 - 080
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                karyawanDTOList ,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }


    public UserDetails loadUserByUsername(String userName, String nip) throws UsernameNotFoundException {
        User user = null;
        Optional<Karyawan> optionalUsr = karyawanRepo.findByUserNameOrNip(userName, nip);

        if (optionalUsr.isEmpty()) {
            return user;
        }
        Karyawan usr = optionalUsr.get();
//        $2a$11$Owf6JAbkUwLBisLGnmmD8u41FRk/Hs5oEt2byIHz9ENOk00oqU4ii
        return new User(usr.getNip(), usr.getPassword(), new ArrayList<>());
    }
    public ResponseEntity<Object> authManager(LoginDTO loginDTO, HttpServletRequest request)//RANGE 006-010
    {
        /*
            Untuk memasukkan user ke dalam
         */
        UserDetails userDetails = loadUserByUsername(loginDTO.getUserName(), loginDTO.getNip());

        if(userDetails==null)
        {
            return new ResponseHandler().generateResponse(
                    "OTENTIKASI GAGAL",//message
                    HttpStatus.UNAUTHORIZED,//httpstatus
                    null,//object
                    "FV-Auth006",
                    request
            );
        }
        /*
            Isi apapun yang perlu diisi disini !!
         */
        Optional<Karyawan> usr = karyawanRepo.findByUserName(loginDTO.getUserName());
        Karyawan usrNext = usr.get();
        Map<String,Object> mapz = new HashMap<>();
        mapz.put("id",usrNext.getIdKaryawan());


        if (bCryptPasswordEncoder.matches( loginDTO.getPassword()+OtherConfiguration.getFlagPwdTrap(),userDetails.getPassword())){

            String token = jwtUtility.generateToken(userDetails,mapz);
            mapz.put("token",token);
            return new ResponseHandler().generateResponse(
                    "Otentikasi Berhasil",//message
                    HttpStatus.OK,//httpstatus created
                    mapz,//object
                    null,//errorCode diisi null ketika data berhasil disimpan
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "OTENTIKASI GAGAL",//message
                HttpStatus.UNAUTHORIZED,//httpstatus
                null,//object
                "FV-Auth006",
                request
        );
    }
}

