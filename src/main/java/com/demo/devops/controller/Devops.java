package com.demo.devops.controller;

import com.demo.devops.domain.MessageRequest;
import com.demo.devops.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class Devops {

    private static final String API_KEY = "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c";

    @PostMapping("/devops")
    public ResponseEntity<Object> sendMessage(@RequestHeader("X-Parse-REST-API-Key") String apiKey, @RequestBody MessageRequest messageRequest) {
        if (!apiKey.equals(API_KEY)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response("Invalid API Key"));
        }
        if (messageRequest == null || messageRequest.getMessage() == null || messageRequest.getTo() == null
                || messageRequest.getFrom() == null || messageRequest.getTimeToLifeSec() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Error"));
        }

        return ResponseEntity.ok(new Response("Hello " + messageRequest.getTo() + " your message will be sent"));
    }

    @RequestMapping(value = "/devops", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<Object> handleError() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new Response("Error"));
    }


}
