package acceptableLosses.work.jobs;


import acceptableLosses.components.Resume;
import com.stewsters.util.math.Point3i;

/**
 * A unit of work.
 */
public interface Job {

    boolean satisfiedBy(Resume resume);

    Point3i getStartPos();

    // This is the distance to
    int getWorkDistance();

    // The entity assigned to this
    void setAssignee(int id);

    int getAssignee();

    void accomplishWork();
}
