package ru.javarush.feoktistov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.feoktistov.dao.TaskDAO;
import ru.javarush.feoktistov.domain.Status;
import ru.javarush.feoktistov.domain.Task;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class TaskService {

    private final TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> getAll(int offset, int limit) {
        return taskDAO.getAll(offset, limit);
    }

    public int getAllCount() {
        return taskDAO.getALlCount();
    }
    @Transactional
    public Task edit(int id, String description, Status status) {
        Task task = taskDAO.getById(id);
        if(isNull(task)) {
            throw new RuntimeException("Not found");
        }
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    public Task create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    @Transactional
    public void delete(int id) {
        Task task = taskDAO.getById(id);
        if(isNull(task)) {
            throw new RuntimeException("Not found");
        }
        taskDAO.delete(task);
    }
}
