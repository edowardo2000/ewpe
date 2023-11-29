package com.bcafinance.ewpe.service;

import com.bcafinance.ewpe.configuration.OtherConfiguration;
import com.bcafinance.ewpe.core.IService;
import com.bcafinance.ewpe.dto.KelasCabangDTO;
import com.bcafinance.ewpe.handler.RequestCapture;
import com.bcafinance.ewpe.handler.ResponseHandler;
import com.bcafinance.ewpe.model.KelasCabang;
import com.bcafinance.ewpe.repo.KelasCabangRepo;
import com.bcafinance.ewpe.repo.LogRequestRepo;
import com.bcafinance.ewpe.util.LogTable;
import com.bcafinance.ewpe.util.LoggingFile;
import com.bcafinance.ewpe.util.TransformDataPaging;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class KelasCabangService implements IService<KelasCabang> {

    private KelasCabangRepo kelasCabangRepo;
    private String [] strExceptionArr = new String[2];
    private TransformDataPaging transformDataPaging = new TransformDataPaging();
    private Map<String,Object> mapz = new HashMap<>();
    @Autowired
    private LogRequestRepo logRequestRepo;

    @Autowired
    private LogService logService;
    private ModelMapper modelMapper;

    public KelasCabangService(KelasCabangRepo kelasCabangRepo) {
        strExceptionArr[0] = "KelasCabangService";
        this.kelasCabangRepo = kelasCabangRepo;
    }

    @Autowired
    public KelasCabangService(KelasCabangRepo kelasCabangRepo, ModelMapper modelMapper) {
        strExceptionArr[0] = "KelasCabangService";
        this.kelasCabangRepo = kelasCabangRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public ResponseEntity<Object> save(KelasCabang kelasCabang, HttpServletRequest request) {
        if(kelasCabang==null)
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
            kelasCabangRepo.save(kelasCabang);

        }catch (Exception e)
        {
            strExceptionArr[1] = "save(KelasCabang kelasCabang, HttpServletRequest request) --- LINE 59 \n"+ RequestCapture.allRequest(request);
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
    public ResponseEntity<Object> update(Long id, KelasCabang kelasCabang, HttpServletRequest request) throws Exception {
        Optional<KelasCabang> opKelasCabang;
        KelasCabang kelasCabangTrans;
        Boolean isValid = true;

        try{
            opKelasCabang =  kelasCabangRepo.findById(id);

            if(opKelasCabang.isEmpty())
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Valid",//message
                        HttpStatus.BAD_REQUEST,//httpstatus
                        null,//object
                        "FV002011",//errorCode Fail Validation modul-code 001 sequence 001 range 011 - 020
                        request
                );
            }

            kelasCabangTrans = opKelasCabang.get();
            kelasCabangTrans.setNamaKelas(kelasCabang.getNamaKelas());
            kelasCabangTrans.setListCabang(kelasCabang.getListCabang());
            kelasCabangTrans.setTarget(kelasCabang.getTarget());
            kelasCabangRepo.save(kelasCabangTrans);

        }
        catch (Exception e)
        {
            isValid = false;
            strExceptionArr[1] = "update(Long id, KelasCabang kelasCabang, HttpServletRequest request) --- LINE 119 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
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
        Optional<KelasCabang> kelasCabangTrans =  kelasCabangRepo.findById(id);

        if(kelasCabangTrans.isEmpty())
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
            kelasCabangRepo.deleteById(id);
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
    public ResponseEntity<Object> saveBatch(List<KelasCabang> lt, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<KelasCabang> kelasCabangTrans ;
        KelasCabangDTO kelasCabangDTO;
        try{
            kelasCabangTrans  = kelasCabangRepo.findById(id);//select kelasCabang dari db
            if(kelasCabangTrans==null)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002041",//errorCode Fail Validation modul-code 001 sequence 001 range 041 - 050
                        request
                );
            }

            kelasCabangDTO = modelMapper.map(kelasCabangTrans, new TypeToken<KelasCabangDTO>() {}.getType());

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
                kelasCabangDTO,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findByPage(Integer page, Integer size, String columFirst, String valueFirst, HttpServletRequest request) {
        Page<KelasCabang> pageKelasCabang;
        List<KelasCabangDTO> listKelasCabangDTO;
        try
        {
            Pageable pageable = PageRequest.of(page,size, Sort.by("idKelas"));
            if(columFirst.equals("namaKelas"))
            {
                pageKelasCabang = kelasCabangRepo.findByNamaKelasContains(pageable,valueFirst);
            }else {
                pageKelasCabang = kelasCabangRepo.findAll(pageable);
            }
            listKelasCabangDTO = modelMapper.map(pageKelasCabang.getContent(), new TypeToken<List<KelasCabangDTO>>() {}.getType());
            int dataSize = pageKelasCabang.getContent().size();

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
                transformDataPaging.mapDataPaging(mapz,pageKelasCabang,listKelasCabangDTO),//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findAllByPage(Integer page, Integer size, HttpServletRequest request) {
        Page<KelasCabang> pageKelasCabang;
        List<KelasCabangDTO> listKelasCabangDTO;
        try
        {
            Pageable pageable = PageRequest.of(page,size, Sort.by("idKelas"));
            pageKelasCabang = kelasCabangRepo.findAll(pageable);
            int dataSize = pageKelasCabang.getContent().size();
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
            listKelasCabangDTO = modelMapper.map(pageKelasCabang.getContent(), new TypeToken<List<KelasCabangDTO>>() {}.getType());


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
                transformDataPaging.mapDataPaging(mapz,pageKelasCabang,listKelasCabangDTO),//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        List<KelasCabang> listKelasCabang;
        List<KelasCabangDTO> listKelasCabangDTO;
        try{
            listKelasCabang = kelasCabangRepo.findAll();
            if(listKelasCabang.size()==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002071",//errorCode Fail Validation modul-code 001 sequence 001 range 071 - 080
                        request
                );
            }
            listKelasCabangDTO = modelMapper.map(listKelasCabang, new TypeToken<List<KelasCabangDTO>>() {}.getType());
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
                listKelasCabangDTO,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }
}
