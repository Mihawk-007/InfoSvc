package com.aadi.InfoSvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity which will store the playback data of video. Each row of this table will correspond to playTime
 * field in the RequestDTO.
 *
 * @author Aditya Kumar
 */
@Entity
@Table(name = "video_playback_record")
public class VideoPlaybackRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "playback_info")
    private long playbackInfo;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "video_id")
    private String videoId;

    /**
     * Construct with the playback info which will be derived from the request, ClientId and videoId
     * which will be used to query the time to process the most played section of the video.
     *
     * @param playbackInfo - time info represented as long
     * @param clientId - id of the client
     * @param videoId - id which can be used to find a video at a particular client.
     */
    public VideoPlaybackRecord(long playbackInfo, String clientId, String videoId) {
        this.playbackInfo = playbackInfo;
        this.clientId = clientId;
        this.videoId = videoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPlaybackInfo() {
        return playbackInfo;
    }

    public void setPlaybackInfo(long playbackInfo) {
        this.playbackInfo = playbackInfo;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
