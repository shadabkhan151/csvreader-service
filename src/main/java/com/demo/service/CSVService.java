package com.demo.service;

import com.demo.customException.NoDataFoundException;
import com.demo.customException.ServiceFailureException;
import com.demo.domain.CsvDTO;
import com.demo.model.response.JSONResponse;
import com.demo.model.response.ServiceResponse;

import java.util.List;

public interface CSVService {
    ServiceResponse<JSONResponse> storeCSVData(List<CsvDTO> csvDTOS) throws ServiceFailureException;
    List<CsvDTO> getAlldata() throws NoDataFoundException;
    CsvDTO getDataById(String id) throws NoDataFoundException;
}