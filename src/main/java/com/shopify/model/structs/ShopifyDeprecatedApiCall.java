package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyDeprecatedApiCall {
    @XmlElement(name = "api_type")
    private String apiType;
    private String description;
    @XmlElement(name = "documentation_url")
    private String documentationUrl;
    private String endpoint;
    @XmlElement(name = "last_call_at")
    private String lastCallAt;
    @XmlElement(name = "migration_deadline")
    private String migrationDeadline;
    @XmlElement(name = "graphql_schema_name")
    private String graphqlSchemaName;
    private String version;

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getLastCallAt() {
        return lastCallAt;
    }

    public void setLastCallAt(String lastCallAt) {
        this.lastCallAt = lastCallAt;
    }

    public String getMigrationDeadline() {
        return migrationDeadline;
    }

    public void setMigrationDeadline(String migrationDeadline) {
        this.migrationDeadline = migrationDeadline;
    }

    public String getGraphqlSchemaName() {
        return graphqlSchemaName;
    }

    public void setGraphqlSchemaName(String graphqlSchemaName) {
        this.graphqlSchemaName = graphqlSchemaName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ShopifyDeprecatedApiCall{" +
                "apiType='" + apiType + '\'' +
                ", description='" + description + '\'' +
                ", documentationUrl='" + documentationUrl + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", lastCallAt='" + lastCallAt + '\'' +
                ", migrationDeadline='" + migrationDeadline + '\'' +
                ", graphqlSchemaName='" + graphqlSchemaName + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
