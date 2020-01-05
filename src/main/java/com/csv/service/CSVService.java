package com.csv.service;

import com.csv.customException.NoDataFoundException;
import com.csv.customException.ServiceFailureException;
import com.csv.domain.CsvDTO;
import com.csv.model.response.JSONResponse;
import com.csv.model.response.ServiceResponse;

import java.util.List;

public interface CSVService {
    ServiceResponse<JSONResponse> storeCSVData(List<CsvDTO> csvDTOS) throws ServiceFailureException;
    List<CsvDTO> getAlldata() throws ServiceFailureException,NoDataFoundException;
    CsvDTO getDataById(String id) throws ServiceFailureException, NoDataFoundException;
}