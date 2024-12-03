import java.util.ArrayList;
import java.util.List;

public class SessionRegistry {
    private static SessionRegistry instance;
    private List<Session> sessions;

    private SessionRegistry() {
        sessions = new ArrayList<>();
    }

    public static SessionRegistry getInstance() {
        if (instance == null) {
            instance = new SessionRegistry();
        }
        return instance;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getAllSessions() {
        return new ArrayList<>(sessions);
    }

    public List<Session> getSessionsByType(SessionType type) {
        List<Session> result = new ArrayList<>();
        for (Session session : sessions) {
            if (session.getSessionType() == type) {
                result.add(session);
            }
        }
        return result;
    }
}
