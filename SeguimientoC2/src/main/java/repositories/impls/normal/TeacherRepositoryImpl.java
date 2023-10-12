package repositories.impls.normal;

import annotations.MysqlConn;
import connection.ConnectionDB;
import domain.mapping.dto.TeacherDto;
import domain.mapping.mappers.TeacherMapper;
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
@Named("TeacherRepository")
public class TeacherRepositoryImpl implements Repository<TeacherDto> {
    @Inject
    @MysqlConn
    private Connection conn;
    public TeacherRepositoryImpl(Connection conn) {
        this.conn = conn;
    }
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConnectionDB.getInstance();
    }

    private Teacher buildObject(ResultSet resultSet) throws
    SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("idTeacher"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));

        return teacher;
    }

    @Override
    public List<TeacherDto> list() {
        List<Teacher> teacherList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from teacher")) {
            while (resultSet.next()) {
                Teacher teacher = buildObject(resultSet);
                teacherList.add(teacher);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServiceJDBCException("Unable to list info");
        }
        return TeacherMapper.mapFrom(teacherList);
    }

    @Override
    public TeacherDto byId(Long id) {
        Teacher teacher = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM teacher WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = buildObject(resultSet);
            }
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServiceJDBCException("Unable to find info");
        }
        return TeacherMapper.mapFrom(teacher);
    }

    @Override
    public void save(TeacherDto teacher) {
        String sql;
        if (teacher.id() != null && teacher.id() > 0) {
            sql = "UPDATE teacher SET name=?, email=? WHERE id=?";
        } else {
            sql = "INSERT INTO teacher (name, email) VALUES(?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, teacher.name());
            stmt.setString(2, teacher.email());

            if (teacher.id() != null && teacher.id() > 0) {
                stmt.setLong(3, teacher.id());
            }
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServiceJDBCException("Unable to save info");
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM teacher WHERE id =?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables){
            throw new ServiceJDBCException("Unable to delete info");
        }
    }
}
