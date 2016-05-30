package com.lrn.nettymysqlprotocol.protocol;

/**
 * 
 * @see https://dev.mysql.com/doc/internals/en/character-set.html
 * @TODO: Add support for collation
 */
public class MysqlCharset {
    protected long charset;
    
    public long getCharset() {
        return charset;
    }

    public void setCharset(long charset) {
        this.charset = charset;
    }
}
