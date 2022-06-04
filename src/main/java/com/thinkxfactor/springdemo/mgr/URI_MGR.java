package com.thinkxfactor.springdemo.mgr;

import org.springframework.stereotype.Service;

@Service
public final class URI_MGR {

    public String URL_SCHEME = "http";

    public String HOST = "localhost";

    public String PORT = "8080";

    public final String BASE_URL = this.URL_SCHEME + "://" + this.HOST + ":" + PORT;
}
