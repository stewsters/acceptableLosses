package acceptableLosses.work.jobs;

import acceptableLosses.components.Resume;
import acceptableLosses.map.Region;
import com.stewsters.util.pathing.threeDimention.searcher.Objective3d;
import com.stewsters.util.pathing.threeDimention.shared.PathNode3d;


public class JobObjective implements Objective3d {

    private Region region;
    private Resume resume;

    public JobObjective(Region region, Resume resume) {
        this.region = region;
        this.resume = resume;
    }


    @Override
    public boolean satisfiedBy(PathNode3d current) {
        //TODO: check to see if there is a job here that we can do.
        Job job = region.getJobAt(current.x, current.y, current.z);

        if (job == null) {
            return false;
        }
        return job.satisfiedBy(resume);
    }
}
