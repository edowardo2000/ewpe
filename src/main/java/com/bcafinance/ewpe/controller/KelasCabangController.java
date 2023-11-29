package com.bcafinance.ewpe.controller;

import com.bcafinance.ewpe.dto.KelasCabangDTO;
import com.bcafinance.ewpe.model.KelasCabang;
import com.bcafinance.ewpe.service.KelasCabangService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/kc")
public class KelasCabangController {

    private KelasCabangService kelasCabangService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public KelasCabangController(KelasCabangService kelasCabangService) {
        this.kelasCabangService = kelasCabangService;
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@RequestBody KelasCabangDTO kelasCabangDTO, HttpServletRequest request)
    {
        KelasCabang kelasCabang = modelMapper.map(kelasCabangDTO, new TypeToken<KelasCabang>() {}.getType());
        return kelasCabangService.save(kelasCabang,request);
    }
    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody KelasCabangDTO kelasCabangDTO, HttpServletRequest request)
            throws Exception
    {
        KelasCabang kelasCabang = modelMapper.map(kelasCabangDTO, new TypeToken<KelasCabang>() {}.getType());
        return kelasCabangService.update(id,kelasCabang,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return kelasCabangService.delete(id,request);
    }

    @PostMapping("/v1/svb")
    public ResponseEntity<Object> saveBatch(@Valid @RequestBody List<KelasCabangDTO> listKelasCabangDTO,
                                            HttpServletRequest request)
    {
        List<KelasCabang> listKelasCabang = modelMapper.map(listKelasCabangDTO, new TypeToken<List<KelasCabang>>() {}.getType());
        return kelasCabangService.saveBatch(listKelasCabang,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return kelasCabangService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return kelasCabangService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @GetMapping("/v1/fabp/{page}/{size}")
    public ResponseEntity<Object> findAllByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            HttpServletRequest request)
    {
        return kelasCabangService.findAllByPage(page,size,request);
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return kelasCabangService.findAll(request);
    }
}
