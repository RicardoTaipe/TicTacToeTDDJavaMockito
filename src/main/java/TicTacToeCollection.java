import com.mongodb.DB;
import com.mongodb.MongoClient;
import mongo.TicTacToeBean;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;

public class TicTacToeCollection {
    private MongoCollection mongoCollection;

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public TicTacToeCollection() throws UnknownHostException {
        DB db = new MongoClient().getDB("tic-tac-toe");
        mongoCollection = new Jongo(db).getCollection("game");

    }

    public boolean saveMove(TicTacToeBean bean) {
        boolean result;
        try {
            getMongoCollection().save(bean);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean drop() {
        boolean result;
        try {
            getMongoCollection().drop();
            result = true;
        } catch (Exception e) {
            result= false;
        }
        return result;
    }
}
