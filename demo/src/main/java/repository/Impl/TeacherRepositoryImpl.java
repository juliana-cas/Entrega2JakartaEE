package repository.Impl;

import conexion.ConexionDB;
import domain.models.Teacher;
import mapper.dtos.TeacherDto;
import mapper.mappers.TeacherMapper;
import repository.TeacherRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {
    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }
    private Teacher createTeacher(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getLong("id"));
        teacher.setName(rs.getString("name"));
        teacher.setEmail(rs.getString("email"));
        return teacher;
    }
    @Override
    public List<TeacherDto> teacherList() {
        List<Teacher> teacherList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from teachers")) {
            while (resultSet.next()) {
                Teacher teacher = createTeacher(resultSet);
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TeacherMapper.mapFrom(teacherList);
    }

    @Override
    public TeacherDto byId(Long id) {
        Teacher teacher = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM teachers WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = createTeacher(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TeacherMapper.mapFrom(teacher);
    }

    @Override
    public void update(TeacherDto teacher) {
        String sql;
        if (teacher.teacherId() != null && teacher.teacherId()>0) {
            sql = "UPDATE teachers SET name=?, email=? WHERE id=?";
        } else {
            sql = "INSERT INTO students (name, email) VALUES(?,?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, teacher.teacherName());

            if (teacher.teacherId() != null && teacher.teacherId()>0 ){
                stmt.setString(2, teacher.teacherEmail());
                stmt.setLong(3, teacher.teacherId());
            } else {
                stmt.setString(2, teacher.teacherEmail());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM teachers WHERE id =?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
