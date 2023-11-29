package com.bcafinance.ewpe.controller;

import com.bcafinance.ewpe.dto.SalesDTO;
import com.bcafinance.ewpe.model.Sales;
import com.bcafinance.ewpe.service.SalesService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    private SalesService salesService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@RequestBody SalesDTO salesDTO, HttpServletRequest request)
    {
        Sales sales = modelMapper.map(salesDTO, new TypeToken<Sales>() {}.getType());
        return salesService.save(sales,request);
    }
    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody SalesDTO salesDTO, HttpServletRequest request)
            throws Exception
    {
        Sales sales = modelMapper.map(salesDTO, new TypeToken<Sales>() {}.getType());
        return salesService.update(id,sales,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return salesService.delete(id,request);
    }

    @PostMapping("/v1/svb")
    public ResponseEntity<Object> saveBatch(@Valid @RequestBody List<SalesDTO> listSalesDTO,
                                            HttpServletRequest request)
    {
        List<Sales> listSales = modelMapper.map(listSalesDTO, new TypeToken<List<Sales>>() {}.getType());
        return salesService.saveBatch(listSales,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return salesService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return salesService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @GetMapping("/v1/fabp/{page}/{size}")
    public ResponseEntity<Object> findAllByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            HttpServletRequest request)
    {
        return salesService.findAllByPage(page,size,request);
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return salesService.findAll(request);
    }
}
