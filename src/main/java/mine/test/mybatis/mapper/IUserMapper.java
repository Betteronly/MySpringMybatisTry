package mine.test.mybatis.mapper;

import java.util.List;

import mine.test.mybatis.domain.User;

public interface IUserMapper {
    User getUserById(int id);

    List<User> getUsers(String name);
}
