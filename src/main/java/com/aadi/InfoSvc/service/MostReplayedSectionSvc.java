package com.aadi.InfoSvc.service;

import com.aadi.InfoSvc.dto.RequestDto;
import com.aadi.InfoSvc.dto.ResponseDto;
import com.aadi.InfoSvc.exception.InfoSvcException;

/**
 * Service which is responsible for processing the request data for the playBackInfo api
 * and which also stores the data in db for future processing.
 *
 * @author Aditya Kumar
 */
public interface MostReplayedSectionSvc {
    /**
     * This function is responsible for processing the request data and determining the most
     * replayed section of the video.
     *
     * @param dto - Request data for the playBackInfo api.
     * @return - Response dto with data for the mostPlayedSection field.
     * @throws InfoSvcException - When dao errors out while saving the data.
     */
    ResponseDto processRequest(RequestDto dto) throws InfoSvcException;
}
