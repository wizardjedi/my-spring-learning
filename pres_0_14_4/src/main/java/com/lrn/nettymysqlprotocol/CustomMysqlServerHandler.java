package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.server.ResultSet;
import com.lrn.nettymysqlprotocol.server.ServerObject;
import com.lrn.nettymysqlprotocol.server.ServerObjectException;
import com.lrn.nettymysqlprotocol.server.Success;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomMysqlServerHandler extends DefaultMysqlServerHandler {

    public static final Logger logger = LoggerFactory.getLogger(CustomMysqlServerHandler.class);
        
    @Override
    public ServerObject onQuery(String query) { 
        logger.debug("-->{}", query);
        
        if (query.startsWith("select * from tbl1")) {
            logger.info("Recognized query!!!!!");
            
            ResultSet rs = new ResultSet();
            
            rs.getColumns().add(new ResultSet.Column("id"));
            rs.getColumns().add(new ResultSet.Column("name"));
            rs.getColumns().add(new ResultSet.Column("data"));
            
            rs.getRows().add(new ResultSet.Row("1", "Value1","d1"));
            rs.getRows().add(new ResultSet.Row("2", "Value2",null));
            
            return rs;
        } else {
            ServerObjectException soe = new ServerObjectException();
            soe.setErrorCode(1);
            soe.setSqlState("HY01");
            soe.setErrorMessage("Query not recognized.");
            
            throw soe;
        }
    }    

    @Override
    public ServerObject initDb(String schemaName) {
        logger.info("Change db to:'{}'", schemaName);
        
        if ("test".equals(schemaName)) {
            Success success = new Success();
            success.setInfo("Database changed");                    

            return success;
        } else {
            ServerObjectException soe = new ServerObjectException();
            soe.setErrorCode(2);
            soe.setSqlState("HY00");
            soe.setErrorMessage("Database not found.");
            
            throw soe;
        }
    }
    
    
}
