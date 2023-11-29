package com.bcafinance.ewpe.controller;

import com.bcafinance.ewpe.dto.RegionalDTO;
import com.bcafinance.ewpe.model.Regional;
import com.bcafinance.ewpe.service.KelasCabangService;
import com.bcafinance.ewpe.service.RegionalService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/region")
public class RegionalController {

    private RegionalService regionalService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public RegionalController(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@RequestBody RegionalDTO regionalDTO, HttpServletRequest request)
    {
        Regional barang = modelMapper.map(regionalDTO, new TypeToken<Regional>() {}.getType());
        return regionalService.save(barang,request);
    }
    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody RegionalDTO regionalDTO, HttpServletRequest request)
            throws Exception
    {
        Regional regional = modelMapper.map(regionalDTO, new TypeToken<Regional>() {}.getType());
        return regionalService.update(id,regional,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return regionalService.delete(id,request);
    }

    @PostMapping("/v1/svb")
    public ResponseEntity<Object> saveBatch(@Valid @RequestBody List<RegionalDTO> listRegionalDTO,
                                            HttpServletRequest request)
    {
        List<Regional> listRegional = modelMapper.map(listRegionalDTO, new TypeToken<List<Regional>>() {}.getType());
        return regionalService.saveBatch(listRegional,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return regionalService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return regionalService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @GetMapping("/v1/fabp/{page}/{size}")
    public ResponseEntity<Object> findAllByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            HttpServletRequest request)
    {
        return regionalService.findAllByPage(page,size,request);
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return regionalService.findAll(request);
    }
}
