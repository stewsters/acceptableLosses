package acceptableLosses.systems;

import acceptableLosses.components.Position;
import acceptableLosses.components.Resume;
import acceptableLosses.components.Sentience;
import acceptableLosses.map.Region;
import acceptableLosses.work.jobs.Job;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.stewsters.util.math.Point3i;
import com.stewsters.util.pathing.threeDimention.searcher.DjikstraSearcher3d;
import com.stewsters.util.spatial.IntervalKDTree3d;

import java.util.HashSet;

/**
 * This doles out tasks
 */
public class JobAssignerSystem extends EntityProcessingSystem {

//    IntervalKDTree3d jobs;
//    private HashSet<Job> jobTemp;

    private final Region region;
    private final DjikstraSearcher3d searcher;

    @Wire
    ComponentMapper<Resume> resumeComponentMapper;
    @Wire
    ComponentMapper<Position> positionComponentMapper;

    public JobAssignerSystem(Region region) {
        super(Aspect.getAspectForAll(Resume.class, Sentience.class, Position.class));
        searcher = new DjikstraSearcher3d(region, 10000, false);
        this.region = region;

    }

    @Override
    protected void process(Entity e) {

        Resume resume = resumeComponentMapper.get(e);
        Position position = positionComponentMapper.get(e);


        //find any local jobs.

        // sort by distance, do job




//
//                FullPath3d fullPath3d = searcher.search(
//                new CivilianMover(region),
//                position.x, position.y, position.z,
//                new JobObjective(region, resume)
//        );


//        if (fullPath3d != null) {
//            // we are near a potential job, find it.
//
//            FullPath3d.Step step = fullPath3d.getStep(fullPath3d.getLength() - 1);
//            Job job = null;
//
//            for (Facing3d facing : Facing3d.values()) {
//                Job prospectiveJob = region.getJobAt(step.getX() + facing.x, step.getY() + facing.y, step.getZ() + facing.z);
//
//                if (prospectiveJob != null && prospectiveJob.satisfiedBy(resume) && prospectiveJob.getAssignee() == 0) {
//                    job = prospectiveJob;
//                    break;
//                }
//
//            }
//
//            if (job != null) {
//                // TODO: Add Task to player containing the job
//                job.setAssignee(e.getId());
//                e.edit().create(Task.class).set(job);
//                e.edit().create(Path.class).set(fullPath3d);
//            } else {
//                Gdx.app.log(this.getClass().getName(), "There is a path, but no jerbs");
//            }
//
//        } else {
//            Gdx.app.log(this.getClass().getName(), "There is no path to a job");
//        }
//        e.edit().remove(Resume.class);


    }
}
