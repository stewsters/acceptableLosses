package acceptableLosses.systems;

import acceptableLosses.components.Position;
import acceptableLosses.components.Resume;
import acceptableLosses.components.Sentience;
import acceptableLosses.components.Task;
import acceptableLosses.map.Region;
import acceptableLosses.work.jobs.Job;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.stewsters.util.math.MatUtils;
import com.stewsters.util.pathing.threeDimention.searcher.DjikstraSearcher3d;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * This doles out tasks
 */
public class JobAssignerSystem extends EntityProcessingSystem {

    private ArrayList<Job> jobs;
    private HashSet<Job> jobTemp;

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

        //optimize this
        int size = Math.max(Math.max(region.xSize, region.ySize), region.zSize);

        jobs = new ArrayList<Job>();//new IntervalKDTree3d((size / 2) + 1, 10);
    }

    //add
    public void addJob(Job job) {
        jobs.add(job);

//        float radius = job.getWorkDistance() / 2f;
//        Point3i e = job.getStartPos();
//        jobs.put(e.x - radius, e.y - radius, e.z - radius, e.x + radius, e.y + radius, e.z + radius, e);
    }

    //remove

    public void remove(Job e) {
        jobs.remove(e);
    }


//    public HashSet<Entity> getEntitiesAtLocation(int x, int y, int z) {
//        jobTemp.clear();
//        return jobs.getValues(x - 0.5, y - 0.5, z - 0.5, x + 0.5, y + 0.5, z + 0.5, jobTemp);
//    }
//
//    public HashSet<Entity> getEntitiesBetween(int lowX, int lowY, int lowZ, int highX, int highY, int highZ) {
//        jobTemp.clear();
//        return jobs.getValues(lowX - 0.5, lowY - 0.5, lowZ - 0.5, highX + 0.5, highY + 0.5, highZ + 0.5, jobTemp);
//    }

    @Override
    protected void process(Entity e) {

        if (jobs.size() <= 0) {
            return;
        }

        Resume resume = resumeComponentMapper.get(e);
        Position position = positionComponentMapper.get(e);


        //find any local jobs.

        Job job = null;

        //circular buffer search
        int startIndex = MatUtils.getIntInRange(0, jobs.size() - 1);

        for (int attempt = 0; attempt < Math.min(jobs.size() - 1, 5); attempt++) {
            Job prospectiveJob = jobs.get((startIndex + attempt) % jobs.size());

            if (prospectiveJob != null && prospectiveJob.getAssignee() == 0 && prospectiveJob.satisfiedBy(resume)) {
                job = prospectiveJob;
                break;

            }
        }
        if (job == null) {
            Gdx.app.log(this.getClass().getName(), "There is a path, but no jerbs");

        } else {
            // TODO: Add Task to player containing the job
            job.setAssignee(e.getId());
            e.edit().create(Task.class).set(job);
//            e.edit().create(Path.class).set(fullPath3d);
        }

        // sort by distance, do job


//

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
