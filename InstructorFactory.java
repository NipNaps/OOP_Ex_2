import java.util.ArrayList;
import java.util.List;

public class InstructorFactory {

    public static Instructor createInstructor(Person person, int hourlyRate, List<SessionType> sessionTypes) {
        if (sessionTypes == null || sessionTypes.isEmpty()) {
            return null;
        }

        return new Instructor(person, hourlyRate, sessionTypes);
    }
}
