package acceptableLosses.components;

import acceptableLosses.work.jobs.Job;
import com.artemis.PooledComponent;

/**
 * This is the assigned task
 */
public class Task extends PooledComponent {


    public Job job;


    @Override
    protected void reset() {
        job = null;
    }


    public Task set(Job job) {
        this.job = job;
        return this;
    }
}
