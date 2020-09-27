package com.sovietcity.Model;

import java.io.Serializable;

//нужен для вызова taskevents  и их обработки
public interface TaskListener extends Serializable {
    void observeTask(TaskEvent taskEvent);
}
