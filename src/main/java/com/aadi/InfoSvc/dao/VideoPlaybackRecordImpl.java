package com.aadi.InfoSvc.dao;

import com.aadi.InfoSvc.entity.ClientRequest;
import com.aadi.InfoSvc.entity.VideoPlaybackRecord;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VideoPlaybackRecordImpl implements VideoPlaybackRecordDao {
    private EntityManager entityManager;

    @Autowired
    public VideoPlaybackRecordImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(List<Long> times, String clientId, String videoId) {
        for(Long time:times){
            final VideoPlaybackRecord record = new VideoPlaybackRecord(time, clientId, videoId);
            entityManager.persist(record);
        }
    }

    @Override
    public List<VideoPlaybackRecord> retrieveInfo(String clientId, String videoId) {
        final TypedQuery<VideoPlaybackRecord> query = entityManager.createQuery(
                "FROM VideoPlaybackRecord where clientId=:clientData && videoId=:vidData", VideoPlaybackRecord.class);

        query.setParameter("clientData", clientId);
        query.setParameter("vidData", videoId);

        return query.getResultList();
    }
}
