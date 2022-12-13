package objective.kempf.domain.usecase.impl;

import io.smallrye.mutiny.Uni;
import objective.kempf.domain.entity.Task;
import objective.kempf.domain.gateway.ITaskGateway;
import objective.kempf.domain.usecase.IFindTaskUseCase;
import objective.kempf.infra.dataprovider.TaskDataProvider;

import java.util.List;

public final class FindTaseUseCase implements IFindTaskUseCase {

    private ITaskGateway gateway;

    public FindTaseUseCase() {
        gateway = new TaskDataProvider();
    }

    @Override
    public Uni<Task> getById(final Long id) {
        return gateway.getById(id);
    }

    @Override
    public Uni<List<Task>> getAll() {
        return gateway.getAll();
    }
}
