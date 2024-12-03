package gym.management;
import java.util.ArrayList;
import java.util.List;

public class InstructorRegistry {
    private static InstructorRegistry instance;
    private List<Instructor> instructors;

    private InstructorRegistry() {
        instructors = new ArrayList<>();
    }

    public static InstructorRegistry getInstance() {
        if (instance == null) {
            instance = new InstructorRegistry();
        }
        return instance;
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public boolean isInstructorRegistered(Instructor instructor) {
        return instructors.contains(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return new ArrayList<>(instructors);
    }
}
