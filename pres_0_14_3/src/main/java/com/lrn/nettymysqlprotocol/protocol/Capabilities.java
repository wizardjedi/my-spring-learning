package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.HexUtils;

public class Capabilities {

    protected long capabilities;

    public Capabilities() {
    }

    public Capabilities(long capabilities) {
        this.capabilities = capabilities;
    }

    public long getHighWord() {
        return capabilities >> 16;
    }

    public long getLowWord() {
        return capabilities & 0xFFFF;
    }

    public long getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(long capabilities) {
        this.capabilities = capabilities;
    }

    public boolean isClientLongPassword() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_LONG_PASSWORD) != 0;
    }

    public void setClientLongPassword() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_LONG_PASSWORD;
    }

    public boolean isClientFoundRows() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_FOUND_ROWS) != 0;
    }

    public void setClientFoundRows() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_FOUND_ROWS;
    }

    public boolean isClientLongFlag() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_LONG_FLAG) != 0;
    }

    public void setClientLongFlag() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_LONG_FLAG;
    }

    public boolean isClientConnectWithDb() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_CONNECT_WITH_DB) != 0;
    }

    public void setClientConnectWithDb() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_CONNECT_WITH_DB;
    }

    public boolean isClientNoSchema() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_NO_SCHEMA) != 0;
    }

    public void setClientNoSchema() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_NO_SCHEMA;
    }

    public boolean isClientCompress() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_COMPRESS) != 0;
    }

    public void setClientCompress() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_COMPRESS;
    }

    public boolean isClientOdbc() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_ODBC) != 0;
    }

    public void setClientOdbc() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_ODBC;
    }

    public boolean isClientLocalFiles() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_LOCAL_FILES) != 0;
    }

    public void setClientLocalFiles() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_LOCAL_FILES;
    }

    public boolean isClientIgnoreSpace() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_IGNORE_SPACE) != 0;
    }

    public void setClientIgnoreSpace() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_IGNORE_SPACE;
    }

    public boolean isClientProtocol41() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_PROTOCOL_41) != 1;
    }

    public void setClientProtocol41() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_PROTOCOL_41;
    }

    public boolean isClientInteractive() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_INTERACTIVE) != 0;
    }

    public void setClientInteractive() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_INTERACTIVE;
    }

    public boolean isClientSsl() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_SSL) != 0;
    }

    public void setClientSsl() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_SSL;
    }

    public boolean isClientIgnoreSigpipe() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_IGNORE_SIGPIPE) != 0;
    }

    public void setClientIgnoreSigpipe() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_IGNORE_SIGPIPE;
    }

    public boolean isClientTransactions() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_TRANSACTIONS) != 0;
    }

    public void setClientTransactions() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_TRANSACTIONS;
    }

    public boolean isClientReserved() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_RESERVED) != 0;
    }

    public void setClientReserved() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_RESERVED;
    }

    public boolean isClientSecureConnection() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_SECURE_CONNECTION) != 0;
    }

    public void setClientSecureConnection() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_SECURE_CONNECTION;
    }

    public boolean isClientMultiStatements() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_MULTI_STATEMENTS) != 0;
    }

    public void setClientMultiStatements() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_MULTI_STATEMENTS;
    }

    public boolean isClientMultiResults() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_MULTI_RESULTS) != 0;
    }

    public void setClientMultiResults() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_MULTI_RESULTS;
    }

    public boolean isClientPsMultiResults() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_PS_MULTI_RESULTS) != 0;
    }

    public void setClientPsMultiResults() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_PS_MULTI_RESULTS;
    }

    public boolean isClientPluginAuth() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_PLUGIN_AUTH) != 0;
    }

    public void setClientPluginAuth() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_PLUGIN_AUTH;
    }

    public boolean isClientConnectAttrs() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_CONNECT_ATTRS) != 0;
    }

    public void setClientConnectAttrs() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_CONNECT_ATTRS;
    }

    public boolean isClientPluginAuthLenencClientData() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA) != 0;
    }

    public void setClientPluginAuthLenencClientData() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA;
    }

    public boolean isClientCanHandleExpiredPasswords() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS) != 0;
    }

    public void setClientCanHandleExpiredPasswords() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS;
    }

    public boolean isClientSessionTrack() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_SESSION_TRACK) != 0;
    }

    public void setClientSessionTrack() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_SESSION_TRACK;
    }

    public boolean isClientDeprecateEof() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_DEPRECATE_EOF) != 0;
    }

    public void setClientDeprecateEof() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_DEPRECATE_EOF;
    }

    public boolean isClientSslVerifyServerCert() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_SSL_VERIFY_SERVER_CERT) != 0;
    }

    public void setClientSslVerifyServerCert() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_SSL_VERIFY_SERVER_CERT;
    }

    public boolean isClientRememberOptions() {
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_REMEMBER_OPTIONS) != 0;
    }

    public void setClientRememberOptions() {
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_REMEMBER_OPTIONS;
    }

    @Override
    public String toString() {
        return "Capabilities{capabilities=" + Long.toHexString(capabilities) + '}';
    }

    public String toPrettyString() {
        return toPrettyString(true);
    }

    public String toPrettyString(boolean significant) {
        StringBuilder sb = new StringBuilder("Capabilities{capabilities=" + Long.toHexString(capabilities) + "}[\n");

        boolean resClientLongPassword = isClientLongPassword();
        if (!significant || resClientLongPassword) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientLongPassword,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_LONG_PASSWORD,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_LONG_PASSWORD
                            )
                    ).append("\n");
        }

        boolean resClientFoundRows = isClientFoundRows();
        if (!significant || resClientFoundRows) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientFoundRows,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_FOUND_ROWS,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_FOUND_ROWS
                            )
                    ).append("\n");
        }

        boolean resClientLongFlag = isClientLongFlag();
        if (!significant || resClientLongFlag) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientLongFlag,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_LONG_FLAG,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_LONG_FLAG
                            )
                    ).append("\n");
        }

        boolean resClientConnectWithDb = isClientConnectWithDb();
        if (!significant || resClientConnectWithDb) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientConnectWithDb,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_CONNECT_WITH_DB,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_CONNECT_WITH_DB
                            )
                    ).append("\n");
        }

        boolean resClientNoSchema = isClientNoSchema();
        if (!significant || resClientNoSchema) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientNoSchema,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_NO_SCHEMA,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_NO_SCHEMA
                            )
                    ).append("\n");
        }

        boolean resClientCompress = isClientCompress();
        if (!significant || resClientCompress) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientCompress,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_COMPRESS,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_COMPRESS
                            )
                    ).append("\n");
        }

        boolean resClientOdbc = isClientOdbc();
        if (!significant || resClientOdbc) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientOdbc,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_ODBC,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_ODBC
                            )
                    ).append("\n");
        }

        boolean resClientLocalFiles = isClientLocalFiles();
        if (!significant || resClientLocalFiles) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientLocalFiles,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_LOCAL_FILES,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_LOCAL_FILES
                            )
                    ).append("\n");
        }

        boolean resClientIgnoreSpace = isClientIgnoreSpace();
        if (!significant || resClientIgnoreSpace) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientIgnoreSpace,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_IGNORE_SPACE,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_IGNORE_SPACE
                            )
                    ).append("\n");
        }

        boolean resClientProtocol41 = isClientProtocol41();
        if (!significant || resClientProtocol41) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientProtocol41,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_PROTOCOL_41,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_PROTOCOL_41
                            )
                    ).append("\n");
        }

        boolean resClientInteractive = isClientInteractive();
        if (!significant || resClientInteractive) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientInteractive,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_INTERACTIVE,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_INTERACTIVE
                            )
                    ).append("\n");
        }

        boolean resClientSsl = isClientSsl();
        if (!significant || resClientSsl) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientSsl,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_SSL,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_SSL
                            )
                    ).append("\n");
        }

        boolean resClientIgnoreSigpipe = isClientIgnoreSigpipe();
        if (!significant || resClientIgnoreSigpipe) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientIgnoreSigpipe,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_IGNORE_SIGPIPE,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_IGNORE_SIGPIPE
                            )
                    ).append("\n");
        }

        boolean resClientTransactions = isClientTransactions();
        if (!significant || resClientTransactions) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientTransactions,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_TRANSACTIONS,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_TRANSACTIONS
                            )
                    ).append("\n");
        }

        boolean resClientReserved = isClientReserved();
        if (!significant || resClientReserved) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientReserved,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_RESERVED,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_RESERVED
                            )
                    ).append("\n");
        }

        boolean resClientSecureConnection = isClientSecureConnection();
        if (!significant || resClientSecureConnection) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientSecureConnection,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_SECURE_CONNECTION,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_SECURE_CONNECTION
                            )
                    ).append("\n");
        }

        boolean resClientMultiStatements = isClientMultiStatements();
        if (!significant || resClientMultiStatements) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientMultiStatements,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_MULTI_STATEMENTS,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_MULTI_STATEMENTS
                            )
                    ).append("\n");
        }

        boolean resClientMultiResults = isClientMultiResults();
        if (!significant || resClientMultiResults) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientMultiResults,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_MULTI_RESULTS,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_MULTI_RESULTS
                            )
                    ).append("\n");
        }

        boolean resClientPsMultiResults = isClientPsMultiResults();
        if (!significant || resClientPsMultiResults) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientPsMultiResults,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_PS_MULTI_RESULTS,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_PS_MULTI_RESULTS
                            )
                    ).append("\n");
        }

        boolean resClientPluginAuth = isClientPluginAuth();
        if (!significant || resClientPluginAuth) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientPluginAuth,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_PLUGIN_AUTH,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_PLUGIN_AUTH
                            )
                    ).append("\n");
        }

        boolean resClientConnectAttrs = isClientConnectAttrs();
        if (!significant || resClientConnectAttrs) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientConnectAttrs,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_CONNECT_ATTRS,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_CONNECT_ATTRS
                            )
                    ).append("\n");
        }

        boolean resClientPluginAuthLenencClientData = isClientPluginAuthLenencClientData();
        if (!significant || resClientPluginAuthLenencClientData) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientPluginAuthLenencClientData,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA
                            )
                    ).append("\n");
        }

        boolean resClientCanHandleExpiredPasswords = isClientCanHandleExpiredPasswords();
        if (!significant || resClientCanHandleExpiredPasswords) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientCanHandleExpiredPasswords,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS
                            )
                    ).append("\n");
        }

        boolean resClientSessionTrack = isClientSessionTrack();
        if (!significant || resClientSessionTrack) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientSessionTrack,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_SESSION_TRACK,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_SESSION_TRACK
                            )
                    ).append("\n");
        }

        boolean resClientDeprecateEof = isClientDeprecateEof();
        if (!significant || resClientDeprecateEof) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientDeprecateEof,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_DEPRECATE_EOF,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_DEPRECATE_EOF
                            )
                    ).append("\n");
        }

        boolean resClientSslVerifyServerCert = isClientSslVerifyServerCert();
        if (!significant || resClientSslVerifyServerCert) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientSslVerifyServerCert,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_SSL_VERIFY_SERVER_CERT,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_SSL_VERIFY_SERVER_CERT
                            )
                    ).append("\n");
        }

        boolean resClientRememberOptions = isClientRememberOptions();
        if (!significant || resClientRememberOptions) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resClientRememberOptions,
                                    32,
                                    MysqlConstants.CapabilitiesConstants.BIT_NUM_CLIENT_REMEMBER_OPTIONS,
                                    MysqlConstants.CapabilitiesConstants.DESCRIPTION_CLIENT_REMEMBER_OPTIONS
                            )
                    ).append("\n");
        }

        sb.append("]");

        return sb.toString();
    }

}
