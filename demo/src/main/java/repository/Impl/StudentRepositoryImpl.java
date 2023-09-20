package repository.Impl;

import conexion.ConexionDB;
import domain.models.Student;
import mapper.dtos.StudentDto;
import mapper.mappers.StudentMapper;
import repository.StudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }
    private Student createStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setCareer(rs.getString("career"));
        student.setSemester(rs.getString("semester"));
        return student;
    }
    @Override
    public List<StudentDto> studentList() {
        List<Student> studentList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from students")) {
            while (resultSet.next()) {
                Student student = createStudent(resultSet);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentMapper.mapFrom(studentList);
    }

    @Override
    public StudentDto byId(Long id) {
        Student student = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM students WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = createStudent(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentMapper.mapFrom(student);
    }

    @Override
    public void update(StudentDto student) {
        String sql;
        if (student.studentId() != null && student.studentId()>0) {
            sql = "UPDATE students SET name=?, email=?, degree=?, semester=? WHERE id=?";
        } else {
            sql = "INSERT INTO students (name, email, degree, semester) VALUES(?,?,?,?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, student.studentName());
            stmt.setString(2, student.studentEmail());
            stmt.setString(3, student.career());

            if (student.studentId() != null && student.studentId() > 0) {
                stmt.setString(4, student.semester());
                stmt.setLong(5, student.studentId());
            } else{
                stmt.setString(4, student.semester());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM students WHERE id =?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}