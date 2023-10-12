package repositories.impls.normal;

import annotations.MysqlConn;
import connection.ConnectionDB;
import domain.mapping.dto.SubjectDto;
import domain.mapping.mappers.SubjectMapper;
import domain.models.Subject;
import domain.models.Teacher;
import exceptions.ServiceJDBCException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named("SubjectRepository")
public class SubjectRepositoryImpl implements Repository<SubjectDto> {
    @Inject
    @MysqlConn
    private Connection conn;
    public SubjectRepositoryImpl(Connection conn) {
        this.conn = conn;
    }
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConnectionDB.getInstance();
    }

    private Subject buildObject(ResultSet resultSet) throws
            SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("idsubject"));
        subject.setName(resultSet.getString("name"));
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("idteacher"));
        teacher.setName(resultSet.getString("name"));
        subject.setTeacher(teacher);

        return subject;
    }

    @Override
    public List<SubjectDto> list() {
        List<Subject> SubjectList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT subject.name, teacher.name, teacher.email " +
                     "FROM subject INNER JOIN teacher on subject.id=teacher.id;")) {
            while (resultSet.next()) {
                Subject Subject = buildObject(resultSet);
                SubjectList.add(Subject);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServiceJDBCException("Unable to list info");
        }
        return SubjectMapper.mapFrom(SubjectList);
    }

    @Override
    public SubjectDto byId(Long id) {
        Subject Subject = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT subject.name, teacher.name, teacher.email FROM subject INNER JOIN " +
                        "teacher on subject.id=teacher.id WHERE subject.id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Subject = buildObject(resultSet);
            }
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServiceJDBCException("Unable to search info");
        }
        return SubjectMapper.mapFrom(Subject);
    }

    @Override
    public void save(SubjectDto Subject) {
        String sql;
        if (Subject.id() != null && Subject.id() > 0) {
            sql = "UPDATE subject SET name=?, id=? WHERE id=?";
        } else {
            sql = "INSERT INTO subject (name, id) VALUES(?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, Subject.name());
            stmt.setLong(2, Subject.teacher().getId());

            if (Subject.id() != null && Subject.id() > 0) {
                stmt.setLong(3, Subject.id());
            }
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServiceJDBCException("Unable to save info");

        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM subject WHERE id =?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables ){
            throw new ServiceJDBCException("Unable to delete info");
        }
    }
}
