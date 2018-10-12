package com.texoit.worstmovies.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public interface ImportService {

    <T> List<T> importData(Class<T> type, String fileName) throws IOException;

}
