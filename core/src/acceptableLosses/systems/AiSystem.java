package acceptableLosses.systems;

import acceptableLosses.components.*;
import acceptableLosses.map.Region;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;

/**
 * This controls the ai of sentient creatures.
 * <p>
 * The primary goal of this class is to assign them things to do,
 * whether it is to walk somewhere, shoot someone, or build something
 */
public class AiSystem extends EntityProcessingSystem {


    private final Region region;
    private final JobAssignerSystem jobAssignerSystem;

    @Wire
    ComponentMapper<Path> pathComponentMapper;

    @Wire
    ComponentMapper<Destination> destinationComponentMapper;

    @Wire
    ComponentMapper<Task> taskComponentMapper;
    @Wire
    ComponentMapper<Position> positionComponentMapper;

    @Wire
    ComponentMapper<Resume> resumeComponentMapper;


    public AiSystem(Region region, JobAssignerSystem jobAssignerSystem) {
        super(Aspect.getAspectForAll(Sentience.class, Position.class));
        this.region = region;
        this.jobAssignerSystem = jobAssignerSystem;
    }

    @Override
    protected void process(Entity e) {

        //Yeah, not sure yet what this will involve.  Right now keep them moving

        Path path = pathComponentMapper.getSafe(e);
        Destination destination = destinationComponentMapper.getSafe(e);


        if (path == null && destination == null) {

            Task task = taskComponentMapper.getSafe(e);

            if (task == null) {
                Resume resume = resumeComponentMapper.get(e);
                if (resume == null)
                    e.edit().create(Resume.class);

            } else {
                // We have a task

                Position position = positionComponentMapper.get(e);

                int distanceToTask = Math.abs(position.x - task.job.getStartPos().x) +
                        Math.abs(position.y - task.job.getStartPos().y) +
                        Math.abs(position.z - task.job.getStartPos().z);

                if (distanceToTask <= task.job.getWorkDistance()) {
                    // if we are close enough, do the task
                    jobAssignerSystem.remove(task.job);
                    e.edit().remove(Task.class);

                } else if (task != null) {
                    // do it
                    Gdx.app.log(this.getClass().getName(), "Setting work destination : " + task.job.getStartPos());
                    e.edit().create(Destination.class).set(task.job.getStartPos());
                }

            }
//            else {

            // else wander
//                e.edit().create(Destination.class).set(MathUtils.random(1, 10), MathUtils.random(1, 10), 50);
//            }

        }

    }
}
