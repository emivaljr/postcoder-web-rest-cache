package com.fexco.postcoder.webrestcache.infra.builders;

import java.util.Map;

/**
 * Created by emival on 18/09/16.
 */
public class RestUrlBuilder {

    private StringBuilder query;
    private String prefix;
    private StringBuilder pathParam;

    public RestUrlBuilder(){
        query = new StringBuilder("?");
        pathParam = new StringBuilder();
    }

    public RestUrlBuilder prefix(String prefix){
        this.prefix = prefix;
        return this;
    }
    public RestUrlBuilder addPathParam(String path){
        this.pathParam.append(path+"/");
        return this;
    }

    public RestUrlBuilder urlVariables(Map<String,String> urlVariables){
        for (String variable :urlVariables.keySet()){
            query.append(String.format("%1$s={%1$s}&",variable));
        }
        query.deleteCharAt(query.lastIndexOf("&"));
        return this;
    }

    public String build(){
        pathParam.deleteCharAt(pathParam.lastIndexOf("/"));
        return String.format("%s%s%s",prefix,pathParam,query.toString());
    }

}
