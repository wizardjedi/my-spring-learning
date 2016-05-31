package com.lrn.nettymysqlprotocol.server;

public class ServerObjectException extends RuntimeException implements ServerObject {
    protected int errorCode;
    
    protected String errorMessage;

    protected String sqlState;
    
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    } 

    public String getSqlState() {
        return sqlState;
    }

    public void setSqlState(String sqlState) {
        this.sqlState = sqlState;
    }    
}
