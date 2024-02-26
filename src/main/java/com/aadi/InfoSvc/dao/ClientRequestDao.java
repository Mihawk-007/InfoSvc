package com.aadi.InfoSvc.dao;

import com.aadi.InfoSvc.entity.ClientRequest;
import com.aadi.InfoSvc.exception.InfoSvcException;

import java.util.List;

/**
 * Dao for the ClientRequest entity.
 *
 * @author Aditya Kumar
 */
public interface ClientRequestDao {
    /**
     * This function is responsible for persisting the ClientRequest entity into the db.
     *
     * @param clientRequest - data to be saved in the database.
     * @throws InfoSvcException - throws if the data already exists.
     */
    void save(ClientRequest clientRequest) throws InfoSvcException;

    /**
     * This method is responsible for finding the data by clientId and vidId.
     * In case the data is not found it will return en empty list.
     *
     * @param clientId - id of the client.
     * @param vidId - videoId
     * @return - list consists of a response.
     */
    List<ClientRequest> getInfoById(String clientId, String vidId);

    /**
     * Updated the client record in the db with a updated mostReplayedSection data.
     *
     * @param req - Client Request entity.
     * @throws InfoSvcException - If the record to update is not found.
     */
    void updateRecord(ClientRequest req) throws InfoSvcException;
}
