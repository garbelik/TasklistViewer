package com.garbelik.model;

import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "memory"})
public class Task {

    private StringProperty name;
    private IntegerProperty pid;
    private LongProperty memory;

    public Task() {
        this.name = new SimpleStringProperty();
        this.pid = new SimpleIntegerProperty();
        this.memory = new SimpleLongProperty();
    }

    public static Task parseTask(String inputLine) {
        int imageNameColLength = 25;
        int pidColLength = 8;
        int sessionNameColLength = 16;
        int sessionColLength = 11;
        int usedMemoryColLength = 12;
        Task task = new Task();
        task.setName(inputLine.substring(0, imageNameColLength).trim());

        //cutting the parsed part off the string (column length + 1 space)
        inputLine = inputLine.substring(imageNameColLength + 1);
        task.setPid(Integer.parseInt(inputLine.substring(0, pidColLength).trim()));

        //cutting the parsed part off the string (length of the three columns + 3 spaces)
        inputLine = inputLine.substring(pidColLength + sessionNameColLength + sessionColLength + 3,
                pidColLength + sessionNameColLength + sessionColLength + 3 + usedMemoryColLength - 1); // -1 to exclude the "K" literal
        task.setMemory(Long.parseLong(inputLine.trim().replaceAll("\\W+", "")));
        return task;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @XmlTransient
    public int getPid() {
        return pid.get();
    }

    public void setPid(int pid) {
        this.pid.set(pid);
    }

    public long getMemory() {
        return memory.get();
    }

    public void setMemory(long memory) {
        this.memory.set(memory);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty pidProperty() {
        return pid;
    }

    public LongProperty memoryProperty() {
        return memory;
    }

    @Override
    public String toString() {
        return "Image Name: " + name.get() + " PID: " + pid.get() + " Used Memory: " + memory.get();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Task &&
                this.getName().equals(((Task) obj).getName()) &&
                this.getMemory() == ((Task) obj).getMemory();
    }
}
