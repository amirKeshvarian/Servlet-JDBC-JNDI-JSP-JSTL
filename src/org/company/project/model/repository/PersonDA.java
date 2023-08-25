package org.company.project.model.repository;

import org.company.project.common.exception.NotMatchRecordVersionException;
import org.company.project.common.exception.UserNotFoundException;
import org.company.project.common.jndi.ConnectionProvider;
import org.company.project.model.domain.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDA implements GenericDA{
    private Connection connection;
    private PreparedStatement preparedStatement;
    public PersonDA () throws SQLException {
        connection = ConnectionProvider.getConnection();
        connection.setAutoCommit(false);
    }
    public void insert (Person person) throws SQLException {
        preparedStatement = connection
                .prepareStatement("insert into person (id,name,family,salary,record_version) values (person_seq.nextval,?,?,?,?)");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setLong(3, person.getSalary());
        preparedStatement.setLong(4, 0);
        preparedStatement.executeUpdate();
    }
    public void update (Person person) throws SQLException, NotMatchRecordVersionException {
        preparedStatement = connection
                .prepareStatement("update person set name=?,family=?,salary=?, record_version=record_version+1 where id=? and record_version=?");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setLong(3, person.getSalary());
        preparedStatement.setLong(4, person.getId());
        preparedStatement.setLong(5, person.getRecordVersion());
        if(preparedStatement.executeUpdate() != 1){
            throw new NotMatchRecordVersionException();
        }
    }
    public void delete (Person person) throws SQLException, NotMatchRecordVersionException {
        preparedStatement = connection.prepareStatement("delete from person where id=? and record_version=?");
        preparedStatement.setLong(1, person.getId());
        preparedStatement.setLong(2, person.getRecordVersion());
        if(preparedStatement.executeUpdate() != 1){
            throw  new NotMatchRecordVersionException();
        }
    }
    public Person selectOne (Person person) throws SQLException, UserNotFoundException {
        preparedStatement = connection.prepareStatement("select * from person where id=?");
        preparedStatement.setLong(1, person.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return new Person()
                    .setId(resultSet.getLong("id"))
                    .setName(resultSet.getString("name"))
                    .setFamily(resultSet.getString("family"))
                    .setSalary(resultSet.getLong("salary"))
                    .setRecordVersion(resultSet.getLong("record_version"));
        else
            throw new UserNotFoundException();
    }
    public List<Person> selectAll () throws SQLException {
        preparedStatement = connection.prepareStatement("select * from person");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();
        while (resultSet.next()){
            personList.add(new Person()
                    .setId(resultSet.getLong("id"))
                    .setName(resultSet.getString("name"))
                    .setFamily(resultSet.getString("family"))
                    .setSalary(resultSet.getLong("salary"))
                    .setRecordVersion(resultSet.getLong("record_version")));
        }
        return personList;
    }
    @Override
    public void commit() throws Exception {
        connection.commit();
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
