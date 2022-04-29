package com.redhat.fuse.boosters.cb;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;


@Service("nameService")
public class NameServiceImpl implements NameService {

    @Override
    public Name getName(@Header("name") String name) {
        return new Name("Good day " + name);
    }

}