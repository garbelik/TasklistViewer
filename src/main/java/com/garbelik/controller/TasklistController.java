package com.garbelik.controller;

import com.garbelik.TasklistApp;
import com.garbelik.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TasklistController {
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> nameColumn;
    @FXML
    private TableColumn<Task, String> pidColumn;
    @FXML
    private TableColumn<Task, String> usedMemoryColumn;
    private TasklistApp application;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        pidColumn.setCellValueFactory(cellData -> cellData.getValue().pidProperty().asString());
        usedMemoryColumn.setCellValueFactory(cellData -> cellData.getValue().memoryProperty().asString());
    }

    public void setApplication(TasklistApp application) {
        this.application = application;
        taskTable.setItems(application.getTaskList());
    }

    @FXML
    private void removeDuplicates() {
        application.clearDuplicates();
    }
}
