package com.aadi.InfoSvc.dao;

import com.aadi.InfoSvc.entity.ClientRequest;
import com.aadi.InfoSvc.exception.InfoSvcException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRequestDaoImpl implements ClientRequestDao{
    private EntityManager entityManager;

    @Autowired
    public ClientRequestDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(ClientRequest clientRequest) throws InfoSvcException {
        if(!getInfoById(clientRequest.getClientId(), clientRequest.getVideoId()).isEmpty()){
            throw new InfoSvcException(List.of("Duplicate Vid id. Aborting Save"));
        }
        entityManager.persist(clientRequest);
    }

    @Override
    public List<ClientRequest> getInfoById(String clientId, String vidId) {
        final TypedQuery<ClientRequest> query = entityManager.createQuery(
                "FROM ClientRequest where clientId=:clientData AND videoId=:vidData", ClientRequest.class);

        query.setParameter("clientData", clientId);
        query.setParameter("vidData", vidId);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateRecord(ClientRequest req) throws InfoSvcException {
        final List<ClientRequest> clientRequestList = getInfoById(req.getClientId(),req.getVideoId());

        if(clientRequestList.isEmpty()){
            throw new InfoSvcException(List.of("Record to update is not found."));
        }

        final ClientRequest recordToUpdate = clientRequestList.get(0);
        recordToUpdate.setMostReplayedSection(req.getMostReplayedSection());
        entityManager.merge(recordToUpdate);
    }
}
