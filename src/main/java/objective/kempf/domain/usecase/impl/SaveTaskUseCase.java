package objective.kempf.domain.usecase.impl;

import io.smallrye.mutiny.Uni;
import objective.kempf.cross.ZonedDateFactory;
import objective.kempf.domain.entity.Task;
import objective.kempf.domain.gateway.ITaskGateway;
import objective.kempf.domain.usecase.ISaveTaskUseCase;
import objective.kempf.infra.dataprovider.TaskDataProvider;

public final class SaveTaskUseCase implements ISaveTaskUseCase {

    private ITaskGateway gateway;

    public SaveTaskUseCase() {
        gateway = new TaskDataProvider();
    }

    @Override
    public Uni<Task> save(Task task) {
        return gateway.save(of(task));
    }

    private Task of(Task task) {
        task.setCreatedAt(ZonedDateFactory.now(ZonedDateFactory.BR));
        return task;
    }
}
