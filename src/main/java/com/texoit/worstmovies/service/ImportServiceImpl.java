package com.texoit.worstmovies.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImportServiceImpl implements  ImportService {

    public <T> List<T> importData(Class<T> type, String fileName) throws IOException {
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        MappingIterator<T> mappingIterator = new CsvMapper().readerFor(type).with(csvSchema).readValues(new File(fileName));
        return mappingIterator.readAll();
    }

    @Override
    public List<Long[]> importRelationships(String fileName) throws IOException {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withSkipFirstDataRow(true);
        CsvMapper mapper = new CsvMapper().enable(CsvParser.Feature.WRAP_AS_ARRAY);
        MappingIterator<Long[]> readValues = mapper.readerFor(Long[].class).with(bootstrapSchema).readValues(new File(fileName));
        return readValues.readAll();
    }
}
