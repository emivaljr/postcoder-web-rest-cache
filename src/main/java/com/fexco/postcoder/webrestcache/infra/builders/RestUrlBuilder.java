package com.fexco.postcoder.webrestcache.infra.builders;

import java.util.Map;

/**
 * Builder implementation for assembly URL calls for Postcoder Web API.
 * @author Emival Junior
 * @version 1.0
 */
public class RestUrlBuilder {

    private StringBuilder query;
    private String prefix;
    private StringBuilder pathParam;

    public RestUrlBuilder(){
        query = new StringBuilder();
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
        if(urlVariables!= null && !urlVariables.isEmpty()){
            query.append("?");
            for (String variable :urlVariables.keySet()){
                query.append(String.format("%1$s={%1$s}&",variable));
            }
            query.deleteCharAt(query.lastIndexOf("&"));
        }
        return this;
    }

    public String build(){
        pathParam.deleteCharAt(pathParam.lastIndexOf("/"));
        return String.format("%s%s%s",prefix,pathParam,query.toString());
    }

}
