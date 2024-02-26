package com.aadi.InfoSvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity which represent the request data from the client and the derived result from processing.
 *
 * @author Aditya Kumar
 */
@Entity
@Table(name = "client_request")
public class ClientRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "video_id")
    private String videoId;
    @Column(name = "most_replayed_section")
    private String mostReplayedSection;

    public ClientRequest() {}

    /**
     * Create client request with clientId, videoId, mostReplayedSection derived from the client request.
     *
     * @param clientId
     * @param videoId
     * @param mostReplayedSection
     */
    public ClientRequest(String clientId, String videoId, String mostReplayedSection) {
        this.clientId = clientId;
        this.videoId = videoId;
        this.mostReplayedSection = mostReplayedSection;
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

    public String getMostReplayedSection() {
        return mostReplayedSection;
    }

    public void setMostReplayedSection(String mostReplayedSection) {
        this.mostReplayedSection = mostReplayedSection;
    }

    @Override
    public String toString() {
        return "ClientRequest [" +
                "id="
                + id
                + ", clientId="
                + clientId
                + ", videoId="
                + videoId
                + ", mostReplayedSection="
                + mostReplayedSection
                + ']';
    }
}
