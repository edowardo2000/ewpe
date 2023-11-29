package com.bcafinance.ewpe.controller;

import com.bcafinance.ewpe.dto.CabangDTO;
import com.bcafinance.ewpe.model.Cabang;
import com.bcafinance.ewpe.service.CabangService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cabang")
public class CabangController {

    private CabangService cabangService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CabangController(CabangService cabangService) {
        this.cabangService = cabangService;
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@RequestBody CabangDTO cabangDTO, HttpServletRequest request)
    {
        Cabang cabang = modelMapper.map(cabangDTO, new TypeToken<Cabang>() {}.getType());
        return cabangService.save(cabang,request);
    }
    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody CabangDTO cabangDTO, HttpServletRequest request)
            throws Exception
    {
        Cabang cabang = modelMapper.map(cabangDTO, new TypeToken<Cabang>() {}.getType());
        return cabangService.update(id,cabang,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return cabangService.delete(id,request);
    }

    @PostMapping("/v1/svb")
    public ResponseEntity<Object> saveBatch(@Valid @RequestBody List<CabangDTO> listCabangDTO,
                                            HttpServletRequest request)
    {
        List<Cabang> listCabang = modelMapper.map(listCabangDTO, new TypeToken<List<Cabang>>() {}.getType());
        return cabangService.saveBatch(listCabang,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return cabangService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return cabangService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @GetMapping("/v1/fabp/{page}/{size}")
    public ResponseEntity<Object> findAllByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            HttpServletRequest request)
    {
        return cabangService.findAllByPage(page,size,request);
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return cabangService.findAll(request);
    }

}
