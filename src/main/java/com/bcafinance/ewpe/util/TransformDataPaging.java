package com.bcafinance.ewpe.util;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public class TransformDataPaging {


    public Map<String,Object> mapDataPaging(Map<String,Object> mapz, Page page)
    {
        mapz.put("content",page.getContent());
        mapz.put("currentPage",page.getNumber());
        mapz.put("totalPages",page.getTotalPages());
        mapz.put("numberOfElements",page.getNumberOfElements());

        return mapz;
    }
    public Map<String,Object> mapDataPaging(Map<String,Object> mapz, Page page, List list)
    {
        mapz.put("content",list);
        mapz.put("currentPage",page.getNumber());
        mapz.put("totalPages",page.getTotalPages());
        mapz.put("numberOfElements",page.getNumberOfElements());

        return mapz;
    }
}
