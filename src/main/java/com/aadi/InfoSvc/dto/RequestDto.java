package com.aadi.InfoSvc.dto;

import com.aadi.InfoSvc.validation.ClientIdChecker;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

/**
 * Request Obj for the playback info api. ClientId will be a UUID which will uniquely determine a client.
 * videoId will also be a UUID which can be used to uniquely find a video at the client. VidPlayData is
 * list of long where each value in the list is a play time in the video of some user converted into seconds.
 * isRepeatCall need to set to true if there is a repeat call for the same movie.
 *
 * @author Aditya Kumar
 */
public class RequestDto {
    @ClientIdChecker
    private String clientId;
    @NotNull(message = "Video id can not be null.")
    @Size(message = "Video id should be 4 to 16 character long.",min = 4, max = 16)
    @Pattern(message = "Invalid Video Id.", regexp = "^(vid)[a-zA-Z0-9-]+$")
    private String videoId;
    @NotEmpty(message = "vid play data can not be null or empty.")
    private List<Long> vidPlayData;
    @JsonProperty
    private boolean isRepeatCall;

    /**
     * No Args constructor.
     */
    public RequestDto() {}

    /**
     * Construct with clientId, videoId and vidPlayData.
     *
     * @param clientId - Id which is used to identify the client.
     * @param videoId - Id which is used to identify the video.
     * @param vidPlayData - video play back data which will be used to identity the most played section of video.
     * @param isRepeatCall - Indicate if this is repeat call for the same movie.
     */
    public RequestDto(String clientId, String videoId, List<Long> vidPlayData, boolean isRepeatCall) {
        this.clientId = clientId;
        this.videoId = videoId;
        this.vidPlayData = vidPlayData;
        this.isRepeatCall = isRepeatCall;
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

    public List<Long> getVidPlayData() {
        return vidPlayData;
    }

    public void setVidPlayData(List<Long> vidPlayData) {
        this.vidPlayData = vidPlayData;
    }

    public boolean isRepeatCall() {
        return isRepeatCall;
    }

    public void setRepeatCall(boolean repeatCall) {
        isRepeatCall = repeatCall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, videoId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(!(obj instanceof RequestDto)){
            return false;
        }
        final RequestDto req = (RequestDto) obj;
        return this.clientId.equals(req.clientId) && this.videoId.equals(req.videoId);
    }

    @Override
    public String toString() {
        return "RequestDto [" +
                "clientId="
                + clientId
                + ", videoId="
                + videoId
                + ", vidPlayData="
                + vidPlayData
                + ", isRepeatCall="
                + isRepeatCall
                + ']';
    }
}
