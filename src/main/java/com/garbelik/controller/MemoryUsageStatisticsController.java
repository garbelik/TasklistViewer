package com.garbelik.controller;

import com.garbelik.TasklistApp;
import com.garbelik.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.util.stream.Collectors;

public class MemoryUsageStatisticsController {

    @FXML
    private BarChart<String, Long> barChart;

    @FXML
    private CategoryAxis xAxis;
    private ObservableList<String> taskNames = FXCollections.observableArrayList();
    private TasklistApp application;

    public void setApplication(TasklistApp application) {
        this.application = application;
    }

    public void setTaskList(ObservableList<Task> taskList) {
        application.clearDuplicates();
        taskNames.addAll(taskList.stream().map(Task::getName).collect(Collectors.toList()));
        xAxis.setCategories(taskNames);
        XYChart.Series<String, Long> series = new XYChart.Series<>();
        taskList.stream().forEach(task -> series.getData().add(new XYChart.Data<>(task.getName(), task.getMemory())));
        barChart.getData().add(series);
    }
}
