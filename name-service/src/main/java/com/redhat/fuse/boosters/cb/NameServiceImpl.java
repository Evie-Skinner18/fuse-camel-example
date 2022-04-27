package com.redhat.fuse.boosters.cb;

import org.springframework.stereotype.Service;


@Service("nameService")
public class NameServiceImpl implements NameService {

    @Override
    public Name getName(String name) {
        return new Name("Wazzup " + name);
    }

}