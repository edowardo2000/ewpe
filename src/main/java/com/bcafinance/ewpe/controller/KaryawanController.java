package com.bcafinance.ewpe.controller;

import com.bcafinance.ewpe.dto.KaryawanDTO;
import com.bcafinance.ewpe.model.Karyawan;
import com.bcafinance.ewpe.service.KaryawanService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/karyawan")
public class KaryawanController {

    private KaryawanService karyawanService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public KaryawanController(KaryawanService karyawanService) {
        this.karyawanService = karyawanService;
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@RequestBody KaryawanDTO karyawanDTO, HttpServletRequest request)
    {
        Karyawan karyawan = modelMapper.map(karyawanDTO, new TypeToken<Karyawan>() {}.getType());
        return karyawanService.save(karyawan,request);
    }
    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody KaryawanDTO karyawanDTO, HttpServletRequest request)
            throws Exception
    {
        Karyawan karyawan = modelMapper.map(karyawanDTO, new TypeToken<Karyawan>() {}.getType());
        return karyawanService.update(id,karyawan,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return karyawanService.delete(id,request);
    }

    @PostMapping("/v1/svb")
    public ResponseEntity<Object> saveBatch(@Valid @RequestBody List<KaryawanDTO> listKaryawanDTO,
                                            HttpServletRequest request)
    {
        List<Karyawan> listKaryawan = modelMapper.map(listKaryawanDTO, new TypeToken<List<Karyawan>>() {}.getType());
        return karyawanService.saveBatch(listKaryawan,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return karyawanService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return karyawanService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @GetMapping("/v1/fabp/{page}/{size}")
    public ResponseEntity<Object> findAllByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            HttpServletRequest request)
    {
        return karyawanService.findAllByPage(page,size,request);
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return karyawanService.findAll(request);
    }

}
