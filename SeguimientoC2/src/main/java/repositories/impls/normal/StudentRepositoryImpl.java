package repositories.impls.normal;

import annotations.MysqlConn;
import domain.mapping.dto.StudentDto;
import domain.mapping.mappers.StudentMapper;
import domain.models.Student;
import exceptions.ServiceJDBCException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named("StudentRepository")
public class StudentRepositoryImpl implements Repository<StudentDto> {
    @Inject
    @MysqlConn
    private Connection conn;

    public StudentRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    private StudentDto createStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setSemester(rs.getString("semester"));
        return StudentMapper.mapFrom(student);
    }

    @Override
    public List<StudentDto> list() throws ServiceJDBCException{
        List<StudentDto> studentList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from student")) {
            while (rs.next()) {
                StudentDto ps = createStudent(rs);
                studentList.add(ps);
            }
        } catch (SQLException e) {
            throw new ServiceJDBCException("Unable to list info");
        }
        return studentList;
    }


    @Override
    public StudentDto byId(Long id) {
        StudentDto student = null;
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student WHERE id=?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = createStudent(rs);
                }
            }
        } catch (SQLException e) {
            throw new ServiceJDBCException("Unable to find info");
        }
        return student;
    }

    @Override
    public void save(StudentDto student) {
        String sql;
        if (student.id() != null && student.id() > 0) {
            sql = "UPDATE student SET name=?, email=?, semester=? WHERE id=?";
        } else {
            sql = "INSERT INTO student (name, email, semester) VALUES(?,?,?)";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.name());
            pstmt.setString(2, student.email());
            pstmt.setString(3, student.semester());

            if (student.id() != null && student.id() > 0) {
                pstmt.setLong(4, student.id());
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException("Unable to save info");
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM student WHERE id = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJDBCException("Unable to delete info");
        }
    }
}