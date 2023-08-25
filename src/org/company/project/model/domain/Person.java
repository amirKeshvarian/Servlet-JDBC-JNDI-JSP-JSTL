package org.company.project.model.domain;

import java.io.Serializable;

public class Person implements Serializable {
    private long id;
    private String name;
    private String family;
    private long salary;
    private long recordVersion;

    public Person() {
    }

    public Person(String name, String family, long salary, long recordVersion) {
        this.name = name;
        this.family = family;
        this.salary = salary;
        this.recordVersion = recordVersion;
    }

    public Person(long id, String name, String family, long salary, long recordVersion) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.salary = salary;
        this.recordVersion = recordVersion;
    }

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Person setFamily(String family) {
        this.family = family;
        return this;
    }

    public long getSalary() {
        return salary;
    }

    public Person setSalary(long salary) {
        this.salary = salary;
        return this;
    }

    public long getRecordVersion() {
        return recordVersion;
    }

    public Person setRecordVersion(long recordVersion) {
        this.recordVersion = recordVersion;
        return this;
    }
}
