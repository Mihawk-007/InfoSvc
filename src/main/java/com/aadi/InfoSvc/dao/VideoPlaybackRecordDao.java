package com.aadi.InfoSvc.dao;

import com.aadi.InfoSvc.entity.VideoPlaybackRecord;

import java.util.List;

/**
 * Dao for the VideoPlaybackRecord entity. It is not being actively utilized as of now,
 * but in the future it will be used to save the history of playback record of a video.
 *
 * @author Aditya Kumar
 */
public interface VideoPlaybackRecordDao {
    /**
     * This function is responsible for saving the playback record of the video in the
     * VideoPlaybackRecord entity.
     *
     * @param time - List of playback time sent in the request for the playBackInfo api.
     * @param clientId - id of the client.
     * @param videoId - id used to identify the video on the website.
     */
    void save(List<Long> time, String clientId, String videoId);

    /**
     * This function is responsible for retrieving the playback record of a video from
     * the db based on the clientId and video id.
     *
     * @param clientId - Id of the client.
     * @param videoId - id of the video.
     * @return - List of VideoPlaybackRecord entity.
     */
    List<VideoPlaybackRecord> retrieveInfo(String clientId, String videoId);
}
