package com.demo.service;

import com.demo.constants.Constants;
import com.demo.customException.NoDataFoundException;
import com.demo.customException.ServiceFailureException;
import com.demo.dao.Dao;
import com.demo.domain.CsvDTO;
import com.demo.entity.CSVEntity;
import com.demo.model.response.JSONResponse;
import com.demo.model.response.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CSVServiceImpl implements CSVService {

    /** variable to hold logger object */
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVServiceImpl.class);

    /** variable to hold ModelMapper object */
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Dao csvDao;

    @Override
    public ServiceResponse<JSONResponse> storeCSVData(List<CsvDTO> csvDTOS) throws ServiceFailureException{
        ServiceResponse<JSONResponse> response = new ServiceResponse<>();
            JSONResponse jsonResponse = new JSONResponse();
            try {
                int updateValue = storeAll(csvDTOS);
                if (updateValue > 0) {
                    jsonResponse.setMessage(Constants.SUCCESS_MESSAGE);
                    response.setPayload(jsonResponse);
                } else {
                    jsonResponse.setMessage(Constants.NO_DATA);
                    response.setPayload(jsonResponse);
                }
                return response;
            }catch(IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }catch (Exception e) {
                LOGGER.error("CSVServiceImpl.storeCSVData() - Error while saving data :" + e.getLocalizedMessage());
                throw new ServiceFailureException(e.getMessage());
            }
    }

    @Override
    public List<CsvDTO> getAlldata() throws NoDataFoundException{
        try {
            List<CsvDTO> csvDTOS = getAll(csvDao.getAlldata());
            if(null != csvDTOS && csvDTOS.size() > 0) {
                return csvDTOS;
            }else{
                throw new NoDataFoundException("No data available");
            }
        }catch (Exception e) {
            LOGGER.error("CSVServiceImpl.getAlldata() - Error while fetching data : " + e.getLocalizedMessage());
            throw new NoDataFoundException(e.getMessage());
        }
    }

    @Override
    public CsvDTO getDataById(String id) throws  NoDataFoundException{
        try {
            CSVEntity csvEntity = csvDao.getDataById(id);
            Optional<CSVEntity> emptyCheck = Optional.ofNullable(csvEntity);
            if(emptyCheck.isPresent()) {
                CsvDTO csvDTO = modelMapper.map(csvEntity, CsvDTO.class);
                return csvDTO;
            }else{
                throw new NoDataFoundException("No data available with id : "+ id);
            }
        }catch (Exception e) {
            LOGGER.error("CSVServiceImpl.getDataById() - Error while fetching data :" + e.getLocalizedMessage());
            throw new NoDataFoundException(e.getMessage());
        }
    }

    private int storeAll(List<CsvDTO> csvDTOS){
        List<CSVEntity> csvEntities = new ArrayList<>();
            for (CsvDTO csvDTO : csvDTOS) {
                CSVEntity csvEntity = modelMapper.map(csvDTO, CSVEntity.class);
                csvEntities.add(csvEntity);
            }
            List<CSVEntity> saveEntities = csvDao.storeCsv(csvEntities);
            if (saveEntities.size() > 0) {
                return 1;
            } else {
                return -1;
            }
    }

    private List<CsvDTO> getAll(List<CSVEntity> csvEntities){
        List<CsvDTO> csvDTOS = new ArrayList<>();
            for (CSVEntity csvEntity : csvEntities) {
                CsvDTO csvDTO = modelMapper.map(csvEntity, CsvDTO.class);
                csvDTOS.add(csvDTO);
            }
            return csvDTOS;
    }
}
