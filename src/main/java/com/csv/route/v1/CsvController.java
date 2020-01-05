package com.csv.route.v1;

import com.csv.customException.NoDataFoundException;
import com.csv.customException.ServiceFailureException;
import com.csv.domain.CsvDTO;
import com.csv.model.response.JSONResponse;
import com.csv.model.response.ServiceResponse;
import com.csv.service.CSVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class CsvController {

    @Autowired
    private CSVService csvService;

    /** variable to hold logger object */
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvController.class);

    /**
     * This method is used to get all data
     */
    @GetMapping("/data")
    public List<CsvDTO> getallData() throws NoDataFoundException{
        LOGGER.debug("CsvController.getallData() :: Enters");
        try {
            return csvService.getAlldata();
        }catch (NoDataFoundException e) {
            e.setCode("200");
            throw new NoDataFoundException(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("CsvController.getallData():: Error occured ", e);
        }
        return null;
    }
    /**
     * This method is used to get data by id
     * @param id
     */
    @GetMapping("/data/{id}")
    public CsvDTO getDataById(@PathVariable(value = "id") String id) throws NoDataFoundException{
        LOGGER.debug("CsvController.getDataById() :: Enters");
        try {
            return csvService.getDataById(id);
        }catch (NoDataFoundException e) {
            e.setCode("200");
            throw new NoDataFoundException(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("CsvController.getDataById():: Error occured ", e);
        }
        return null;
    }
    /**
     * This method is save/persist data into database
     */
    @PostMapping("/store/csv")
    public ServiceResponse<JSONResponse> storeAll(@RequestBody List<CsvDTO> csvDTOS) throws ServiceFailureException{
        LOGGER.debug("CsvController.storeAll() ::: Enters");
        ServiceResponse<JSONResponse> response = new ServiceResponse<>();
        try {
            LOGGER.debug("CsvController.storeAll() ::: Exits");
            return csvService.storeCSVData(csvDTOS);
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }catch (ServiceFailureException e) {
            throw new ServiceFailureException(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("CsvController.storeAll() error occured while saving data",e);
            return response;
        }
    }
}