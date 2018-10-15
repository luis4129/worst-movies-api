package com.texoit.worstmovies.util;

import com.texoit.worstmovies.exception.EmptySearchException;

import java.util.Collection;
import java.util.Optional;

public class Validator {

    public static <T> Collection<T>  getNonEmptyCollection(Collection<T>  collection) throws EmptySearchException {
        if(collection.isEmpty())
            throw new EmptySearchException();

        return collection;
    }

    public static <T> Optional<T> getNonEmptyOptional(Optional<T> optional) throws EmptySearchException {
        if(!optional.isPresent())
            throw new EmptySearchException();

        return optional;
    }

}
