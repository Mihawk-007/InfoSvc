package com.aadi.InfoSvc.service;

import com.aadi.InfoSvc.dao.ClientRequestDao;
import com.aadi.InfoSvc.dao.VideoPlaybackRecordDao;
import com.aadi.InfoSvc.dto.RequestDto;
import com.aadi.InfoSvc.dto.ResponseDto;
import com.aadi.InfoSvc.entity.ClientRequest;
import com.aadi.InfoSvc.exception.InfoSvcException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MostReplayedSectionSvcImpl implements MostReplayedSectionSvc {

    final ClientRequestDao clientRequestDao;
    final VideoPlaybackRecordDao videoPlaybackRecord;

    public MostReplayedSectionSvcImpl(ClientRequestDao clientRequestDao, VideoPlaybackRecordDao videoPlaybackRecordDao) {
        this.clientRequestDao = clientRequestDao;
        this.videoPlaybackRecord = videoPlaybackRecordDao;
    }

    @Override
    public ResponseDto processRequest(RequestDto dto) throws InfoSvcException {
        // time is stored by first converted into sec and the passed as list of long.
        String mostReplayedSection = null;
        final Map<Long, Integer> counter = new HashMap<>();
        final Map<Integer, Long> key = new HashMap<>();
        final AtomicInteger temp = new AtomicInteger(0);

        dto.getVidPlayData().forEach(time -> counter.put(time, counter.getOrDefault(time, 0)+1));

        counter.forEach((k, v) -> {
            if(v > temp.get()){
                temp.set(v);
                key.put(temp.get(), k);
            }
        });

        mostReplayedSection = String.valueOf(key.get(temp.get()));

        // If isRepeatCall is set to true, it will try to update the existing record.
        saveClientEnt(dto, mostReplayedSection);
        final ResponseDto response = new ResponseDto();

        response.setClientId(dto.getClientId());
        response.setVideoId(dto.getVideoId());
        response.setMostPlayedSection(mostReplayedSection);

        return response;
    }

    /**
     * This function is responsible for saving the clintRequest entity into the db. If isRepeatCall
     * is set to true it will try to update the existing record, otherwise it will try to save a new
     * record.
     *
     * @param req - Request data for the playBackInfo api.
     * @param replayedSec - Most replayed section of the video.
     * @throws InfoSvcException - If a video which currently exists for the client is being saved again.
     */
    private void saveClientEnt(RequestDto req, String replayedSec) throws InfoSvcException {
        final ClientRequest clientEnt = new ClientRequest();

        clientEnt.setClientId(req.getClientId());
        clientEnt.setMostReplayedSection(replayedSec);
        clientEnt.setVideoId(req.getVideoId());

        if(req.isRepeatCall()){
            clientRequestDao.updateRecord(clientEnt);
        } else {
            clientRequestDao.save(clientEnt);
        }
    }
}
