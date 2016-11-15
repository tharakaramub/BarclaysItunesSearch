package com.apps.barclaysitunes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Response implements Serializable {

    private Integer resultCount;
    private List<ItunesResponse> results = new ArrayList<ItunesResponse>();

    /**
     * @return The resultCount
     */
    public Integer getResultCount() {
        return resultCount;
    }

    /**
     * @param resultCount The resultCount
     */
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * @return The results
     */
    public List<ItunesResponse> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<ItunesResponse> results) {
        this.results = results;
    }

}
