package org.company.project.model.repository;

public interface GenericDA extends AutoCloseable{
    void commit () throws Exception;
}
