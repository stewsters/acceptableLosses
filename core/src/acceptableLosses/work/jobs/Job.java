package acceptableLosses.work.jobs;


import acceptableLosses.components.Resume;
import com.stewsters.util.math.Point3i;

/**
 * A unit of work.
 */
public interface Job {

    public boolean satisfiedBy(Resume resume);

    public Point3i getStartPos();

    // This is the distance to
    public int getWorkDistance();

    // The entity assigned to this
    public void setAssignee(int id);

    public int getAssignee();
}
