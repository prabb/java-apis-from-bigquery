package com.prabhsingh.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.cloud.bigquery.*;

import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prabh on 2016-11-19.
 */

public class Client implements Serializable {

    //Hack: getColumnHeaders() doesn't always return column headers so created a global variable
    private ArrayList<String> columnHeaders;

    /* Create BigQuery Instance, run the query and get back the result*/
    public QueryResult getResult(String q) {

        // Authenticate with Google BigQuery Project - Just one Line (GCloud SDK should be installed)
        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

        QueryRequest queryRequest =
                QueryRequest
                        .newBuilder(q)
                        .setUseLegacySql(false)
                        .build();
        QueryResponse response = bigquery.query(queryRequest);
        QueryResult result = response.getResult();
        getColumnHeaders(result);
        return result;
    }

    /* Major Heavy Work*/
    public String printResult(QueryResult result) {

        //Performance Gain: Always chose Lists over ArrayLists
        List<Map<?, ?>> rows = null;

        while (result != null) {

            Iterator<List<FieldValue>> iter = result.iterateAll();

            rows = new ArrayList<>();

            // Iterate until all rows are scanned
            while (iter.hasNext()) {

                List<FieldValue> row = iter.next();

                /* Clone the Column Headers at the start of each row since we are deleting all column headers at end of columns */
                ArrayList<String> ch = (ArrayList<String>) columnHeaders.clone();


                Map<String, String> column = new HashMap<>();

                // Iterate over all column values
                for (FieldValue f : row) {

                    //Column.put (Column Header, Column Value) for JSON View
                    column.put(ch.get(0), f.getStringValue());
                    try {
                        //Delete Column Header from ArrayList
                        ch.remove(0);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }

                }

                rows.add(column);

            }
            try {
                result = result.getNextPage();
            } catch (Exception e) {
                System.err.println("No more Results: " + e.getMessage());
            }
        }
        return prettyPrintJSON(rows);
    }

    /*Retrieve the columns headers from the Query Result*/
    public ArrayList<String> getColumnHeaders(QueryResult result) {
        columnHeaders = new ArrayList<>();
        try {
            List<Field> CH = result.getSchema().getFields();
            //Only add Field Names to ArrayList and ignore rest of the data
            columnHeaders.addAll(CH.stream().map(Field::getName).collect(Collectors.toList()));
        } catch (Exception e) {
            System.err.println("Error in getColumnHeaders() Method: " + e.getMessage());
        }
        return columnHeaders;
    }

    /* This function just pretty prints JSON and does nothing else */
    public String prettyPrintJSON(List<Map<?, ?>> data) {
        String output = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            output = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            System.err.println("Error Parsing Data: " + e.getMessage());
            e.printStackTrace();
        }
        return output;
    }
}