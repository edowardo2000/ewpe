package com.bcafinance.ewpe.controller;

import com.bcafinance.ewpe.dto.TargetDTO;
import com.bcafinance.ewpe.model.Target;
import com.bcafinance.ewpe.service.SalesService;
import com.bcafinance.ewpe.service.TargetService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/target")
public class TargetController {

    private TargetService targetService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@RequestBody TargetDTO targetDTO, HttpServletRequest request)
    {
        Target target = modelMapper.map(targetDTO, new TypeToken<Target>() {}.getType());
        return targetService.save(target,request);
    }
    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody TargetDTO targetDTO, HttpServletRequest request)
            throws Exception
    {
        Target target = modelMapper.map(targetDTO, new TypeToken<Target>() {}.getType());
        return targetService.update(id,target,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return targetService.delete(id,request);
    }

    @PostMapping("/v1/svb")
    public ResponseEntity<Object> saveBatch(@Valid @RequestBody List<TargetDTO> listBarangDTO,
                                            HttpServletRequest request)
    {
        List<Target> listBarang = modelMapper.map(listBarangDTO, new TypeToken<List<Target>>() {}.getType());
        return targetService.saveBatch(listBarang,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return targetService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return targetService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @GetMapping("/v1/fabp/{page}/{size}")
    public ResponseEntity<Object> findAllByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            HttpServletRequest request)
    {
        return targetService.findAllByPage(page,size,request);
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return targetService.findAll(request);
    }
}
