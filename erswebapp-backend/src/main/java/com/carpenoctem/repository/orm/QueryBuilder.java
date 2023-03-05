package com.carpenoctem.repository.orm;

public class QueryBuilder {

    StringBuilder query;

    public QueryBuilder() {
        query = new StringBuilder();
    }

    public QueryBuilder(StringBuilder query) {
        this.query = query;
    }

    public QueryBuilder buildSelect(String table) {
        query.append("SELECT * FROM ");
        query.append(table);
        return this;
    }

    public QueryBuilder addWhereClause() {
        query.append(" WHERE");
        return this;
    }

    public <T> QueryBuilder addCondition(Attribute attribute, T value) {
        query.append(' ');
        query.append(attribute.getName());
        query.append(" = \'");
        try {
            query.append(value.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        query.append("\'");

        return this;
    }

    public QueryBuilder addAnd() {
        query.append(" AND");
        return this;
    }

    public QueryBuilder addOr() {
        query.append(" OR");
        return this;
    }

    public QueryBuilder addOpenParentheses() {
        query.append(" (");
        return this;
    }

    public QueryBuilder addCloseParentheses() {
        query.append(" )");
        return this;
    }

    @Override
    public String toString() {
        return query.toString();
    }

}
