package com.aadi.InfoSvc.dto;

import java.util.List;

/**
 * Response dto for the playBackInfo Api. Most played section field provides the time
 * which has the most playback to the client. It has an extra details filed which will
 * be used later.
 *
 * @author Aditya Kumar
 */
public class ResponseDto {
    private String clientId;
    private String videoId;
    private String mostPlayedSection;
    private List<String> details;
    private List<String> errorMessages;

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

    public String getMostPlayedSection() {
        return mostPlayedSection;
    }

    public void setMostPlayedSection(String mostPlayedSection) {
        this.mostPlayedSection = mostPlayedSection;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    public String toString() {
        return "ResponseDto [" +
                "clientId="
                + clientId
                +", videoId="
                + videoId
                + ", mostPlayedSection="
                + mostPlayedSection
                + ", details="
                + details
                + ", errorMessages="
                + errorMessages
                + ']';
    }
}
