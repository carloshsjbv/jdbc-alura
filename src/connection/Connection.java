package connection;

import javax.naming.spi.DirStateFactory.Result;

public interface Connection {
    
    Result executeQuery(String sql);
    
}
