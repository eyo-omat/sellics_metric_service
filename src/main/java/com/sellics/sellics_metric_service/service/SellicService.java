package com.sellics.sellics_metric_service.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sellics.sellics_metric_service.configs.Configs;
import com.sellics.sellics_metric_service.model.AsinData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SellicService {

    private Configs configs;

    private List<AsinData> extractAsinData() throws IOException {
        List<AsinData> asinDataList = new ArrayList<>();

        AWSCredentials awsCredentials = new BasicAWSCredentials(configs.getSellicsAwsAccessKey(), configs.getSellicsAwsSecretKey());
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        S3Object s3Object = s3Client.getObject(configs.getSellicsAwsBucketName(), configs.getSellicsAwsFileName());
        InputStream inputStream = s3Object.getObjectContent();

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');

        MappingIterator<AsinData> data = csvMapper.readerWithTypedSchemaFor(AsinData.class)
                .with(schema)
                .readValues(inputStream);

        while (data.hasNext()) {
            AsinData asinData = data.next();
            asinDataList.add(asinData);
        }

        return asinDataList;
    }

    public List<AsinData> fetchIndividualRanksForAsinForKeyword(String keyword, String asin) throws IOException {
        return extractAsinData().stream()
                .filter(asinData -> asinData.getAsin().equals(asin))
                .filter(asinData -> asinData.getKeyword().equals(keyword))
                .sorted(Comparator.comparing(AsinData::getRank))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<AsinData>> fetchAggregatedRanksForKeyword(String keyword) throws IOException {
        return extractAsinData().stream()
                .filter(asinData -> asinData.getKeyword().equals(keyword))
                .collect(Collectors.groupingBy(AsinData::getRank));
    }
}
