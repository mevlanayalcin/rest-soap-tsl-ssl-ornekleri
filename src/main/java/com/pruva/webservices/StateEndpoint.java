package com.pruva.webservices;

import com.pruva.spring.soapws.GetStateRequest;
import com.pruva.spring.soapws.GetStateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StateEndpoint {
    private static final String NAMESPACE_URI = "http://pruva.com/spring/soapws";

    private StateRepository stateRepository;

    @Autowired
    public StateEndpoint(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStateRequest")
    @ResponsePayload
    public GetStateResponse getState(@RequestPayload GetStateRequest request) {
        GetStateResponse response = new GetStateResponse();
        response.setState(stateRepository.findState(request.getId()));

        return response;
    }
}
