package com.aadi.InfoSvc.restController;

import com.aadi.InfoSvc.dto.RequestDto;
import com.aadi.InfoSvc.dto.ResponseDto;
import com.aadi.InfoSvc.exception.InfoSvcException;
import com.aadi.InfoSvc.service.MostReplayedSectionSvc;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for the InfoSvc.
 *
 * @author Aditya Kumar
 */
@RestController
@RequestMapping("/v1")
public class Controller {

    private MostReplayedSectionSvc svc;

    public Controller(MostReplayedSectionSvc svc) {
        this.svc = svc;
    }

    /**
     * Endpoint which checks the connection with the service.
     *
     * @return - ResponseEntity with status of 200.
     */
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * playBackInfoApi is designed to be consumed by clients, which needs information about the most
     * played sections in the video. It accepts Json value as request and produces a Json value as
     * response. The response object will indicate the most played section of the video along with
     * other details.
     *
     * <p>This endpoint assumes that the playback data provided to it in the request dto, will be
     * represented in a list of long where each value is specific time duration of the video
     * represented in seconds.
     *
     * @param requestDto - Request data
     * @return - Response with info on mostPlayed section of the video.
     */
    @RequestMapping(value = "/playBackInfoApi", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto>  streamInfo(@Valid @RequestBody RequestDto requestDto){
        try{
            return new ResponseEntity<ResponseDto>(svc.processRequest(requestDto), HttpStatus.OK);
        } catch (InfoSvcException exception){
            final ResponseDto res = new ResponseDto();
            res.setErrorMessages(exception.getErrorMessages());
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Exception handler which handles MethodArgumentNotValidExceptions. The exception is thrown when
     * there is an issue with the request data for playBackInfo api.
     *
     * @param ex - MethodArgumentNotValidException
     * @return - Response with correct error messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> invalidArgumentHandler(MethodArgumentNotValidException ex){
        final List<ObjectError> errors = ex.getAllErrors();
        final ResponseDto res = new ResponseDto();

        res.setErrorMessages(errors.stream().map(error -> error.getDefaultMessage()).
                collect(Collectors.toList()));

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
