package com.redhat.fuse.boosters.cb;

import org.springframework.stereotype.Service;

import javax.ws.rs.PathParam;

@Service("nameService")
public class NameServiceImpl implements NameService {

    @Override
    public Name getName(@PathParam("name") String name) {
        return new Name("Wazzup " + name);
    }

}