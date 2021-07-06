package com.ufkunl.workshop.exception;

import com.ufkunl.workshop.util.Consts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = Consts.NO_PLACE_FOUND)
public class PlaceNotFound extends Exception{

    static final long serialVersionUID = -3387516993224229948L;

    public PlaceNotFound(String message)
    {
        super(message);
    }

}
