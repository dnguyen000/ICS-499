package edu.metrostate.ics499.team2.model;

import org.springframework.http.HttpStatus;

public class HttpResponse
{

    private int val = 0;
    private HttpStatus word;
    private String word2;
    private String word3;

    public HttpResponse(int val, HttpStatus forbidden, String word2, String word3)
    {
        this.val = val;
        this.word = forbidden;
        this.word2 = word2;
        this.word3 = word3;
    }

}
